package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    GamePanel gamePanel;
    public int x;
    public int y;
    public int speed;
    public BufferedImage moveLeft;
    public BufferedImage moveRight;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public Rectangle solidPart = new Rectangle(0, 0, 32, 32);
    public int solidPartDefaultX;
    public int solidPartDefaultY;
    public boolean collisionOn = false;


    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
