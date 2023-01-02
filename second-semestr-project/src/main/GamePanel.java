package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    final int maxScreenColumn = 11; // Разделение окна на 13 равных частей;
    final int maxScreenRow = 15; // Разделение окна на 12 равных частей;
    final int screenWidth = tileSize * maxScreenColumn;
    final int screenHeight = tileSize * maxScreenRow;
    KeyHandler keyHandler = new KeyHandler();

    Player mouse = new Player(this, keyHandler);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    Thread gameThread;
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    int FPS = 60;

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();
//            System.out.println("Current Time is: " + currentTime );

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        mouse.update();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        mouse.draw(graphics2D);

        graphics2D.dispose();
    }
}
