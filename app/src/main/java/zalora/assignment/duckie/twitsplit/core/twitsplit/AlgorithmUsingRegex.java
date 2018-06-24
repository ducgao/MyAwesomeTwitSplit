package zalora.assignment.duckie.twitsplit.core.twitsplit;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This algorithm using regex to split a message to a chunks, but seem like
 * in some case, the output of this algorithm goes wrong.
 * But the performance when using this algorithm is very good
 * I will try to learn about regex and find another way to make it
 * run perfectly
 */
public class AlgorithmUsingRegex implements TwitSplitAlgorithm {
    @Override
    public List<String> SplitMessage(String input) throws TwitSplitException {
        List<String> output = new ArrayList<>();
        if (input.length() <= 50) {
            output.add(input);
        }
        else {
            Matcher matcher = Pattern.compile("(\\b.{1,45}(\\s|$))\\s*").matcher(input);

            int totalSize = 0;
            while (matcher.find()) {
                String candidate = matcher.group(0);
                totalSize += candidate.length();

                output.add(candidate.trim());
            }

            if (missAnyPart(totalSize, input)) {
                throw new TwitSplitException();
            }

            addIndicator(output);
        }
        return output;
    }

    private boolean missAnyPart(int size, String input) {
        int originalLength = input.length();
        if (originalLength - 50 < size && size <= originalLength) {
            return false;
        }

        return true;
    }

    private void addIndicator(List<String> output) {
        int size = output.size();
        for (int i = 0; i < size; i++) {
            String oldOne = output.get(i);
            String newOne = String.format("%d/%d %s", i + 1, size, oldOne);
            output.set(i, newOne);
        }
    }
}
