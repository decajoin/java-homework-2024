package niss.net;
import java.awt.*;

public class Circle
{
    private int r;

    public Circle()
    {
    }

    public Circle(int r)
    {
        this.r = r;
    }

    public int getR()
    {
        return r;
    }

    public void setR(int r)
    {
        this.r = r;
    }

    public void drawCircle1(Graphics g)
    {
        // r=3
        g.setColor(Color.DARK_GRAY);
        g.fillArc(64, 84, 2*getR(), 2*getR(), 0, 360);
    }

    public void drawCircle2(Graphics g)
    {
        // r=3
        g.setColor(Color.DARK_GRAY);
        g.fillArc(154, 84, 2*getR(), 2*getR(), 0, 360);
    }
}
