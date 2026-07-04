package ui;

import game.GameMain;
import managers.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HighScorePanel extends JPanel {

    //Fields
    private GameMain gameMain;

    //----------------------------------------------------------------

    //Constructor
    public HighScorePanel(GameMain gameMain) {

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

        JLabel titleLabel = new JLabel("HIGH SCORES");
        titleLabel.setForeground(new Color(220, 225, 255));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 56));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(150, 30, 500, 70);
        backgroundLabel.add(titleLabel);

        //----------------------------------------------------------------
        //Scores area

        JTextArea scoresArea = new JTextArea();
        scoresArea.setText(DatabaseManager.getHighScoresText());
        scoresArea.setEditable(false);
        scoresArea.setLineWrap(true);
        scoresArea.setWrapStyleWord(true);
        scoresArea.setBackground(new Color(15, 10, 35));
        scoresArea.setForeground(new Color(220, 225, 255));
        scoresArea.setFont(new Font("Segoe UI", Font.BOLD, 16));
        scoresArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(scoresArea);
        scrollPane.setBounds(125, 115, 550, 360);
        scrollPane.setBorder(new RoundedBorder(30));
        scrollPane.setBackground(new Color(15, 10, 35));
        scrollPane.getViewport().setBackground(new Color(15, 10, 35));
        backgroundLabel.add(scrollPane);
        //----------------------------------------------------------------
        //Back button

        JButton backButton = new JButton("Back");
        backButton.setBounds(275, 500, 250, 50);
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