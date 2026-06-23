package model.boss;

import model.enemy.Enemy;

import javax.swing.*;
import java.awt.*;

public abstract class Boss extends Enemy {

    protected int direction = 1;
    protected int maxHealth;

    public Boss(int x, int y, int health, int score) {
        super(x, y, health, score);
        maxHealth = health;
    }
    public void moveBoss() {

        x += direction * 2;

        if (x <= 0 || x >= 640)
            direction *= -1;

        setBounds(x, (int)y, getWidth(), getHeight());
    }

    public void damageBoss() {
        damage();
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

}