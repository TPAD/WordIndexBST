import java.util.Comparator;

/**
 * 17683 Data Structures for Application Programmers.
 * Homework Assignment 6 Building Index using BST.
 *
 * Comparator which sorts words according to their frequencies (a word with
 * highest frequency comes first).
 * andrew ID: apadilla
 * @author Tony Padilla
 */
public class Frequency implements Comparator<Word> {

    /**
     * Implementation of compare method using the frequency of Words.
     * @param x the word to compare to y.
     * @param y the word to compare to x.
     * @return positive, 0 or negative values.
     */
    @Override
    public int compare(Word x, Word y) {
        return Integer.compare(y.getFrequency(), x.getFrequency());
    }

}
