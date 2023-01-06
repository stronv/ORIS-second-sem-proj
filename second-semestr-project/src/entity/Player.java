package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler, String username) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.username = username;
        screenY  = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);
        getPlayerImage();
        setDefaultValues();
    }


    public void setDefaultValues() {
        x = gamePanel.tileSize * 5;
        y = gamePanel.tileSize * 28;
        speed = 4;
        direction = "left";
        username = "player1";
    }

    public void getPlayerImage() {
        try{
            moveLeft = ImageIO.read(getClass().getResourceAsStream("/resources/player/mouse1.png"));
            moveRight = ImageIO.read(getClass().getResourceAsStream("/resources/player/mouse2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyHandler.leftPressed == true) {
            direction = "left";
            x -= speed;
        }
        else if (keyHandler.rightPressed == true) {
            direction = "right";
            x += speed;
        } else if (keyHandler.upPressed == true) {
            direction = "up";
            y -= speed;
        } else if (keyHandler.downPressed == true) {
            direction = "down";
            y += speed;
        }
        spriteCounter++;
        if(spriteCounter > 20) {
            if(spriteNumber == 1) {
                spriteNumber = 2;
            }
            else if(spriteNumber == 2) {
                 spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;
        switch (direction){
            case "left":
                if(spriteNumber == 1) {
                    image = moveLeft;
                }
                if(spriteNumber == 2) {
                    image = moveRight;
                }
                break;

            case "right":
                if(spriteNumber == 1) {
                    image = moveRight;
                }
                if(spriteNumber == 2) {
                    image = moveLeft;
                }
                break;

            case "up":
                if(spriteNumber == 1) {
                    image = moveRight;
                }
                if(spriteNumber == 2) {
                    image = moveLeft;
                }
                break;

            case "down":
                if(spriteNumber == 1) {
                    image = moveRight;
                }
                if(spriteNumber == 2) {
                    image = moveLeft;
                }
                break;
        }
        graphics2D.drawImage(image, x, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
