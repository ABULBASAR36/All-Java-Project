
package pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class PacmanGame extends JPanel implements ActionListener {

    private static final int BOARD_WIDTH = 400;
    private static final int BOARD_HEIGHT = 400;
    private static final int UNIT_SIZE = 20;
    private static final int DELAY = 100; // Milliseconds between each frame update
    private int pacmanX = 200;
    private int pacmanY = 200;
    private int pacmanSize = 20;
    private int dx = 0;
    private int dy = 0;
    private Timer timer;
    private ArrayList<Point> food;
    private boolean gameOver;

    public PacmanGame() {
        initBoard();
    }

    private void initBoard() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new PacmanKeyListener());
        timer = new Timer(DELAY, this);
        timer.start();
        initFood();
    }

    private void initFood() {
        food = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(BOARD_WIDTH / UNIT_SIZE) * UNIT_SIZE;
            int y = random.nextInt(BOARD_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
            food.add(new Point(x, y));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFood(g);
        drawPacman(g);
        if (gameOver) {
            drawGameOver(g);
        }
    }

    private void drawFood(Graphics g) {
        g.setColor(Color.yellow);
        for (Point point : food) {
            g.fillOval(point.x, point.y, UNIT_SIZE, UNIT_SIZE);
        }
    }

    private void drawPacman(Graphics g) {
        g.setColor(Color.yellow);
        g.fillArc(pacmanX, pacmanY, pacmanSize, pacmanSize, 45, 270);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Game Over!", BOARD_WIDTH / 2 - 80, BOARD_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            updatePacman();
            checkCollisions();
        }
        repaint();
    }

    private void updatePacman() {
        pacmanX += dx;
        pacmanY += dy;

        if (pacmanX < 0) {
            pacmanX = BOARD_WIDTH - pacmanSize;
        }
        if (pacmanX >= BOARD_WIDTH) {
            pacmanX = 0;
        }
        if (pacmanY < 0) {
            pacmanY = BOARD_HEIGHT - pacmanSize;
        }
        if (pacmanY >= BOARD_HEIGHT) {
            pacmanY = 0;
        }
    }

    private void checkCollisions() {
        Rectangle pacmanRect = new Rectangle(pacmanX, pacmanY, pacmanSize, pacmanSize);
        for (int i = 0; i < food.size(); i++) {
            Point foodPoint = food.get(i);
            Rectangle foodRect = new Rectangle(foodPoint.x, foodPoint.y, UNIT_SIZE, UNIT_SIZE);
            if (pacmanRect.intersects(foodRect)) {
                food.remove(i);
                break;
            }
        }
        // Check for game over condition (e.g., collision with ghost)
        // For simplicity, we'll just end the game if Pacman reaches the edge of the board
        if (pacmanX < 0 || pacmanX >= BOARD_WIDTH || pacmanY < 0 || pacmanY >= BOARD_HEIGHT) {
            gameOver = true;
        }
    }

    private class PacmanKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    dx = -UNIT_SIZE;
                    dy = 0;
                    break;
                case KeyEvent.VK_RIGHT:
                    dx = UNIT_SIZE;
                    dy = 0;
                    break;
                case KeyEvent.VK_UP:
                    dx = 0;
                    dy = -UNIT_SIZE;
                    break;
                case KeyEvent.VK_DOWN:
                    dx = 0;
                    dy = UNIT_SIZE;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pacman");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new PacmanGame());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
