package ui;

import game.GameMain;
import managers.DatabaseManager;
import managers.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {

    private GameMain gameMain;

    public MainMenu(GameMain gameMain) {

        this.gameMain = gameMain;
        setLayout(null);
        if (DatabaseManager.getBackgroundMusic() == 1) {

            SoundManager.playBackgroundMusic("C:\\Users\\Asus\\Downloads\\background.wav");
        }

        ImageIcon background = new ImageIcon("C:\\Users\\Asus\\Downloads\\background.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);

        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 800, 600);
        backgroundLabel.setLayout(null);
        add(backgroundLabel);

        JLabel titleLabel = new JLabel("Chicken Invaders", SwingConstants.CENTER);
        titleLabel.setBounds(175, 60, 450, 60);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 42));
        backgroundLabel.add(titleLabel);

        //----------------------------------------------------------------

        JButton highScoresButton = new JButton("High Scores");
        highScoresButton.setBounds(300, 220, 200, 40);
        backgroundLabel.add(highScoresButton);
        //----------------------------------------------------------------
        //Setting button

        JButton settingsButton = new JButton("Settings");
        settingsButton.setBounds(300, 280, 200, 40);
        backgroundLabel.add(settingsButton);

        settingsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                gameMain.showSettingsPanel();
            }
        });


        //----------------------------------------------------------------
        //How to play button
        JButton howToPlayButton = new JButton("How to Play");
        howToPlayButton.setBounds(300, 340, 200, 40);
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
                                "Esc: Back to Main Menu / End Game\n" +
                                "M: Open Sound Settings (Optional)");
            }
        });

        //----------------------------------------------------------------
//New game button
        JButton newGameButton = new JButton("New Game");
        newGameButton.setBounds(300, 160, 200, 40);
        backgroundLabel.add(newGameButton);

        newGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (DatabaseManager.getCurrentUsername() == null) {

                    gameMain.showLoginPanel();
                }

                else {

                    gameMain.startNewGame();
                }
            }
        });



        //----------------------------------------------------------------
        //Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(300, 400, 200, 40);
        backgroundLabel.add(exitButton);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


}