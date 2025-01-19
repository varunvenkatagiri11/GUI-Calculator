
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICalculator extends JFrame implements ActionListener {
    private JTextField display; // To display the numbers and result
    private double num1 = 0, num2 = 0, result = 0; // Numbers and result
    private char operator = ' '; // Operator (+, -, *, /)

    public GUICalculator() {
        // Frame settings
        setTitle("Varun's Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Buttons for numbers and operations
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));

        // Button labels
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        // Add buttons to panel
        for (String label : buttons) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Clear button
        if (command.equals("C")) {
            num1 = num2 = result = 0;
            operator = ' ';
            display.setText("");
            return;
        }

        // Equals button
        if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': 
                    if (num2 == 0) {
                        display.setText("Cannot divide by 0");
                        return;
                    }
                    result = num1 / num2;
                    break;
                default: display.setText("Error"); return;
            }
            display.setText(String.valueOf(result));
            operator = ' '; // Reset operator
            num1 = result; // Use result as next num1
            return;
        }

        // Operator buttons
        if (command.matches("[+\\-*/]")) {
            operator = command.charAt(0);
            num1 = Double.parseDouble(display.getText());
            display.setText(""); // Clear display for next number
            return;
        }

        // Number buttons
        display.setText(display.getText() + command);
    }

    public static void main(String[] args) {
        new GUICalculator();
    }
}

