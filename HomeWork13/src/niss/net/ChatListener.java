package niss.net;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// 一个统一的 ChatListener 处理发送按钮的事件
// 通过构造函数区分是客户端还是服务器端，从而实现了代码的重用和结构的简化
public class ChatListener implements ActionListener {
    private Client client;
    private Server server;
    private String userType; // "Client" or "Server"
    
    public ChatListener(Client client) {
        this.client = client;
        this.userType = "Client";
    }

    public ChatListener(Server server) {
        this.server = server;
        this.userType = "Server";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String message = null;
        try {
            if (userType.equals("Client")) {
                ClientFrame frame = client.getFrame();
                message = frame.getInputField().getText();
                client.sendMessage(new Information("Client", "Server", message));
                frame.displayMessage(new Information("Client", "Server", message));
            } else {
                ServerFrame frame = server.getFrame();
                message = frame.getInputField().getText();
                server.sendMessage(new Information("Server", "Client", message));
                frame.displayMessage(new Information("Server", "Client", message));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (userType.equals("Client")) {
            client.getFrame().getInputField().setText("");
        } else {
            server.getFrame().getInputField().setText("");
        }
    }
}
