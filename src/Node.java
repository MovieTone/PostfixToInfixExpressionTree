
/**
 * Node class represents a node in a binary tree that is used by ExpTree class. Every node has a char value and references to its left and right
 * nodes.
 */
public class Node {

    char value;
    Node l;
    Node r;

    public Node(char c) {
        this.value = c;
        l = r = null;
    }

    public String toString() {
        return (r == null && l == null) ? Character.toString(value) : "(" + l.toString() + value + r.toString() + ")";
    }

}