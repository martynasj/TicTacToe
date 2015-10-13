package dk.jankauskas;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

public class Receiver implements Runnable {

    // Receiver details
    private int port;
    private DatagramSocket socket;
    private int bufferSize;
    private static Controller controller;
    private Board board;

    // Sender details

    public Receiver(int port) {
        try {
            this.board = Main.getBoard();
//            this.controller = Main.getController();
            this.port = port;
            this.socket = new DatagramSocket(port);
            this.bufferSize = 10;
            System.out.println("Socket created on port: " + port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Thread is starting to receive bytes");

        byte[] buf = new byte[bufferSize];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        try {
            while (true) {
                socket.receive(packet);
                readMessage(packet.getData());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // todo: every time msg received, refresh the board
    private void readMessage(byte[] data) {
        String message = new String(data).trim();
        String confirmText = "Received: ";
        String[] msg = message.split(" ");
        System.out.println(confirmText += message);
        System.out.println("Split message: " + Arrays.toString(msg));
        switch (msg[0]) {
            case "SHOOT" :
                int position = Integer.parseInt(msg[1]);
                System.out.println(position);
                board.setCellValue(position);
                controller.recalculateButtons();
        }
    }

    public static void setController(Controller controlleris) {
        controller = controlleris;
    }

    public int getPort() {
        return port;
    }
}
