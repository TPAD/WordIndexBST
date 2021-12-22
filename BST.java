import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * 17683 Data Structures for Application Programmers.
 * Homework Assignment 6 Building Index using BST.
 *
 * Binary Search Tree.
 * @param <T> data type of objects.
 * andrew ID: apadilla
 * @author Tony Padilla
 */
public class BST<T extends Comparable<T>>
    implements Iterable<T>, BSTInterface<T> {

    /**
     * Reference to the root of the BST.
     */
    private Node<T> root;

    /**
     * Reference to the BST Comparator.
     */
    private Comparator<T> comparator;

    /**
     * No arg constructor for the BST.
     */
    public BST() {
        this(null);
    }

    /**
     * Initializes the BST with the given comparator.
     * @param comp the comparator to initialize.
     */
    public BST(Comparator<T> comp) {
        comparator = comp;
        root = null;
    }

    /**
     * Getter for the BST's comparator.
     * @return the BST's comparator.
     */
    public Comparator<T> comparator() {
        return comparator;
    }

    /**
     * Getter for the data of the root of the BST.
     * @return the data of the root of the BST.
     */
    public T getRoot() {
        if (root == null) {
            return null;
        } else {
            return root.data;
        }
    }

    /**
     * Getter for the height of the BST.
     * @return the height of the BST.
     */
    public int getHeight() {
        return getHeight(root);
    }

    /**
     * Recursive private helper method that gets the height of the BST.
     * @param node the given node to find the height from
     * @return the height of the BST.
     */
    private int getHeight(Node node) {
        // base case.
        if (node == null) {
            return 0;
        }
        // recursively count the nodes to the left.
        int leftHeight = getHeight(node.left);
        // recursively count the nodes to the right.
        int rightHeight = getHeight(node.right);
        // add the root node to the max of left and right.
        return  Math.max(leftHeight, rightHeight) + 1;
    }


    /**
     * Getter for the number of nodes in the BST.
     * @return the number of nodes in the BST.
     */
    public int getNumberOfNodes() {
        return getNumberOfNodes(root);
    }

    /**
     * Recursive private helper method that gets the number of nodes in a BST.
     * @param node the given node from which to start the count.
     * @return the number of nodes in the BST.
     */
    private int getNumberOfNodes(Node node) {
        // base case.
        if (node == null) {
            return 0;
        }
        // recursively count the number of nodes to the left.
        int leftCount = getNumberOfNodes(node.left);
        // recursively count the number of nodes to the right.
        int rightCount = getNumberOfNodes(node.right);
        // add the root to the sum of the nodes to the left and right.
        return leftCount + rightCount + 1;

    }

    /**
     * Method which accepts any comparable object to recursively search in the
     * BST.
     * @param toSearch Object value to search
     * @return the found object.
     */
    @Override
    public T search(T toSearch) {
        if (toSearch == null) {
            return null;
        }
        Node<T> result = search(root, toSearch);
        if (result == null) {
            return null;
        } else {
            return result.data;
        }
    }

    private Node<T> search(Node<T> node, T toSearch) {
        if (toSearch == null) {
            return null;
        }
        // base case.
        if (node == null || toSearch.equals(node.data)) {
            return node;
        }
        if (comparator == null) {
            if (toSearch.compareTo(node.data) < 0) {
                return search(node.right, toSearch);
            }  else if (toSearch.compareTo(node.data) > 0) {
                return search(node.left, toSearch);
            }
        } else {
            if (comparator.compare(toSearch, node.data) < 0) {
                return search(node.right, toSearch);
            } else if (comparator.compare(toSearch, node.data) > 0) {
                return search(node.left, toSearch);
            }
        }
        return null;
    }

    /**
     * Method which accepts any comparable object and recursively inserts it
     * into the BST based on the natural ordering or the specified comparator
     * passed into the BST.
     * @param toInsert a value (object) to insert into the tree.
     */
    @Override
    public void insert(T toInsert) {
        root = insert(root, toInsert);

    }

    /**
     * Recursive private helper method which recursively inserts the given data
     * into the given Node.
     * @param node the node to insert to.
     * @param toInsert the data to insert.
     * @return the Node with the given data inserted.
     */
    private Node<T> insert(Node<T> node, T toInsert) {
        if (toInsert == null) {
            return null;
        }
        // base case.
        if (node == null) {
            node = new Node<>(toInsert);
            return node;
        }
        if (comparator == null) {
            if (toInsert.compareTo(node.data) < 0) {
                node.left = insert(node.left, toInsert);
            } else if (toInsert.compareTo(node.data) > 0) {
                node.right = insert(node.right, toInsert);
            }
        } else {
            if (comparator.compare(toInsert, node.data) < 0) {
                node.left = insert(node.left, toInsert);
            } else if (comparator.compare(toInsert, node.data) > 0) {
                node.right = insert(node.right, toInsert);
            }
        }
        return node;
    }

    /**
     * Returns iterator object that allows access to private elements.
     * @return Iterator object.
     */
    @Override
    public Iterator<T> iterator() {
        return new BSTIterator(root);
    }

    /**
     * Inner (non-static) class for Iterator implementation.
     */
    private class BSTIterator implements Iterator<T> {

        /**
         * Reference to the stack of Nodes in the BST.
         */
        private Stack<Node<T>> stack;

        /**
         * Constructor for the Iterator from a given node.
         * @param node the node to start iterating from.
         */
        BSTIterator(Node<T> node) {
            stack = new Stack<>();
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> node = stack.pop();
            T result = node.data;
            if (node.right != null) {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
            return result;
        }
    }

    /**
     * The Node inner class.
     * @param <T> data type of the Node.
     */
    private static class Node<T> {

        /**
         * Reference to the data of the Node.
         */
        private T data;

        /**
         * Reference to the left Node.
         */
        private Node<T> left;

        /**
         * Reference to the right Node.
         */
        private Node<T> right;

        /**
         * Constructor for the Node with no left or right nodes.
         * @param d the data of the Node.
         */
        Node(T d) {
            this(d, null, null);
        }

        /**
         * Constructor for the Node with given references to left and right
         * Node.
         * @param d the data of the Node.
         * @param l the left Node of Node.
         * @param r the right Node of the Node.
         */
        Node(T d, Node<T> l, Node<T> r) {
            data = d;
            left = l;
            right = r;
        }
    }

}
