package model.enemy;

import javax.swing.*;
import java.awt.*;

public class ZigzagEnemy extends Enemy {

    //Fields
    private int baseY;
    private double angle = 0;
    private int speed = 3;

    //----------------------------------------------------------------

    //Constructor
    public ZigzagEnemy(int x, int y) {

        super(x, y, 2, 20);
        this.baseY = y;

        ImageIcon enemyIcon = new ImageIcon("visuals/zigzag_chicken.png");
        Image enemyImage = enemyIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        setIcon(new ImageIcon(enemyImage));
        setBounds(x, y, 60, 60);
    }
    //----------------------------------------------------------------

    @Override
    public void moveHorizontal(int dir) {

        x += dir * speed;

        angle += 0.1;

        y = baseY + (int)(Math.sin(angle) * 20);

        setBounds(x, (int)y, 60, 60);
    }

    @Override
    public void moveVertical(int amount) {

        baseY += amount;
        y += amount;

        setBounds(x, (int)y, 60, 60);
    }


}