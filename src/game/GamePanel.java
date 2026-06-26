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

    //BULLET
    private long lastBulletShotTime;
    private ArrayList<Bullet> bullets;

    //ENEMY
    private ArrayList<Cell> cells;
    private ArrayList<Bullet> enemyBullets;
    private int enemyDirection;
    private double enemySpeed;
    private double enemyMoveCounter;
    private int verticalStep;
    private int eggDropInterval;

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

    private int level;
    private JLabel levelLabel;

    private boolean paused;
    private JLabel pauseLabel;

    private boolean gameOver;
    private JLabel gameOverLabel;

    private JLabel fireLabel;

    private int rows = 5;
    private int cols = 8;

    //----------------------------------------------------------------

    //Constructor
    public GamePanel(GameMain gameMain) {

        this.gameMain = gameMain;
        setLayout(null);

        lastBulletShotTime = 0;
        lastEggDropTime = 0;
        lastBossEggTime = 0;

        level = 1;
        enemyDirection = 1;
        enemySpeed = 1;
        enemyMoveCounter = 0;
        verticalStep = 20;
        eggDropInterval = 3000;

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

        //Score
        score = 0;
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(50, 50, 200, 40);
        backgroundLabel.add(scoreLabel);

        //Lives
        lives = 3;
        livesLabel = new JLabel("Lives: 3");
        livesLabel.setForeground(Color.WHITE);
        livesLabel.setBounds(50, 0, 200, 40);
        backgroundLabel.add(livesLabel);

        //Level
        levelLabel = new JLabel("LEVEL: 1");
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setBounds(650, 0, 120, 40);
        backgroundLabel.add(levelLabel);

        //Pause
        paused = false;
        pauseLabel = new JLabel("PAUSED");
        pauseLabel.setForeground(Color.WHITE);
        pauseLabel.setFont(new Font("Arial", Font.BOLD, 50));
        pauseLabel.setBounds(290, 240, 250, 70);
        pauseLabel.setVisible(false);
        backgroundLabel.add(pauseLabel);

        //Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(650, 80, 100, 30);
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
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 50));
        gameOverLabel.setBounds(250, 240, 350, 70);
        gameOverLabel.setVisible(false);
        backgroundLabel.add(gameOverLabel);

        //level Message
        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.YELLOW);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 28));
        messageLabel.setBounds(220, 180, 400, 60);
        messageLabel.setVisible(false);
        backgroundLabel.add(messageLabel);

        //Fire
        fireLabel = new JLabel("Fire: 1");
        fireLabel.setForeground(Color.WHITE);
        fireLabel.setBounds(650, 40, 120, 40);
        backgroundLabel.add(fireLabel);

        setFocusable(true);
        addKeyListener(this);

        //Timer
        timer = new Timer(16, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (!paused && !gameOver) {

                    moveBullets();
                    moveEnemies();
                    moveBoss();
                    dropEgg();
                    moveEggs();
                    moveEnemyBullets();
                    shooterEnemiesShoot();
                    movePowerUps();
                    checkPowerUpTimes();
                }
            }
        });

        timer.start();
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

        backgroundLabel.repaint();
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

                if (DatabaseManager.getCrashSound() == 1) {

                    SoundManager.playSoundWithVolume("C:\\Users\\Asus\\Downloads\\sound-effects-20260621T162013Z-3-001\\sound-effects\\mixkit-epic-impact-afar-explosion-2782.wav", -12.0f);
                }

                backgroundLabel.remove(bullet);
                bullets.remove(i--);

                if (boss.isDead()) {

                    score += boss.getScore();
                    scoreLabel.setText("Score: " + score);

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
                        scoreLabel.setText("Score: " + score);

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

        backgroundLabel.repaint();
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

        if (hitEdge) {

            enemyDirection *= -1;

            for (Cell cell : cells)
                cell.getEnemy().moveVertical(verticalStep);
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
                scoreLabel.setText("Score: " + score);
                showMessage("LEVEL COMPLETE +200");
            }

            level++;

            levelLabel.setText("LEVEL: " + level);

            updateLevelSettings();

            plane.resetPosition();

            clearMovingObjects();

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

        levelLabel.setText("LEVEL: " + level);

        updateLevelSettings();

        plane.resetPosition();

        clearMovingObjects();

        if (level == 9) {
            SoundManager.stopBackgroundMusic();
            DatabaseManager.saveGameRecord(score, 8);
            gameOver = true;
            gameOverLabel.setText("YOU WIN");
            gameOverLabel.setVisible(true);

            DatabaseManager.saveScore(score, level);

            if (DatabaseManager.getGameOverSound() == 1) {

                SoundManager.playSoundWithVolume("C:\\Users\\Asus\\Downloads\\sound-effects-20260621T162013Z-3-001\\sound-effects\\mixkit-retro-arcade-game-over-470.wav", 6.0f);
            }
        }

        else {
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

                backgroundLabel.remove(b);
                enemyBullets.remove(i--);

                if (!shieldActive) {

                    loseLife();
                }

                else {

                    showMessage("SHIELD");
                }
            }

            else if (b.getY() > 600) {

                backgroundLabel.remove(b);
                enemyBullets.remove(i--);
            }
        }

        backgroundLabel.repaint();
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

                backgroundLabel.remove(egg);
                eggs.remove(i--);

                if (!shieldActive) {

                    loseLife();
                }

                else {

                    showMessage("SHIELD");
                }
            }


            // OUT OF SCREEN
            if (!removed && egg.isOutOfScreen()) {

                backgroundLabel.remove(egg);
                eggs.remove(i--);
            }
        }

        backgroundLabel.repaint();
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
    // Message
    private void showMessage(String text) {

        messageLabel.setText(text);
        messageLabel.setVisible(true);

        Timer messageTimer = new Timer(1000, new ActionListener() {

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
            livesLabel.setText("Lives: " + lives);

            if (lives == 0) {
                SoundManager.stopBackgroundMusic();
                DatabaseManager.saveGameRecord(score, level);

                if (DatabaseManager.getGameOverSound() == 1) {

                    SoundManager.playSoundWithVolume("C:\\Users\\Asus\\Downloads\\sound-effects-20260621T162013Z-3-001\\sound-effects\\mixkit-retro-arcade-game-over-470.wav", 6.0f);
                }

                gameOver = true;
                gameOverLabel.setText("GAME OVER");
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
    //KEYBOARD
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

                int shootDelay = 300;

                if (now < rapidFireEndTime) {

                    shootDelay = 100;
                }

                if (now - lastBulletShotTime >= shootDelay) {

                    int startX = plane.getXPosition() + 28;
                    int startY = plane.getYPosition();

                    for (int i = 0; i < fireCount; i++) {

                        int bulletX = startX + (i * 12) - ((fireCount - 1) * 6);

                        Bullet bullet = new Bullet(bulletX, startY);

                        bullets.add(bullet);
                        backgroundLabel.add(bullet);
                    }

                    if (DatabaseManager.getShotSound() == 1) {

                        SoundManager.playShotSound("C:\\Users\\Asus\\Downloads\\sound-effects-20260621T162013Z-3-001\\sound-effects\\mixkit-short-laser-gun-shot-1670.wav");

                    }

                    lastBulletShotTime = now;
                }
            }

        }

        if (e.getKeyCode() == KeyEvent.VK_P) {
            paused = !paused;
            pauseLabel.setVisible(paused);
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

            backgroundLabel.revalidate();
            backgroundLabel.repaint();
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

        backgroundLabel.repaint();
    }

    private void applyPowerUp(String type) {

        if (type.equals("RAPID_FIRE")) {

            rapidFireEndTime = System.currentTimeMillis() + 8000;
            showMessage("RAPID FIRE");
        }

        else if (type.equals("FREEZE")) {

            freezeActive = true;
            freezeEndTime = System.currentTimeMillis() + 3000;
            showMessage("FREEZE");
        }

        else if (type.equals("EXTRA_LIFE")) {

            if (lives < 5) {

                lives++;
                livesLabel.setText("Lives: " + lives);
            }

            showMessage("EXTRA LIFE");
        }

        else if (type.equals("SHIELD")) {

            shieldActive = true;
            shieldEndTime = System.currentTimeMillis() + 10000;
            showMessage("SHIELD");
        }

        else if (type.equals("ADD_FIRE")) {

            if (fireCount < 5) {

                fireCount++;
                fireLabel.setText("Fire: " + fireCount);
                showMessage("ADD FIRE");
            }

            else {

                showMessage("MAX FIRE");
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
    }



    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}