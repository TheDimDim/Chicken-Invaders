package game;

import model.Plane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {

    private GameMain gameMain;

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

        Plane plane = new Plane();
        backgroundLabel.add(plane);

        //Back to menu button
        JButton backButton = new JButton("Back to Menu");
        backButton.setBounds(300, 300, 200, 40);
        backgroundLabel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameMain.showMainMenu();
            }
        });
    }
}