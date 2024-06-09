package niss.net;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerFrame extends JFrame {
    private JTextArea displayArea;
    private JTextField inputField;
    private JButton sendButton;
    private Server server;

    public ServerFrame(Server server) {
        this.server = server;
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

        // 创建send按键监听器
        ChatListener listener = new ChatListener(server);
        sendButton.addActionListener(listener);
        inputField.addActionListener(listener);

        setTitle("Server Chat");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void displayMessage(Information info) {
        displayArea.append(info.getFromUser() + ": " + info.getMessage() + "\n");
    }

    public JTextField getInputField() {
        return inputField;
    }
}
