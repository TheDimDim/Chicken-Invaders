package model;

import javax.swing.*;
        import java.awt.*;

public class Egg extends JLabel {

    //Fields
    private int x;
    private int y;
    private int xDirection;
    private int yDirection;
    private int speed;

    //----------------------------------------------------------------
    //Constructor

    //Enemy
    public Egg(int x, int y) {
        this.x = x;
        this.y = y;
        this.xDirection = 0;
        this.yDirection = 1;
        this.speed = 5;

        ImageIcon eggIcon = new ImageIcon("C:\\Users\\Asus\\Downloads\\chicken-20260613T110124Z-3-001\\chicken\\egg.png");
        Image eggImage = eggIcon.getImage().getScaledInstance(40, 50, Image.SCALE_SMOOTH);

        setIcon(new ImageIcon(eggImage));
        setBounds(this.x, this.y, 40, 50);
    }

    //Boss
    public Egg(int x, int y, int xDirection, int yDirection) {
        this.x = x;
        this.y = y;
        this.xDirection = xDirection;
        this.yDirection = yDirection;
        this.speed = 5;

        ImageIcon eggIcon = new ImageIcon("C:\\Users\\Asus\\Downloads\\chicken-20260613T110124Z-3-001\\chicken\\egg.png");
        Image eggImage = eggIcon.getImage().getScaledInstance(40, 50, Image.SCALE_SMOOTH);

        setIcon(new ImageIcon(eggImage));
        setBounds(this.x, this.y, 40, 50);
    }

    //----------------------------------------------------------------

    //Methods
    public void movement() {

        x += xDirection * speed;
        y += yDirection * speed;

        setBounds(x, y, 40, 50);
    }

    public int getY() {
        return y;
    }

    public boolean isOutOfScreen() {

        return x < -50 || x > 850 || y < -50 || y > 650;
    }

    public boolean hitPlane(Plane plane) {

        return getBounds().intersects(plane.getBounds());
    }
}