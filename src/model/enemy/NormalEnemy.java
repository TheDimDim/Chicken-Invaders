package model.enemy;

import javax.swing.*;
import java.awt.*;

public class NormalEnemy extends Enemy {

    public NormalEnemy(int x, int y) {
        super(x, y, 2 , 10);

        ImageIcon enemyIcon = new ImageIcon("visuals/normal_chicken.png");
        Image enemyImage = enemyIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        setIcon(new ImageIcon(enemyImage));
        setBounds(x, y, 60, 60);
    }

}