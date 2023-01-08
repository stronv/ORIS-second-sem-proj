package main;

import entity.Cat;
import entity.Entity;
import entity.Player;
import entity.PlayerMP;
import main.tile.TileManager;
import object.SuperObject;

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

    //настройки мира
    public final int maxWorldColumns = 11;
    public final int maxWorldRow = 30;
    public final int WorldWidth = tileSize * maxWorldColumns;
    public final int WorldHeight = tileSize *  maxWorldRow;

    KeyHandler keyHandler = new KeyHandler();

    public CollisionChecker collcheck = new CollisionChecker(this);
    public AssetSetter asSet = new AssetSetter(this);
    public UI ui = new UI(this);
    public Player mouse = new Player(this, keyHandler,  "player1");

    public Cat cats[]  = new Cat[3];

    public SuperObject object[] = new SuperObject[10];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void SetUpGame() {

        asSet.setObject();
        asSet.setCats();
    }

    Thread gameThread;
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
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
        //Player
        mouse.update();

        for(int i = 0; i < cats.length; i++){
            if(cats[i] != null) {
                cats[i].update();
            }
        }

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        //Tile
        tileM.draw(graphics2D);
        //obl
        for(int i = 0; i < object.length; i++){
            if(object[i] != null) {
                object[i].draw(graphics2D, this);
            }
        }

        for(int i = 0; i < cats.length; i++){
            if(cats[i] != null) {
                cats[i].draw(graphics2D);
            }
        }

        //player
        mouse.draw(graphics2D);
        //UI
        ui.draw(graphics2D);

        graphics2D.dispose();
    }
}
