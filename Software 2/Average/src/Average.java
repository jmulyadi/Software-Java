import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Josh Mulyadi
 *
 */
public final class Average {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Average() {
    }

    /**
     * Returns the integer average of two given {@code int}s.
     *
     * @param j
     *            the first of two integers to average
     * @param k
     *            the second of two integers to average
     * @return the integer average of j and k
     * @ensures average = (j+k)/2
     */
    public static int average(int j, int k) {
        int roundingError = 0;
        if (j % 2 != 0 && k % 2 != 0) {
            if (j < 0 && k < 0) {
                roundingError = -1;
            } else if (j > 0 && k > 0) {
                roundingError = 1;
            }
        }

        return roundingError + j / 2 + k / 2;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        out.println(average(3, 5));
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
