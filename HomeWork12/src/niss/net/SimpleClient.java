package niss.net;

import java.io.*;
import java.net.*;

public class SimpleClient {
	Socket s;
	BufferedReader br;
	PrintStream ps;

	public SimpleClient(String host, int port) {
		try {
			s = new Socket(host, port);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			ps = new PrintStream(s.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void SendMessageToServer(String info) {
		try {
			ps.print(info + "\r\n");
			ps.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String GetMessageFromServer() {
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	public void CloseClient() {
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SimpleClient cli = new SimpleClient("localhost", 5432); // Connect to the server at localhost and port 5432
		cli.SendMessageToServer("Hello, Server!");
		System.out.println(cli.GetMessageFromServer());
		cli.CloseClient();
	}
}
