package model;

import javax.swing.*;
import java.awt.*;

public class Plane extends JLabel {

    //Fields
    private int x;
    private int y;
    private int speed;

    //----------------------------------------------------------------

    //Constructor
    public Plane() {
        x = 355;
        y = 460;
        speed = 5;

        ImageIcon planeIcon = new ImageIcon("C:/Users/Asus/Downloads/airplan-20260613T102345Z-3-001/airplan/2.png");
        Image planeImage = planeIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);

        setIcon(new ImageIcon(planeImage));
        setBounds(x, y, 90, 90);
    }

    //----------------------------------------------------------------

    //Methods
    public void moveRight() {

        if (x < 710) {
            x += speed;
            setBounds(x, y, 90, 90);
        }
    }

    public void moveLeft() {

        if (x > 0) {
            x -= speed;
            setBounds(x, y, 90, 90);
        }
    }

    public void moveUp() {

        if (y > 0) {
            y -= speed;
            setBounds(x, y, 90, 90);
        }
    }

    public void moveDown() {

        if (y < 510) {
            y += speed;
            setBounds(x, y, 90, 90);
        }
    }


    public int getXPosition() {
        return x;
    }

    public int getYPosition() {
        return y;
    }

    public void resetPosition() {
        x = 355;
        y = 460;
        setBounds(x, y, 90, 90);
    }
}