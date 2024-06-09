package niss.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ClientFrame frame;

    public Client(String serverAddress, int port) throws IOException {
        socket = new Socket(serverAddress, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        frame = new ClientFrame(this);
        // 启动消息监听线程
        new ClientGetThread(in, frame).start();
    }

    public void sendMessage(Information info) throws IOException {
        out.writeObject(info);
    }

    public ClientFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        try {
            Client client = new Client("localhost", 12345);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
