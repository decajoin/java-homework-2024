package niss.net;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ChatGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    public ChatGUI() {
        setTitle("ChatAI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chatArea = new JTextArea(10, 50);
        chatArea.setEditable(false);

        inputField = new JTextField(30);
        sendButton = new JButton("发送");

        // 设置文本区域字体大小
        Font chatFont = new Font("宋体", Font.PLAIN, 20); //  20是字体大小，可以根据需要调整
        chatArea.setFont(chatFont);

        // 设置文本框字体大小
        Font inputFont = new Font("宋体", Font.PLAIN, 20); // 同样，根据需要调整字体大小
        inputField.setFont(inputFont);

        // 添加回车键绑定的事件处理
        InputMap inputMap = inputField.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actionMap = inputField.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "submit");
        actionMap.put("submit", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitInput();
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitInput();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        panel.add(inputField, BorderLayout.PAGE_END);
        panel.add(sendButton, BorderLayout.LINE_END);

        add(panel);
        pack();
        setVisible(true);
    }

    private void appendToChatArea(String text) {
        chatArea.append(text + "\n");
    }

    // 点击提交按钮执行的逻辑
    private void submitInput() {
        String input = inputField.getText();

        // 根据输入的逻辑进行处理，这里只是一个简单的示例
        String output = processInput(input);

        appendToChatArea("用户: " + input);
        appendToChatArea("AI: " + output);

        inputField.setText("");
    }

    private String processInput(String input) {
    	// 对话核心程序（估值一个亿的AI核心程序）
       String answer;
       answer = input;
       answer = answer.replace("吗", "");
       answer = answer.replace("?", "!");
       answer = answer.replace("？", "!");
       return answer;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatGUI();
            }
        });
    }
}
