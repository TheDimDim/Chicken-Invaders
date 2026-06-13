package game;

import model.Plane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {

    private GameMain gameMain;
    private Plane plane;

    public GamePanel(GameMain gameMain) {
        this.gameMain = gameMain;

        setLayout(null);

        ImageIcon background = new ImageIcon("C:\\Users\\Asus\\Downloads\\background.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);

        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 800, 600);
        backgroundLabel.setLayout(null);
        add(backgroundLabel);

        JLabel label = new JLabel("Game Panel");
        label.setForeground(Color.WHITE);
        label.setBounds(250, 220, 300, 50);
        backgroundLabel.add(label);

        plane = new Plane();
        backgroundLabel.add(plane);

        //Back to menu button
        JButton backButton = new JButton("Back to Menu");
        backButton.setBounds(200, 0, 200, 40);
        backgroundLabel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameMain.showMainMenu();
            }
        });

        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            plane.moveRight();
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            plane.moveLeft();
        }

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            plane.moveUp();
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            plane.moveDown();
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gameMain.showMainMenu();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}