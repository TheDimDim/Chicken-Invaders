package model;

import javax.swing.*;
import java.awt.*;

public class Explosion extends JLabel {

    //Constructor
    public Explosion(int x, int y) {

        ImageIcon explosionIcon = new ImageIcon("C:\\Users\\Asus\\Downloads\\airplan-20260613T102345Z-3-001\\airplan\\Explosion.png");
        Image explosionImage = explosionIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);

        setIcon(new ImageIcon(explosionImage));
        setBounds(x, y, 70, 70);
    }
}