package model;

import javax.swing.*;
import java.awt.*;

public class Plane extends JLabel {

    private int x;
    private int y;
    private int speed;

    public Plane() {
        x = 355;
        y = 460;
        speed = 5;

        ImageIcon planeIcon = new ImageIcon("C:/Users/Asus/Downloads/airplan-20260613T102345Z-3-001/airplan/2.png");
        Image planeImage = planeIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);

        setIcon(new ImageIcon(planeImage));
        setBounds(x, y, 90, 90);
    }
    //Movements

    public void moveRight() {
        x += speed;
        setBounds(x, y, 90, 90);
    }

    public void moveLeft() {
        x -= speed;
        setBounds(x, y, 90, 90);
    }

    public void moveUp() {
        y -= speed;
        setBounds(x, y, 90, 90);
    }

    public void moveDown() {
        y += speed;
        setBounds(x, y, 90, 90);
    }
}