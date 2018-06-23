package zalora.assignment.duckie.twitsplit.utility.twitsplit;

import java.util.List;

public class TwitSplit {
    String content;

    private TwitSplit(String content) {
        this.content = content;
    }

    public static TwitSplit init(String content) {
        return new TwitSplit(content);
    }

    public List<String> build() throws TwitSplitException {
        TwitSplitAlgorithm twitSplitAlgorithm = new AlgorithmUsingPlantLogic();
        return twitSplitAlgorithm.SplitMessage(content);
    }
}
