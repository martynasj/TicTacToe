package dk.jankauskas;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;

import java.net.*;
import java.util.ArrayList;

public class Controller {

    @FXML
    private TilePane tilePane;

    Board board;
    ArrayList<Button> buttons;
    private int sendPort;

    public void initialize() {

        Receiver.setController(this);

        // gets the board from the Main class
        // todo board size select option
        this.board = Main.getBoard();
        board.getCells().addListener((ListChangeListener<String>) (c) -> {
            while (c.next()) {
                System.out.println("changed: " + c.wasReplaced());
                recalculateButtons();
            }
        });

        createButtons();

        // setOnAction on all buttons
        for (Button btn : buttons) {
            btn.setOnAction(event -> {
//                sendShoot(btn.getId());
//        setButtonsDisabled(true);
                board.setCellValue(Integer.parseInt(btn.getId()));
            });
        }

    }

    // Create buttons and adds to the tile pane and button array list
    private void createButtons() {
        buttons = new ArrayList<>();
        for (int i = 0; i < board.getSize(); i++) {
            Button btn = new Button();
            btn.setId(Integer.toString(i));
            btn.setText(board.getCellValue(i) + "");
            // Buttons expand in size according to Tile Pane's tile size
            btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            tilePane.getChildren().add(btn);
            buttons.add(btn);
            setButtonsDisabled(false);
        }
    }

    private void setButtonsDisabled(boolean disabled) {
        for (Button btn : buttons) {
            btn.setDisable(disabled);
        }
    }

    // Recalculates all buttons based on board cells arraylist
    public void recalculateButtons() {
        for (int i = 0; i < board.getSize(); i++) {
            String cellMark = board.getCellValue(i);
            buttons.get(i).setText(cellMark);
        }
    }

    //=================================================================
    //                      Send methods
    //=================================================================

    private void sendShoot(String btnId) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getLocalHost();
            int port = getSendPort();

            byte[] buf;
            buf = ("SHOOT " + btnId).getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // TODO could be improved
    public int getSendPort() {
        if (Main.receiver.getPort() == 5000) {
            return 6000;
        } else return 5000;
    }
}
