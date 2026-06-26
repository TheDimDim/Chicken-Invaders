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
        setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("HIGH SCORES");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setBounds(250, 40, 350, 50);
        add(titleLabel);

        JTextArea scoresArea = new JTextArea();
        scoresArea.setText(DatabaseManager.getHighScoresText());
        scoresArea.setEditable(false);
        scoresArea.setBackground(Color.BLACK);
        scoresArea.setForeground(Color.WHITE);
        scoresArea.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(scoresArea);
        scrollPane.setBounds(180, 120, 440, 330);
        add(scrollPane);

        JButton backButton = new JButton("Back");
        backButton.setBounds(340, 480, 120, 35);
        add(backButton);

        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                gameMain.showMainMenu();
            }
        });
    }
}