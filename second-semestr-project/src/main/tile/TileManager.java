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
    Tile[] tile;
    int mapTileNumber[][];

    public TileManager(GamePanel gamePanel) {
         this.gamePanel = gamePanel;
         tile = new Tile[10];
        mapTileNumber = new int[gamePanel.maxScreenColumn][gamePanel.maxScreenRow];

        getTileImage();
        loadMap();
    }
    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/sand.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/leftboard.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/rightboard.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/resources/maps/map.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            int column  = 0;
            int row = 0;

            while (column < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow) {
                String line = bufferedReader.readLine();

                while (column < gamePanel.maxScreenColumn) {
                    String numbers[] = line.split(" ");
                    int number = Integer.parseInt(numbers[column]);
                    mapTileNumber[column][row] = number;
                    column++;
                }
                if(column == gamePanel.maxScreenColumn) {
                    column = 0;
                    row++;
                }
            }
            bufferedReader.close();
        }   catch (Exception e) {

        }
    }
    public void draw(Graphics2D graphics2D) {
        int column = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (column < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow) {

            int tileNumber = mapTileNumber[column][row];

            graphics2D.drawImage(tile[tileNumber].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            column ++;
            x += gamePanel.tileSize;

            if(column == gamePanel.maxScreenColumn) {
                column = 0;
                x = 0;
                row++;
                y += gamePanel.tileSize;
            }
        }
    }
}
