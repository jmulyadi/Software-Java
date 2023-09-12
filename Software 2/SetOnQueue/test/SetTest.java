import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /*
     * Test cases for constructors
     */

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.constructorTest();
        Set<String> qExpected = this.constructorRef();
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
        Set<String> q = this.createFromArgsTest();
        Set<String> qExpected = this.createFromArgsRef("red");
        /*
         * Call method under test
         */
        q.add("red");
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
        Set<String> q = this.createFromArgsTest("red");
        Set<String> qExpected = this.createFromArgsRef("red", "blue");
        /*
         * Call method under test
         */
        q.add("blue");
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
        Set<String> q = this.createFromArgsTest("red", "blue", "green");
        Set<String> qExpected = this.createFromArgsRef("red", "blue", "green",
                "yellow");
        /*
         * Call method under test
         */
        q.add("yellow");
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
        Set<String> q = this.createFromArgsTest("red");
        Set<String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        String x = q.remove("red");
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
        Set<String> q = this.createFromArgsTest("red", "blue");
        Set<String> qExpected = this.createFromArgsRef("blue");
        /*
         * Call method under test
         */
        String x = q.remove("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("red", x);
    }

    @Test
    public final void testDeSetLeavingNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest("red", "green", "blue");
        Set<String> qExpected = this.createFromArgsRef("red", "green");
        /*
         * Call method under test
         */
        String x = q.remove("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("blue", x);
    }

    @Test
    public final void testsizeEmpty() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest();
        Set<String> qExpected = this.createFromArgsRef();
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
        Set<String> q = this.createFromArgsTest("red");
        Set<String> qExpected = this.createFromArgsRef("red");
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
        Set<String> q = this.createFromArgsTest("red", "green", "blue");
        Set<String> qExpected = this.createFromArgsRef("red", "green", "blue");
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
        Set<String> q1 = this.createFromArgsTest();
        Set<String> q1Expected = this.createFromArgsRef();
        Set<String> q2Expected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        Set<String> q2 = q1.newInstance();
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
        Set<String> q1 = this.createFromArgsTest("red");
        Set<String> q1Expected = this.createFromArgsRef("red");
        Set<String> q2Expected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        Set<String> q2 = q1.newInstance();
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
        Set<String> q1 = this.createFromArgsTest("red", "green", "blue");
        Set<String> q1Expected = this.createFromArgsRef("red", "green", "blue");
        Set<String> q2Expected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        Set<String> q2 = q1.newInstance();
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
        Set<String> q = this.createFromArgsTest();
        Set<String> qExpected = this.createFromArgsRef();
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
        Set<String> q = this.createFromArgsTest("red");
        Set<String> qExpected = this.createFromArgsRef();
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
        Set<String> q = this.createFromArgsTest("red", "green", "blue");
        Set<String> qExpected = this.createFromArgsRef();
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
        Set<String> q1 = this.createFromArgsTest();
        Set<String> q1Expected = this.createFromArgsRef();
        Set<String> q2 = this.createFromArgsTest();
        Set<String> q2Expected = this.createFromArgsRef();
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
        Set<String> q1 = this.createFromArgsTest();
        Set<String> q1Expected = this.createFromArgsRef("red", "green", "blue");
        Set<String> q2 = this.createFromArgsTest("red", "green", "blue");
        Set<String> q2Expected = this.createFromArgsRef();
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
        Set<String> q1 = this.createFromArgsTest("red", "green", "blue");
        Set<String> q1Expected = this.createFromArgsRef();
        Set<String> q2 = this.createFromArgsTest();
        Set<String> q2Expected = this.createFromArgsRef();
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
        Set<String> q1 = this.createFromArgsTest("red", "green", "blue");
        Set<String> q1Expected = this.createFromArgsRef("yellow", "orange");
        Set<String> q2 = this.createFromArgsTest("yellow", "orange");
        Set<String> q2Expected = this.createFromArgsRef();
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
        Set<String> q = this.createFromArgsTest("red");
        Set<String> qExpected = this.createFromArgsRef("red");
        /*
         * Call method under test
         */
        String x = q.removeAny();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected.contains(x), true);
        qExpected.remove(x);
        assertEquals(q, qExpected);
    }

    @Test
    public final void testContainsTrue() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest("red", "green", "blue");
        Set<String> qExpected = this.createFromArgsRef("red", "green", "blue");
        boolean check = true;

        /*
         * Call method under test
         */
        boolean x = q.contains("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(check, x);
    }

    @Test
    public final void testContainsFalse() {
        /*
         * Set up variables
         */
        Set<String> q = this.createFromArgsTest("red", "green", "blue");
        Set<String> qExpected = this.createFromArgsRef("red", "green", "blue");
        boolean check = false;

        /*
         * Call method under test
         */
        boolean x = q.contains("afnsowenvwepoofpnwq");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(check, x);
    }
}
