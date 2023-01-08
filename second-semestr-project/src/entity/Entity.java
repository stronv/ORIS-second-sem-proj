package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public int x;
    public int y;
    public String username;
    public int speed;
    public BufferedImage moveLeft;
    public BufferedImage moveRight;

    public BufferedImage moveLeft1;
    public BufferedImage moveRight2;

    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;

    public abstract void getPlayerImage();

    public abstract void update();

    public abstract void draw(Graphics2D graphics2D);

    public String getUsername() {
        return username;
    }
}
