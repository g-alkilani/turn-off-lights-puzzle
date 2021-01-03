package puzzle;

import javax.swing.*;
import java.awt.*;

public class MyCircle extends JComponent {

    private final boolean on;
    private final int xCoordinate;
    private final int yCoordinate;
    private final int diameter;

    public MyCircle(boolean on, int xCoordinate, int yCoordinate, int diameter) {
        this.on = on;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.diameter = diameter;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (on) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.GRAY);
        }
        g.fillOval(0, 0, diameter, diameter);
    }

}
