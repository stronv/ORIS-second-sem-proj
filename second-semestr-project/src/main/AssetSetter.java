package main;

import entity.Cat;
import object.Cheese;
import object.Hole;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.object[0] = new Cheese();
        gamePanel.object[0].x = 5 * gamePanel.tileSize;
        gamePanel.object[0].y = 3 * gamePanel.tileSize;

        gamePanel.object[1] = new Cheese();
        gamePanel.object[1].x = 2 * gamePanel.tileSize;
        gamePanel.object[1].y = 18 * gamePanel.tileSize;

        gamePanel.object[2] = new Cheese();
        gamePanel.object[2].x = 8 * gamePanel.tileSize;
        gamePanel.object[2].y = 11 * gamePanel.tileSize;

        gamePanel.object[3] = new Hole();
        gamePanel.object[3].x = 5 * gamePanel.tileSize;
        gamePanel.object[3].y = 1 * gamePanel.tileSize;
    }

    public void setCats() {
        gamePanel.cats[0] = new Cat(gamePanel, "cat1");
        gamePanel.cats[0].x = gamePanel.tileSize * 2;
        gamePanel.cats[0].y = gamePanel.tileSize * 15;

        gamePanel.cats[1] = new Cat(gamePanel, "cat2");
        gamePanel.cats[1].x = gamePanel.tileSize * 5;
        gamePanel.cats[1].y = gamePanel.tileSize * 7;

        gamePanel.cats[2] = new Cat(gamePanel, "cat1");
        gamePanel.cats[2].x = gamePanel.tileSize * 8;
        gamePanel.cats[2].y = gamePanel.tileSize * 20;
    }
}
