import java.util.HashSet;
import java.util.Set;

/**
 * 17683 Data Structures for Application Programmers.
 * Homework Assignment 6 Building Index using BST.
 *
 * A class representing a word
 * andrew ID: apadilla
 * @author Tony Padilla
 */
public class Word implements Comparable<Word> {

    /**
     * Private variable which represents a word.
     */
    private String word;

    /**
     * Private variable representing the indices where the word is found.
     */
    private Set<Integer> index;

    /**
     * Private variable representing the frequency of the word.
     */
    private int frequency;

    /**
     * Constructor for the word object representing the given String.
     * @param w the String to represent as a word.
     */
    public Word(String w) {
        word = w;
        index = new HashSet<>();
        frequency = 0;
    }

    /**
     * Setter for the word.
     * @param w the word to be represented by the Word object.
     */
    public void setWord(String w) {
        word = word;
    }

    /**
     * Getter for the word that the Word object represents.
     * @return the String representation of the Word object.
     */
    public String getWord() {
        return word;
    }

    /**
     * Setter for the frequency of the Word object.
     * @param freq the Word object's updated frequency value.
     */
    public void setFrequency(int freq) {
        frequency = freq;
    }

    /**
     * Getter for the Word object's frequency.
     * @return the frequency of the Word object.
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Adds a new line number for the word that should be added to the index.
     * @param line the line number to add to the index.
     */
    public void addToIndex(Integer line) {
        index.add(line);
    }

    /**
     * Getter for the indices of the Word object.
     * @return the Set containing the indices of the Word object.
     */
    public Set<Integer> getIndex() {
        return Set.copyOf(index);
    }

    /**
     * Returns the String representation of the Word object.
     * @return A String representation of the Word object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %d ", word, frequency));
        sb.append(index.toString());
        return sb.toString();
    }

    /**
     * Compares a word to another by alphabetical order.
     * @param o the word to compare.
     * @return order.
     */
    @Override
    public int compareTo(Word o) {
        return word.compareTo(o.word);
    }

}
