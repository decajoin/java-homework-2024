package niss.net;
import java.awt.*;

public class BuildHouse extends Panel
{
    public void paint(Graphics g)
    {
        Point p_left = new Point(10, 50);
        Point p_right = new Point(200, 50);
        Point p_top = new Point(105, 0);

        Door[] doors = new Door[2];//对象数组
        Point p1 = new Point(50, 70);
        Point p2 = new Point(140, 70);
        doors[0] = new Door(p1, 20, 30);
        doors[1] = new Door(p2, 20, 30);

        Window[] windows = new Window[4];
        Point q1 = new Point(20, 70);
        Point q2 = new Point(80, 70);
        Point q3 = new Point(110, 70);
        Point q4 = new Point(170, 70);
        windows[0] = new Window(q1, 20, 20, 2, 2);
        windows[1] = new Window(q2, 20, 20, 2, 2);
        windows[2] = new Window(q3, 20, 20, 2, 2);
        windows[3] = new Window(q4, 20, 20, 2, 2);

        doors[0].drawDoor(g);
        doors[1].drawDoor(g);
        for (int i = 0; i < 4; i++)
        {
            windows[i].drawWindow(g);
            windows[i].drawLines(g);
        }

        Circle c = new Circle(3);
        c.drawCircle1(g);
        c.drawCircle2(g);

        House house = new House(p_left, p_right, p_top, 50, doors, windows);
        house.drawHouse(g);
    }

    public static void main(String[] args)
    {
    	// 画房子
        Frame jp1 = new Frame("house");
        BuildHouse draw = new BuildHouse();
        jp1.add(draw, "Center");
        jp1.setSize(new Dimension(220, 150));
        jp1.setVisible(true);
    }
}
