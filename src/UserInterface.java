import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class UserInterface extends JFrame implements ActionListener {

    private final JPanel inputPanel, evalButtonPanel, resultPanel;

    private final JLabel inputLabel, resultLabel;

    private final JTextField equationInput, resultOutput;

    private final JButton evaluateButton;

    public static void main(String[] args) {
        UserInterface proj2Gui = new UserInterface();
        proj2Gui.setVisible(true);
        proj2Gui.setSize(550, 200);
    }

    UserInterface() {
        // Title of GUI
        super("Postfix to Infix using Expression Tree");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Layout of entire GUI
        setLayout(new GridLayout(3, 1));

        // Input Panel (1st Row)
        inputPanel = new JPanel(new FlowLayout());
        inputLabel = new JLabel("Enter Postfix Expression:");
        equationInput = new JTextField(30);

        // Evaluate Button Panel (2nd Row)
        evalButtonPanel = new JPanel(new FlowLayout());
        evaluateButton = new JButton("Construct Tree");
        evaluateButton.addActionListener(this);

        // Evaluation Result Panel (3rd Row)
        resultPanel = new JPanel(new FlowLayout());
        resultLabel = new JLabel("Infix Expression:", SwingConstants.CENTER);
        resultOutput = new JTextField(30);
        resultOutput.setEditable(false);

        // Adds the panels to the overall GridLayout
        add(inputPanel);
        add(evalButtonPanel);
        add(resultPanel);

        // Adds labels and eval button to all three panels
        inputPanel.add(inputLabel);
        inputPanel.add(equationInput);
        evalButtonPanel.add(evaluateButton);
        resultPanel.add(resultLabel);
        resultPanel.add(resultOutput);
    }

    public void actionPerformed(ActionEvent arg0) {
        try {
            ExpressionTree expTree = new ExpressionTree();
            String postfixExp = equationInput.getText();
            char[] charArray = postfixExp.replace(" ", "").toCharArray();
            for (char c : charArray) {
                if (!Character.isDigit(c) && !ExpressionTree.isOperator(c)) {
                    throw new RuntimeException("Invalid token " + c);
                }
            }
            Node root = expTree.convertToTree(charArray);
            resultOutput.setText(root.toString());
            // resets textbox to empty
            equationInput.setText("");
        } catch (RuntimeException e) {
            String message = e.getMessage();
            if (message == null || message.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid postfix expression", "Message", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}