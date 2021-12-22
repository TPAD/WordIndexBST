import java.util.Comparator;

/**
 * 17683 Data Structures for Application Programmers.
 * Homework Assignment 6 Building Index using BST.
 *
 * Comparator which sorts words according to alphabets first and if there is a
 * tie, then words are sorted by their frequencies in ascending order.
 * andrew ID: apadilla
 * @author Tony Padilla
 */
public class AlphaFreq implements Comparator<Word> {

    /**
     * Implementation of compare method using alphabetical ordering first, and
     * frequency ordering in the case of a tie.
     * @param x the word to compare to y.
     * @param y the word to compare to x.
     * @return positive, 0 or negative values.
     */
    @Override
    public int compare(Word x, Word y) {
        int alphaResult = x.compareTo(y);
        if (alphaResult != 0) {
            return alphaResult;
        }
        return Integer.compare(x.getFrequency(), y.getFrequency());
    }

}
