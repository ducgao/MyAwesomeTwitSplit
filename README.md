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
  + My job is split a string into an array that each part not bigger than 50 characters (indicator included).
  + My first idea is using regular expression to deal with this. I found over internet some regex string that serve for this algorithm like `(\b.{1,45}(\s|$))\s*/g`. It's pass the unit test with some first basic cases, for another cases, it's goes wrong when missing special characters in output, like `,` `.` `'`
  + I try to understand the regex logic, try another, try fix, try a lot of way but seem like my knowledge about regex is not enougn, so I leaved it there.
  + The second idea is using plain logic and deal with this from top to toe. So, here we go, the idea is split the string to parts that not greater than 45 characters, find the spacing character index that around position 45 (not over 45), and do a substring with founded index. Put it into a recursive util the subject smaller than 45. 
  + The exception of this algorithm is if a word that bigger than 50 stand at middle of the input string, the output after recursive finish will contain at least one elements. So I handled this exception by count the character of output array and compare with the original input. Solved!.
  
  ## Go into the code:
  
  + first step is trim the input, we won't allow a troll message like empty or a massive of spacing, throw an exception if found something kind of.
  
  ```js
    input = input.trim();
    
    if (input.isEmpty()) {
        throw new TwitSplitException(R.string.add_new_twit_error_input_empty);
    }
  
  ```
  
  + next, I need to return the array with one element that exactly the original input if it not bigger than 50
  
  ```js
    List<String> output = new ArrayList<>();
    if (input.length() <= 50) {
        output.add(input);
    }
  ```
  + Okie, the victim appear, let deal with it
  + `subject` is the spacing charater
  + `point` is 45, the limit, because we need to include indicator and one spacing character at the head of splitted string, so the limit is not 50 anymore.
  + go to the first step, the end condition of recursive, if the part of subject string we're dealing is adapt limit
  + if not, find the index near `point` limit, with `subject` and `input`
  + if you can't find it, let's end the recursive, there is not thing to deal with
  + if you find one, let's cut the string to that index, you got a part of output
  + and so on, let the recursive does it's job.
  
  ```js
    private void processInput(List<String> output, String input) {
        if (input.length() <= point) {
            totalSize += input.length();
            output.add(input);
            return;
        }

        int foundIndex = getIndexNearPoint(input, subject, point);

        if (foundIndex == -1) {
            return;
        }

        String candidate = input.substring(0, foundIndex);
        totalSize += candidate.length();
        output.add(candidate);

        String nextInput = input.substring(foundIndex + 1);
        processInput(output, nextInput);
    }
    
    private int getIndexNearPoint(String input, String object, int point) {
        int returnIndex = -1;

        int index = input.indexOf(object);
        while (index >= 0) {
            if (index > point) {
                return returnIndex;
            }

            returnIndex = index;
            index = input.indexOf(object, index + 1);
        }

        return returnIndex;
    }
  ```
  
  + and after you got an array as output, just put it in validation method, add indicator.
  + for more detail, please enter `AlgorithmUsingPlainLogic.java` located in `core/twitsplit`
  + you can also check `AlgorithmUsingRegex.java`, it's for regex solution, not bad but fail in some special case of unit test.
  
  # Thread Excutor
  I'm using `ThreadPoolExecutor` for upload message feature. But I think the messages need to be send synchronize.
  The `2/2` must be a latest post. Sometime `ThreadPoolExecutor` with multi thread will make the `2/2` post go down, So I set `maximum of core` in ThreadPool to `1` to resolve this problem.
  
  # Memory Optimization
  I usually use LeakCanary to detect leak memory from android application. This app run without leak.

  # Special Thanks
  + lethanhtam1604: Thank you for your readme file. My one is based on it
  + https://github.com/tokbes/zalora-tweet-split: Thank you for your regex idea, that's the awesome way but my knowledge about regex is not enough to make it complete, there is some exception I found and I can't deal with it.
