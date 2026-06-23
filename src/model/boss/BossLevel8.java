package model.boss;

import javax.swing.*;
import java.awt.*;

public class BossLevel8 extends Boss {

    //Fields
    private int maxHealth;
    private int health;

    //----------------------------------------------------------------

    //Constructor
    public BossLevel8(int x, int y, int health, int score) {
        super(x, y, health, score);

        this.maxHealth = health;
        this.health = health;

        ImageIcon icon = new ImageIcon("C:\\Users\\Asus\\Downloads\\chicken-20260613T110124Z-3-001\\chicken\\boss2.png");

        Image bossImage = icon.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(bossImage));

        setBounds(x, y, 220, 220);
    }

    //----------------------------------------------------------------

    //Methods
    @Override
    public void damageBoss() {
        health--;
        repaint();
    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }

    public int getScore() {
        return super.getScore();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        drawHealthBar(g);
    }

    private void drawHealthBar(Graphics g) {

        int barWidth = 180;
        int barHeight = 10;

        int barX = 20;
        int barY = 5;

        g.setColor(Color.RED);
        g.fillRect(barX, barY, barWidth, barHeight);

        int greenWidth = (int) ((health / (double) maxHealth) * barWidth);

        g.setColor(Color.GREEN);
        g.fillRect(barX, barY, greenWidth, barHeight);

        g.setColor(Color.WHITE);
        g.drawRect(barX, barY, barWidth, barHeight);
    }
}