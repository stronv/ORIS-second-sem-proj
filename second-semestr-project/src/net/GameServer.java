package net;

import main.GamePanel;

import java.io.IOException;
import java.net.*;

public class GameServer extends Thread {
    private DatagramSocket socket;
    private GamePanel gamePanel;

    public GameServer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        try {
            this.socket = new DatagramSocket(1717);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
            try {
                socket.receive(datagramPacket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String message = new String(datagramPacket.getData());
            System.out.println("CLIENT [" + datagramPacket.getAddress().getHostAddress() + ":" + datagramPacket.getPort() +
                    "] -> " + message);
            if(message.trim().equalsIgnoreCase("ping")) {
                sendData(
                        "pong".getBytes(),
                        datagramPacket.getAddress(),
                        datagramPacket.getPort()
                );
            }
        }
    }

    public void sendData(byte[] data, InetAddress ipAddress, int port) {
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, ipAddress, port);
        try {
            this.socket.send(datagramPacket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
