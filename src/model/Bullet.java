package model;

import javax.swing.*;
import java.awt.*;

public class Bullet extends JLabel {

    //Fields
    private int x;
    private int y;
    private int speed;

    //Constructor
    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 10;

        ImageIcon bulletIcon = new ImageIcon("C:/Users/Asus/Downloads/airplan-20260613T102345Z-3-001/airplan/shot.png");
        Image bulletImage = bulletIcon.getImage().getScaledInstance(20, 50, Image.SCALE_SMOOTH);

        setIcon(new ImageIcon(bulletImage));
        setBounds(this.x, this.y, 20, 50);
    }

    //Methods
    public void moveUp() {
        y -= speed;
        setBounds(x, y, 20, 50);
    }

    public int getYPosition() {
        return y;
    }
}