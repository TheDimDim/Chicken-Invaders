package ui;

import game.GameMain;
import managers.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StorePanel extends JPanel {

    //Fields
    private GameMain gameMain;
    private JLabel backgroundLabel;
    private JLabel scoreLabel;
    private JLabel selectedPlaneLabel;
    private JLabel messageLabel;

    //----------------------------------------------------------------

    //Constructor
    public StorePanel(GameMain gameMain) {

        this.gameMain = gameMain;
        setLayout(null);

        ImageIcon background = new ImageIcon("C:\\Users\\Asus\\Downloads\\background.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);

        backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 800, 600);
        backgroundLabel.setLayout(null);
        add(backgroundLabel);

        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 15, 80, 35);
        backButton.setFont(new Font("Impact", Font.PLAIN, 18));
        backButton.setForeground(Color.WHITE);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backgroundLabel.add(backButton);

        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                gameMain.showMainMenu();
            }
        });

        JLabel titleLabel = new JLabel("STORE");
        titleLabel.setBounds(250, 25, 300, 65);
        titleLabel.setFont(new Font("Impact", Font.BOLD, 60));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundLabel.add(titleLabel);

        scoreLabel = new JLabel("High Score: " + DatabaseManager.getCurrentUserHighScore());
        scoreLabel.setBounds(190, 95, 420, 35);
        scoreLabel.setFont(new Font("Impact", Font.PLAIN, 24));
        scoreLabel.setForeground(new Color(255, 220, 80));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundLabel.add(scoreLabel);

        selectedPlaneLabel = new JLabel("Selected Plane: " + DatabaseManager.getSelectedPlane());
        selectedPlaneLabel.setBounds(150, 130, 500, 35);
        selectedPlaneLabel.setFont(new Font("Impact", Font.PLAIN, 24));
        selectedPlaneLabel.setForeground(Color.WHITE);
        selectedPlaneLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundLabel.add(selectedPlaneLabel);

        messageLabel = new JLabel("");
        messageLabel.setBounds(150, 540, 500, 35);
        messageLabel.setFont(new Font("Impact", Font.PLAIN, 24));
        messageLabel.setForeground(new Color(255, 220, 80));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundLabel.add(messageLabel);

        addPlaneCard("Default", 0, "Speed 5", "Fire 300ms", "Lives 3", "-",
                "C:\\Users\\Asus\\Downloads\\airplan-20260613T102345Z-3-001\\airplan\\2.png",
                70, 185);

        addPlaneCard("Fast", 5000, "Speed 7", "Fire 250ms", "Lives 3", "-",
                "C:\\Users\\Asus\\Downloads\\airplan-20260613T102345Z-3-001\\airplan\\4.png",
                430, 185);

        addPlaneCard("Heavy", 8000, "Speed 4", "Fire 200ms", "Lives 5", "-",
                "C:\\Users\\Asus\\Downloads\\airplan-20260613T102345Z-3-001\\airplan\\6.png",
                70, 355);

        addPlaneCard("Sniper", 10000, "Speed 5", "Fire 150ms", "Lives 3", "Boss x2",
                "C:\\Users\\Asus\\Downloads\\airplan-20260613T102345Z-3-001\\airplan\\5.png",
                430, 355);
    }

    //----------------------------------------------------------------

    private void addPlaneCard(String planeName, int cost, String speed, String fire, String lives, String special, String imagePath, int x, int y) {

        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBounds(x, y, 300, 145);
        card.setOpaque(false);
        card.setBorder(BorderFactory.createLineBorder(new Color(170, 130, 255), 2));
        backgroundLabel.add(card);

        ImageIcon planeIcon = new ImageIcon(imagePath);
        Image planeImage = planeIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);

        JLabel planeImageLabel = new JLabel(new ImageIcon(planeImage));
        planeImageLabel.setBounds(10, 35, 80, 80);
        card.add(planeImageLabel);

        JLabel nameLabel = new JLabel(planeName);
        nameLabel.setBounds(95, 5, 120, 35);
        nameLabel.setFont(new Font("Impact", Font.BOLD, 28));
        nameLabel.setForeground(Color.WHITE);
        card.add(nameLabel);

        JLabel costLabel = new JLabel("Cost: " + cost);
        costLabel.setBounds(195, 10, 90, 25);
        costLabel.setFont(new Font("Impact", Font.PLAIN, 17));
        costLabel.setForeground(new Color(255, 220, 80));
        costLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        card.add(costLabel);

        JLabel infoLabel1 = new JLabel(speed + "   " + fire);
        infoLabel1.setBounds(95, 45, 190, 25);
        infoLabel1.setFont(new Font("Impact", Font.PLAIN, 17));
        infoLabel1.setForeground(new Color(220, 225, 255));
        card.add(infoLabel1);

        JLabel infoLabel2 = new JLabel(lives);
        infoLabel2.setBounds(95, 70, 190, 25);
        infoLabel2.setFont(new Font("Impact", Font.PLAIN, 17));
        infoLabel2.setForeground(new Color(220, 225, 255));
        card.add(infoLabel2);

        JLabel specialLabel = new JLabel("Special: " + special);
        specialLabel.setBounds(95, 92, 190, 25);
        specialLabel.setFont(new Font("Impact", Font.PLAIN, 16));
        specialLabel.setForeground(new Color(220, 225, 255));
        card.add(specialLabel);

        JButton buyButton = new JButton("Select");
        buyButton.setBounds(190, 110, 90, 25);
        buyButton.setFont(new Font("Impact", Font.PLAIN, 15));
        buyButton.setForeground(Color.WHITE);
        buyButton.setBackground(new Color(35, 25, 70));
        buyButton.setFocusPainted(false);
        buyButton.setBorder(BorderFactory.createLineBorder(new Color(170, 130, 255), 2));
        card.add(buyButton);

        buyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                buyPlane(planeName, cost);
            }
        });
    }

    //----------------------------------------------------------------

    private void buyPlane(String planeName, int cost) {

        if (DatabaseManager.canBuyPlane(cost)) {

            DatabaseManager.setSelectedPlane(planeName);

            selectedPlaneLabel.setText("Selected Plane: " + DatabaseManager.getSelectedPlane());
            messageLabel.setText(planeName + " selected");
        }

        else {

            messageLabel.setText("Not enough score");
        }
    }
}