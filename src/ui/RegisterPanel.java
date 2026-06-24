package ui;

import game.GameMain;
import managers.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterPanel extends JPanel {

    //Fields
    private GameMain gameMain;

    private JTextField usernameField;
    private JPasswordField passwordField;

    //----------------------------------------------------------------

    //Constructor
    public RegisterPanel(GameMain gameMain) {

        this.gameMain = gameMain;

        setLayout(null);
        setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("REGISTER");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setBounds(290, 80, 250, 50);
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

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(290, 300, 120, 35);
        add(registerButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(430, 300, 100, 35);
        add(backButton);

        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Please fill all fields");
                }

                else {
                    boolean registered = DatabaseManager.registerUser(username, password);

                    if (registered) {

                        JOptionPane.showMessageDialog(null, "Registered successfully");
                        gameMain.showLoginPanel();
                    }

                    else {

                        JOptionPane.showMessageDialog(null, "Username already exists");
                    }

                }
            }
        });

        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                gameMain.showLoginPanel();
            }
        });
    }
}