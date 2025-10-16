package entity;

import main.GamePanel;
import main.KeyHandler;
import entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;


public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;



    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldX = gp.tileSize * 1;
        worldY = gp.tileSize * 1;
        speed = 5;
        direction = "down";
    }


    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/up/player_run_up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/up/player_run_up2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/up/player_run_up3.png")));
            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/up/player_run_up4.png")));
            up5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/up/player_run_up5.png")));
            up6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/up/player_run_up6.png")));
            up7 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/up/player_run_up7.png")));
            up8 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/up/player_run_up8.png")));

            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/down/player_run_down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/down/player_run_down2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/down/player_run_down3.png")));
            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/down/player_run_down4.png")));
            down5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/down/player_run_down5.png")));
            down6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/down/player_run_down6.png")));
            down7 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/down/player_run_down7.png")));
            down8 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/down/player_run_down8.png")));

            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/left/player_run_left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/left/player_run_left2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/left/player_run_left3.png")));
            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/left/player_run_left4.png")));
            left5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/left/player_run_left5.png")));
            left6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/left/player_run_left6.png")));
            left7 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/left/player_run_left7.png")));
            left8 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/left/player_run_left8.png")));

            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/right/player_run_right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/right/player_run_right2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/right/player_run_right3.png")));
            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/right/player_run_right4.png")));
            right5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/right/player_run_right5.png")));
            right6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/right/player_run_right6.png")));
            right7 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/right/player_run_right7.png")));
            right8 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/run/right/player_run_right8.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void update() {
        if (keyH.upPressed) {
            direction = "up";
            worldY -= speed;
        } else if (keyH.downPressed) {
            direction = "down";
            worldY += speed;
        } else if (keyH.leftPressed) {
            direction = "left";
            worldX -= speed;
        } else if (keyH.rightPressed) {
            direction = "right";
            worldX += speed;
        }
    }
    public void draw(Graphics2D g2) {
        /*g2.setColor(Color.WHITE);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);*/

        BufferedImage image = null;

        switch(direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
