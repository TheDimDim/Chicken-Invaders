package ui;

import game.GameMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HowToPlayPanel extends JPanel {

    //Fields
    private GameMain gameMain;

    //----------------------------------------------------------------

    //Constructor
    public HowToPlayPanel(GameMain gameMain) {

        this.gameMain = gameMain;

        setLayout(null);

        ImageIcon background = new ImageIcon("visuals/background.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);

        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 800, 600);
        backgroundLabel.setLayout(null);
        add(backgroundLabel);

        //----------------------------------------------------------------
        //Title

        JLabel titleLabel = new JLabel("HOW TO PLAY");
        titleLabel.setForeground(new Color(220, 225, 255));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 56));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(150, 30, 500, 65);
        backgroundLabel.add(titleLabel);

        //----------------------------------------------------------------
        //Welcome

        JLabel welcomeLabel = new JLabel("Welcome Pilot! Get ready to save the galaxy.");
        welcomeLabel.setForeground(new Color(220, 225, 255));
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBounds(150, 95, 500, 35);
        backgroundLabel.add(welcomeLabel);

        //----------------------------------------------------------------
        //Text area

        JTextArea textArea = new JTextArea();

        textArea.setText(
                "Your mission:\n" +
                        "Destroy the chickens, collect powerups, defeat the bosses,\n" +
                        "and get the highest score.\n\n" +

                        "Controls:\n\n" +
                        "Right Arrow / D     : Move Right\n" +
                        "Left Arrow / A      : Move Left\n" +
                        "Up Arrow / W        : Move Up\n" +
                        "Down Arrow / S      : Move Down\n" +
                        "Space               : Shoot\n" +
                        "P                   : Pause / Resume\n" +
                        "Esc                 : Back To Menu\n\n" +

                        "PowerUps:\n\n" +
                        "Rapid Fire   : Faster shooting for a short time\n" +
                        "Freeze Bomb  : Freezes enemies and eggs\n" +
                        "Extra Life   : Adds one life up to 5\n" +
                        "Shield       : Protects the plane for a short time\n" +
                        "Add Fire     : Adds one extra bullet permanently\n\n" +

                        "Game rules:\n\n" +
                        "You have 3 lives at the start of the game.\n" +
                        "Your lives will not refresh between levels.\n" +
                        "If your lives become 0, the game is over.\n\n" +

                        "Scoring:\n\n" +
                        "Normal Chicken  : 10 points\n" +
                        "Fast Chicken    : 15 points\n" +
                        "Zigzag Chicken  : 20 points\n" +
                        "Shooter Chicken : 25 points\n" +
                        "Complete Level  : +200 points\n" +
                        "Boss Level 4    : +500 points\n" +
                        "Final Boss      : +1000 points\n\n" +

                        "Good luck pilot!"
        );

        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(15, 10, 35));
        textArea.setForeground(new Color(220, 225, 255));
        textArea.setFont(new Font("Segoe UI", Font.BOLD, 15));
        textArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(175, 145, 450, 280);
        scrollPane.setBorder(new RoundedBorder(30));
        scrollPane.setBackground(new Color(15, 10, 35));
        scrollPane.getViewport().setBackground(new Color(15, 10, 35));
        backgroundLabel.add(scrollPane);

        //----------------------------------------------------------------
        //Back button

        JButton backButton = new JButton("Back");
        backButton.setBounds(275, 465, 250, 50);
        styleButton(backButton);
        backgroundLabel.add(backButton);

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

        button.setFont(new Font("Impact", Font.PLAIN, 24));
        button.setForeground(new Color(220, 225, 255));
        button.setBackground(new Color(35, 25, 70));
        button.setFocusPainted(false);
        button.setBorder(new RoundedBorder(35));
    }
}