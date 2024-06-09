package niss.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ServerFrame frame;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        frame = new ServerFrame(this);
        start();
    }

    public void start() throws IOException {
        clientSocket = serverSocket.accept();
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
        // 启动消息监听线程
        new ServerGetThread(in, frame).start();
    }

    public void sendMessage(Information info) throws IOException {
        out.writeObject(info);
    }

    public ServerFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        try {
            Server server = new Server(12345);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
