import java.io.Serializable;
import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue2;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * This program takes a given texts as input and output name for the output file
 * then it counts all the words inside of that text file and outputs a file with
 * the given output name that has a table with all the words and amount of times
 * the appear.
 *
 * @author Josh Mulyadi
 *
 */
public final class WordCounter {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private WordCounter() {
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";
        String answer = "";
        boolean isChar = true;
        //go through each character in the text to try and find the separators
        for (Character c : separators) {
            if (text.charAt(position) == c) {
                isChar = false;
            }
        }
        answer += text.charAt(position);
        //depending on if the the current char is a separator or not
        if (isChar) {
            for (int i = position + 1; i < text.length(); i++) {
                for (Character c : separators) {
                    if (text.charAt(i) == c) {
                        isChar = false;
                    }
                }
                if (isChar) {
                    answer += text.charAt(i);
                } else {
                    i = text.length();
                }
            }
            //this is the case if it is a separator
        } else {
            isChar = true;
            for (int i = position + 1; i < text.length(); i++) {
                isChar = true;
                for (Character c : separators) {
                    if (text.charAt(i) == c) {
                        isChar = false;
                    }
                }
                if (!isChar) {
                    answer += text.charAt(i);
                } else {
                    i = text.length();
                }
            }
        }
        //returns either a word or a string of separators
        return answer;
    }

    /**
     * Returns a Map of the word as the key and the definition as the value.
     *
     *
     * @param in
     *            the file to read from
     * @return a map with key value as the word and the number of times they
     *         occur
     *
     * @requires in is not null
     *
     */
    private static Map<String, Integer> makeTable(String in) {
        //create variables
        SimpleWriter output = new SimpleWriter1L();
        SimpleReader fileReader = new SimpleReader1L(in);
        //create my list of separators
        Set<Character> separatorSet = new Set1L<>();
        separatorSet.add(' ');
        separatorSet.add('\t');
        separatorSet.add('.');
        separatorSet.add(',');
        separatorSet.add('-');
        Map<String, Integer> table = new Map1L<>();
        //read the file until the end
        while (!fileReader.atEOS()) {
            int position = 0;
            String line = fileReader.nextLine();
            //use next word or separators for each line to find every word
            while (position < line.length()) {
                String word = nextWordOrSeparator(line, position, separatorSet);
                if (!separatorSet.contains(word.charAt(0))) {
                    if (table.hasKey(word)) {
                        table.replaceValue(word, table.value(word) + 1);
                    } else {
                        table.add(word, 1);
                    }
                }
                position += word.length();
            }
        }
        //close variables
        output.close();
        fileReader.close();
        return table;
    }

    /**
     * Comparator Class.
     */
    private static class StringLT implements Comparator<String>, Serializable {

        /**
         * Added ID.
         */
        private static final long serialVersionUID = 1L;

        @Override
        public int compare(String s1, String s2) {
            String str1 = s1.toLowerCase();
            String str2 = s2.toLowerCase();
            return str1.compareTo(str2);
        }
    }

    /***
     *
     * @param table
     *            this is the map that holds all the words and occurrences
     * @param q
     *            this is the q of all the keys in table alphabetized
     * @param title
     *            title of the page
     * @param outputFile
     *            this is the title of the output file
     *
     */

    private static void makePage(Map<String, Integer> table, Queue<String> q,
            String title, String outputFile) {
        //create the file to write to with the output file name
        SimpleWriter fileWriter = new SimpleWriter1L(outputFile);
        //html
        fileWriter.println("<html><head>");
        fileWriter.println(
                "<title>Words Counted in " + title + "</title></head>");
        fileWriter.println("<body><h2>Words Counted in " + title + "<h2><hr/>");
        fileWriter.println("<table border=\"1\">");
        fileWriter.println("<tr>\r\n<th>Words</th><th>Counts</th></tr>");
        //go through the whole table and write each word and occurrences in html
        for (String key : q) {
            int occurances = table.value(key);
            fileWriter.println(
                    "<tr><td>" + key + "</td><td>" + occurances + "</td></tr>");
        }
        //close all tags
        fileWriter.println("</table></body></html>");
        //close file
        fileWriter.close();
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        //create variables
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        //ask user for input
        output.print("Enter a name for the input file : ");
        String inputFileName = input.nextLine();
        output.print("Enter a name for the output file : ");
        String outputFileName = input.nextLine();
        //create the map of words and occurrences
        Map<String, Integer> table = makeTable(inputFileName);
        //create a queue
        Queue<String> q = new Queue2<>();
        //go through the map key values and add them to a queue
        for (Map.Pair<String, Integer> pair : table) {
            q.enqueue(pair.key());
        }
        //alphabetize the queue
        StringLT order = new StringLT();
        q.sort(order);
        //make the actual page
        makePage(table, q, inputFileName, outputFileName);
        //close variables
        input.close();
        output.close();
    }

}
