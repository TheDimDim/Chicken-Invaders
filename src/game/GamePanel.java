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
    private int enemyDirection = 1;

    private int score;
    private JLabel scoreLabel;

    private int lives;
    private JLabel livesLabel;

    private boolean paused;
    private JLabel pauseLabel;

    private boolean gameOver;
    private JLabel gameOverLabel;


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

        plane = new Plane();
        backgroundLabel.add(plane);

        bullets = new java.util.ArrayList<>();
        enemies = new java.util.ArrayList<>();

        Enemy normalEnemy = new NormalEnemy(370, 80);
        Enemy shooterEnemy = new ShooterEnemy(440, 80);
        Enemy fastEnemy = new FastEnemy(300, 80);
        Enemy zigzagEnemy = new ZigzagEnemy(230, 80);

        enemies.add(normalEnemy);
        enemies.add(shooterEnemy);
        enemies.add(fastEnemy);
        enemies.add(zigzagEnemy);

        backgroundLabel.add(normalEnemy);
        backgroundLabel.add(shooterEnemy);
        backgroundLabel.add(fastEnemy);
        backgroundLabel.add(zigzagEnemy);

        //Score
        score = 0;
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(50, 50, 200, 40);
        backgroundLabel.add(scoreLabel);

        //Lives
        lives = 3;
        livesLabel = new JLabel("Lives: " + lives);
        livesLabel.setForeground(Color.WHITE);
        livesLabel.setBounds(50, 0, 200, 40);
        backgroundLabel.add(livesLabel);

        //Pause
        paused = false;
        pauseLabel = new JLabel("PAUSED");
        pauseLabel.setForeground(Color.WHITE);
        pauseLabel.setFont(new Font("Arial", Font.BOLD, 50));
        pauseLabel.setBounds(290, 240, 250, 70);
        pauseLabel.setVisible(false);
        backgroundLabel.add(pauseLabel);

        //Game Over
        gameOver = false;
        gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 50));
        gameOverLabel.setBounds(250, 240, 350, 70);
        gameOverLabel.setVisible(false);
        backgroundLabel.add(gameOverLabel);


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
                if (!paused && !gameOver) {
                    moveBullets();
                    moveEnemies();
                }
            }
        });
        timer.start();
    }


    //Bullets Movement
    public void moveBullets() {

        for (int i = 0; i < bullets.size(); i++) {

            Bullet bullet = bullets.get(i);
            bullet.movement();

            for (int j = 0; j < enemies.size(); j++) {

                Enemy enemy = enemies.get(j);

                if (bullet.hitEnemy(enemy)) {

                    backgroundLabel.remove(bullet);
                    bullets.remove(i);
                    i--;

                    enemy.damage();

                    if (enemy.isDead()) {

                        backgroundLabel.remove(enemy);
                        enemies.remove(j);
                        score += enemy.getScore();
                        scoreLabel.setText("Score: " + score);
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


    //Enemies Movement
    public void moveEnemies() {

        boolean hitEdge = false;

        for (int i = 0; i < enemies.size(); i++) {

            Enemy enemy = enemies.get(i);

            enemy.setLocation(enemy.getX() + enemyDirection, enemy.getY());

            if (enemy.getX() <= 0 || enemy.getX() >= 740) {
                hitEdge = true;
            }

            if (enemy.hitPlane(plane)) {
                backgroundLabel.remove(enemy);
                enemies.remove(i);
                i--;
                loseLife();
            }

            else if (enemy.isOutOfScreen()) {
                backgroundLabel.remove(enemy);
                enemies.remove(i);
                i--;
                loseLife();
            }
        }

        if (hitEdge) {
            enemyDirection *= -1;

            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                enemy.setLocation(enemy.getX(), enemy.getY() + 20);
            }
        }

        backgroundLabel.repaint();
    }



    //Lose Life
    public void loseLife() {

        if (lives > 0) {

            lives--;
            livesLabel.setText("Lives: " + lives);

            if (lives == 0) {

                gameOver = true;
                gameOverLabel.setVisible(true);
            }
        }
    }


    //Keyboard
    @Override
    public void keyPressed(KeyEvent e) {

        if ((e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) && !paused && !gameOver) {
            plane.moveRight();
        }

        if ((e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) && !paused && !gameOver) {
            plane.moveLeft();
        }

        if ((e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) && !paused && !gameOver) {
            plane.moveUp();
        }

        if ((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) && !paused && !gameOver) {
            plane.moveDown();
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE && !paused && !gameOver) {

            long currentTime = System.currentTimeMillis();

            if (currentTime - lastBulletShotTime >= 300) {

                Bullet bullet = new Bullet(plane.getXPosition() + 28, plane.getYPosition());
                bullets.add(bullet);
                backgroundLabel.add(bullet);

                lastBulletShotTime = currentTime;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_P && !gameOver) {
            paused = !paused;
            pauseLabel.setVisible(paused);
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gameMain.showMainMenu();
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}