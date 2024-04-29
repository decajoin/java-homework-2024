package niss.net;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JFrame frame;
    private JPanel panel;
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton addButton, subtractButton, multiplyButton, divideButton, equalsButton, clearButton;

    private String firstNumber;
    private String operator;

    public Calculator() {
        frame = new JFrame("计算器");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        textField = new JTextField();
        
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(new NumberButtonListener());
        }

        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        equalsButton = new JButton("=");
        clearButton = new JButton("C");

        addButton.addActionListener(new OperatorButtonListener());
        subtractButton.addActionListener(new OperatorButtonListener());
        multiplyButton.addActionListener(new OperatorButtonListener());
        divideButton.addActionListener(new OperatorButtonListener());
        equalsButton.addActionListener(new EqualsButtonListener());
        clearButton.addActionListener(new ClearButtonListener());

        // 按JPanel的布局顺序加入(4 * 4)
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subtractButton);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(multiplyButton);
        panel.add(numberButtons[0]);
        panel.add(clearButton);
        panel.add(equalsButton);
        panel.add(divideButton);

        frame.add(textField, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();
            String currentText = textField.getText();
            textField.setText(currentText + buttonText);
        }
    }

    private class OperatorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();
            firstNumber = textField.getText();
            operator = buttonText;
            textField.setText("");
        }
    }

    private class EqualsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String secondNumber = textField.getText();
            double result = 0.0;

            if (operator.equals("+")) {
                result = Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber);
            } else if (operator.equals("-")) {
                result = Double.parseDouble(firstNumber) - Double.parseDouble(secondNumber);
            } else if (operator.equals("*")) {
                result = Double.parseDouble(firstNumber) * Double.parseDouble(secondNumber);
            } else if (operator.equals("/")) {
                result = Double.parseDouble(firstNumber) / Double.parseDouble(secondNumber);
            }

            textField.setText(String.valueOf(result));
        }
    }

    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
    }
}
