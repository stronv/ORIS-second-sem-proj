package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gamePanel;
    public CollisionChecker(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
    int entityLeftX = entity.x + entity.solidPart.x;
    int entityRightX = entity.x + entity.solidPart.x + entity.solidPart.width;
    int entityUndersideY = entity.y + entity.solidPart.height;
    int entityTopY = entity.y + entity.solidPart.y;

    int entityLeftColumn = entityLeftX / gamePanel.tileSize;
    int entityRightColumn = entityRightX / gamePanel.tileSize;
    int entityTopRow = entityTopY / gamePanel.tileSize;
    int entityUndersideRow = entityUndersideY  / gamePanel.tileSize;

    int tileNumber1;
    int tileNumber2;

    switch (entity.direction) {
        case "up":
            entityTopRow = (entityTopY - entity.speed)/gamePanel.tileSize;
            tileNumber1 = gamePanel.tileM.mapTileNumber[entityLeftColumn][entityTopRow];
            tileNumber2 = gamePanel.tileM.mapTileNumber[entityRightColumn][entityTopRow];
            if(gamePanel.tileM.tile[tileNumber1].collision == true || gamePanel.tileM.tile[tileNumber2].collision == true) {
                entity.collisionOn = true;
            }
            break;
        case "down":
            entityUndersideRow = (entityUndersideY + entity.speed)/gamePanel.tileSize;
            tileNumber1 = gamePanel.tileM.mapTileNumber[entityLeftColumn][entityUndersideRow];
            tileNumber2 = gamePanel.tileM.mapTileNumber[entityRightColumn][entityUndersideRow];
            if(gamePanel.tileM.tile[tileNumber1].collision == true || gamePanel.tileM.tile[tileNumber2].collision == true) {
                entity.collisionOn = true;
            }
            break;
        case "left":
            entityLeftColumn = (entityLeftX - entity.speed)/gamePanel.tileSize;
            tileNumber1 = gamePanel.tileM.mapTileNumber[entityLeftColumn][entityTopRow];
            tileNumber2 = gamePanel.tileM.mapTileNumber[entityRightColumn][entityUndersideRow];
            if(gamePanel.tileM.tile[tileNumber1].collision == true || gamePanel.tileM.tile[tileNumber2].collision == true) {
                entity.collisionOn = true;
            }
            break;
        case "right":
            entityRightColumn = (entityRightX + entity.speed)/gamePanel.tileSize;
            tileNumber1 = gamePanel.tileM.mapTileNumber[entityRightColumn][entityTopRow];
            tileNumber2 = gamePanel.tileM.mapTileNumber[entityRightColumn][entityUndersideRow];
            if(gamePanel.tileM.tile[tileNumber1].collision == true || gamePanel.tileM.tile[tileNumber2].collision == true) {
                entity.collisionOn = true;
            }
            break;
    }
    }
}
