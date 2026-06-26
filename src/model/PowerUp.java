package model;

import javax.swing.*;
import java.awt.*;

public class PowerUp extends JLabel {

    //Fields
    private int x;
    private int y;
    private int speed;
    private String type;

    //----------------------------------------------------------------

    //Constructor
    public PowerUp(int x, int y, String type) {

        this.x = x;
        this.y = y;
        this.type = type;
        this.speed = 2;

        if (type.equals("RAPID_FIRE")) {

            setText("R");
        }

        else if (type.equals("FREEZE")) {

            setText("F");
        }

        else if (type.equals("EXTRA_LIFE")) {

            setText("L");
        }

        else if (type.equals("SHIELD")) {

            setText("S");
        }

        else {

            setText("+");
        }

        setHorizontalAlignment(SwingConstants.CENTER);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 22));
        setOpaque(true);
        setBackground(Color.MAGENTA);

        setBounds(this.x, this.y, 35, 35);
    }

    //----------------------------------------------------------------

    //Methods
    public void movement() {

        y += speed;
        setBounds(x, y, 35, 35);
    }

    public boolean hitPlane(Plane plane) {

        return getBounds().intersects(plane.getBounds());
    }

    public boolean isOutOfScreen() {

        return y > 600;
    }

    public String getType() {

        return type;
    }
}