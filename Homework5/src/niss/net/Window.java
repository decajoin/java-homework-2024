package niss.net;
import java.awt.*;

public class Window extends Point
{
    private Point left;
    private int width;
    private int height;
    private int width_n;
    private int height_n;

    public Window()
    {
    }

    public Window(Point left, int width, int height, int width_n, int height_n)
    {
        this.left = left;
        this.width = width;
        this.height = height;
        this.width_n = width_n;
        this.height_n = height_n;
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

    public int getWidth_n()
    {
        return width_n;
    }

    public void setWidth_n(int width_n)
    {
        this.width_n = width_n;
    }

    public int getHeight_n()
    {
        return height_n;
    }

    public void setHeight_n(int height_n)
    {
        this.height_n = height_n;
    }

    public void drawWindow(Graphics g)
    {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(getLeft().getX(), getLeft().getY(), getWidth(), getHeight());
    }

    public void drawLines(Graphics g)
    {
        int x = getWidth()/getWidth_n();
        int y = getHeight()/getHeight_n();
        for (int i = 1; i < getWidth_n(); i++)
        {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(getLeft().getX()+x*i, getLeft().getY(), getLeft().getX()+x*i, getLeft().getY()+getHeight());
        }
        for (int j = 1; j < getHeight_n(); j++)
        {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(getLeft().getX(), getLeft().getY()+y*j, getLeft().getX()+getWidth(), getLeft().getY()+y*j);
        }
    }
}
