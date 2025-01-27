import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display; // Display for the calculator
    private StringBuilder currentInput = new StringBuilder(); // For input tracking

    public Calculator() {
        // Set up the frame
        setTitle("Calculator");
        setSize(400, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400,100);
        setLayout(new BorderLayout());

        // Create the display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28)); // Increased font size
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setPreferredSize(new Dimension(400, 80)); // Increased output area
        add(display, BorderLayout.NORTH);

        // Create the buttons
        String[] buttons = {
                "9", "8", "7", "+",
                "6", "5", "4", "-",
                "3", "2", "1", "*",
                "0", "DEL", "CLR", "/",
                "(", ")", ".", "^",
                "=", "", "", ""
        };

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 5, 5)); // Reduced button size with more rows

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            if (!text.isEmpty()) { // Add only non-empty buttons
                button.addActionListener(this);
                buttonPanel.add(button);
            } else {
                buttonPanel.add(new JLabel()); // Add blank space for alignment
            }
        }

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9.]")) {
            // Append numbers and decimals
            currentInput.append(command);
            display.setText(currentInput.toString());
        } else if (command.matches("[+\\-*/^()]")) {
            // Handle operators and brackets
            currentInput.append(command);
            display.setText(currentInput.toString());
        } else if (command.equals("=")) {
            // Evaluate the expression
            try {
                String result = evaluateExpression(currentInput.toString());
                display.setText(result);
                currentInput = new StringBuilder(result); // Allow chaining
            } catch (Exception ex) {
                display.setText("Error");
                currentInput = new StringBuilder();
            }
        } else if (command.equals("CLR")) {
            // Clear input
            currentInput.setLength(0);
            display.setText("");
        } else if (command.equals("DEL")) {
            // Delete the last character
            if (currentInput.length() > 0) {
                currentInput.setLength(currentInput.length() - 1);
                display.setText(currentInput.toString());
            }
        }
    }

    private String evaluateExpression(String expression) {
        try {
            return String.valueOf(evaluateInfix(expression));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Expression");
        }
    }

    private double evaluateInfix(String expression) {
        // Convert infix to postfix and evaluate it
        return evaluatePostfix(convertToPostfix(expression));
    }

    private String convertToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);

            if (Character.isDigit(ch) || ch == '.') {
                postfix.append(ch); // Add numbers to postfix
            } else {
                postfix.append(' '); // Add space to separate numbers
                if (ch == '(') {
                    stack.push(ch);
                } else if (ch == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        postfix.append(stack.pop()).append(' ');
                    }
                    stack.pop(); // Remove '('
                } else {
                    while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch)) {
                        postfix.append(stack.pop()).append(' ');
                    }
                    stack.push(ch);
                }
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(' ').append(stack.pop());
        }
        return postfix.toString();
    }

    private double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = postfix.trim().split("\\s+");
        for (String token : tokens) {
            if (token.matches("[0-9.]+")) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+" -> stack.push(a + b);
                    case "-" -> stack.push(a - b);
                    case "*" -> stack.push(a * b);
                    case "/" -> stack.push(a / b);
                    case "^" -> stack.push(Math.pow(a, b));
                }
            }
        }
        return stack.pop();
    }

    private int precedence(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> 0;
        };
    }

    public static void main(String[] args) {
       new Calculator();
    }
}
