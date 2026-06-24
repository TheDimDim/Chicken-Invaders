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

    //----------------------------------------------------------------

    //Constructor
    public LoginPanel(GameMain gameMain) {

        this.gameMain = gameMain;

        setLayout(null);
        setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("LOGIN");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setBounds(330, 80, 200, 50);
        add(titleLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(250, 180, 120, 30);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(370, 180, 180, 30);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(250, 230, 120, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(370, 230, 180, 30);
        add(passwordField);

        //LOGIN

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(300, 300, 100, 35);
        add(loginButton);

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

                        gameMain.startNewGame();
                    }

                    else {

                        JOptionPane.showMessageDialog(null, "Wrong username or password");
                    }

                }
            }
        });

        //REGISTER
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(420, 300, 120, 35);
        add(registerButton);

        registerButton.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                gameMain.showRegisterPanel();
            }
        });
    }


    
}