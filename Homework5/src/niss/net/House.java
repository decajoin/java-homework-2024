package niss.net;
import java.awt.*;

public class House
{
    private Point left;
    private Point right;
    private Point top;
    private int height;//正屋高度
    private Door[] doors = new Door[100];
    private Window[] windows = new Window[100];

    public House()
    {
    }

    public House(Point left, Point right, Point top, int height, Door[] doors, Window[] windows)
    {
        this.left = left;
        this.right = right;
        this.top = top;
        this.height = height;
        this.doors = doors;
        this.windows = windows;
    }

    public Point getLeft()
    {
        return left;
    }

    public void setLeft(Point left)
    {
        this.left = left;
    }

    public Point getRight()
    {
        return right;
    }

    public void setRight(Point right)
    {
        this.right = right;
    }

    public Point getTop()
    {
        return top;
    }

    public void setTop(Point top)
    {
        this.top = top;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public Door[] getDoors()
    {
        return doors;
    }

    public void setDoors(Door[] doors)
    {
        this.doors = doors;
    }

    public Window[] getWindows()
    {
        return windows;
    }

    public void setWindows(Window[] windows)
    {
        this.windows = windows;
    }


    public void drawHouse(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawLine(getLeft().getX(), getLeft().getY(), getRight().getX(), getRight().getY());
        g.drawLine(getLeft().getX(), getLeft().getY(), getTop().getX(), getTop().getY());
        g.drawLine(getTop().getX(), getTop().getY(), getRight().getX(), getRight().getY());
        g.drawLine(getLeft().getX(), getLeft().getY()+getHeight(), getRight().getX(), getRight().getY()+getHeight());
        g.drawLine(getLeft().getX(), getLeft().getY(), getLeft().getX(), getLeft().getY()+getHeight());
        g.drawLine(getRight().getX(), getRight().getY()+getHeight(), getRight().getX(), getRight().getY());
    }
}
