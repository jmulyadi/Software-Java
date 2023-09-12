import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Put your name here
 *
 */
public final class SequenceSmooth {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmooth() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @replaces s2
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static void smoothOriginal(Sequence<Integer> s1,
            Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1 != s2 : "Violation of: s1 is not s2";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";
        s2.clear();
        Sequence<Integer> temp = s1.newInstance();
        for (int n : s1) {
            temp.add(temp.length(), n);
        }
        for (int i = 0; i < temp.length() - 1; i++) {
            Integer first = temp.entry(i);
            Integer second = temp.entry(i + 1);
            s2.add(s2.length(), (first - second) / 2 + second);
        }
        if (s1.length() == 1) {
            s2.clear();
        }
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @returns |s2| = |s1| - 1 and for all i, j: integer, a, b: string of
     *          integer where (s1 = a * <i> * <j> * b) (there exists c, d:
     *          string of integer (|c| = |a| and s2 = c * <(i+j)/2> * d))
     * @requires |s1| >= 1
     */
    public static Sequence<Integer> smooth2(Sequence<Integer> s1,
            Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1 != s2 : "Violation of: s1 is not s2";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";
        Sequence<Integer> temp = s1.newInstance();
        Sequence<Integer> temp2 = s1.newInstance();
        for (int n : s1) {
            temp.add(temp.length(), n);
            temp2.add(temp.length(), n);
        }
        for (int i = 0; i < temp.length() - 1; i++) {
            Integer first = temp.entry(i);
            Integer second = temp.entry(i + 1);
            temp2.add(temp2.length(), (int) ((long) first + (long) second) / 2);
        }
        if (s1.length() == 1) {
            temp2.clear();
        }
        return temp2;
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth the resulting sequence
     * @returns |s2| = |s1| - 1 and for all i, j: integer, a, b: string of
     *          integer where (s1 = a * <i> * <j> * b) (there exists c, d:
     *          string of integer (|c| = |a| and s2 = c * <(i+j)/2> * d))
     * @requires |s1| >= 1
     */
    public static Sequence<Integer> smooth4(Sequence<Integer> s1) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";
        Sequence<Integer> s2 = s1.newInstance();
        if (!(s1.length() == 1)) {
            int first = s1.remove(0);
            int second = s1.entry(0);
            s2.transferFrom(smooth4(s1));
            int average = (int) ((long) first + (long) second) / 2;
            s2.add(0, average);
            s1.add(0, first);
        }
        return s2;
    }

    /***
     *
     * @param args
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();
        Sequence<Integer> s2 = new Sequence1L<>();
        s2.add(s2.length(), -1073741823);
        s2.add(s2.length(), 1073741824);
        out.println(smooth4(s2));
        in.close();
        out.close();
    }

}
