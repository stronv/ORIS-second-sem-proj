package main;

import object.Cheese;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gamePanel;
    Font courier_new;
    Font courier_new_60B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public boolean gameLost = false;
    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        courier_new = new Font("Courier New", Font.PLAIN, 30);
        courier_new_60B = new Font("Courier New", Font.BOLD, 50 );
    }

    public void ShowMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D graphics2D) {

        if (gameLost == true) {
            graphics2D.setFont(courier_new);
            graphics2D.setColor(Color.yellow);

            String text;
            int textLength;
            int x;
            int y;

            text = "You Lost!";
            //чтобы текст был выровнен по центру
            textLength = (int)graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = gamePanel.screenWidth/2 - textLength/2;
            y = gamePanel.screenHeight/2 - (gamePanel.tileSize * 3);
            graphics2D.drawString(text, x, y);
        }


        //игра закончилась?
        if (gameFinished == true) {
            graphics2D.setFont(courier_new);
            graphics2D.setColor(Color.yellow);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the burrow!";
            //чтобы текст был выровнен по центру
            textLength = (int)graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = gamePanel.screenWidth/2 - textLength/2;
            y = gamePanel.screenHeight/2 - (gamePanel.tileSize * 3);
            graphics2D.drawString(text, x, y);


            graphics2D.setFont(courier_new_60B);
            graphics2D.setColor(Color.black);

            text = "Сongratulations!";
            textLength = (int)graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = gamePanel.screenWidth/2 - textLength/2;
            y = gamePanel.screenHeight/2 + (gamePanel.tileSize * 2);
            graphics2D.drawString(text, x, y);

            gamePanel.gameThread = null;
        } else {
            // количество сыра
            graphics2D.setFont(courier_new);
            graphics2D.setColor(Color.yellow);
            graphics2D.drawString("Cheese: " + gamePanel.mouse.hasCheese, 50, 50);
            //сообщения
            if(messageOn == true) {
                graphics2D.setFont(graphics2D.getFont().deriveFont(20F));
                graphics2D.drawString(message, 50, gamePanel.tileSize*3);

                messageCounter++;

                if(messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }

    }
}
