import java.util.Comparator;

/**
 * 17683 Data Structures for Application Programmers.
 * Homework Assignment 6 Building Index using BST.
 *
 * Comparator which sorts words by case insensitive alphabetical order.
 * andrew ID: apadilla
 * @author Tony Padilla
 */
public class IgnoreCase implements Comparator<Word> {

    /**
     * Implementation of compare method using the natural ordering of the String
     * which is represented by the Word objects specified.
     * @param x the word to compare to y.
     * @param y the word to compare to x.
     * @return positive, 0 or negative values.
     */
    @Override
    public int compare(Word x, Word y) {
        return x.getWord().toLowerCase()
            .compareTo(y.getWord().toLowerCase());
    }

}
