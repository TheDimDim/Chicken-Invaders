package game;

import ui.LoginPanel;
import ui.MainMenu;

import javax.swing.*;
import java.awt.*;

public class GameMain extends JFrame {

    public GameMain() {
        setTitle("Chicken Invaders");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        MainMenu mainMenu = new MainMenu(this);
        add(mainMenu);
    }

    //----------------------------------------------------------------
    public void startNewGame() {
        getContentPane().removeAll();

        GamePanel gamePanel = new GamePanel(this);
        add(gamePanel);

        revalidate();
        repaint();
        gamePanel.requestFocusInWindow();
    }

    //----------------------------------------------------------------
    public void showMainMenu() {
        getContentPane().removeAll();

        MainMenu mainMenu = new MainMenu(this);
        add(mainMenu);

        revalidate();
        repaint();
    }

    //----------------------------------------------------------------

    public void showLoginPanel() {

        setContentPane(new LoginPanel(this));

        revalidate();
        repaint();
    }

    //----------------------------------------------------------------
    public static void main(String[] args) {
        GameMain window = new GameMain();
        window.setVisible(true);
    }
}