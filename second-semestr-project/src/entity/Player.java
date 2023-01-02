package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update() {

        if (keyHandler.leftPressed == true) {
            x -= speed;
        }
        else if (keyHandler.rightPressed == true) {
            x += speed;
        }
    }

    public void draw(Graphics2D graphics2D) {

        graphics2D.setColor(Color.white);

        graphics2D.fillRect(x, y, gamePanel.titleSize, gamePanel.titleSize);

    }
}
