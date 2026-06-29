package ui;

import game.GameMain;
import managers.DatabaseManager;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    //Fields
    private GameMain gameMain;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private boolean passwordVisible;
    private char defaultEchoChar;

    //----------------------------------------------------------------

    //Constructor
    public LoginPanel(GameMain gameMain) {

        this.gameMain = gameMain;

        setLayout(null);

        ImageIcon background = new ImageIcon("C:\\Users\\Asus\\Downloads\\background.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);

        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 800, 600);
        backgroundLabel.setLayout(null);
        add(backgroundLabel);

        passwordVisible = false;

        //----------------------------------------------------------------
        //Title

        JLabel titleLabel = new JLabel("LOGIN");
        titleLabel.setForeground(new Color(220, 225, 255));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 70));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(250, 55, 300, 85);
        backgroundLabel.add(titleLabel);

        //----------------------------------------------------------------
        //Username

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(new Color(220, 225, 255));
        usernameLabel.setFont(new Font("Impact", Font.PLAIN, 28));
        usernameLabel.setBounds(150, 185, 160, 40);
        backgroundLabel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(330, 185, 320, 48);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        usernameField.setBackground(new Color(15, 10, 35));
        usernameField.setForeground(new Color(220, 225, 255));
        usernameField.setCaretColor(Color.WHITE);
        usernameField.setBorder(new RoundedBorder(30));
        backgroundLabel.add(usernameField);

        //----------------------------------------------------------------
        //Password

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(new Color(220, 225, 255));
        passwordLabel.setFont(new Font("Impact", Font.PLAIN, 28));
        passwordLabel.setBounds(150, 260, 160, 40);
        backgroundLabel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(330, 260, 230, 48);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        passwordField.setBackground(new Color(15, 10, 35));
        passwordField.setForeground(new Color(220, 225, 255));
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setBorder(new RoundedBorder(30));
        backgroundLabel.add(passwordField);

        defaultEchoChar = passwordField.getEchoChar();

        JButton showButton = new JButton("Show");
        showButton.setBounds(570, 260, 80, 48);
        showButton.setFont(new Font("Impact", Font.PLAIN, 20));
        showButton.setForeground(new Color(220, 225, 255));
        showButton.setBackground(new Color(35, 25, 70));
        showButton.setFocusPainted(false);
        showButton.setBorder(new RoundedBorder(30));
        backgroundLabel.add(showButton);

        showButton.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                if (passwordVisible) {

                    passwordField.setEchoChar(defaultEchoChar);
                    showButton.setText("Show");
                    passwordVisible = false;
                }

                else {

                    passwordField.setEchoChar((char) 0);
                    showButton.setText("Hide");
                    passwordVisible = true;
                }
            }
        });

        //----------------------------------------------------------------
        //LOGIN

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(165, 365, 140, 55);
        styleButton(loginButton);
        backgroundLabel.add(loginButton);

        loginButton.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Please fill all fields");
                }

                else {

                    boolean loggedIn = DatabaseManager.loginUser(username, password);

                    if (loggedIn) {

                        gameMain.showMainMenu();
                    }

                    else {

                        JOptionPane.showMessageDialog(null, "Wrong username or password");
                    }
                }
            }
        });

        //----------------------------------------------------------------
        //REGISTER

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(330, 365, 140, 55);
        styleButton(registerButton);
        backgroundLabel.add(registerButton);

        registerButton.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                gameMain.showRegisterPanel();
            }
        });

        //----------------------------------------------------------------
        //BACK

        JButton backButton = new JButton("Back");
        backButton.setBounds(495, 365, 140, 55);
        styleButton(backButton);
        backgroundLabel.add(backButton);

        backButton.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

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