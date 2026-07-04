package model.enemy;

import model.Bullet;

import javax.swing.*;
import java.awt.*;

public class ShooterEnemy extends Enemy {

    private long lastShootTime;

    public ShooterEnemy(int x, int y) {

        super(x, y, 2, 25);

        lastShootTime = 0;

        ImageIcon enemyIcon = new ImageIcon("visuals/shooter_chicken.png");
        Image enemyImage = enemyIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        setIcon(new ImageIcon(enemyImage));
        setBounds(x, y, 60, 60);
    }

    public Bullet shoot() {

        long now = System.currentTimeMillis();

        if (now - lastShootTime > 2000) {

            lastShootTime = now;

            int startX = getXPosition() + 30;
            int startY = getYPosition() + 30;

            int randomDirection = (int)(Math.random() * 100);

            if (randomDirection < 70) {

                return new Bullet(startX, startY, 0, 6);
            }

            else if (randomDirection < 85) {

                return new Bullet(startX, startY, -6, 0);
            }

            else {

                return new Bullet(startX, startY, 6, 0);
            }
        }

        return null;
    }
}