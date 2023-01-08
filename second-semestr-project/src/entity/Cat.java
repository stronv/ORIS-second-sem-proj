package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Cat extends Entity {

    String username;
    int life;

    public Cat(GamePanel gamePanel, String username) {
        super(gamePanel);
        this.username = username;
        direction = "down";
        speed = 1;
        life = 1;

        solidPart = new Rectangle();
        solidPart.x = 0;
        solidPart.y = 0;
        solidPartDefaultX = solidPart.x;
        solidPartDefaultY = solidPart.y;
        solidPart.width = 32;
        solidPart.height = 32;
        setAction();
        getImage();
    }

    public void getImage() {
        try {
            if (username.equalsIgnoreCase("cat1")) {
                moveLeft = ImageIO.read(getClass().getResourceAsStream("/resources/NPC/blackcat_1.png"));
                moveRight = ImageIO.read(getClass().getResourceAsStream("/resources/NPC/blackcat_2.png"));
            }
            if (username.equalsIgnoreCase("cat2")) {
                moveLeft = ImageIO.read(getClass().getResourceAsStream("/resources/NPC/greycat_1.png"));
                moveRight = ImageIO.read(getClass().getResourceAsStream("/resources/NPC/greycat_2.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {
        Random random = new Random();
        int i = random.nextInt() + 1;

        if (i <= 25) {
            direction = "down";
        }

        if (i > 25 && i <= 50) {
            direction = "down";
        }

        if (i > 50 && i <= 75) {
            direction = "left";
        }
        if (i > 75 && i <= 100) {
            direction = "right";
        }

    }

    public void update() {
        collisionOn = false;
        gamePanel.collcheck.checkTile(this);
        gamePanel.collcheck.checkPlayer(this);


        if(collisionOn == false) {
            switch (direction) {
                case "up":  y -= speed;
                    break;
                case "down": y += speed;
                    break;
                case "left": x -= speed;
                    break;
                case "right": x += speed;
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
        int screenX = (x - gamePanel.mouse.x + gamePanel.mouse.x);
        int screenY = (y - gamePanel.mouse.y + gamePanel.mouse.screenY);
        BufferedImage image = null;
        if(x + gamePanel.tileSize > gamePanel.mouse.x - gamePanel.mouse.screenX &&
                x - gamePanel.tileSize < gamePanel.mouse.x + gamePanel.mouse.screenX &&
                y + gamePanel.tileSize > gamePanel.mouse.y - gamePanel.mouse.screenY &&
                y - gamePanel.tileSize < gamePanel.mouse.y + gamePanel.mouse.screenY) {
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
            graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }
}
