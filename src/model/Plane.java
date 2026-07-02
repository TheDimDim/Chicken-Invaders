package model;

import managers.DatabaseManager;

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

        String selectedPlane = DatabaseManager.getSelectedPlane();

        String planePath = "C:\\Users\\Asus\\Downloads\\airplan-20260613T102345Z-3-001\\airplan\\2.png";

        if (selectedPlane.equals("Fast")) {

            speed = 7;
            planePath = "C:\\Users\\Asus\\Downloads\\airplan-20260613T102345Z-3-001\\airplan\\4.png";
        }

        else if (selectedPlane.equals("Heavy")) {

            speed = 4;
            planePath = "C:\\Users\\Asus\\Downloads\\airplan-20260613T102345Z-3-001\\airplan\\6.png";
        }

        else if (selectedPlane.equals("Sniper")) {

            speed = 5;
            planePath = "C:\\Users\\Asus\\Downloads\\airplan-20260613T102345Z-3-001\\airplan\\5.png";
        }

        else {

            speed = 5;
            planePath = "C:\\Users\\Asus\\Downloads\\airplan-20260613T102345Z-3-001\\airplan\\2.png";
        }

        ImageIcon planeIcon = new ImageIcon(planePath);
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