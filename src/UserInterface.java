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

    private static final String UI_TITLE = "Postfix to Infix using Expression Tree";
    private static final String INPUT_LABEL_TEXT = "Enter Postfix Expression:";
    private static final String EVALUATE_BUTTON_TEXT = "Construct Tree";
    private static final String RESULT_LABEL_TEXT = "Infix Expression:";
    private static final String INVALID_EXPRESSION_ERROR = "Invalid postfix expression";

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.setVisible(true);
        ui.setSize(550, 200);
    }

    UserInterface() {
        // GUI title
        super(UI_TITLE);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // layout of the entire GUI
        setLayout(new GridLayout(3, 1));

        // Input Panel (1st Row)
        inputPanel = new JPanel(new FlowLayout());
        inputLabel = new JLabel(INPUT_LABEL_TEXT);
        equationInput = new JTextField(30);

        // Evaluate Button Panel (2nd Row)
        evalButtonPanel = new JPanel(new FlowLayout());
        evaluateButton = new JButton(EVALUATE_BUTTON_TEXT);
        evaluateButton.addActionListener(this);

        // Evaluation Result Panel (3rd Row)
        resultPanel = new JPanel(new FlowLayout());
        resultLabel = new JLabel(RESULT_LABEL_TEXT, SwingConstants.CENTER);
        resultOutput = new JTextField(30);
        resultOutput.setEditable(false);

        // adds the panels to the overall GridLayout
        add(inputPanel);
        add(evalButtonPanel);
        add(resultPanel);

        // adds labels and eval button to all three panels
        inputPanel.add(inputLabel);
        inputPanel.add(equationInput);
        evalButtonPanel.add(evaluateButton);
        resultPanel.add(resultLabel);
        resultPanel.add(resultOutput);
    }

    public void actionPerformed(ActionEvent event) {
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
            // resets the textbox
            equationInput.setText("");
        } catch (RuntimeException e) {
            String message = e.getMessage();
            if (message == null || message.isEmpty()) {
                JOptionPane.showMessageDialog(this, INVALID_EXPRESSION_ERROR, "Message", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
