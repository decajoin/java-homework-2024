package niss.net;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientFrame extends JFrame {
    private JTextArea displayArea;
    private JTextField inputField;
    private JButton sendButton;
    private Client client;

    public ClientFrame(Client client) {
        this.client = client;
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        inputField = new JTextField(30);
        sendButton = new JButton("Send");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        setLayout(new BorderLayout());
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // 定义一个监听器
        ActionListener sendActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 获得文本框输入的信息
                String message = inputField.getText();
                try {
                	// 发送信息
                    client.sendMessage(new Information("Client", "Server", message));
                    displayMessage(new Information("Client", "Server", message));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                inputField.setText("");
            }
        };

        
        sendButton.addActionListener(sendActionListener);

        
        inputField.addActionListener(sendActionListener);

        setTitle("Client Chat");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void displayMessage(Information info) {
        displayArea.append(info.getFromUser() + ": " + info.getMessage() + "\n");
    }
}
