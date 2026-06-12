package game;

import ui.MainMenu;

import javax.swing.*;
import java.awt.*;

public class GameMain extends JFrame {
    public GameMain() {
        setTitle("Chicken Invaders");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        MainMenu mainMenu = new MainMenu(this);
        add(mainMenu);
    }

    public void startNewGame() {
        getContentPane().removeAll();

        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(Color.BLACK);

        add(gamePanel);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        GameMain window = new GameMain();
        window.setVisible(true);
    }
}