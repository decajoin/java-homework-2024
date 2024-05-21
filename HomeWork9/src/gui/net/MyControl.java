package gui.net;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyControl extends JPanel{
	
	public int grain = 2;  // 线条的精细度 ( 粒度 )
	public int radius = 50; // 高度 ( 振幅 )
	public int period = 100; // X轴, 每100像素表示一个周期(2PI)
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		// 得到当前这个控件的宽度、高度
		int width = this.getWidth();
		int height = this.getHeight();

		// 底色设为白色
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, width, height);

		// 中线
		int center = height/2;
		g.setColor(Color.blue);
		g.drawLine(0, center, width, center);
				
		// 正弦曲线 （ 掌握思想即可:由多个小段连接而成，近似为一条曲线 )
		int x1 = 0;
		int y1 = 0;
		for(int i=0; i<width; i+= grain) {
			// 参考本节课下的图文教程，有详细讲解
			int x2 = i;
			// 把横坐标x换算成角度 （ 弧度值） 
			double angle = 2 * Math.PI * x2 / period;
			// 计算得到 y 坐标
			int y2 = (int) (radius * Math.sin( angle ));			
					
			g.drawLine(x1, center+y1, x2, center+y2);
					
			x1 = x2;
			y1 = y2;
		}
	}
}
