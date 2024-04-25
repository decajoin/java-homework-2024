package niss.net;
import java.awt.*;

public class Door extends Point
{
    private Point left;//左上角的位置（点）
    private int width;
    private int height;
    private Circle handle;//圆形把手（圆）

    public Door()
    {
    }

    public Door(Point left, int width, int height)
    {
        this.left = left;
        this.width = width;
        this.height = height;
    }

    public Point getLeft()
    {
        return left;
    }

    public void setLeft(Point left)
    {
        this.left = left;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public Circle getHandle()
    {
        return handle;
    }

    public void setHandle(Circle handle)
    {
        this.handle = handle;
    }

    public void drawDoor(Graphics g)
    {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(getLeft().getX(), getLeft().getY(), getWidth(), getHeight());
    }
}
