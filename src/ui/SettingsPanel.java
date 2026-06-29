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

        ImageIcon background = new ImageIcon("C:\\Users\\Asus\\Downloads\\background.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);

        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 800, 600);
        backgroundLabel.setLayout(null);
        add(backgroundLabel);

        //----------------------------------------------------------------
        //Title

        JLabel titleLabel = new JLabel("SETTINGS");
        titleLabel.setForeground(new Color(220, 225, 255));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 64));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(200, 55, 400, 80);
        backgroundLabel.add(titleLabel);

        //----------------------------------------------------------------
        //Check boxes

        backgroundMusicCheckBox = new JCheckBox("Background Music");
        styleCheckBox(backgroundMusicCheckBox);
        backgroundMusicCheckBox.setSelected(DatabaseManager.getBackgroundMusic() == 1);
        backgroundMusicCheckBox.setBounds(260, 165, 330, 45);
        backgroundLabel.add(backgroundMusicCheckBox);

        shotSoundCheckBox = new JCheckBox("Shot Sound");
        styleCheckBox(shotSoundCheckBox);
        shotSoundCheckBox.setSelected(DatabaseManager.getShotSound() == 1);
        shotSoundCheckBox.setBounds(260, 220, 330, 45);
        backgroundLabel.add(shotSoundCheckBox);

        crashSoundCheckBox = new JCheckBox("Crash / Explosion Sound");
        styleCheckBox(crashSoundCheckBox);
        crashSoundCheckBox.setSelected(DatabaseManager.getCrashSound() == 1);
        crashSoundCheckBox.setBounds(260, 275, 330, 45);
        backgroundLabel.add(crashSoundCheckBox);

        gameOverSoundCheckBox = new JCheckBox("Game Over / Win Sound");
        styleCheckBox(gameOverSoundCheckBox);
        gameOverSoundCheckBox.setSelected(DatabaseManager.getGameOverSound() == 1);
        gameOverSoundCheckBox.setBounds(260, 330, 330, 45);
        backgroundLabel.add(gameOverSoundCheckBox);

        //----------------------------------------------------------------
        //Save button

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(230, 430, 160, 55);
        styleButton(saveButton);
        backgroundLabel.add(saveButton);

        //----------------------------------------------------------------
        //Back button

        JButton backButton = new JButton("Back");
        backButton.setBounds(410, 430, 160, 55);
        styleButton(backButton);
        backgroundLabel.add(backButton);

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

    //----------------------------------------------------------------
    //Style button

    private void styleButton(JButton button) {

        button.setFont(new Font("Impact", Font.PLAIN, 26));
        button.setForeground(new Color(220, 225, 255));
        button.setBackground(new Color(35, 25, 70));
        button.setFocusPainted(false);
        button.setBorder(new RoundedBorder(35));
    }

    //----------------------------------------------------------------
    //Style check box

    private void styleCheckBox(JCheckBox checkBox) {

        checkBox.setFont(new Font("Segoe UI", Font.BOLD, 18));
        checkBox.setForeground(new Color(220, 225, 255));
        checkBox.setBackground(new Color(15, 10, 35));
        checkBox.setFocusPainted(false);
        checkBox.setOpaque(true);
        checkBox.setBorder(new RoundedBorder(25));
    }
}