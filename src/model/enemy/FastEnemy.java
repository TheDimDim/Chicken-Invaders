package model.enemy;

import javax.swing.*;
import java.awt.*;

public class FastEnemy extends Enemy {

    public FastEnemy(int x, int y) {
        super(x, y, 1, 15);

        ImageIcon enemyIcon = new ImageIcon("C:/Users/Asus/Downloads/chicken-20260613T110124Z-3-001/chicken/fast_chicken.png");
        Image enemyImage = enemyIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);

        setIcon(new ImageIcon(enemyImage));
        setBounds(x, y, 70, 70);
    }

    @Override
    public void moveHorizontal(int direction) {
        super.moveHorizontal(direction*2);
    }
}