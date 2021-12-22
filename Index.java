import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 17683 Data Structures for Application Programmers.
 * Homework Assignment 6 Building Index using BST.
 *
 * Index class in charge of building an index tree in three different ways.
 * andrew ID: apadilla
 * @author Tony Padilla
 */
public class Index {

    /**
     * Regular expression used to filter out non-words.
     */
    private static final String REGEX = "[a-zA-Z]+";

    /**
     * Parses an input text file (using the same order words appear in the file)
     * and build an index tree using natural alphabetical order.
     * @param fileName the name of the file to parse.
     * @return An index tree of words in alphabetical order.
     */
    public BST<Word> buildIndex(String fileName) {
        BST<Word> bst = new BST<>();
        HashMap<String, Integer> wordMap = new HashMap<>();
        HashMap<String, Set<Integer>> indexMap = new HashMap<>();
        if (fileName == null) {
            return bst;
        }
        Scanner scanner = null;
        int lineNum = 0;
        try {
            scanner = new Scanner(new File(fileName), "latin1");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;
                String[] wordsFromText =  line.split("\\W");
                for (String word : wordsFromText) {
                    if (word.matches(REGEX)) {
                        Integer freq = wordMap.get(word);
                        freq = (freq == null) ? 1 : ++freq;
                        wordMap.put(word, freq);
                        Set<Integer> index = indexMap.get(word);
                        if (index == null) {
                            index = new HashSet<>();
                        }
                        index.add(lineNum);
                        indexMap.put(word, index);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            Word word = new Word(entry.getKey());
            word.setFrequency(entry.getValue());
            for (Integer index : indexMap.get(word.getWord())) {
                word.addToIndex(index);
            }
            bst.insert(word);
        }
        return bst;
    }

    /**
     * Parses an input text file (using the same order words appear in the file)
     * and build an index tree using specific ordering among words provided
     * by the given comparator.
     * @param fileName the name of the file to parse.
     * @param comparator the comparator to use in comparison of words.
     * @return An index tree of words in order according to given comparator.
     */
    public BST<Word> buildIndex(String fileName, Comparator<Word> comparator) {
        BST<Word> bst = new BST<>(comparator);
        HashMap<String, Integer> wordMap = new HashMap<>();
        HashMap<String, Set<Integer>> indexMap = new HashMap<>();
        if (fileName == null) {
            return bst;
        }
        Scanner scanner = null;
        int lineNum = 0;
        try {
            scanner = new Scanner(new File(fileName), "latin1");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;
                String[] wordsFromText =  line.split("\\W");
                for (String word : wordsFromText) {
                    if (word.matches(REGEX)) {
                        Integer freq;
                        Set<Integer> index;
                        if (comparator instanceof IgnoreCase) {
                            freq = wordMap.get(word.toLowerCase());
                            index = indexMap.get(word.toLowerCase());
                            freq = (freq == null) ? 1 : ++freq;
                            wordMap.put(word.toLowerCase(), freq);
                            if (index == null) {
                                index = new HashSet<>();
                            }
                            index.add(lineNum);
                            indexMap.put(word.toLowerCase(), index);
                        } else {
                            freq = wordMap.get(word);
                            index = indexMap.get(word);
                            freq = (freq == null) ? 1 : ++freq;
                            wordMap.put(word, freq);
                            if (index == null) {
                                index = new HashSet<>();
                            }
                            index.add(lineNum);
                            indexMap.put(word, index);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            Word word = new Word(entry.getKey());
            word.setFrequency(entry.getValue());
            for (Integer index : indexMap.get(word.getWord())) {
                word.addToIndex(index);
            }
            bst.insert(word);
        }
        return bst;
    }

    /**
     * Takes the given list of Word objects and builds an index tree using
     * the ordering specified by the comparator.
     * @param list the list of Word objects from which to build the index tree.
     * @param comparator the comparator used for the ordering.
     * @return an index tree of words in order according to given comparator.
     */
    public BST<Word> buildIndex(ArrayList<Word> list,
                                Comparator<Word> comparator) {
        BST<Word> bst = new BST<>(comparator);
        for (Word word : list) {
            bst.insert(word);
        }
        return bst;
    }

    /**
     * Returns a list of Word objects sorted by alphabetical order from the
     * given index tree.
     * @param tree the index tree to sort.
     * @return the sorted list of Word objects ordered by alphabetical order.
     */
    public ArrayList<Word> sortByAlpha(BST<Word> tree) {
        ArrayList<Word> list = new ArrayList<>();
        for (Word word : tree) {
            list.add(word);
        }
        list.sort(new AlphaFreq());
        return list;
    }


    /**
     * Returns a list of Word objects sorted by frequency from the given
     * index tree.
     * @param tree the index tree to sort.
     * @return the sorted list of Word objects ordered by frequency
     */
    public ArrayList<Word> sortByFrequency(BST<Word> tree) {
        ArrayList<Word> list = new ArrayList<>();
        for (Word word : tree) {
            list.add(word);
        }
        list.sort(new Frequency());
        return list;
    }

    /**
     * Returns a list of Word objects which have the highest frequency from
     * the given index tree.
     * @param tree the index tree to search.
     * @return a list of Word objects.
     */
    public ArrayList<Word> getHighestFrequency(BST<Word> tree) {
        ArrayList<Word> result = new ArrayList<>();
        ArrayList<Word> sorted = sortByFrequency(tree);
        for (Word word : sorted) {
            if (word.getFrequency() == sorted.get(0).getFrequency()) {
                result.add(word);
            } else {
                break;
            }
        }
        return result;
    }

}
