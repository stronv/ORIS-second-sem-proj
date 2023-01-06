package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.InetAddress;

public class PlayerMP extends Player {

    public InetAddress ipAddress;
    public int port;

    public PlayerMP(GamePanel gamePanel, KeyHandler keyHandler, String username, InetAddress ipAddress, int port) {
        super(gamePanel, keyHandler, username);
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public PlayerMP(GamePanel gamePanel,String username, InetAddress ipAddress, int port) {
        super(gamePanel, null, username);
        this.ipAddress = ipAddress;
        this.port = port;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void getPlayerImage() {
        try{
            moveLeft = ImageIO.read(getClass().getResourceAsStream("/resources/player/blackmouse.png"));
            moveRight = ImageIO.read(getClass().getResourceAsStream("/resources/player/blackmouse2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
