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

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
    }

    Thread gameThread;

    public void startGameThread() {
        gameThread = new Thread();
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {

            update();

            repaint();

        }
    }

    public void update() {

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(Color.white);

        graphics2D.fillRect(384, 520, titleSize, titleSize);

        graphics2D.dispose();
    }
}
