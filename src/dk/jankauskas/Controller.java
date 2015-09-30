package dk.jankauskas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Arrays;

public class Controller {

    @FXML
    private GridPane gridButtons;

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

        // TODO pabaigti su mygtukais!
        // Create buttons and add to grid
        Button[] buttons = new Button[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button();
                // Set Id for every button to be it's position
                gridButtons.add(buttons[j], i, j);
            }
        }

        for (int i = 0; i < 9; i++) {

        }

        // Set Id for every button to be it's position
        for (Button[] line : buttons) {
            for (Button button : line) {
                button.setOnAction(event -> {
                    shoot(Integer.parseInt(button.getId()));
                });
            }
        }

        setBoard();

    }

    public void setBoard() {
        this.board = new Board();
    }

    private void shoot(int position) {
//        board.markCell(position);
        System.out.println("Shooting at: " + position);
    }

}
