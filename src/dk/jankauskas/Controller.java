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
    private Player player;

    public void initialize() {

        Receiver.setController(this);
        // gets the board from the Main class
        // todo board size select option
        this.board = Main.getBoard();
        this.player = Main.getPlayer();
        createButtons();

        // =================== End of initial methods =================== //


        // Executes every time there's a change in the cells array list
        board.getCells().addListener((ListChangeListener<String>) (c) -> {
            while (c.next()) {
                // win can only occur after last move, so it's enough to check based on the last position placed
                recalculateButtons();
                checkWin();
            }
        });


        // setOnAction on all buttons
        for (Button btn : buttons) {
            btn.setOnAction(event -> {
                board.setCellValue(Integer.parseInt(btn.getId()), player.getPlayerSymbol());
                sendShoot(btn.getId());
                setButtonsDisabled(true);
            });
        }

    }

    // todo
    private void checkWin() {
        if (board.isWon()) {
            System.out.println("We have a winner");
        } else if (board.isFull()) {
            System.out.println("It's a tie");
        }
    }

    // Create buttons and adds to the tile pane and button array list
    // each button has the same ID as it's position in the array
    private void createButtons() {
        buttons = new ArrayList<>();
        for (int i = 0; i < board.getSize(); i++) {
            Button btn = new Button();
            btn.setId(Integer.toString(i));
            // Buttons expand in size according to Tile Pane's tile size
            btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            tilePane.getChildren().add(btn);
            buttons.add(btn);
            setButtonsDisabled(false);
        }
    }

    public void setButtonsDisabled(boolean disabled) {
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
