package model;

import javax.swing.*;
import java.awt.*;

public class Plane extends JLabel {

    public Plane() {
        ImageIcon planeIcon = new ImageIcon("C:/Users/Asus/Downloads/airplan-20260613T102345Z-3-001/airplan/2.png");
        Image planeImage = planeIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);

        setIcon(new ImageIcon(planeImage));
        setBounds(355, 460, 90, 90);
    }
}