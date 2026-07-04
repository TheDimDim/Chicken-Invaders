package model.boss;

import model.enemy.Enemy;

import javax.swing.*;
import java.awt.*;

public abstract class Boss extends Enemy {

    //Fields

    protected int direction = 1;
    protected int maxHealth;

    //----------------------------------------------------------------

    //Constructor

    public Boss(int x, int y, int health, int score) {
        super(x, y, health, score);
        maxHealth = health;
    }

    //----------------------------------------------------------------
    //Methods

    public void moveBoss() {

        x += direction * 2;

        if (x <= 0 || x + getWidth() >= 800) {

            direction *= -1;
        }

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