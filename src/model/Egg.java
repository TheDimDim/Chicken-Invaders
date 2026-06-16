package model;

import javax.swing.*;
import java.awt.*;

public class Egg extends JLabel {

    private int x;
    private int y;
    private int speed;

    public Egg(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 5;

        ImageIcon eggIcon = new ImageIcon("C:\\Users\\Asus\\Downloads\\chicken-20260613T110124Z-3-001\\chicken\\egg.png");
        Image eggImage = eggIcon.getImage().getScaledInstance(40, 50, Image.SCALE_SMOOTH);

        setIcon(new ImageIcon(eggImage));
        setBounds(this.x, this.y, 40, 50);
    }

    public void movement() {
        y += speed;
        setBounds(x, y, 40, 50);
    }

    public int getY() {
        return y;
    }

    public boolean hitPlane(Plane plane) {
        return getBounds().intersects(plane.getBounds());
    }
}