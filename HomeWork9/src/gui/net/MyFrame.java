package gui.net;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame {
	
	public MyFrame(String frameName) {

		super(frameName);
 
		// 创建容器(根容器)
		JPanel rootPanel = new JPanel();
		this.setContentPane(rootPanel);

		MyControl c = new MyControl();
		rootPanel.add(c);
		c.setPreferredSize(new Dimension(400, 300));

	}
}
