package zalora.assignment.duckie.twitsplit;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import zalora.assignment.duckie.twitsplit.utility.twitsplit.TwitSplit;
import zalora.assignment.duckie.twitsplit.utility.twitsplit.TwitSplitException;

import static org.junit.Assert.*;

public class TwitSplitUnitTest {

    private class TestCollection {
        public String input;
        public List<String> expectedOutput;
    }
    @Test
    public void testTwitSplit() {

        TestCollection testCollection1 = new TestCollection();
        testCollection1.input = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself.";
        testCollection1.expectedOutput = new ArrayList<>();
        testCollection1.expectedOutput.add("1/2 I can't believe Tweeter now supports chunking");
        testCollection1.expectedOutput.add("2/2 my messages, so I don't have to do it myself.");

        TestCollection testCollection2 = new TestCollection();
        testCollection2.input = "This isn't bigger than 50 characters.";
        testCollection2.expectedOutput = new ArrayList<>();
        testCollection2.expectedOutput.add(testCollection2.input);

        TestCollection testCollection3 = new TestCollection();
        testCollection3.input = "Ican't believeTweeternowsupportschunkingmymessages,soIdon'thave to do it myself.";
        testCollection3.expectedOutput = new ArrayList<>();
        testCollection3.expectedOutput.add("");

        TestCollection testCollection4 = new TestCollection();
        testCollection4.input = "          ";
        testCollection4.expectedOutput = new ArrayList<>();
        testCollection4.expectedOutput.add("");

        List<TestCollection> testCollections = new ArrayList<>();
        testCollections.add(testCollection1);
        testCollections.add(testCollection2);
        testCollections.add(testCollection3);
        testCollections.add(testCollection4);

        for (TestCollection t : testCollections) {
            List<String> result = null;
            try {
                result = TwitSplit.init(t.input).build();
                assertArrayEquals(t.expectedOutput.toArray(), result.toArray());
            } catch (TwitSplitException e) {
                assertEquals(e.getMessage(), t.expectedOutput.get(0));
            }
        }
    }
}