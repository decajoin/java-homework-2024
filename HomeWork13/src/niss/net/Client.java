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
	}

	public void sendMessage(Information info) throws IOException {
		out.writeObject(info);
	}

	// 相当于一个监听程序，while(true)一直监听有没有收到信息
	public void receiveMessage() throws IOException, ClassNotFoundException {
		while (true) {
			Information info = (Information) in.readObject();
			frame.displayMessage(info);
		}
	}

	public static void main(String[] args) {
		try {
			Client client = new Client("localhost", 12345);
			client.receiveMessage();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
