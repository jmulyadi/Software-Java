import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;
import components.stack.Stack;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Josh Mulyadi
 *
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    // TODO - add test cases for constructor, add, remove, and length
    /**
     * Shifts entries between {@code leftStack} and {@code rightStack}, keeping
     * reverse of the former concatenated with the latter fixed, and resulting
     * in length of the former equal to {@code newLeftLength}.
     *
     * @param <T>
     *            type of {@code Stack} entries
     * @param leftStack
     *            the left {@code Stack}
     * @param rightStack
     *            the right {@code Stack}
     * @param newLeftLength
     *            desired new length of {@code leftStack}
     * @updates leftStack, rightStack
     * @requires <pre>
     * 0 <= newLeftLength and
     * newLeftLength <= |leftStack| + |rightStack|
     * </pre>
     * @ensures <pre>
     * rev(leftStack) * rightStack = rev(#leftStack) * #rightStack and
     * |leftStack| = newLeftLength}
     * </pre>
     */
    private static <T> void setLengthOfLeftStack(Stack<T> leftStack,
            Stack<T> rightStack, int newLeftLength) {
        while (leftStack.length() != newLeftLength) {
            rightStack.push(leftStack.pop());
        }
    }

    /*
     * Test cases for constructors
     */
    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> q = this.constructorTest();
        Sequence<String> qExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /*
     * Test cases for kernel methods
     */
    @Test
    public final void testAddEmpty() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest();
        Sequence<String> qExpected = this.createFromArgsRef("red");
        /*
         * Call method under test
         */
        q.add(q.length(), "red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testAddNonEmptyOne() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest("red");
        Sequence<String> qExpected = this.createFromArgsRef("red", "blue");
        /*
         * Call method under test
         */
        q.add(q.length(), "blue");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testAddNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest("red", "blue", "green");
        Sequence<String> qExpected = this.createFromArgsRef("red", "blue",
                "green", "yellow");
        /*
         * Call method under test
         */
        q.add(q.length(), "yellow");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testRemoveLeavingEmpty() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest("red");
        Sequence<String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        String x = q.remove(q.length() - 1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("red", x);
    }

    @Test
    public final void testRemoveLeavingNonEmptyOne() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest("red", "blue");
        Sequence<String> qExpected = this.createFromArgsRef("blue");
        /*
         * Call method under test
         */
        String x = q.remove(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("red", x);
    }

    @Test
    public final void testDeSequenceLeavingNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest("red", "green", "blue");
        Sequence<String> qExpected = this.createFromArgsRef("red", "green");
        /*
         * Call method under test
         */
        String x = q.remove(q.length() - 1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("blue", x);
    }

    @Test
    public final void testLengthEmpty() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest();
        Sequence<String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        int i = q.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(0, i);
    }

    @Test
    public final void testLengthNonEmptyOne() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest("red");
        Sequence<String> qExpected = this.createFromArgsRef("red");
        /*
         * Call method under test
         */
        int i = q.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(1, i);
    }

    @Test
    public final void testLengthNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest("red", "green", "blue");
        Sequence<String> qExpected = this.createFromArgsRef("red", "green",
                "blue");
        /*
         * Call method under test
         */
        int i = q.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(3, i);
    }

    /*
     * Test cases for Standard methods
     */
    @Test
    public final void testNewInstanceEmpty() {
        /*
         * Set up variables
         */
        Sequence<String> q1 = this.createFromArgsTest();
        Sequence<String> q1Expected = this.createFromArgsRef();
        Sequence<String> q2Expected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        Sequence<String> q2 = q1.newInstance();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(q1Expected, q1);
        assertEquals(q2Expected, q2);
    }

    @Test
    public final void testNewInstanceNonEmptyOne() {
        /*
         * Set up variables
         */
        Sequence<String> q1 = this.createFromArgsTest("red");
        Sequence<String> q1Expected = this.createFromArgsRef("red");
        Sequence<String> q2Expected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        Sequence<String> q2 = q1.newInstance();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(q1Expected, q1);
        assertEquals(q2Expected, q2);
    }

    @Test
    public final void testNewInstanceNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Sequence<String> q1 = this.createFromArgsTest("red", "green", "blue");
        Sequence<String> q1Expected = this.createFromArgsRef("red", "green",
                "blue");
        Sequence<String> q2Expected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        Sequence<String> q2 = q1.newInstance();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(q1Expected, q1);
        assertEquals(q2Expected, q2);
    }

    @Test
    public final void testClearEmpty() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest();
        Sequence<String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        q.clear();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testClearNonEmptyOne() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest("red");
        Sequence<String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        q.clear();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testClearNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest("red", "green", "blue");
        Sequence<String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        q.clear();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testTransferFromEmptyEmpty() {
        /*
         * Set up variables
         */
        Sequence<String> q1 = this.createFromArgsTest();
        Sequence<String> q1Expected = this.createFromArgsRef();
        Sequence<String> q2 = this.createFromArgsTest();
        Sequence<String> q2Expected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        q1.transferFrom(q2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(q1Expected, q1);
        assertEquals(q2Expected, q2);
    }

    @Test
    public final void testTransferFromEmptyNonEmpty() {
        /*
         * Set up variables
         */
        Sequence<String> q1 = this.createFromArgsTest();
        Sequence<String> q1Expected = this.createFromArgsRef("red", "green",
                "blue");
        Sequence<String> q2 = this.createFromArgsTest("red", "green", "blue");
        Sequence<String> q2Expected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        q1.transferFrom(q2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(q1Expected, q1);
        assertEquals(q2Expected, q2);
    }

    @Test
    public final void testTransferFromNonEmptyEmpty() {
        /*
         * Set up variables
         */
        Sequence<String> q1 = this.createFromArgsTest("red", "green", "blue");
        Sequence<String> q1Expected = this.createFromArgsRef();
        Sequence<String> q2 = this.createFromArgsTest();
        Sequence<String> q2Expected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        q1.transferFrom(q2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(q1Expected, q1);
        assertEquals(q2Expected, q2);
    }

    @Test
    public final void testTransferFromNonEmptyNonEmpty() {
        /*
         * Set up variables
         */
        Sequence<String> q1 = this.createFromArgsTest("red", "green", "blue");
        Sequence<String> q1Expected = this.createFromArgsRef("yellow",
                "orange");
        Sequence<String> q2 = this.createFromArgsTest("yellow", "orange");
        Sequence<String> q2Expected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        q1.transferFrom(q2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(q1Expected, q1);
        assertEquals(q2Expected, q2);
    }

    /*
     * Test cases for other methods
     */
    @Test
    public final void testFrontNonEmptyOne() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest("red");
        Sequence<String> qExpected = this.createFromArgsRef("red");
        /*
         * Call method under test
         */
        String x = q.entry(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("red", x);
    }

    @Test
    public final void testFrontNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest("red", "green", "blue");
        Sequence<String> qExpected = this.createFromArgsRef("red", "green",
                "blue");
        /*
         * Call method under test
         */
        String x = q.entry(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("red", x);
    }
}
