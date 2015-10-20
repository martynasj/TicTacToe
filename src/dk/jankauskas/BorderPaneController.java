package dk.jankauskas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BorderPaneController {

    @FXML
    private Button newGameBtn;

    @FXML
    private Button saveGameBtn;

    @FXML
    private Button loadGameBtn;

    public void initialize() {

        newGameBtn.setOnAction(event -> {
            Main.setBoard(new Board());
            sendNewGame();
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

    }

    private void sendNewGame() {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getLocalHost();
            int port = Main.getSendPort();

            byte[] buf;
            buf = ("NEW").getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
            socket.send(packet);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
