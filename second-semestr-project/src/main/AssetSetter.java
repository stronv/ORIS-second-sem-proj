package main;

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
}
