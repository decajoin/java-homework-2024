package gui.net;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

// 自定义彩色标签控件
public class ColorLabel extends JLabel{
	
	public ColorLabel(String text, Color bgColor) {
		super(text);

		this.setOpaque(true);
		this.setBackground(bgColor);
		this.setPreferredSize(new Dimension(60, 30));
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}
}
