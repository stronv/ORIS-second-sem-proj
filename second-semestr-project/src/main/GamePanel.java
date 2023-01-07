package main;

import entity.Entity;
import entity.Player;
import entity.PlayerMP;
import main.tile.TileManager;
import net.GameClient;
import net.GameServer;
import net.packets.Packet00Login;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenColumn = 11;
    public final int maxScreenRow = 15;
    public final int screenWidth = tileSize * maxScreenColumn;
    public final int screenHeight = tileSize * maxScreenRow;

    //настройки мира
    public final int maxWorldColumns = 11;
    public final int maxWorldRow = 30;
    public final int WorldWidth = tileSize * maxWorldColumns;
    public final int WorldHeight = tileSize *  maxWorldRow;

    KeyHandler keyHandler = new KeyHandler();

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }
    private GameClient socketClient;
    private GameServer socketServer;

    public Player mouse = new Player(this, keyHandler, "player1");
    private List<Entity> playerArrayList = new ArrayList<Entity>();

    public void addPlayer(Entity player) {
        this.playerArrayList.add(player);
    }

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

        if (JOptionPane.showConfirmDialog(this, "Do you want to run the server?") == 0) {
            socketServer = new GameServer(this);
            socketServer.start();
        }
        socketClient = new GameClient(this, "localhost");
        socketClient.start();
        socketClient.sendData("ping".getBytes());

        Packet00Login packet00Login = (new Packet00Login(JOptionPane.showInputDialog(this, "Enter your username")));
        packet00Login.writeData(socketClient);
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
        for (Entity p: playerArrayList) {
            p.update();
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        tileM.draw(graphics2D);

        for (Entity p: playerArrayList) {
            p.draw(graphics2D);
        }
        graphics2D.dispose();
    }
}
