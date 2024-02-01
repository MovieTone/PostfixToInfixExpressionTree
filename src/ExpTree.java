import java.util.Stack;

/**
 * 
 * ExpTree class represents an expression tree that holds operands and operators
 * initially having converted a postfix expression. To convert to infix
 * expression inorder method is used
 *
 */
class ExpTree {

    public static boolean isOperator(char c) {
		return (c == '+' || c == '-' || c == '*' || c == '/');
    }
    
    public void inorder(Node n) {
        if (n != null) {
            inorder(n.l);
            System.out.print(n.value + " ");
            inorder(n.r);
        }
    }

    Node convertToTree(char postfixExp[]) {
        Stack<Node> stack = new Stack<Node>();
        Node n, firstOperand, secondOperand;
        for (int i = 0; i < postfixExp.length; i++) {
            if (!isOperator(postfixExp[i])) {
                n = new Node(postfixExp[i]);
                stack.push(n);
            } else {
                n = new Node(postfixExp[i]);
                secondOperand = stack.pop(); 
                firstOperand = stack.pop();
                n.r = secondOperand;
                n.l = firstOperand;
                stack.push(n);
            }
        }
        n = stack.peek();
        stack.pop();
        return n;
    }

}