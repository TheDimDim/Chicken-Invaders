package game;

import ui.LoginPanel;
import ui.MainMenu;
import ui.RegisterPanel;
import ui.SettingsPanel;

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

        GamePanel gamePanel = new GamePanel(this);

        setContentPane(gamePanel);

        revalidate();
        repaint();

        gamePanel.requestFocusInWindow();
    }

    //----------------------------------------------------------------
    public void showMainMenu() {

        setContentPane(new MainMenu(this));

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

    public void showSettingsPanel() {

        setContentPane(new SettingsPanel(this));

        revalidate();
        repaint();
    }
    //----------------------------------------------------------------

    public void showRegisterPanel() {

        setContentPane(new RegisterPanel(this));

        revalidate();
        repaint();
    }

    //----------------------------------------------------------------
    public static void main(String[] args) {
        GameMain window = new GameMain();
        window.setVisible(true);
    }
}