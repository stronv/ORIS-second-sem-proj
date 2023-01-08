package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetAddress;

public class PlayerMP extends Player {

    public InetAddress ipAddress;
    public int port;

    public PlayerMP(GamePanel gamePanel, KeyHandler keyHandler, String username) {
        super(gamePanel, keyHandler, username);
        this.ipAddress = ipAddress;
        this.port = port;
        setDefaultValues();
    }

    public PlayerMP(GamePanel gamePanel,String username, InetAddress ipAddress, int port) {
        super(gamePanel, null, null);
        this.ipAddress = ipAddress;
        this.port = port;
        setDefaultValues();
    }

    @Override
    public void update() {
        if (keyHandler.upPressed1 == true) {
            direction = "up";
        }
        else if (keyHandler.downPressed1 == true) {
            direction = "down";
        } else if (keyHandler.leftPressed1 == true) {
            direction = "left";
        } else if (keyHandler.rightPressed1 == true) {
            direction = "right";
        }

        //проверка столковение поля
        collisionOn = false;
        gamePanel.collcheck.checkTile(this);
        //проверка столковения oбъекта
        int objectIndex = gamePanel.collcheck.checkObject(this, true);
        pickUpObject(objectIndex);
        //если столкновения не обнаружено то мышь может дальше двигаться
        if(collisionOn == false) {
            switch (direction) {
                case "up":
                    if (keyHandler.upPressed1 == true) {
                        y -= speed;
                    }
                    break;
                case "down":
                    if (keyHandler.downPressed1 == true) {
                        y += speed;
                    }
                    break;
                case "left":
                    if (keyHandler.leftPressed1 == true) {
                        x -= speed;
                    }
                    break;
                case "right":
                    if (keyHandler.rightPressed1 == true) {
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

    public void setDefaultValues() {
        x = gamePanel.tileSize * 5 + 60;
        y = gamePanel.tileSize * 28;
        speed = 4;
        direction = "right";
    }

    @Override
    public void getPlayerImage() {
        try{
            moveLeft = ImageIO.read(getClass().getResourceAsStream("/resources/player/blackmouse.png"));
            moveRight = ImageIO.read(getClass().getResourceAsStream("/resources/player/blackmouse2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;
        switch (direction) {
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
