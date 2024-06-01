package niss.net;

import java.io.*;
import java.net.*;

public class SimpleServer {
	ServerSocket ss;
	Socket s;

	public SimpleServer() {
		try {
			ss = new ServerSocket(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SimpleServer(int port) { // port为端口号
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean judgeBouond() {
		return ss.isBound();
	}

	public void waitForConnect() { // 等待与客户端连接
		try {
			while (true) {
				s = ss.accept();
				return;
			}
		} catch (Exception e) {
			System.out.println("error");
		}
	}

	public void SendMessageToClient(String info) { // 向客户端发送消息
		try {
			PrintStream out = new PrintStream(s.getOutputStream());
			out.print(info + "\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String GetMessageFromClient() { // 从客户端接受消息
		String str = new String("");
		while (true) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				str = br.readLine();
				if (!str.equals(""))
					return str;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public void CloseSocket() {
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void CloseServer() {
		try {
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		SimpleServer Ser1 = new SimpleServer(5432);
		try {
			Ser1.waitForConnect();
			System.out.println(Ser1.GetMessageFromClient());
			Ser1.SendMessageToClient("hi");
			Ser1.CloseSocket();
			// Ser1.CloseServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
