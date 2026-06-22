package model.boss;

import model.enemy.Enemy;

import javax.swing.*;

public abstract class Boss extends Enemy {

    protected int direction = 1;

    public Boss(int x, int y, int health, int score) {
        super(x, y, health, score);
    }

    //MOVE (shared logic)
    public void moveBoss() {

        x += direction * 3;

        if (x <= 0 || x >= 640)
            direction *= -1;

        setBounds(x, (int)y, 250, 250);
    }

    //DAMAGE
    public void damageBoss() {
        damage();
    }
}