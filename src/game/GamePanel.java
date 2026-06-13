package game;

import model.Bullet;
import model.Plane;
import model.enemy.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {

    //Fields
    private java.util.ArrayList<Enemy> enemies;
    private GameMain gameMain;
    private Plane plane;
    private JLabel backgroundLabel;
    private java.util.ArrayList<Bullet> bullets;
    private Timer timer;
    private long lastBulletShotTime;


    //Constructor
    public GamePanel(GameMain gameMain) {

        this.gameMain = gameMain;
        setLayout(null);
        lastBulletShotTime = 0;

        ImageIcon background = new ImageIcon("C:\\Users\\Asus\\Downloads\\background.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);

        backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 800, 600);
        backgroundLabel.setLayout(null);
        add(backgroundLabel);

        JLabel label = new JLabel("Game Panel");
        label.setForeground(Color.WHITE);
        label.setBounds(250, 220, 300, 50);
        backgroundLabel.add(label);

        plane = new Plane();
        backgroundLabel.add(plane);

        bullets = new java.util.ArrayList<Bullet>();
        enemies = new java.util.ArrayList<Enemy>();

        Enemy normalEnemy = new NormalEnemy(370, 80);
        enemies.add(normalEnemy);
        backgroundLabel.add(normalEnemy);

        Enemy shooterEnemy = new ShooterEnemy(440, 80);
        enemies.add(shooterEnemy);
        backgroundLabel.add(shooterEnemy);

        Enemy fastEnemy = new FastEnemy(300, 80);
        enemies.add(fastEnemy);
        backgroundLabel.add(fastEnemy);

        Enemy zigzagEnemy = new ZigzagEnemy(230, 80);
        enemies.add(zigzagEnemy);
        backgroundLabel.add(zigzagEnemy);


        //Back to menu button
        JButton backButton = new JButton("Back to Menu");
        backButton.setBounds(200, 0, 200, 40);
        backgroundLabel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameMain.showMainMenu();
            }
        });


        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBullets();
            }
        });
        timer.start();
    }

    public void moveBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.movement();

            for (int j = 0; j < enemies.size(); j++) {
                Enemy enemy = enemies.get(j);

                if (bullet.getBounds().intersects(enemy.getBounds())) {
                    backgroundLabel.remove(bullet);
                    bullets.remove(i);
                    i--;

                    enemy.damage();

                    if (enemy.isDead()) {
                        backgroundLabel.remove(enemy);
                        enemies.remove(j);
                    }

                    break;
                }
            }

            if (i >= 0 && i < bullets.size() && bullet.getY() < 0) {
                backgroundLabel.remove(bullet);
                bullets.remove(i);
                i--;
            }
        }

        backgroundLabel.repaint();
    }

    //Keyboard
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            plane.moveRight();
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            plane.moveLeft();
        }

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            plane.moveUp();
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            plane.moveDown();
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gameMain.showMainMenu();
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            long currentTime = System.currentTimeMillis();

            if (currentTime - lastBulletShotTime >= 300) {
                Bullet bullet = new Bullet(plane.getXPosition() + 28, plane.getYPosition());
                bullets.add(bullet);
                backgroundLabel.add(bullet);
                backgroundLabel.repaint();

                lastBulletShotTime = currentTime;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}