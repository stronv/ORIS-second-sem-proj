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

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenY  = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);
        solidPart = new Rectangle();
        solidPart.x = 8;
        solidPart.y = 0;
        solidPart.width = 16;
        solidPart.height = 32;
        getPlayerImage();
        setDefaultValues();
    }

    public void setDefaultValues() {
        x = gamePanel.tileSize * 5;
        y = gamePanel.tileSize * 28;
        speed = 4;
        direction = "left";
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

        if (keyHandler.upPressed == true) {
            direction = "up";
        }
        else if (keyHandler.downPressed == true) {
            direction = "down";
        } else if (keyHandler.leftPressed == true) {
            direction = "left";
        } else if (keyHandler.rightPressed == true) {
            direction = "right";
        }

        //проверка столковение поля
        collisionOn = false;
        gamePanel.collcheck.checkTile(this);

        //если столкновения не обнаружено то мышь может дальше двигаться
         if(collisionOn == false) {
             switch (direction) {
                 case "up":
                     if (keyHandler.upPressed == true) {
                        y -= speed;
                     }
                     break;
                 case "down":
                     if (keyHandler.downPressed == true) {
                         y += speed;
                     }
                     break;
                 case "left":
                     if (keyHandler.leftPressed == true) {
                         x -= speed;
                     }
                     break;
                 case "right":
                     if (keyHandler.rightPressed == true) {
                         x += speed;
                     }
                     break;
             }
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
