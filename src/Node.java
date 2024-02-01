
/**
 * Node class represents a node in a binary tree that is used by ExpressionTree class.
 * Each node has a char value and references to its left and right nodes.
 */
public class Node {

    public char value;
    public Node left;
    public Node right;

    public Node(char value) {
        this.value = value;
        left = right = null;
    }

    public String toString() {
        return (right == null && left == null)
            ? Character.toString(value)
            : "(" + left.toString() + value + right.toString() + ")";
    }

}