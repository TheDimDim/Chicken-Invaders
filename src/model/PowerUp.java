package model;

import javax.swing.*;
import java.awt.*;

public class PowerUp extends JLabel {

    //Fields
    private int x;
    private int y;
    private String type;
    private int speed;

    //----------------------------------------------------------------

    //Constructor
    public PowerUp(int x, int y, String type) {

        this.x = x;
        this.y = y;
        this.type = type;
        speed = 2;

        setIcon(getPowerUpIcon());

        setBounds(x, y, 45, 45);
    }

    //----------------------------------------------------------------

    //Get power up icon
    private ImageIcon getPowerUpIcon() {

        String path = "";

        if (type.equals("RAPID_FIRE")) {

            path = "C:\\Users\\Asus\\Downloads\\powerup2-20260626T142950Z-3-001\\powerup2\\fast_shot.png";
        }

        else if (type.equals("FREEZE")) {

            path = "C:\\Users\\Asus\\Downloads\\powerup2-20260626T142950Z-3-001\\powerup2\\freeze.png";
        }

        else if (type.equals("EXTRA_LIFE")) {

            path = "C:\\Users\\Asus\\Downloads\\powerup2-20260626T142950Z-3-001\\powerup2\\heal.png";
        }

        else if (type.equals("SHIELD")) {

            path = "C:\\Users\\Asus\\Downloads\\powerup2-20260626T142950Z-3-001\\powerup2\\sheild.png";
        }

        else if (type.equals("ADD_FIRE")) {

            path = "C:\\Users\\Asus\\Downloads\\powerup2-20260626T142950Z-3-001\\powerup2\\add_shot.png";
        }

        else {

            path = "C:\\Users\\Asus\\Downloads\\powerup2-20260626T142950Z-3-001\\powerup2\\bomb.png";
        }

        ImageIcon icon = new ImageIcon(path);

        Image image = icon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);

        return new ImageIcon(image);
    }

    //----------------------------------------------------------------

    //Move
    public void movement() {

        y += speed;

        setBounds(x, y, 45, 45);
    }

    //----------------------------------------------------------------

    //Hit plane
    public boolean hitPlane(Plane plane) {

        return getBounds().intersects(plane.getBounds());
    }

    //----------------------------------------------------------------

    //Get type
    public String getType() {

        return type;
    }
    //----------------------------------------------------------------
    //Is out of screen
    public boolean isOutOfScreen() {

        return y > 600;
    }
}