package main;

import entity.Player;
import main.tile.TileManager;
import net.GameClient;
import net.GameServer;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenColumn = 11;
    public final int maxScreenRow = 15;
    public final int screenWidth = tileSize * maxScreenColumn;
    public final int screenHeight = tileSize * maxScreenRow;
    KeyHandler keyHandler = new KeyHandler();
    Player mouse = new Player(this, keyHandler);

    private GameClient socketClient;
    private GameServer socketServer;

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

        socketServer = new GameServer(this);
        socketServer.start();

        socketClient = new GameClient(this, "localhost");
        socketClient.start();
        socketClient.sendData("ping".getBytes());
    }

    int FPS = 60;
    TileManager tileM = new TileManager(this);

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();

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

        tileM.draw(graphics2D);

        mouse.draw(graphics2D);

        graphics2D.dispose();
    }
}
