package model.enemy;
import javax.swing.*;

public class Enemy extends JLabel {

    //Fields
    protected int x;
    protected int y;
    protected int health;

    //Constructor
    public Enemy(int x, int y, int health) {
        this.x = x;
        this.y = y;
        this.health = health;
    }
    //Methods
    public void damage() {
        health--;
    }

    public boolean isDead() {
        return health <= 0;
    }
}