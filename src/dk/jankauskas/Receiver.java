package dk.jankauskas;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receiver implements Runnable {

    // Receiver details
    private int port;
    private DatagramSocket socket;

    // Sender details

    public Receiver(int port) {
        try {
            this.port = port;
            this.socket = new DatagramSocket(port);
            System.out.println("Socket created on port: " + port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Starting to receive bytes");

        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        try {
            while (true) {
                socket.receive(packet);
                System.out.println(new String(packet.getData()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }
}
