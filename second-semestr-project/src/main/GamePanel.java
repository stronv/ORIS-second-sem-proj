package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTitleSize = 16;
    final int scale = 3;
    final int titleSize = originalTitleSize * scale;
    final int maxScreenColumn = 16; // Разделение окна на 13 равных частей;
    final int maxScreenRow = 13; // Разделение окна на 12 равных частей;
    final int screenWidth = titleSize * maxScreenColumn;
    final int screenHeight = titleSize * maxScreenRow;
    KeyHandler keyHandler = new KeyHandler();

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

    int mouseX = 100;
    int mouseY = 100;
    int mouseSpeed = 10;
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

        if (keyHandler.leftPressed == true) {
            mouseX -= mouseSpeed;
        }
        else if (keyHandler.rightPressed == true) {
            mouseX += mouseSpeed;
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(Color.white);

        graphics2D.fillRect(mouseX, mouseY, titleSize, titleSize);

        graphics2D.dispose();
    }
}