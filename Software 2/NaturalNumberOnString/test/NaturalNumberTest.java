import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    // TODO - add test cases for four constructors, multiplyBy10, divideBy10, isZero

    // Test cases for constructors

    /*
     * Test case for the no argument constructor
     */
    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test case for int constructor using 0
     */
    @Test
    public final void testInt0Constructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test case for int constructor using 100
     */
    @Test
    public final void testInt100Constructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(100);
        NaturalNumber nExpected = this.constructorRef(100);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test case for int constructor using 2147483647
     */
    @Test
    public final void testInt2147483647Constructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(2147483647);
        NaturalNumber nExpected = this.constructorRef(2147483647);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test case for String constructor using "0"
     */
    @Test
    public final void testString0Constructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest("0");
        NaturalNumber nExpected = this.constructorRef("0");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test case for String constructor using "100"
     */
    @Test
    public final void testString100Constructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest("100");
        NaturalNumber nExpected = this.constructorRef("100");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test case for String constructor using "2147483648"
     */
    @Test
    public final void testStringLargerThanIntConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest("2147483648");
        NaturalNumber nExpected = this.constructorRef("2147483648");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test case for NaturalNumber constructor using 0
     */
    @Test
    public final void testNaturalNumber0Constructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nExpected = this.constructorRef();
        NaturalNumber n = this.constructorTest(nExpected);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test case for NaturalNumber constructor using 100
     */
    @Test
    public final void testNaturalNumber100Constructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nExpected = this.constructorRef(100);
        NaturalNumber n = this.constructorTest(nExpected);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test case for NaturalNumber constructor using 2147483648
     */
    @Test
    public final void testNaturalNumberLargerThanIntConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nExpected = this.constructorRef("2147483648");
        NaturalNumber n = this.constructorTest(nExpected);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    // Test cases for methods

    /*
     * Test case for multiplyBy10 with with 0 and 0
     */
    @Test
    public final void testMultiplyBy10with0and0() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef(10);
        n.multiplyBy10(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test case for multiplyBy10 with with 0 and 5
     */
    @Test
    public final void testMultiplyBy10with0and5() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef(15);
        n.multiplyBy10(5);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test case for multiplyBy10 with with 1111 and 1
     */
    @Test
    public final void testMultiplyBy10with1111and1() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(1111);
        NaturalNumber nExpected = this.constructorRef(11111);
        n.multiplyBy10(1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test case for multiplyBy10 with with 34 and 8
     */
    @Test
    public final void testMultiplyBy10with34and8() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(34);
        NaturalNumber nExpected = this.constructorRef(348);
        n.multiplyBy10(8);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test case for multiplyBy10 with with 2147483647 and 5
     */
    @Test
    public final void testMultiplyBy10with2147483647and5() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(2147483647);
        NaturalNumber nExpected = this.constructorRef("21474836475");
        n.multiplyBy10(5);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test case for multiplyBy10 with with 2147483648 and 0
     */
    @Test
    public final void testMultiplyBy10with2147483648and0() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest("2147483648");
        NaturalNumber nExpected = this.constructorRef("21474836480");
        n.multiplyBy10(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test case for multiplyBy10 with with 1000000000000 and 0
     */
    @Test
    public final void testMultiplyBy10ManyZeros() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest("1000000000000");
        NaturalNumber nExpected = this.constructorRef("10000000000000");
        n.multiplyBy10(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test case for multiplyBy10 with with 1000000000000 and 1
     */
    @Test
    public final void testMultiplyBy10ManyZerosAndOne() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest("1000000000000");
        NaturalNumber nExpected = this.constructorRef("10000000000001");
        n.multiplyBy10(1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test case for divideBy10 with 10
     */
    @Test
    public final void testDivideBy10with10() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(10);
        NaturalNumber nExpected = this.constructorRef(1);
        int removed = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(removed, 0);
    }

    /*
     * Test case for divideBy10 with 12345
     */
    @Test
    public final void testDivideBy10with12345() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(12345);
        NaturalNumber nExpected = this.constructorRef(1234);
        int removed = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(removed, 5);
    }

    /*
     * Test case for divideBy10 with 1111
     */
    @Test
    public final void testDivideBy10with1111() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(1111);
        NaturalNumber nExpected = this.constructorRef(111);
        int removed = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(removed, 1);
    }

    /*
     * Test case for divideBy10 with 2147483648
     */
    @Test
    public final void testDivideBy10with2147483648() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest("2147483648");
        NaturalNumber nExpected = this.constructorRef(214748364);
        int removed = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(removed, 8);
    }

    /*
     * Test case for isZero with 0
     */
    @Test
    public final void testIsZeroWithZero() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        boolean zero = n.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(zero, true);
    }

    /*
     * Test case for isZero with 1
     */
    @Test
    public final void testIsZeroWith1() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(1);
        NaturalNumber nExpected = this.constructorRef(1);
        boolean zero = n.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(zero, false);
    }

    /*
     * Test case for isZero with 12345
     */
    @Test
    public final void testIsZeroWith12345() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(12345);
        NaturalNumber nExpected = this.constructorRef(12345);
        boolean zero = n.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(zero, false);
    }

    /*
     * Test case for isZero with 1111
     */
    @Test
    public final void testIsZeroWith1111() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(1111);
        NaturalNumber nExpected = this.constructorRef(1111);
        boolean zero = n.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(zero, false);
    }

    /*
     * Test case for isZero with 2147483648
     */
    @Test
    public final void testIsZeroWith2147483648() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest("2147483648");
        NaturalNumber nExpected = this.constructorRef("2147483648");
        boolean zero = n.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(zero, false);
    }

}
