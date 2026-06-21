package model.enemy;

import model.Bullet;

import javax.swing.*;
import java.awt.*;

public class ShooterEnemy extends Enemy {

    private long lastShootTime;

    public ShooterEnemy(int x, int y) {

        super(x, y, 2, 25);

        lastShootTime = 0;

        ImageIcon enemyIcon = new ImageIcon("C:/Users/Asus/Downloads/chicken-20260613T110124Z-3-001/chicken/shooter_chicken.png");
        Image enemyImage = enemyIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        setIcon(new ImageIcon(enemyImage));
        setBounds(x, y, 60, 60);
    }

    public Bullet shoot() {

        long now = System.currentTimeMillis();

        if (now - lastShootTime > 2000) {

            lastShootTime = now;
            return new Bullet(this.getX(), this.getY());
        }

        return null;
    }
}