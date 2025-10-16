package tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    main.GamePanel gp;
    tile.Tile[] tile;
    // Erste Dimension = Spalten, zweite = Zeilen (passt zu mapTileNum[col][row])
    int[][] mapTileNum;

    public TileManager(main.GamePanel gp) {
        this.gp = gp;
        tile = new tile.Tile[5];
        // Korrektur: [maxScreenCol][maxScreenRow] statt [maxScreenRow][maxScreenCol]
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {
            tile[0] = new tile.Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));

            tile[1] = new tile.Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tile[2] = new tile.Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water01.png"));

            tile[3] = new tile.Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new tile.Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road01.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try (InputStream is = getClass().getResourceAsStream("/maps/map.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            for (int row = 0; row < gp.maxScreenRow; row++) {
                String line = br.readLine();
                if (line == null) {
                    // Weniger Zeilen als erwartet: Rest bleibt 0 (Standard-Tile)
                    break;
                }

                String[] numbers = line.trim().split("\\s+");
                for (int col = 0; col < gp.maxScreenCol; col++) {
                    int num = 0;
                    if (col < numbers.length) {
                        try {
                            num = Integer.parseInt(numbers[col]);
                        } catch (NumberFormatException ignored) {
                            num = 0;
                        }
                    }

                    // Defensive Begrenzung, falls Map ungültige Tile-IDs enthält
                    if (num < 0 || num >= tile.length) {
                        num = 0;
                    }
                    mapTileNum[col][row] = num;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;


        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (tileNum < 0 || tileNum >= tile.length) {
                tileNum = 0;
            }

            if(worldX) {

            }

            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}