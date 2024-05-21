package gui.net;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

public class SimpleLayout extends LayoutAdapter{

	List<Component> list = new ArrayList<Component>();
	
	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		// 重要的方法1 --- 在add时调用
		System.out.println("调用了addLayoutComponent");
		list.add(comp);
	}
	
	@Override
	public void removeLayoutComponent(Component comp) {
		// 重要的方法2 --- 在remove时删除
		System.out.println("调用了removeLayoutComponent");
		list.remove(comp);
	}

	@Override
	public void layoutContainer(Container parent) {
		// 重要的方法3 --- 在add完成后或者页面发生变化时调用
		System.out.println("调用了layoutContainer");
		
		// 容器的宽度
		int width = parent.getWidth();
		// 容器的高度
		int height = parent.getHeight();
		
		
		// 对每个子控件进行布局 。本例中，实现网格化的布局显示
		int x = 0;
		int y = 0;
		for( int i = 0; i<list.size(); i++) {
			Component c = list.get(i);
			c.setBounds(x, y, 100, 100);
					
			x += 100;
			if( x > width) {
				x = 0;
				y += 100;
			}
		}
		
	}


}
