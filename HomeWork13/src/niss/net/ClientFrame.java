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

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = inputField.getText();
                try {
                    client.sendMessage(new Information("Client", "Server", message));
                    displayMessage(new Information("Client", "Server", message));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                inputField.setText("");
            }
        });

        setTitle("Client Chat");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void displayMessage(Information info) {
        displayArea.append(info.getFromUser() + ": " + info.getMessage() + "\n");
    }
}
