import java.util.Stack;

/**
 * 
 * ExpressionTree class represents an expression tree that holds operands and operators,
 * having converted a postfix expression prior to that. To convert to infix
 * expression the inorder method is used
 *
 */
public class ExpressionTree {

    public static boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
    }
    
    public void inorder(Node node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }

    public Node convertToTree(char[] postfixExpr) {
        Stack<Node> stack = new Stack<>();
        Node node, firstOperand, secondOperand;

        for (char token : postfixExpr) {
            if (!isOperator(token)) {
                node = new Node(token);
                stack.push(node);
                continue;
            }

            node = new Node(token);
            secondOperand = stack.pop();
            firstOperand = stack.pop();
            node.right = secondOperand;
            node.left = firstOperand;
            stack.push(node);
        }

        node = stack.peek();
        stack.pop();

        return node;
    }

}