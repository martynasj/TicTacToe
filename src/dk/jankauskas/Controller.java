package dk.jankauskas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    @FXML
    private TilePane tilePane;

    @FXML
    private Button cell00;

    @FXML
    private Button cell01;

    @FXML
    private Button cell02;

    @FXML
    private Button cell10;

    @FXML
    private Button cell11;

    @FXML
    private Button cell12;

    @FXML
    private Button cell20;

    @FXML
    private Button cell21;

    @FXML
    private Button cell22;

    Board board;

    public void initialize() {

        ArrayList<Button> buttons = new ArrayList<>();
        // Construct a board
        this.board = new Board();

        // Create buttons and add array list and tile pane
        for (int i = 0; i < board.getSize(); i++) {
            Button btn = new Button();
            btn.setId(Integer.toString(i));
            btn.setText(btn.getId());
            // Buttons expand in size according to Tile Pane's tile size
            btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            tilePane.getChildren().add(btn);
            buttons.add(btn);
        }

        for (Button btn : buttons) {
            btn.setOnAction(event -> {
                shoot(Integer.parseInt(btn.getId()));
            });
        }

    }

    private void shoot(int position) {
        board.markCell(position);
        System.out.println("Shooting at: " + position);
    }

}
