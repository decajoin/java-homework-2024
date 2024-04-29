package niss.net;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextInputDialog {
    private JFrame frame;
    private JTextField textField;
    private JButton submitButton;

    public TextInputDialog() {
        frame = new JFrame("文字输入对话框");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField(20); // 创建一个文本输入框，可以容纳20个字符
        submitButton = new JButton("提交"); // 创建一个提交按钮

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText(); // 获取用户输入的文本

                // 显示对话框，内容为用户输入的文本
                JOptionPane.showMessageDialog(frame, "您输入的内容是：" + inputText);

                // 清空文本输入框
                textField.setText("");
            }
        });

        JPanel panel = new JPanel();
        panel.add(textField);
        panel.add(submitButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(300, 100);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        TextInputDialog dialog = new TextInputDialog();
    }
}