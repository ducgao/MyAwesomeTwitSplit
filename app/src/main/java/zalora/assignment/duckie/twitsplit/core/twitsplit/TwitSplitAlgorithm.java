package zalora.assignment.duckie.twitsplit.core.twitsplit;

import java.util.List;

public interface TwitSplitAlgorithm {
    List<String> SplitMessage(String input) throws TwitSplitException;
}
