package model;

import model.enemy.Enemy;

import javax.swing.*;
import java.awt.*;

public class Bullet extends JLabel {

    //Fields
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int width;
    private int height;

    //----------------------------------------------------------------

    //Constructor(PLANE)
    public Bullet(int x, int y) {

        this.x = x;
        this.y = y;

        this.dx = 0;
        this.dy = -30;

        this.width = 20;
        this.height = 50;

        ImageIcon bulletIcon = new ImageIcon("visuals/shot.png");
        Image bulletImage = bulletIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        setIcon(new ImageIcon(bulletImage));
        setBounds(x, y, width, height);
    }

    //----------------------------------------------------------------

    //Constructor(ENEMY)
    public Bullet(int x, int y, int dx, int dy) {

        this.x = x;
        this.y = y;

        this.dx = dx;
        this.dy = dy;

        this.width = 25;
        this.height = 25;

        ImageIcon bulletIcon = new ImageIcon("visuals/shot.png");
        Image bulletImage = bulletIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        setIcon(new ImageIcon(bulletImage));
        setBounds(x, y, width, height);
    }

    //----------------------------------------------------------------

    //Methods
    public void move() {

        x += dx;
        y += dy;

        setBounds(x, y, width, height);
    }

    public void moveUp() {

        move();
    }

    public void moveDown() {

        y += 6;

        setBounds(x, y, width, height);
    }

    public boolean hitPlane(Plane plane) {

        return getBounds().intersects(plane.getBounds());
    }

    public boolean hitEnemy(Enemy enemy) {

        return getBounds().intersects(enemy.getBounds());
    }

    public boolean isOutOfScreen() {

        return x < -50 || x > 850 || y < -50 || y > 650;
    }

    public int getYPosition() {

        return y;
    }
}