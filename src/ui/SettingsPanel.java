package ui;

import game.GameMain;
import managers.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SettingsPanel extends JPanel {

    //Fields
    private GameMain gameMain;

    private JCheckBox backgroundMusicCheckBox;
    private JCheckBox shotSoundCheckBox;
    private JCheckBox crashSoundCheckBox;
    private JCheckBox gameOverSoundCheckBox;

    //----------------------------------------------------------------

    //Constructor
    public SettingsPanel(GameMain gameMain) {

        this.gameMain = gameMain;

        setLayout(null);
        setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("SETTINGS");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setBounds(290, 70, 250, 50);
        add(titleLabel);

        backgroundMusicCheckBox = new JCheckBox("Background Music");
        backgroundMusicCheckBox.setForeground(Color.WHITE);
        backgroundMusicCheckBox.setBackground(Color.BLACK);
        backgroundMusicCheckBox.setSelected(DatabaseManager.getBackgroundMusic() == 1);
        backgroundMusicCheckBox.setBounds(300, 160, 250, 35);
        add(backgroundMusicCheckBox);

        shotSoundCheckBox = new JCheckBox("Shot Sound");
        shotSoundCheckBox.setForeground(Color.WHITE);
        shotSoundCheckBox.setBackground(Color.BLACK);
        shotSoundCheckBox.setSelected(DatabaseManager.getShotSound() == 1);
        shotSoundCheckBox.setBounds(300, 205, 250, 35);
        add(shotSoundCheckBox);

        crashSoundCheckBox = new JCheckBox("Crash / Explosion Sound");
        crashSoundCheckBox.setForeground(Color.WHITE);
        crashSoundCheckBox.setBackground(Color.BLACK);
        crashSoundCheckBox.setSelected(DatabaseManager.getCrashSound() == 1);
        crashSoundCheckBox.setBounds(300, 250, 250, 35);
        add(crashSoundCheckBox);

        gameOverSoundCheckBox = new JCheckBox("Game Over / Win Sound");
        gameOverSoundCheckBox.setForeground(Color.WHITE);
        gameOverSoundCheckBox.setBackground(Color.BLACK);
        gameOverSoundCheckBox.setSelected(DatabaseManager.getGameOverSound() == 1);
        gameOverSoundCheckBox.setBounds(300, 295, 250, 35);
        add(gameOverSoundCheckBox);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(285, 370, 100, 35);
        add(saveButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(415, 370, 100, 35);
        add(backButton);

        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int backgroundMusic = 0;
                int shotSound = 0;
                int crashSound = 0;
                int gameOverSound = 0;

                if (backgroundMusicCheckBox.isSelected()) {
                    backgroundMusic = 1;
                }

                if (shotSoundCheckBox.isSelected()) {
                    shotSound = 1;
                }

                if (crashSoundCheckBox.isSelected()) {
                    crashSound = 1;
                }

                if (gameOverSoundCheckBox.isSelected()) {
                    gameOverSound = 1;
                }

                DatabaseManager.saveSoundSettings(backgroundMusic, shotSound, crashSound, gameOverSound);

                JOptionPane.showMessageDialog(null, "Settings saved");
            }
        });

        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                gameMain.showMainMenu();
            }
        });
    }
}