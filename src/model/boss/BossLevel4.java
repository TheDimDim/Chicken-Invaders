package model.boss;

import javax.swing.*;

public class BossLevel4 extends Boss {

    public BossLevel4(int x, int y, int health, int score) {
        super(x, y, health, score);

        ImageIcon icon = new ImageIcon("C:\\Users\\Asus\\Downloads\\chicken-20260613T110124Z-3-001\\chicken\\boss1.png");
        setIcon(icon);

        setBounds(x, y, 160, 160);
    }
}