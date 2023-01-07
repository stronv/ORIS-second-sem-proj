package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public String name;
    public boolean collision = false;
    public BufferedImage image;
    public int x, y;
    public Rectangle solidPart = new Rectangle(0, 0, 32, 32);
    public int solidPartDefaultX = 0;
    public int solidPartDefaultY = 0;

    public void draw(Graphics2D graphics2D, GamePanel gamePanel) {
        int screenX = (x - gamePanel.mouse.x + gamePanel.mouse.x);
        int screenY = (y - gamePanel.mouse.y + gamePanel.mouse.screenY);

            if(x + gamePanel.tileSize > gamePanel.mouse.x - gamePanel.mouse.screenX &&
                    x - gamePanel.tileSize < gamePanel.mouse.x + gamePanel.mouse.screenX &&
                    y + gamePanel.tileSize > gamePanel.mouse.y - gamePanel.mouse.screenY &&
                    y - gamePanel.tileSize < gamePanel.mouse.y + gamePanel.mouse.screenY){
        graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }
    }
}
