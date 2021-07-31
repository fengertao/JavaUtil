package charlie.feng.demo.algorithm.tree;

public class AvlNode<T extends Comparable<T>> {
    // Friendly data; accessible by other package routines
    T element; // The data in the node
    AvlNode<T> left; // Left child
    AvlNode<T> right; // Right child
    int height; // Height

    // Constructors
    AvlNode(T theElement) {
        this(theElement, null, null);
    }

    AvlNode(T theElement, AvlNode<T> lt, AvlNode<T> rt) {
        element = theElement;
        left = lt;
        right = rt;
        height = 0;
    }

}
