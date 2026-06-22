package model.enemy;
import model.Plane;

import javax.swing.*;

public abstract class Enemy extends JLabel {

    //Fields
    protected int x;
    protected float y;
    protected int health;
    protected int score;

    //----------------------------------------------------------------

    //Constructor
    public Enemy(int x, float y, int health ,int score) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.score = score;
    }

    //----------------------------------------------------------------

    //Methods
    public void damage() {
        health--;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public int getScore() {
        return score;
    }

    public void moveDown() {
        y += 0.2;
        setBounds(x, (int)y, 60, 60);
    }

    public boolean isOutOfScreen() {
        return y > 540;
    }

    public boolean hitEdge() {
        return x <= 0 || x >= 740;
    }
    public boolean hitPlane(Plane plane) {
        if (getBounds().intersects(plane.getBounds())) {
            return true;
        }
        return false;
    }

    public int getXPosition() {
        return x;
    }

    public int getYPosition() {
        return (int)y;
    }


    public void moveHorizontal(int direction) {
        x += direction;
        setBounds(x, (int)y, 60, 60);
    }

    public void moveVertical(int amount) {
        y += amount;
        setBounds(x, (int)y, 60, 60);
    }

}