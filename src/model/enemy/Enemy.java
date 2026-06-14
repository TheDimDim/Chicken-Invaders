package model.enemy;
import javax.swing.*;

public class Enemy extends JLabel {

    //Fields
    protected int x;
    protected int y;
    protected int health;
    protected int score;



    //Constructor
    public Enemy(int x, int y, int health ,int score) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.score = score;
    }
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


}