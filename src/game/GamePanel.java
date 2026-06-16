package game;

import model.Bullet;
import model.Egg;
import model.Plane;
import model.enemy.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements KeyListener {

    //Fields
    private GameMain gameMain;
    private Plane plane;
    private JLabel backgroundLabel;
    private java.util.ArrayList<Bullet> bullets;
    private Timer timer;
    private long lastBulletShotTime;


    //ENEMY
    private java.util.ArrayList<Enemy> enemies;
    private int enemyDirection;
    private int enemySpeed;

    //EGG
    private java.util.ArrayList<Egg> eggs;
    private long lastEggDropTime;

    private int score;
    private JLabel scoreLabel;

    private int lives;
    private JLabel livesLabel;

    private int stage;
    private JLabel stageLabel;

    private boolean paused;
    private JLabel pauseLabel;

    private boolean gameOver;
    private JLabel gameOverLabel;

    private int rows = 5;
    private int cols = 8;

    //Constructor
    public GamePanel(GameMain gameMain) {

        this.gameMain = gameMain;
        setLayout(null);
        lastBulletShotTime = 0;
        lastEggDropTime = 0;
        stage = 1;

        //ENEMY
        enemyDirection = 1;
        enemySpeed = 1;
        eggs = new java.util.ArrayList<>();

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

        grid();
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

        //Stages
        stageLabel = new JLabel("Stage: " + stage);
        stageLabel.setForeground(Color.WHITE);
        stageLabel.setBounds(650, 0, 120, 40);
        backgroundLabel.add(stageLabel);

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

        //Back button
        JButton backButton = new JButton("Back to Menu");
        backButton.setBounds(200, 0, 200, 40);
        backgroundLabel.add(backButton);

        backButton.addActionListener(e -> gameMain.showMainMenu());

        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!paused && !gameOver) {
                    moveBullets();
                    moveEnemies();
                    dropEgg();
                    moveEggs();
                }
            }
        });

        timer.start();
    }

    //GRID SYSTEM
    public void grid() {

        enemies.clear();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                Enemy enemy;
                int x = 100 + j * 70;
                int y = 50 + i * 60;
                if (stage == 1) {
                    enemy = new NormalEnemy(x, y);

                } else if (stage == 2) {
                    if (i % 2 == 0) {
                        enemy = new NormalEnemy(x, y);
                    } else {
                        enemy = new FastEnemy(x, y);
                    }

                } else if (stage == 3) {
                    if (i % 2 == 0) {
                        enemy = new NormalEnemy(x, y);
                    } else {
                        enemy = new ZigzagEnemy(x, y);
                    }

                } else {
                    enemy = new NormalEnemy(x, y);
                }

                enemies.add(enemy);
                backgroundLabel.add(enemy);
            }
        }
    }

    //Bullets
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

    //Drop Egg
    public void dropEgg() {

        long currentTime = System.currentTimeMillis();

        if (currentTime - lastEggDropTime >= 3000 && enemies.size() > 0) {

            int randomIndex = (int)(Math.random() * enemies.size());
            Enemy enemy = enemies.get(randomIndex);

            Egg egg = new Egg(enemy.getXPosition() + 15, enemy.getYPosition() + 50);

            eggs.add(egg);
            backgroundLabel.add(egg);

            lastEggDropTime = currentTime;
        }
    }

    //Eggs Movement
    public void moveEggs() {

        for (int i = 0; i < eggs.size(); i++) {

            Egg egg = eggs.get(i);
            egg.movement();

            if (egg.hitPlane(plane)) {

                backgroundLabel.remove(egg);
                eggs.remove(i);
                i--;

                loseLife();

            } else if (egg.getY() > 600) {

                backgroundLabel.remove(egg);
                eggs.remove(i);
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
            enemy.moveHorizontal(enemyDirection * enemySpeed);

            if (enemy.hitEdge()) {
                hitEdge = true;
            }

            if (enemy.hitPlane(plane)) {

                backgroundLabel.remove(enemy);
                enemies.remove(i);
                i--;

                loseLife();

            } else if (enemy.isOutOfScreen()) {

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
                enemy.moveVertical(15);
            }
        }

        if (enemies.size() == 0) {
            stage++;
            enemySpeed = stage;
            stageLabel.setText("Stage: " + stage);
            plane.resetPosition();

            for (int i = 0; i < bullets.size(); i++) {
                backgroundLabel.remove(bullets.get(i));
            }
            bullets.clear();

            grid();
        }
        backgroundLabel.repaint();
    }

    //Lose life
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

        if (!paused && !gameOver) {

            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
                plane.moveRight();

            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
                plane.moveLeft();

            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
                plane.moveUp();

            if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
                plane.moveDown();

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {

                long now = System.currentTimeMillis();

                if (now - lastBulletShotTime >= 300) {

                    Bullet bullet = new Bullet(plane.getXPosition() + 28, plane.getYPosition());
                    bullets.add(bullet);
                    backgroundLabel.add(bullet);

                    lastBulletShotTime = now;
                }
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
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}