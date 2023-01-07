package net;


import entity.Player;
import entity.PlayerMP;
import main.GamePanel;
import net.packets.Packet;
import net.packets.Packet00Login;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class GameServer extends Thread {
    private DatagramSocket socket;
    private GamePanel gamePanel;
    private List<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();

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
            parsePacket(datagramPacket.getData(), datagramPacket.getAddress(), datagramPacket.getPort());
            String message = new String(datagramPacket.getData()).trim();
            System.out.println("CLIENT [" + datagramPacket.getAddress().getHostAddress() + ":" + datagramPacket.getPort() +
                    "] -> " + message);
            if (message.trim().equalsIgnoreCase("ping")) {
                sendData(
                        "pong ".getBytes(),
                        datagramPacket.getAddress(),
                        datagramPacket.getPort()
                );
            }
        }
    }

    private void parsePacket(byte[] data, InetAddress address, int port) {
        String message = new String(data).trim();
        Packet.PacketTypes packet = Packet.lookupPacket(message.substring(0, 2));

        switch (packet) {
            default:
            case INVALID: break;
            case LOGIN:
                Packet00Login packet00Login = new Packet00Login(data);
                System.out.println("[" + address.getHostAddress() + ":" + port + "]" + packet00Login.getUsername() + " has connected. ");
                PlayerMP playerMP = null;

                if (address.getHostAddress().equalsIgnoreCase("127.0.0.1")) {
                    playerMP = new PlayerMP(gamePanel, gamePanel.getKeyHandler(), "", address, port);
                } else {
                    playerMP = new PlayerMP(gamePanel, "", address, port);
                }
                if (playerMP != null) {
                    this.connectedPlayers.add(playerMP);
                    gamePanel.addPlayer(playerMP);
                }

            case DISCONNECT: break;
        }
    }

    public void senDataToAllClients(byte[] data) {
        for (PlayerMP p: connectedPlayers) {
            sendData(data, p.ipAddress, p.port);
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
