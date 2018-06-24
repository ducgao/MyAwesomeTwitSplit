package zalora.assignment.duckie.twitsplit.utility.twitsplit;

import java.util.ArrayList;
import java.util.List;

import zalora.assignment.duckie.twitsplit.R;

public class AlgorithmUsingPlantLogic implements TwitSplitAlgorithm {
    String object = " ";
    int point = 45;
    int totalSize;


    @Override
    public List<String> SplitMessage(String input) throws TwitSplitException {
        input = input.trim();

        if (input.isEmpty()) {
            throw new TwitSplitException(R.string.add_new_twit_error_input_empty);
        }

        List<String> output = new ArrayList<>();
        if (input.length() <= 50) {
            output.add(input);
        }
        else {
            totalSize = 0;

            processInput(output, input);

            if (missAnyPart(totalSize, input)) {
                throw new TwitSplitException(R.string.add_new_twit_error_input_invalid);
            }

            addIndicator(output);
        }

        return output;
    }

    private void processInput(List<String> output, String input) {
        if (input.length() <= point) {
            totalSize += input.length();
            output.add(input);
            return;
        }

        int foundIndex = getIndexNearPoint(input, object, point);

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

    private void addIndicator(List<String> output) {
        int size = output.size();
        for (int i = 0; i < size; i++) {
            String oldOne = output.get(i);
            String newOne = String.format("%d/%d %s", i + 1, size, oldOne);
            output.set(i, newOne);
        }
    }

    private boolean missAnyPart(int size, String input) {
        int originalLength = input.length();
        if (originalLength - 50 < size && size <= originalLength) {
            return false;
        }

        return true;
    }
}
