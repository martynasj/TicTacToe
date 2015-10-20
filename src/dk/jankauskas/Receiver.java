package dk.jankauskas;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receiver implements Runnable {

    // Receiver details
    private int port;
    private DatagramSocket socket;
    private int bufferSize;
    private static GameBoardController gameBoardController;

    public Receiver(int port) {
        try {
            this.port = port;
            this.socket = new DatagramSocket(port);
            this.bufferSize = 1024;
            System.out.println("Socket created on port: " + port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Thread is starting to receive bytes");


        try {
            while (true) {
                byte[] buf = new byte[bufferSize];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                readMessage(packet.getData());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // todo: every time msg received, refresh the board
    private void readMessage(byte[] data) {

        String receivedString = new String(data).trim();   // trims the empty members of array
        System.out.println("Received: " + receivedString);
        String[] msg = receivedString.split(" ");  // splits received string into tokens and stores in array
        switch (msg[0]) {
            case "SHOOT" :
                int position = Integer.parseInt(msg[1]);
                // Otherwise I get "not in FX thread exception..."
                Platform.runLater(() -> {
                    Main.getBoard().setCellValue(position, Main.getOpponent().getPlayerSymbol());
                    gameBoardController.setButtonsDisabled(false);
                });
                break;
            case "NEW" :
                Platform.runLater(() -> {
                    Main.setBoard(new Board());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
                    Pane gameBoardPane = null;
                    try {
                        gameBoardPane = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Main.stackPane.getChildren().add(gameBoardPane);
                    Main.setGameBoardController(loader.getController());
                });
                break;
        }
    }

    public static void setGameBoardController(GameBoardController c) {
        gameBoardController = c;
    }

    public int getPort() {
        return port;
    }
}
