package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x;
    public int y;
    public int speed;
    public BufferedImage moveLeft;
    public BufferedImage moveRight;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public Rectangle solidPart;
    public boolean collisionOn = false;
}
