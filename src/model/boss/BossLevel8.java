package model.boss;

import javax.swing.*;
import java.awt.*;

public class BossLevel8 extends Boss {

    //Constructor
    public BossLevel8(int x, int y, int health, int score) {
        super(x, y, health, score);

        ImageIcon bossIcon = new ImageIcon("visuals/boss2.png");

        Image bossImage = bossIcon.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(bossImage));

        setBounds(x, y, 220, 220);
    }

    //----------------------------------------------------------------

    //Methods

    @Override
    public void damageBoss() {

        super.damageBoss();
        repaint();
    }

    @Override
    public boolean isDead() {
        return health <= 0;
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