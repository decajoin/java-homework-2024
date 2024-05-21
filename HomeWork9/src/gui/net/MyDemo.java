package gui.net;

import javax.swing.JFrame;

public class MyDemo {
	public static void main(String[] args) {
		JFrame frame = new MyFrame("Java GUI Demo");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(400, 200);

		frame.setVisible(true);
	}
}
