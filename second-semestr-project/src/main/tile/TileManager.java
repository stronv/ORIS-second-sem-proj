package main.tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tile;
    public int mapTileNumber[][];

    public TileManager(GamePanel gamePanel) {
         this.gamePanel = gamePanel;
         tile = new Tile[12];
        mapTileNumber = new int[gamePanel.maxWorldColumns][gamePanel.maxWorldRow];

        getTileImage();
        loadMap("/resources/maps/mapLvl1.txt");
    }
    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/sand.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/leftboard.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/rightboard.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/downboard.png"));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/downboard_left.png"));


            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/downboard_right.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/upboard.png"));
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/upboard_right.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/upboard_left.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/finishline_4.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/finishline_5.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String mapPath) {
        try {
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            int column  = 0;
            int row = 0;

            while (column < gamePanel.maxWorldColumns && row < gamePanel.maxWorldRow) {
                String line = bufferedReader.readLine();

                while (column < gamePanel.maxWorldColumns) {
                    String numbers[] = line.split(" ");
                    int number = Integer.parseInt(numbers[column]);
                    mapTileNumber[column][row] = number;
                    column++;
                }
                if(column == gamePanel.maxWorldColumns) {
                    column = 0;
                    row++;
                }
            }
            bufferedReader.close();
        }   catch (Exception e) {

        }
    }
    public void draw(Graphics2D graphics2D) {
        int worldColumn = 0;
        int worldRow = 0;


        while (worldColumn < gamePanel.maxWorldColumns && worldRow < gamePanel.maxWorldRow) {

            int tileNumber = mapTileNumber[worldColumn][worldRow];

            int x = worldColumn * gamePanel.tileSize;
            int y = worldRow * gamePanel.tileSize;
            int screenX = x - gamePanel.mouse.x + gamePanel.mouse.x;
            int screenY = y - gamePanel.mouse.y + gamePanel.mouse.screenY;

//            if(x + gamePanel.tileSize > gamePanel.mouse.x - gamePanel.mouse.screenX &&
//                    x - gamePanel.tileSize < gamePanel.mouse.x + gamePanel.mouse.screenX &&
//                    y + gamePanel.tileSize > gamePanel.mouse.y - gamePanel.mouse.screenY &&
//                    y - gamePanel.tileSize < gamePanel.mouse.y + gamePanel.mouse.screenY){
               graphics2D.drawImage(tile[tileNumber].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
//            }



            worldColumn ++;


            if(worldColumn == gamePanel.maxWorldColumns) {
                worldColumn = 0;

                worldRow++;

            }
        }
    }
}
