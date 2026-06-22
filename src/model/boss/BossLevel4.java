package model.boss;

import javax.swing.*;
import java.awt.*;

public class BossLevel4 extends Boss {

    private int maxHealth;
    private int health;

    public BossLevel4(int x, int y, int health, int score) {
        super(x, y, health, score);

        this.maxHealth = health;
        this.health = health;

        ImageIcon icon = new ImageIcon(
                "C:\\Users\\Asus\\Downloads\\chicken-20260613T110124Z-3-001\\chicken\\boss1.png"
        );

        Image bossImage = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(bossImage));

        setBounds(x, y, 200, 200);
    }

    public void damageBoss() {
        health--;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public int getScore() {
        return super.getScore();
    }

}