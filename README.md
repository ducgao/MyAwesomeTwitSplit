# My Awesome TwitSplit

This application Tweeter allows users to post short messages limited to 50 characters each. Sometimes, users get excited and write messages longer than 50 characters. Instead of rejecting these messages, we would like to add a new feature that will split the message into parts and send multiple messages on the user's behalf, all of them meeting the 50 character requirement.

Example: Suppose the user wants to send the following message:
"I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself." (without quotes)

This is 91 characters excluding the surrounding quotes. When the user presses send, it will send the following messages:

+ "1/2 I can't believe Tweeter now supports chunking" (without quotes) 
+ "2/2 my messages, so I don't have to do it myself." (without quotes)

Each message is now 49 characters, each within the allowed limit.

# Third-party libraries
+ Twitter
+ Fresco
+ Realm
+ LeakCanary

# Architecture Pattern

This project is using MVP design pattern.
I decided to use MVP to resolve massive view controller. There is no `design` or `specific requirements` for any screen, so that MVP will help me to change, upgrade content, feature of each screen easier.

# App Module
+ view: view for screen, can be android activity or fragment
+ model: model for screen
+ presenter: presenter for screen
+ adapter: adapter for recycleview (message hub), for viewpager
+ entity: entity object
+ utility: a base activity, some utils methods using during the app
+ core: repository, thread executor, twitter helper and twitspit

# Tiny Description

The is two mode in my TwitSpit, `simulation mode` and `real Twitter mode`.
+ Simulation mode: using the fake authentication session to run the application. Everything user going to do will be saved in realm database. But when user close the app, that's mean the session end, so that all of thing will be erased.
+ Real Twitter mode: user login to his Twitter account, any post message action will affect directly to his Twitter account and saved in realm database also. Until user log out of the app, everything will be keeped in realm and won't go any where.

# Message Splitter Algorithm
  ## My approach:
  + Length of a message part = Length of indicator and whitespace + Length of text <= 50 (EX: "IndexPart/TotalPart" + " " +  text). But we actually don't know total part. So, I determined total part the following:
  + The first: Estimate number of digits of total part: K = numberOfDigits(message.count / 50). And then we can calculate length of indicator and whitespace: IndicatorCharacterCount = numberOfDigits(indexPart) + 1 + K + 1 // 1 first is "/" character and 1 second is white space
  + The Second: Try to split the message with K.
  + The Third: If we can't split the message (Length of total part is greater than K), we increase K value by 1 (K = K + 1) and try split the message again. If we can't get split return nil, otherwise return list of message parts that is splitted
  + Time complexity: O(N) with N is number of characters of the message
  
  ```js
  
    fileprivate func processMessage(_ message: String) -> TwitterResult {
        
        // Estimate number of digits of total part
        var K = numberOfDigits(message.count / TwitterValue.maxTwitterCharacterCount)

        // Try to split the message first with K
        switch trySplitTheMessageWith(message, K) {
        case .error:
            // Try to split the message second with K is increased by 1
            K += 1
            _ = trySplitTheMessageWith(message, K)
        case .excess:
            return TwitterResult.error(Error.nonWhitespace().result)
        case .success:
            break
        }
        
        // Return result
        let messages = twitterParts.map { $0.build() }
        return TwitterResult.success(messages)
    }
    
    fileprivate func trySplitTheMessageWith(_ message: String, _ K: Int) -> ResultSplit {
        
        // Init variables
        indexPart = 1
        twitterParts = []
        
        // Number of characters of indicator and whitespace (EX: "1/1 ")
        var indicatorCharacterCount = numberOfDigits(indexPart) + 1 + K + 1 // 1 first is "/" character and 1 second is white space

        // Init indexBegin and indexEnd
        var indexBegin = 0
        var indexEnd = indexBegin + TwitterValue.maxTwitterCharacterCount - indicatorCharacterCount
        
        // indexBegin is first index of message that we need to split
        // indexEnd is end index of message that we need to split
        // For example the following message: "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        // The First: indexBegin = 0, indexEnd = indexBegin + 50 - Number of characters of indicator and whitespace ("1/K " = 4) = 46. And then we run from indexEnd to indexBegin to find white space index (indexWhiteSpace) and split the message between indexBegin and indexWhiteSpace
        // The Second: indexBegin = indexWhiteSpace + 1, indexEnd = indexBegin + 50 - Number of characters of indicator and whitespace ("2/K "). The we slit the message like the first step.
        // The Third: indexBegin = indexWhiteSpace + 1, indexEnd = indexBegin + 50 - Number of characters of indicator and whitespace ("3/K "). The we slit the message like the first step.
        // And so on...
        
        while indexEnd < message.count {
            
            var indexWhiteSpace = 0
            var isExcess = true
            
            // we run from indexEnd to indexBegin to find index of the whitespace
            for index in stride(from: indexEnd, to: indexBegin, by: -1) {
                if message[index] == " " {
                    isExcess = false
                    indexWhiteSpace = index
                    break
                }
            }
            
            // Check the message is not excessed yet (less than or equal 50 characters) and split that message
            if !isExcess {
                // Split the message at between indexBegin and indexWhiteSpace
                let messagePart = message[indexBegin..<indexWhiteSpace]

                // Add message part is splitted to array
                let twitterPart = TwitterPart(indexPart, messagePart)
                twitterParts.append(twitterPart)
                
                // Increase indexPart by 1
                indexPart += 1
                
                // Update indexBegin value
                indexBegin = indexWhiteSpace + 1
                
                // Update indicatorCharacterCount value
                indicatorCharacterCount = numberOfDigits(indexPart) + 1 + K + 1

                // Update indexEnd value
                indexEnd = indexBegin + TwitterValue.maxTwitterCharacterCount - indicatorCharacterCount
            } else {
                return ResultSplit.excess
            }
            
            // Split the message error at the first time and we will split the message at the second time by increasing K by 1
            if numberOfDigits(indexPart) > K {
                return ResultSplit.error
            }
        }
        
        // Add last one
        if indexBegin < message.count {
            let messagePart = message[indexBegin..<message.count]
            let twitterPart = TwitterPart(indexPart, messagePart)
            twitterParts.append(twitterPart)
        }
        
        // It's time to update total part
        twitterParts.forEach { $0.updateTotalPart(indexPart) }
        
        return ResultSplit.success
    }
    
    // Mark: - Get number of digits
    fileprivate func numberOfDigits(_ n: Int) -> Int {
        if(n == 0) {
            return 0
        } else {
            return 1 + numberOfDigits(n / 10)
        }
    }
    
  ```
  
  # Special Thanks
  + lethanhtam1604: Thank you for your readme file. My one is based on it
  + https://github.com/tokbes/zalora-tweet-split: Thank you for your regex idea, that's the awesome way but my knowledge about regex is not enough to make it complete, there is some exception I found and I can't deal with it.
