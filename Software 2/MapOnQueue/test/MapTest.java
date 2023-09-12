import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value, hasKey, and size
    /*
     * Test cases for constructors
     */

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> q = this.constructorTest();
        Map<String, String> qExpected = this.constructorRef();
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
        Map<String, String> q = this.createFromArgsTest();
        Map<String, String> qExpected = this.createFromArgsRef("yellow",
                "blue");
        /*
         * Call method under test
         */
        q.add("yellow", "blue");
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
        Map<String, String> q = this.createFromArgsTest("red");
        Map<String, String> qExpected = this.createFromArgsRef("red", "blue",
                "hi");
        /*
         * Call method under test
         */
        q.add("blue", "hi");
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
        Map<String, String> q = this.createFromArgsTest("red", "blue", "green");
        Map<String, String> qExpected = this.createFromArgsRef("red", "blue",
                "green", "yellow", "hi");
        /*
         * Call method under test
         */
        q.add("yellow", "hi");
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
        Map<String, String> q = this.createFromArgsTest("red", "hi");
        Map<String, String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        Map.Pair<String, String> x = q.remove("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(x.key(), "red");
        assertEquals(x.value(), "hi");
        assertEquals(qExpected, q);
    }

    @Test
    public final void testDeSetLeavingNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest("red", "hi", "1",
                "dad");
        Map<String, String> qExpected = this.createFromArgsRef("red", "hi");
        /*
         * Call method under test
         */
        Map.Pair<String, String> x = q.remove("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(x.key(), "1");
        assertEquals(x.value(), "dad");
        assertEquals(qExpected, q);
    }

    @Test
    public final void testsizeEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest();
        Map<String, String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        int i = q.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(0, i);
    }

    @Test
    public final void testsizeNonEmptyOne() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest("red");
        Map<String, String> qExpected = this.createFromArgsRef("red");
        /*
         * Call method under test
         */
        int i = q.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(1, i);
    }

    @Test
    public final void testsizeNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest("red", "green", "blue");
        Map<String, String> qExpected = this.createFromArgsRef("red", "green",
                "blue");
        /*
         * Call method under test
         */
        int i = q.size();
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
        Map<String, String> q1 = this.createFromArgsTest();
        Map<String, String> q1Expected = this.createFromArgsRef();
        Map<String, String> q2Expected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        Map<String, String> q2 = q1.newInstance();
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
        Map<String, String> q1 = this.createFromArgsTest("red");
        Map<String, String> q1Expected = this.createFromArgsRef("red");
        Map<String, String> q2Expected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        Map<String, String> q2 = q1.newInstance();
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
        Map<String, String> q1 = this.createFromArgsTest("red", "green",
                "blue");
        Map<String, String> q1Expected = this.createFromArgsRef("red", "green",
                "blue");
        Map<String, String> q2Expected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        Map<String, String> q2 = q1.newInstance();
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
        Map<String, String> q = this.createFromArgsTest();
        Map<String, String> qExpected = this.createFromArgsRef();
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
        Map<String, String> q = this.createFromArgsTest("red");
        Map<String, String> qExpected = this.createFromArgsRef();
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
        Map<String, String> q = this.createFromArgsTest("red", "green", "blue");
        Map<String, String> qExpected = this.createFromArgsRef();
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
        Map<String, String> q1 = this.createFromArgsTest();
        Map<String, String> q1Expected = this.createFromArgsRef();
        Map<String, String> q2 = this.createFromArgsTest();
        Map<String, String> q2Expected = this.createFromArgsRef();
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
        Map<String, String> q1 = this.createFromArgsTest();
        Map<String, String> q1Expected = this.createFromArgsRef("red", "green",
                "blue");
        Map<String, String> q2 = this.createFromArgsTest("red", "green",
                "blue");
        Map<String, String> q2Expected = this.createFromArgsRef();
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
        Map<String, String> q1 = this.createFromArgsTest("red", "green",
                "blue");
        Map<String, String> q1Expected = this.createFromArgsRef();
        Map<String, String> q2 = this.createFromArgsTest();
        Map<String, String> q2Expected = this.createFromArgsRef();
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
        Map<String, String> q1 = this.createFromArgsTest("red", "green",
                "blue");
        Map<String, String> q1Expected = this.createFromArgsRef("yellow",
                "orange");
        Map<String, String> q2 = this.createFromArgsTest("yellow", "orange");
        Map<String, String> q2Expected = this.createFromArgsRef();
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
    public final void testRemoveAny() {
        /*
         * Set up variables
         */
        Map<String, String> q = this.createFromArgsTest("red", "yuh");
        Map<String, String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        Pair<String, String> x = q.removeAny();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(x.key(), "red");
        assertEquals(x.value(), "yuh");
        assertEquals(q, qExpected);
    }

}
