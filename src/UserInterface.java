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

public class UserInterface extends JFrame implements ActionListener {

    /**
     * Warning was appearing from Eclipse. Decided to add it. Probably not needed but thought it would be wise to rid of the warning. This was for the
     * serialVersionUID.
     */

    JPanel inputPanel, evalButtonPanel, resultPanel;

    JLabel inputLabel, resultLabel;

    JTextField equationInput, resultOutput;

    JButton evaluateButton;

    public static void main(String args[]) {
        UserInterface proj2Gui = new UserInterface();
        proj2Gui.setVisible(true);
        proj2Gui.setSize(550, 200);
    }

    /*
     * Constructor ------------------------------------- This
     * constructor constructs the GUI and puts it all together into one package.
     */
    UserInterface() {
        // Title of GUI
        super("Three Adddress Generator");

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

    /*
     * actionPerformed() method
     * ------------------------------------------------------
     *
     */
    public void actionPerformed(ActionEvent arg0) {
        try {
            ExpTree expTree = new ExpTree();
            String postfixExp = equationInput.getText();
            char[] charArray = postfixExp.replace(" ", "").toCharArray();
            for (char c : charArray) {
                if (!Character.isDigit(c) && !ExpTree.isOperator(c)) {
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