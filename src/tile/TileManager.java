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
    int[][] mapTileNum;

    public TileManager(main.GamePanel gp) {
        this.gp = gp;

        // Tiles dynamisch laden (legt tile-Array an)
        getTileImage();

        // Map passend zu den World-Dimensionen anlegen
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        loadMap();
    }

    public void getTileImage() {
        // Alle Tile-Pfade an einer Stelle, keine "magischen" Indizes
        String[] paths = new String[] {
                "/tiles/grass00.png", // 0
                "/tiles/wall.png",    // 1
                "/tiles/water01.png", // 2
                "/tiles/earth.png",   // 3
                "/tiles/tree.png",    // 4
                "/tiles/road01.png"   // 5
        };

        tile = new tile.Tile[paths.length];

        for (int i = 0; i < paths.length; i++) {
            try (InputStream is = getClass().getResourceAsStream(paths[i])) {
                if (is == null) {
                    throw new IllegalStateException("Kachelbild nicht gefunden: " + paths[i]);
                }
                tile[i] = new tile.Tile();
                tile[i].image = ImageIO.read(is);
            } catch (IOException e) {
                throw new RuntimeException("Fehler beim Laden der Kachel: " + paths[i], e);
            }
        }
    }

    public void loadMap() {
        try (InputStream is = getClass().getResourceAsStream("/maps/world01.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            if (is == null) {
                throw new IllegalStateException("Mapdatei nicht gefunden: /maps/world01.txt");
            }

            // Über die World-Dimensionen iterieren, nicht Screen
            for (int row = 0; row < gp.maxWorldRow; row++) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }

                String[] numbers = line.trim().split("\\s+");
                for (int col = 0; col < gp.maxWorldCol; col++) {
                    int num = 0;
                    if (col < numbers.length) {
                        try {
                            num = Integer.parseInt(numbers[col]);
                        } catch (NumberFormatException ignored) {
                            num = 0;
                        }
                    }

                    // Absichern gegen ungültige Tile-IDs
                    if (num < 0 || num >= tile.length) {
                        num = 0;
                    }
                    mapTileNum[col][row] = num;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Laden der Map", e);
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

            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}