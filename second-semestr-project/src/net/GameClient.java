package net;

import main.GamePanel;

import java.io.IOException;
import java.net.*;

public class GameClient extends Thread {
    private InetAddress ipAddress;
    private DatagramSocket socket;
    private GamePanel gamePanel;

    public GameClient(GamePanel gamePanel, String ipAddress) {
        this.gamePanel = gamePanel;
        try {
            this.socket = new DatagramSocket();
            this.ipAddress = InetAddress.getByName(ipAddress);
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
            try {
                socket.receive(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String message = new String(datagramPacket.getData());
            System.out.println("SERVER -> " + message);
        }
    }

    public void sendData(byte[] data) {
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, ipAddress, 1717);
        try {
            socket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
