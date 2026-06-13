package ui;

import game.GameMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {

    private GameMain gameMain;

    public MainMenu(GameMain gameMain) {
        this.gameMain = gameMain;
        setLayout(null);

        ImageIcon background = new ImageIcon("C:\\Users\\Asus\\Downloads\\background.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(900, 700, Image.SCALE_SMOOTH);

        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 900, 700);
        backgroundLabel.setLayout(null);
        add(backgroundLabel);

        JLabel titleLabel = new JLabel("Chicken Invaders", SwingConstants.CENTER);
        titleLabel.setBounds(225, 70, 450, 50);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        backgroundLabel.add(titleLabel);

        JButton newGameButton = new JButton("New Game");
        newGameButton.setBounds(350, 180, 200, 40);
        backgroundLabel.add(newGameButton);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameMain.startNewGame();
            }
        });

        JButton highScoresButton = new JButton("High Scores");
        highScoresButton.setBounds(350, 240, 200, 40);
        backgroundLabel.add(highScoresButton);

        JButton settingsButton = new JButton("Settings");
        settingsButton.setBounds(350, 300, 200, 40);
        backgroundLabel.add(settingsButton);

        //How to play button
        JButton howToPlayButton = new JButton("How to Play");
        howToPlayButton.setBounds(350, 360, 200, 40);
        backgroundLabel.add(howToPlayButton);

        howToPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog( null,
                        "Controls:\n" +
                                "Right Arrow / D: Move Right\n" +
                                "Left Arrow / A: Move Left\n" +
                                "Up Arrow / W: Move Up\n" +
                                "Down Arrow / S: Move Down\n" +
                                "Space: Shoot\n" +
                                "P: Pause / Resume\n" +
                                "Esc: Back to Main Menu / End Game\n");
            }
        });


        //Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(350, 420, 200, 40);
        backgroundLabel.add(exitButton);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}