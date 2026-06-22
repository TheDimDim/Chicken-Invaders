package model.boss;

import javax.swing.*;

public class BossLevel8 extends Boss {

    public BossLevel8(int x, int y, int health, int score) {
        super(x, y, health, score);

        ImageIcon icon = new ImageIcon("C:\\Users\\Asus\\Downloads\\chicken-20260613T110124Z-3-001\\chicken\\boss2.png");
        setIcon(icon);

        setBounds(x, y, 180, 180);
    }
}