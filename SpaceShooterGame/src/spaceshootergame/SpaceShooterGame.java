
package spaceshootergame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpaceShooterGame extends JFrame implements KeyListener, Runnable {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PLAYER_SIZE = 50;
    private static final int PLAYER_SPEED = 5;
    private static final int BULLET_SPEED = 8;
    private static final int ENEMY_SIZE = 30;
    private static final int ENEMY_SPEED = 3;

    private boolean isRunning = true;
    private boolean[] keys;
    private int playerX, playerY;
    private List<Bullet> bullets;
    private List<Enemy> enemies;
    private Random random;

    public SpaceShooterGame() {
        setTitle("Space Shooter");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        keys = new boolean[256];
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        random = new Random();

        playerX = WIDTH / 2 - PLAYER_SIZE / 2;
        playerY = HEIGHT - PLAYER_SIZE - 20;

        addKeyListener(this);
        setFocusable(true);
    }

    public void run() {
        while (isRunning) {
            update();
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        movePlayer();
        moveBullets();
        spawnEnemies();
        moveEnemies();
        checkCollisions();
    }

    private void movePlayer() {
        if (keys[KeyEvent.VK_LEFT] && playerX > 0) {
            playerX -= PLAYER_SPEED;
        }
        if (keys[KeyEvent.VK_RIGHT] && playerX < WIDTH - PLAYER_SIZE) {
            playerX += PLAYER_SPEED;
        }
    }

    private void moveBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.y -= BULLET_SPEED;
            if (bullet.y < 0) {
                bullets.remove(i);
                i--;
            }
        }
    }

    private void spawnEnemies() {
        if (random.nextInt(100) < 2) {
            int enemyX = random.nextInt(WIDTH - ENEMY_SIZE);
            int enemyY = -ENEMY_SIZE;
            enemies.add(new Enemy(enemyX, enemyY));
        }
    }

    private void moveEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            enemy.y += ENEMY_SPEED;
            if (enemy.y > HEIGHT) {
                enemies.remove(i);
                i--;
            }
        }
    }

    private void checkCollisions() {
        Rectangle playerRect = new Rectangle(playerX, playerY, PLAYER_SIZE, PLAYER_SIZE);
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            Rectangle enemyRect = new Rectangle(enemy.x, enemy.y, ENEMY_SIZE, ENEMY_SIZE);
            if (playerRect.intersects(enemyRect)) {
                gameOver();
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            for (int j = 0; j < enemies.size(); j++) {
                Enemy enemy = enemies.get(j);
                Rectangle bulletRect = new Rectangle(bullet.x, bullet.y, bullet.size, bullet.size);
                Rectangle enemyRect = new Rectangle(enemy.x, enemy.y, ENEMY_SIZE, ENEMY_SIZE);
                if (bulletRect.intersects(enemyRect)) {
                    bullets.remove(i);
                    enemies.remove(j);
                    i--;
                    break;
                }
            }
        }
    }

    private void gameOver() {
        isRunning = false;
        JOptionPane.showMessageDialog(this, "Game Over!");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLUE);
        g.fillRect(playerX, playerY, PLAYER_SIZE, PLAYER_SIZE);
        for (Bullet bullet : bullets) {
            g.setColor(Color.WHITE);
            g.fillRect(bullet.x, bullet.y, bullet.size, bullet.size);
        }
        for (Enemy enemy : enemies) {
            g.setColor(Color.RED);
            g.fillRect(enemy.x, enemy.y, ENEMY_SIZE, ENEMY_SIZE);
        }
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length) {
            keys[keyCode] = true;
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            bullets.add(new Bullet(playerX + PLAYER_SIZE / 2, playerY));
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length) {
            keys[keyCode] = false;
        }
    }

    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        SpaceShooterGame game = new SpaceShooterGame();
        game.setVisible(true);
        new Thread(game).start();
    }

    private static class Bullet {
        int x, y, size;

        public Bullet(int x, int y) {
            this.x = x;
            this.y = y;
            this.size = 5;
        }
    }

    private static class Enemy {
        int x, y;

        public Enemy(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
