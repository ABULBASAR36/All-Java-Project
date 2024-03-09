
package calculatorapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame {
    private JTextField displayField;
    private double num1 = 0;
    private String operator = "";
    private boolean start = true;

    public CalculatorApp() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonListener());
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (start) {
                displayField.setText("");
                start = false;
            }
            if (command.equals("=")) {
                calculateResult();
            } else if (command.equals("C")) {
                displayField.setText("");
                start = true;
                num1 = 0;
                operator = "";
            } else {
                displayField.setText(displayField.getText() + command);
            }
        }

        private void calculateResult() {
            String displayText = displayField.getText();
            double num2 = Double.parseDouble(displayText);

            switch (operator) {
                case "+":
                    num1 += num2;
                    break;
                case "-":
                    num1 -= num2;
                    break;
                case "*":
                    num1 *= num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        num1 /= num2;
                    } else {
                        displayField.setText("Error");
                        return;
                    }
                    break;
                default:
                    num1 = num2;
            }

            displayField.setText(Double.toString(num1));
            start = true;
        }
    }

    public static void main(String[] args) {
        new CalculatorApp();
    }
}

