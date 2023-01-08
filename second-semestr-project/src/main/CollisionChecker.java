package main;

import entity.Cat;
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
    //проверка являтся ли сущность игроком или нет
    public int checkObject(Entity entity, boolean mouse) {
        int index = 999;
        for(int i = 0; i < gamePanel.object.length; i++) {

            if(gamePanel.object[i] != null) {
            //находим твердую область сущности
                entity.solidPart.x = entity.x + entity.solidPart.x;
                entity.solidPart.y = entity.y + entity.solidPart.y;
            //твердая область объекта
                gamePanel.object[i].solidPart.x = gamePanel.object[i].x + gamePanel.object[i].solidPart.x;
                gamePanel.object[i].solidPart.y = gamePanel.object[i].y + gamePanel.object[i].solidPart.y;
            //где объект будет находиться после перемещения.
                switch (entity.direction) {
                    case "up":
                        entity.solidPart.y -= entity.speed;
                        if(entity.solidPart.intersects(gamePanel.object[i].solidPart)) {
                            if(gamePanel.object[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(mouse == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidPart.y += entity.speed;
                        if(entity.solidPart.intersects(gamePanel.object[i].solidPart)) {
                            if(gamePanel.object[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(mouse == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidPart.x -= entity.speed;
                        if(entity.solidPart.intersects(gamePanel.object[i].solidPart)) {
                            if(gamePanel.object[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(mouse == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidPart.x += entity.speed;
                        if(entity.solidPart.intersects(gamePanel.object[i].solidPart)) {
                            if(gamePanel.object[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(mouse == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidPart.x = entity.solidPartDefaultX;
                entity.solidPart.y = entity.solidPartDefaultY;
                gamePanel.object[i].solidPart.x = gamePanel.object[i].solidPartDefaultX;
                gamePanel.object[i].solidPart.y = gamePanel.object[i].solidPartDefaultY;
            }
        }
        return index;
    }

    public int checkCats(Entity entity, Cat[] target) {

        int index = 999;
        for(int i = 0; i < target.length; i++) {

            if(target[i] != null) {
                //находим твердую область сущности
                entity.solidPart.x = entity.x + entity.solidPart.x;
                entity.solidPart.y = entity.y + entity.solidPart.y;
                //твердая область объекта
                target[i].solidPart.x = target[i].x + target[i].solidPart.x;
                target[i].solidPart.y = target[i].y + target[i].solidPart.y;
                //где объект будет находиться после перемещения.
                switch (entity.direction) {
                    case "up":
                        entity.solidPart.y -= entity.speed;
                        if(entity.solidPart.intersects(target[i].solidPart)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "down":
                        entity.solidPart.y += entity.speed;
                        if(entity.solidPart.intersects(target[i].solidPart)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "left":
                        entity.solidPart.x -= entity.speed;
                        if(entity.solidPart.intersects(target[i].solidPart)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "right":
                        entity.solidPart.x += entity.speed;
                        if(entity.solidPart.intersects(target[i].solidPart)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                }
                entity.solidPart.x = entity.solidPartDefaultX;
                entity.solidPart.y = entity.solidPartDefaultY;
                target[i].solidPart.x = target[i].solidPartDefaultX;
                target[i].solidPart.y = target[i].solidPartDefaultY;
            }
        }
        return index;
    }

    public void checkPlayer(Entity entity) {
            //находим твердую область сущности
        entity.solidPart.x = entity.x + entity.solidPart.x;
        entity.solidPart.y = entity.y + entity.solidPart.y;
            //твердая область объекта
        gamePanel.mouse.solidPart.x = gamePanel.mouse.x + gamePanel.mouse.solidPart.x;
        gamePanel.mouse.solidPart.y = gamePanel.mouse.y + gamePanel.mouse.solidPart.y;
            //где объект будет находиться после перемещения.
        switch (entity.direction) {
            case "up":
                entity.solidPart.y -= entity.speed;
                if(entity.solidPart.intersects(gamePanel.mouse.solidPart)) {
                    entity.collisionOn = true;
                }
                break;
                case "down":
                    entity.solidPart.y += entity.speed;
                    if(entity.solidPart.intersects(gamePanel.mouse.solidPart)) {
                        entity.collisionOn = true;
                    }
                    break;
                case "left":
                    entity.solidPart.x -= entity.speed;
                    if(entity.solidPart.intersects(gamePanel.mouse.solidPart)) {
                        entity.collisionOn = true;
                    }
                    break;
                case "right":
                    entity.solidPart.x += entity.speed;
                    if(entity.solidPart.intersects(gamePanel.mouse.solidPart)) {
                        entity.collisionOn = true;
                    }
                    break;
            }
            entity.solidPart.x = entity.solidPartDefaultX;
            entity.solidPart.y = entity.solidPartDefaultY;
            gamePanel.mouse.solidPart.x = gamePanel.mouse.solidPartDefaultX;
            gamePanel.mouse.solidPart.y = gamePanel.mouse.solidPartDefaultY;
    }
}
