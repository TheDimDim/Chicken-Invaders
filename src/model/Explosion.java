package model;

import javax.swing.*;
import java.awt.*;

public class Explosion extends JLabel {

    //Constructor
    public Explosion(int x, int y) {

        ImageIcon explosionIcon = new ImageIcon("visuals/explosion.png");
        Image explosionImage = explosionIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);

        setIcon(new ImageIcon(explosionImage));

        setBounds(x - 15, y - 15, 80, 80);
    }
}