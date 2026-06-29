package game;

import managers.DatabaseManager;
import managers.SoundManager;
import model.*;
import model.boss.Boss;
import model.boss.BossLevel4;
import model.boss.BossLevel8;
import model.enemy.*;
import model.enemy.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener {

    //Fields
    private GameMain gameMain;
    private Plane plane;
    private JLabel backgroundLabel;
    private Timer timer;

    //KEYS
    private boolean rightPressed;
    private boolean leftPressed;
    private boolean upPressed;
    private boolean downPressed;
    private boolean spacePressed;

    //BULLET
    private long lastBulletShotTime;
    private long lastShotSoundTime;
    private ArrayList<Bullet> bullets;

    //ENEMY
    private ArrayList<Cell> cells;
    private ArrayList<Bullet> enemyBullets;
    private int enemyDirection;
    private double enemySpeed;
    private double enemyMoveCounter;
    private int verticalStep;
    private int eggDropInterval;
    private boolean edgeHandled;

    //BOSS
    private Boss boss;

    //EGG
    private ArrayList<Egg> eggs;
    private long lastEggDropTime;
    private long lastBossEggTime;

    //POWER UP
    private ArrayList<PowerUp> powerUps;
    private int fireCount;
    private boolean shieldActive;
    private boolean freezeActive;
    private long rapidFireEndTime;
    private long shieldEndTime;
    private long freezeEndTime;

    private int score;
    private JLabel scoreLabel;
    private JLabel messageLabel;

    private int lives;
    private JLabel livesLabel;

    private JLabel usernameLabel;
    private JLabel powerLabel;

    private int level;
    private JLabel levelLabel;

    private boolean paused;
    private JLabel pauseLabel;

    private boolean gameOver;
    private JLabel gameOverLabel;

    private JLabel fireLabel;

    private int rows = 5;
    private int cols = 8;

    //STORE
    private String selectedPlane;
    private int shootDelay;

    //----------------------------------------------------------------

    //Constructor
    public GamePanel(GameMain gameMain) {

        this.gameMain = gameMain;
        setLayout(null);

        lastBulletShotTime = 0;
        lastShotSoundTime = 0;
        lastEggDropTime = 0;
        lastBossEggTime = 0;

        rightPressed = false;
        leftPressed = false;
        upPressed = false;
        downPressed = false;
        spacePressed = false;

        level = 1;
        enemyDirection = 1;
        enemySpeed = 1;
        enemyMoveCounter = 0;
        verticalStep = 20;
        eggDropInterval = 3000;
        edgeHandled = false;

        eggs = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        bullets = new ArrayList<>();
        cells = new ArrayList<>();

        powerUps = new ArrayList<>();

        fireCount = 1;
        shieldActive = false;
        freezeActive = false;

        rapidFireEndTime = 0;
        shieldEndTime = 0;
        freezeEndTime = 0;

        selectedPlane = DatabaseManager.getSelectedPlane();
        shootDelay = 300;

        if (selectedPlane.equals("Fast")) {

            shootDelay = 250;
        }

        else if (selectedPlane.equals("Heavy")) {

            shootDelay = 200;
        }

        else if (selectedPlane.equals("Sniper")) {

            shootDelay = 150;
        }

        ImageIcon background = new ImageIcon("C:\\Users\\Asus\\Downloads\\background.jpg");
        Image backgroundImage = background.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);

        backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 800, 600);
        backgroundLabel.setLayout(null);
        add(backgroundLabel);

        plane = new Plane();
        backgroundLabel.add(plane);

        updateLevelSettings();
        grid();

        if (DatabaseManager.getBackgroundMusic() == 1) {

            SoundManager.playBackgroundMusic("C:\\Users\\Asus\\Downloads\\background.wav");
        }

        //HUD
        score = 0;

        if (selectedPlane.equals("Heavy")) {

            lives = 5;
        }

        else {

            lives = 3;
        }

        usernameLabel = new JLabel("👤 User: " + DatabaseManager.getCurrentUsername());
        usernameLabel.setBounds(10, 8, 150, 35);
        styleHudLabel(usernameLabel);
        backgroundLabel.add(usernameLabel);

        livesLabel = new JLabel("❤️ Lives: " + lives);
        livesLabel.setBounds(155, 8, 100, 35);
        styleHudLabel(livesLabel);
        backgroundLabel.add(livesLabel);

        levelLabel = new JLabel("⭐ Level: " + level);
        levelLabel.setBounds(250, 8, 105, 35);
        styleHudLabel(levelLabel);
        backgroundLabel.add(levelLabel);

        scoreLabel = new JLabel("🏆 Score: " + score);
        scoreLabel.setBounds(350, 8, 125, 35);
        styleHudLabel(scoreLabel);
        backgroundLabel.add(scoreLabel);

        fireLabel = new JLabel("🔥 Fire: " + fireCount);
        fireLabel.setBounds(470, 8, 95, 35);
        styleHudLabel(fireLabel);
        backgroundLabel.add(fireLabel);

        powerLabel = new JLabel("⚡ Power: None");
        powerLabel.setBounds(560, 8, 160, 35);
        styleHudLabel(powerLabel);
        backgroundLabel.add(powerLabel);

        //Pause
        paused = false;
        pauseLabel = new JLabel("PAUSED");
        pauseLabel.setBounds(150, 220, 500, 100);
        styleCenterMessage(pauseLabel, 65);
        pauseLabel.setVisible(false);
        backgroundLabel.add(pauseLabel);

        //Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(725, 8, 65, 35);
        backButton.setFont(new Font("Impact", Font.PLAIN, 16));
        backButton.setForeground(Color.WHITE);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backgroundLabel.add(backButton);

        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                SoundManager.stopBackgroundMusic();

                timer.stop();

                gameMain.showMainMenu();
            }
        });

        //Game Over
        gameOver = false;
        gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setBounds(100, 220, 600, 110);
        styleCenterMessage(gameOverLabel, 75);
        gameOverLabel.setVisible(false);
        backgroundLabel.add(gameOverLabel);

        //level Message
        messageLabel = new JLabel("");
        messageLabel.setBounds(100, 220, 600, 110);
        styleCenterMessage(messageLabel, 75);
        messageLabel.setVisible(false);
        backgroundLabel.add(messageLabel);


        setFocusable(true);
        addKeyListener(this);

        //Timer
        timer = new Timer(16, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (!paused && !gameOver) {
                    handleKeys();
                    moveBullets();
                    moveEnemies();
                    moveBoss();
                    dropEgg();
                    moveEggs();
                    moveEnemyBullets();
                    shooterEnemiesShoot();
                    movePowerUps();
                    checkPowerUpTimes();

                    backgroundLabel.repaint();
                }
            }
        });

        timer.start();
    }

    //----------------------------------------------------------------

    //Style hud label
    private void styleHudLabel(JLabel label) {

        label.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setOpaque(false);
        label.setBorder(null);
    }

    //----------------------------------------------------------------
    //Style center message
    private void styleCenterMessage(JLabel label, int fontSize) {

        label.setFont(new Font("Impact", Font.BOLD, fontSize));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setOpaque(false);
        label.setBorder(null);
    }

    //----------------------------------------------------------------
    //LEVEL SETTINGS
    private void updateLevelSettings() {

        if (level == 1) {
            enemySpeed = 1;
            verticalStep = 20;
            eggDropInterval = 3000;
        }

        else if (level == 2) {
            enemySpeed = 1.5;
            verticalStep = 20;
            eggDropInterval = 2000;
        }

        else if (level == 3) {
            enemySpeed = 2;
            verticalStep = 25;
            eggDropInterval = 1500;
        }

        else if (level == 4) {
            enemySpeed = 1.5;
            verticalStep = 0;
            eggDropInterval = 1500;
        }

        else if (level == 5) {
            enemySpeed = 2.5;
            verticalStep = 25;
            eggDropInterval = 1000;
        }

        else if (level == 6) {
            enemySpeed = 3;
            verticalStep = 30;
            eggDropInterval = 800;
        }

        else if (level == 7) {
            enemySpeed = 3.5;
            verticalStep = 30;
            eggDropInterval = 700;
        }

        else if (level == 8) {
            enemySpeed = 2;
            verticalStep = 0;
            eggDropInterval = 1000;
        }
    }

    //----------------------------------------------------------------
    //GRID
    public void grid() {

        for (Cell cell : cells) {
            backgroundLabel.remove(cell.getEnemy());
        }

        cells.clear();

        if (level == 4 || level == 8) {
            backgroundLabel.revalidate();
            backgroundLabel.repaint();
            return;
        }

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                int x = 100 + j * 70;
                int y = 50 + i * 60;

                Enemy enemy;

                if (level == 1) {
                    enemy = new NormalEnemy(x, y);
                }

                else if (level == 2) {
                    enemy = (i % 2 == 0) ? new NormalEnemy(x, y) : new FastEnemy(x, y);
                }

                else if (level == 3) {
                    enemy = (i % 2 == 0) ? new NormalEnemy(x, y) : new ZigzagEnemy(x, y);
                }

                else if (level == 5) {
                    enemy = (i % 2 == 0) ? new ShooterEnemy(x, y) : new FastEnemy(x, y);
                }

                else if (level == 6) {
                    enemy = (i % 2 == 0) ? new ZigzagEnemy(x, y) : new ShooterEnemy(x, y);
                }

                else {
                    if (j % 4 == 0)
                        enemy = new NormalEnemy(x, y);
                    else if (j % 4 == 1)
                        enemy = new FastEnemy(x, y);
                    else if (j % 4 == 2)
                        enemy = new ZigzagEnemy(x, y);
                    else
                        enemy = new ShooterEnemy(x, y);
                }

                int hitCount;

                if (enemy instanceof FastEnemy && level <= 3) {
                    hitCount = 1;
                }

                else if (level == 1 || level == 2) {
                    hitCount = 2;
                }

                else if (level == 3 || level == 5) {
                    hitCount = 3;
                }

                else {
                    hitCount = 4;
                }

                Cell cell = new Cell(enemy, hitCount);

                cells.add(cell);
                backgroundLabel.add(enemy);
            }
        }

        backgroundLabel.revalidate();
        backgroundLabel.repaint();
    }

    //----------------------------------------------------------------
    //BULLETS
    public void moveBullets() {

        bulletsMovement();
        bulletsCollision();
        bulletsRemove();
    }

    private void bulletsMovement() {

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).movement();
        }
    }

    private void bulletsCollision() {

        for (int i = 0; i < bullets.size(); i++) {

            Bullet bullet = bullets.get(i);

            //BOSS HIT
            if (boss != null && bullet.hitEnemy(boss)) {

                boss.damageBoss();

                if (selectedPlane.equals("Sniper")) {

                    boss.damageBoss();
                }

                if (DatabaseManager.getCrashSound() == 1) {

                    SoundManager.playSoundWithVolume("C:\\Users\\Asus\\Downloads\\sound-effects-20260621T162013Z-3-001\\sound-effects\\mixkit-epic-impact-afar-explosion-2782.wav", -12.0f);
                }

                backgroundLabel.remove(bullet);
                bullets.remove(i--);

                if (boss.isDead()) {

                    score += boss.getScore();
                    scoreLabel.setText("🏆 Score: " + score);

                    showExplosion(boss.getX(), boss.getY());

                    backgroundLabel.remove(boss);
                    boss = null;

                    bossLevelFinished();
                }

                return;
            }

            //ENEMY HIT
            for (int j = 0; j < cells.size(); j++) {

                Cell cell = cells.get(j);

                if (bullet.hitEnemy(cell.getEnemy())) {

                    if (DatabaseManager.getCrashSound() == 1) {

                        SoundManager.playSoundWithVolume("C:\\Users\\Asus\\Downloads\\sound-effects-20260621T162013Z-3-001\\sound-effects\\mixkit-epic-impact-afar-explosion-2782.wav", -12.0f);
                    }

                    cell.hit();

                    backgroundLabel.remove(bullet);
                    bullets.remove(i--);

                    if (cell.isDestroyed()) {

                        score += cell.getEnemy().getScore();
                        scoreLabel.setText("🏆 Score: " + score);

                        showExplosion(cell.getEnemy().getXPosition(), cell.getEnemy().getYPosition());

                        createPowerUp(cell.getEnemy().getXPosition(), cell.getEnemy().getYPosition());

                        backgroundLabel.remove(cell.getEnemy());
                        cells.remove(j);
                    }

                    break;
                }
            }
        }
    }

    private void bulletsRemove() {

        for (int i = 0; i < bullets.size(); i++) {

            if (bullets.get(i).getY() < 0) {
                backgroundLabel.remove(bullets.get(i));
                bullets.remove(i--);
            }
        }
    }

    //----------------------------------------------------------------
    //ENEMY
    public void moveEnemies() {
        if (freezeActive) {

            return;
        }

        if (boss != null) {
            return;
        }

        enemiesCleanup();
        enemiesMovement();
        enemiesCollision();
        enemiesLevel();
    }

    private void enemiesCleanup() {

        for (int i = 0; i < cells.size(); i++) {

            if (cells.get(i).getEnemy().isDead()) {

                backgroundLabel.remove(cells.get(i).getEnemy());
                cells.remove(i--);
            }
        }
    }

    private void enemiesMovement() {

        enemyMoveCounter += enemySpeed;

        int moveAmount = (int) enemyMoveCounter;

        if (moveAmount < 1) {
            return;
        }

        enemyMoveCounter -= moveAmount;

        for (int i = 0; i < cells.size(); i++) {

            Cell cell = cells.get(i);
            cell.getEnemy().moveHorizontal(enemyDirection * moveAmount);
        }
    }

    private void enemiesCollision() {

        for (int i = 0; i < cells.size(); i++) {

            Cell cell = cells.get(i);

            if (cell.getEnemy().hitPlane(plane)) {

                backgroundLabel.remove(cell.getEnemy());
                cells.remove(i--);

                loseLife();
            }

            else if (cell.getEnemy().isOutOfScreen()) {

                backgroundLabel.remove(cell.getEnemy());
                cells.remove(i--);

                loseLife();
            }
        }
    }

    private void enemiesLevel() {

        boolean hitEdge = false;

        for (int i = 0; i < cells.size(); i++) {

            if (cells.get(i).getEnemy().hitEdge())
                hitEdge = true;
        }

        if (hitEdge && !edgeHandled) {

            enemyDirection *= -1;

            for (Cell cell : cells) {

                cell.getEnemy().moveVertical(verticalStep);
            }

            edgeHandled = true;
        }

        else if (!hitEdge) {

            edgeHandled = false;
        }

        boolean allDead = true;

        for (int i = 0; i < cells.size(); i++) {

            if (!cells.get(i).getEnemy().isDead()) {
                allDead = false;
            }
        }

        if ((allDead || cells.isEmpty()) && boss == null) {

            if (level == 1 || level == 2 || level == 3 || level == 5 || level == 6 || level == 7) {

                score += 200;
                scoreLabel.setText("🏆 Score: " + score);
            }

            level++;

            levelLabel.setText("⭐ Level: " + level);

            showMessage("LEVEL " + level);

            updateLevelSettings();

            plane.resetPosition();

            clearMovingObjects();

            edgeHandled = false;
            enemyDirection = 1;
            enemyMoveCounter = 0;

            if (level == 4) {

                boss = new BossLevel4(300, 50, 50, 500);
                backgroundLabel.add(boss);
            }

            else if (level == 8) {

                boss = new BossLevel8(290, 40, 100, 1000);
                backgroundLabel.add(boss);
            }

            else {
                grid();
            }

            backgroundLabel.revalidate();
            backgroundLabel.repaint();
        }
    }

    //----------------------------------------------------------------
    //BOSS
    private void moveBoss() {

        if (boss != null) {
            boss.moveBoss();
        }
    }

    private void bossLevelFinished() {

        level++;

        levelLabel.setText("⭐ Level: " + level);

        updateLevelSettings();

        plane.resetPosition();

        clearMovingObjects();

        if (level == 9) {
            SoundManager.stopBackgroundMusic();
            DatabaseManager.saveGameRecord(score, 8);
            gameOver = true;
            gameOverLabel.setText("YOU WIN");
            gameOverLabel.setForeground(Color.WHITE);
            gameOverLabel.setVisible(true);

            DatabaseManager.saveScore(score, level);

            if (DatabaseManager.getGameOverSound() == 1) {

                SoundManager.playSoundWithVolume("C:\\Users\\Asus\\Downloads\\sound-effects-20260621T162013Z-3-001\\sound-effects\\mixkit-retro-arcade-game-over-470.wav", 6.0f);
            }
        }

        else {
            showMessage("LEVEL " + level);
            grid();
        }

        backgroundLabel.revalidate();
        backgroundLabel.repaint();
    }

    private void clearMovingObjects() {

        for (Egg egg : eggs) {
            backgroundLabel.remove(egg);
        }

        for (Bullet enemyBullet : enemyBullets) {
            backgroundLabel.remove(enemyBullet);
        }

        for (Bullet b : bullets) {
            backgroundLabel.remove(b);
        }

        bullets.clear();
        eggs.clear();
        enemyBullets.clear();
    }

    //----------------------------------------------------------------
    //Shooter enemy
    public void shooterEnemiesShoot() {
        if (freezeActive) {

            return;
        }

        if (boss != null) {
            return;
        }

        for (int i = 0; i < cells.size(); i++) {

            Cell cell = cells.get(i);
            Enemy enemy = cell.getEnemy();

            if (enemy instanceof ShooterEnemy) {

                Bullet b = ((ShooterEnemy) enemy).shoot();

                if (b != null) {

                    enemyBullets.add(b);
                    backgroundLabel.add(b);
                }
            }
        }
    }

    //----------------------------------------------------------------
    //Enemy bullets
    public void moveEnemyBullets() {
        if (freezeActive) {

            return;
        }

        for (int i = 0; i < enemyBullets.size(); i++) {

            Bullet b = enemyBullets.get(i);

            b.enemyMovement();
            if (b.hitPlane(plane)) {

                showExplosion(plane.getXPosition(), plane.getYPosition());

                backgroundLabel.remove(b);
                enemyBullets.remove(i--);

                if (!shieldActive) {

                    loseLife();
                }

                else {

                }
            }

            else if (b.getY() > 600) {

                backgroundLabel.remove(b);
                enemyBullets.remove(i--);
            }
        }
    }

    //----------------------------------------------------------------
    // Drop Egg
    private void dropEgg() {

        if (freezeActive) {

            return;
        }

        long currentTime = System.currentTimeMillis();

        //Boss eggs
        if (boss != null) {

            int bossEggInterval;

            if (level == 8)
                bossEggInterval = 1000;
            else
                bossEggInterval = 1500;

            if (currentTime - lastBossEggTime >= bossEggInterval) {

                int bossX = boss.getX() + boss.getWidth() / 2 - 20;
                int bossY = boss.getY() + boss.getHeight() / 2 - 25;

                Egg eggDown = new Egg(bossX, bossY, 0, 1);
                Egg eggUp = new Egg(bossX, bossY, 0, -1);
                Egg eggRight = new Egg(bossX, bossY, 1, 0);
                Egg eggLeft = new Egg(bossX, bossY, -1, 0);

                eggs.add(eggDown);
                eggs.add(eggUp);
                eggs.add(eggRight);
                eggs.add(eggLeft);

                backgroundLabel.add(eggDown);
                backgroundLabel.add(eggUp);
                backgroundLabel.add(eggRight);
                backgroundLabel.add(eggLeft);

                if (level == 8) {

                    Egg eggDownRight = new Egg(bossX, bossY, 1, 1);
                    Egg eggDownLeft = new Egg(bossX, bossY, -1, 1);
                    Egg eggUpRight = new Egg(bossX, bossY, 1, -1);
                    Egg eggUpLeft = new Egg(bossX, bossY, -1, -1);

                    eggs.add(eggDownRight);
                    eggs.add(eggDownLeft);
                    eggs.add(eggUpRight);
                    eggs.add(eggUpLeft);

                    backgroundLabel.add(eggDownRight);
                    backgroundLabel.add(eggDownLeft);
                    backgroundLabel.add(eggUpRight);
                    backgroundLabel.add(eggUpLeft);
                }

                lastBossEggTime = currentTime;
            }

            return;
        }

        //Normal enemies eggs
        if (currentTime - lastEggDropTime >= eggDropInterval && !cells.isEmpty()) {

            int randomIndex = (int)(Math.random() * cells.size());
            Enemy enemy = cells.get(randomIndex).getEnemy();

            Egg egg = new Egg(enemy.getXPosition() + 10, enemy.getYPosition() + 40);

            eggs.add(egg);
            backgroundLabel.add(egg);

            lastEggDropTime = currentTime;
        }
    }

    //----------------------------------------------------------------
    // Eggs Movement
    public void moveEggs() {
        if (freezeActive) {

            return;
        }

        for (int i = 0; i < eggs.size(); i++) {

            Egg egg = eggs.get(i);

            egg.movement();

            boolean removed = false;

            // HIT PLAYER
            if (egg.hitPlane(plane)) {

                showExplosion(plane.getXPosition(), plane.getYPosition());

                backgroundLabel.remove(egg);
                eggs.remove(i--);

                removed = true;

                if (!shieldActive) {

                    loseLife();
                }

                else {

                }
            }

            // OUT OF SCREEN
            if (!removed && egg.isOutOfScreen()) {

                backgroundLabel.remove(egg);
                eggs.remove(i--);
            }
        }
    }

    //----------------------------------------------------------------
    // Explosion
    private void showExplosion(int x, int y) {

        Explosion explosion = new Explosion(x, y);

        backgroundLabel.add(explosion);
        backgroundLabel.repaint();

        Timer explosionTimer = new Timer(300, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                backgroundLabel.remove(explosion);
                backgroundLabel.repaint();
            }
        });

        explosionTimer.setRepeats(false);
        explosionTimer.start();
    }

    //----------------------------------------------------------------

    //----------------------------------------------------------------
// Message
    private void showMessage(String text) {

        messageLabel.setText(text);
        messageLabel.setVisible(true);

        backgroundLabel.setComponentZOrder(messageLabel, 0);
        backgroundLabel.repaint();

        Timer messageTimer = new Timer(1200, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                messageLabel.setVisible(false);
            }
        });

        messageTimer.setRepeats(false);
        messageTimer.start();
    }


    //----------------------------------------------------------------
    //Lose life
    public void loseLife() {

        if (lives > 0) {

            lives--;
            livesLabel.setText("❤️ Lives: " + lives);

            if (lives == 0) {
                SoundManager.stopBackgroundMusic();
                DatabaseManager.saveGameRecord(score, level);

                if (DatabaseManager.getGameOverSound() == 1) {

                    SoundManager.playSoundWithVolume("C:\\Users\\Asus\\Downloads\\sound-effects-20260621T162013Z-3-001\\sound-effects\\mixkit-retro-arcade-game-over-470.wav", 6.0f);
                }

                gameOver = true;
                gameOverLabel.setText("GAME OVER");
                gameOverLabel.setForeground(Color.WHITE);
                gameOverLabel.setVisible(true);

                DatabaseManager.saveScore(score, level);
            }

            else {

                if (DatabaseManager.getCrashSound() == 1) {

                    SoundManager.playSoundWithVolume("C:\\Users\\Asus\\Downloads\\sound-effects-20260621T162013Z-3-001\\sound-effects\\mixkit-epic-impact-afar-explosion-2782.wav", -12.0f);
                }
            }
        }
    }

    //----------------------------------------------------------------
    //Handle keys
    private void handleKeys() {

        if (rightPressed) {
            plane.moveRight();
        }

        if (leftPressed) {
            plane.moveLeft();
        }

        if (upPressed) {
            plane.moveUp();
        }

        if (downPressed) {
            plane.moveDown();
        }

        if (spacePressed) {

            shootBullet();
        }
    }

    //----------------------------------------------------------------
    //Shoot bullet
    private void shootBullet() {

        long now = System.currentTimeMillis();

        int currentShootDelay = shootDelay;

        if (now < rapidFireEndTime) {

            currentShootDelay = 120;
        }

        if (now - lastBulletShotTime >= currentShootDelay) {

            int startX = plane.getXPosition() + 28;
            int startY = plane.getYPosition();

            for (int i = 0; i < fireCount; i++) {

                int bulletX = startX + (i * 12) - ((fireCount - 1) * 6);

                Bullet bullet = new Bullet(bulletX, startY);

                bullets.add(bullet);
                backgroundLabel.add(bullet);
            }

            if (DatabaseManager.getShotSound() == 1) {

                if (now - lastShotSoundTime >= 250) {

                    SoundManager.playShotSound("C:\\Users\\Asus\\Downloads\\sound-effects-20260621T162013Z-3-001\\sound-effects\\mixkit-short-laser-gun-shot-1670.wav");

                    lastShotSoundTime = now;
                }
            }

            lastBulletShotTime = now;
        }
    }

    //----------------------------------------------------------------
    //KEYBOARD
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            rightPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            leftPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            upPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            downPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_P) {
            paused = !paused;
            pauseLabel.setVisible(paused);
            backgroundLabel.setComponentZOrder(pauseLabel, 0);
            backgroundLabel.repaint();
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            SoundManager.stopBackgroundMusic();
            gameMain.showMainMenu();
        }
    }

    //----------------------------------------------------------------
    //POWER UP
    private void createPowerUp(int x, int y) {

        int chance = (int)(Math.random() * 100);

        if (chance < 20) {

            String type = randomPowerUpType();

            PowerUp powerUp = new PowerUp(x, y, type);

            powerUps.add(powerUp);
            backgroundLabel.add(powerUp);
        }
    }

    private String randomPowerUpType() {

        int random = (int)(Math.random() * 5);

        if (random == 0) {
            return "RAPID_FIRE";
        }

        else if (random == 1) {
            return "FREEZE";
        }

        else if (random == 2) {
            return "EXTRA_LIFE";
        }

        else if (random == 3) {
            return "SHIELD";
        }

        else {
            return "ADD_FIRE";
        }
    }

    private void movePowerUps() {

        for (int i = 0; i < powerUps.size(); i++) {

            PowerUp powerUp = powerUps.get(i);

            powerUp.movement();

            if (powerUp.hitPlane(plane)) {

                applyPowerUp(powerUp.getType());

                backgroundLabel.remove(powerUp);
                powerUps.remove(i--);
            }

            else if (powerUp.isOutOfScreen()) {

                backgroundLabel.remove(powerUp);
                powerUps.remove(i--);
            }
        }
    }

    private void applyPowerUp(String type) {

        if (type.equals("RAPID_FIRE")) {

            rapidFireEndTime = System.currentTimeMillis() + 8000;
            powerLabel.setText("⚡ Power: Rapid");
        }

        else if (type.equals("FREEZE")) {

            freezeActive = true;
            freezeEndTime = System.currentTimeMillis() + 3000;
            powerLabel.setText("❄️ Power: Freeze");
        }

        else if (type.equals("EXTRA_LIFE")) {

            if (lives < 5) {

                lives++;
                livesLabel.setText("❤️ Lives: " + lives);
            }
        }

        else if (type.equals("SHIELD")) {

            shieldActive = true;
            shieldEndTime = System.currentTimeMillis() + 10000;
            powerLabel.setText("🛡 Power: Shield");
        }

        else if (type.equals("ADD_FIRE")) {

            if (fireCount < 5) {

                fireCount++;
                fireLabel.setText("🔥 Fire: " + fireCount);
            }

            else {

            }
        }
    }

    private void checkPowerUpTimes() {

        long now = System.currentTimeMillis();

        if (shieldActive) {

            livesLabel.setForeground(Color.CYAN);
        }

        else {

            livesLabel.setForeground(Color.WHITE);
        }

        if (shieldActive && now > shieldEndTime) {

            shieldActive = false;
            livesLabel.setForeground(Color.WHITE);
        }

        if (freezeActive && now > freezeEndTime) {

            freezeActive = false;
        }

        if (now > rapidFireEndTime && !shieldActive && !freezeActive) {

            powerLabel.setText("⚡ Power: None");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            rightPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            leftPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            upPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            downPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
}