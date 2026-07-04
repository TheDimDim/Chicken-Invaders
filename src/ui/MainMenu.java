package ui;

import game.GameMain;
import managers.DatabaseManager;
import managers.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {

    //Fields
    private GameMain gameMain;

    //----------------------------------------------------------------

    //Constructor
    public MainMenu(GameMain gameMain) {

        this.gameMain = gameMain;
        setLayout(null);

        //Background music
        if (DatabaseManager.getBackgroundMusic() == 1) {

            SoundManager.playBackgroundMusic("visuals/background_music.wav");
        }

        ImageIcon background = new ImageIcon("visuals/background.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);

        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 800, 600);
        backgroundLabel.setLayout(null);
        add(backgroundLabel);

        //Game title
        JLabel titleLabel = new JLabel("CHICKEN INVADERS");
        titleLabel.setForeground(new Color(220, 225, 255));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 60));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(100, 35, 600, 80);
        backgroundLabel.add(titleLabel);

        //----------------------------------------------------------------
        //New game button
        JButton newGameButton = new JButton("New Game");
        newGameButton.setBounds(275, 145, 250, 45);
        styleButton(newGameButton);
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
        //Store button

        JButton storeButton = new JButton("Store");
        storeButton.setBounds(275, 200, 250, 45);
        styleButton(storeButton);
        backgroundLabel.add(storeButton);

        storeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (DatabaseManager.getCurrentUsername() == null) {

                    gameMain.showLoginPanel();
                }

                else {

                    gameMain.showStore();
                }
            }
        });

        //----------------------------------------------------------------
        //High score

        JButton highScoresButton = new JButton("High Scores");
        highScoresButton.setBounds(275, 255, 250, 45);
        styleButton(highScoresButton);
        backgroundLabel.add(highScoresButton);

        highScoresButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                gameMain.showHighScorePanel();
            }
        });

        //----------------------------------------------------------------
        //Setting button

        JButton settingsButton = new JButton("Settings");
        settingsButton.setBounds(275, 310, 250, 45);
        styleButton(settingsButton);
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
        howToPlayButton.setBounds(275, 365, 250, 45);
        styleButton(howToPlayButton);
        backgroundLabel.add(howToPlayButton);

        howToPlayButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                gameMain.showHowToPlayPanel();
            }
        });

        //----------------------------------------------------------------
        //Exit button

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(275, 420, 250, 45);
        styleButton(exitButton);
        backgroundLabel.add(exitButton);

        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });
    }
    //----------------------------------------------------------------

    //Methods

    //Style button

    private void styleButton(JButton button) {

        button.setFont(new Font("Impact", Font.PLAIN, 24));
        button.setForeground(new Color(220, 225, 255));
        button.setBackground(new Color(35, 25, 70));
        button.setFocusPainted(false);
        button.setBorder(new RoundedBorder(35));
    }
}

//----------------------------------------------------------------
//Rounded border
class RoundedBorder implements javax.swing.border.Border {

    private int radius;

    public RoundedBorder(int radius) {

        this.radius = radius;
    }

    @Override
    public Insets getBorderInsets(Component c) {

        return new Insets(8, 15, 8, 15);
    }

    @Override
    public boolean isBorderOpaque() {

        return false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(new Color(170, 130, 255));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x + 1, y + 1, width - 3, height - 3, radius, radius);
    }
}