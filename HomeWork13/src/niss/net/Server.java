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
	}

	public void start() throws IOException, ClassNotFoundException {
		clientSocket = serverSocket.accept();
		out = new ObjectOutputStream(clientSocket.getOutputStream());
		in = new ObjectInputStream(clientSocket.getInputStream());
		while (true) {
			Information info = (Information) in.readObject();
			frame.displayMessage(info);
		}
	}

	public void sendMessage(Information info) throws IOException {
		out.writeObject(info);
	}

	public static void main(String[] args) {
		try {
			Server server = new Server(12345);
			server.start();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
