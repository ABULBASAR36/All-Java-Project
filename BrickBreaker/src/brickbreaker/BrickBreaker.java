
package brickbreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BrickBreaker extends JPanel implements ActionListener {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int PADDLE_WIDTH = 80;
    private static final int PADDLE_HEIGHT = 10;
    private static final int BALL_DIAMETER = 20;
    private static final int BRICK_WIDTH = 50;
    private static final int BRICK_HEIGHT = 20;
    private static final int BRICK_ROWS = 5;
    private static final int BRICK_COLUMNS = 10;
    private static final int INITIAL_PADDLE_X = 260;
    private static final int INITIAL_PADDLE_Y = 360;
    private static final int INITIAL_BALL_X = 285;
    private static final int INITIAL_BALL_Y = 340;
    private static final int BALL_SPEED = 4;

    private int paddleX = INITIAL_PADDLE_X;
    private int ballX = INITIAL_BALL_X;
    private int ballY = INITIAL_BALL_Y;
    private int ballXDir = -1;
    private int ballYDir = -2;
    private Timer timer;
    private boolean playing = true;
    private boolean bricks[][];

    public BrickBreaker() {
        bricks = new boolean[BRICK_ROWS][BRICK_COLUMNS];
        for (int i = 0; i < BRICK_ROWS; i++) {
            for (int j = 0; j < BRICK_COLUMNS; j++) {
                bricks[i][j] = true;
            }
        }
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new PaddleListener());
        timer = new Timer(5, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (playing) {
            // Draw paddle
            g.setColor(Color.white);
            g.fillRect(paddleX, INITIAL_PADDLE_Y, PADDLE_WIDTH, PADDLE_HEIGHT);
            // Draw ball
            g.setColor(Color.white);
            g.fillOval(ballX, ballY, BALL_DIAMETER, BALL_DIAMETER);
            // Draw bricks
            for (int i = 0; i < BRICK_ROWS; i++) {
                for (int j = 0; j < BRICK_COLUMNS; j++) {
                    if (bricks[i][j]) {
                        g.setColor(Color.white);
                        g.fillRect(j * BRICK_WIDTH, i * BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT);
                    }
                }
            }
        } else {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            FontMetrics metrics = g.getFontMetrics();
            String msg = "Game Over! Press R to Restart";
            g.drawString(msg, (WIDTH - metrics.stringWidth(msg)) / 2, HEIGHT / 2);
        }
    }

    public void actionPerformed(ActionEvent e) {
        moveBall();
        checkCollision();
        repaint();
    }

    private void moveBall() {
        ballX += ballXDir;
        ballY += ballYDir;
        if (ballX <= 0 || ballX >= WIDTH - BALL_DIAMETER) {
            ballXDir = -ballXDir;
        }
        if (ballY <= 0) {
            ballYDir = -ballYDir;
        }
        if (ballY >= HEIGHT - BALL_DIAMETER) {
            playing = false;
        }
    }

    private void checkCollision() {
        // Paddle collision
        if (ballY + BALL_DIAMETER >= INITIAL_PADDLE_Y && ballY + BALL_DIAMETER <= INITIAL_PADDLE_Y + PADDLE_HEIGHT) {
            if (ballX >= paddleX && ballX <= paddleX + PADDLE_WIDTH) {
                ballYDir = -ballYDir;
            }
        }
        // Brick collision
        for (int i = 0; i < BRICK_ROWS; i++) {
            for (int j = 0; j < BRICK_COLUMNS; j++) {
                if (bricks[i][j]) {
                    int brickX = j * BRICK_WIDTH;
                    int brickY = i * BRICK_HEIGHT;
                    if (ballX + BALL_DIAMETER >= brickX && ballX <= brickX + BRICK_WIDTH
                            && ballY + BALL_DIAMETER >= brickY && ballY <= brickY + BRICK_HEIGHT) {
                        ballYDir = -ballYDir;
                        bricks[i][j] = false;
                    }
                }
            }
        }
    }

    private class PaddleListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_LEFT) {
                if (paddleX > 0) {
                    paddleX -= 20;
                }
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                if (paddleX < WIDTH - PADDLE_WIDTH) {
                    paddleX += 20;
                }
            } else if (keyCode == KeyEvent.VK_R) {
                reset();
            }
        }
    }

    private void reset() {
        paddleX = INITIAL_PADDLE_X;
        ballX = INITIAL_BALL_X;
        ballY = INITIAL_BALL_Y;
        ballXDir = -1;
        ballYDir = -2;
        playing = true;
        for (int i = 0; i < BRICK_ROWS; i++) {
            for (int j = 0; j < BRICK_COLUMNS; j++) {
                bricks[i][j] = true;
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Brick Breaker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new BrickBreaker());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

