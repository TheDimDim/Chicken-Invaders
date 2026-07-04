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

    //Power up icon
    private ImageIcon getPowerUpIcon() {

        String path = "";

        if (type.equals("RAPID_FIRE")) {

            path = "visuals/rapid_fire.png";
        }

        else if (type.equals("FREEZE")) {

            path = "visuals/freeze.png";
        }

        else if (type.equals("EXTRA_LIFE")) {

            path = "visuals/extra_life.png";
        }

        else if (type.equals("SHIELD")) {

            path = "visuals/shield.png";
        }

        else if (type.equals("ADD_FIRE")) {

            path = "visuals/add_fire.png";
        }

        ImageIcon icon = new ImageIcon(path);

        Image image = icon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);

        return new ImageIcon(image);
    }

    //----------------------------------------------------------------

    //Move
    public void moveDown() {

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