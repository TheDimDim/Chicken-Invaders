package game;

import ui.*;

import javax.swing.*;

public class GameMain extends JFrame {

    //Constructor
    public GameMain() {

        setTitle("Chicken Invaders");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        showMainMenu();

        setVisible(true);
    }

    //----------------------------------------------------------------
    //Methods

    public void showMainMenu() {

        MainMenu mainMenu = new MainMenu(this);

        setContentPane(mainMenu);

        revalidate();
        repaint();
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

    public void showHowToPlayPanel() {

        HowToPlayPanel howToPlayPanel = new HowToPlayPanel(this);

        setContentPane(howToPlayPanel);

        revalidate();
        repaint();
    }

    //----------------------------------------------------------------

    public void showHighScorePanel() {

        HighScorePanel highScorePanel = new HighScorePanel(this);

        setContentPane(highScorePanel);

        revalidate();
        repaint();
    }

    //----------------------------------------------------------------

    public void showSettingsPanel() {

        SettingsPanel settingsPanel = new SettingsPanel(this);

        setContentPane(settingsPanel);

        revalidate();
        repaint();
    }

    //----------------------------------------------------------------

    public void showLoginPanel() {

        LoginPanel loginPanel = new LoginPanel(this);

        setContentPane(loginPanel);

        revalidate();
        repaint();
    }

    //----------------------------------------------------------------

    public void showRegisterPanel() {

        RegisterPanel registerPanel = new RegisterPanel(this);

        setContentPane(registerPanel);

        revalidate();
        repaint();
    }

    //----------------------------------------------------------------

    public void showStore() {

        StorePanel storePanel = new StorePanel(this);

        setContentPane(storePanel);

        revalidate();
        repaint();

        storePanel.requestFocusInWindow();
    }

    //----------------------------------------------------------------

    public static void main(String[] args) {

        new GameMain();
    }
}