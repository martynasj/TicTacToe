package dk.jankauskas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

public class Controller {

    @FXML
    private TilePane tilePane;

    Board board;
    ArrayList<Button> buttons;

    public void initialize() {

        buttons = new ArrayList<>();
        // Construct a board with default size
        // todo board size select option
        this.board = new Board();

        // Create buttons and add array list and tile pane
        for (int i = 0; i < board.getSize(); i++) {
            Button btn = new Button();
            btn.setId(Integer.toString(i));
            btn.setText(board.getCellValue(i) + "");
            // Buttons expand in size according to Tile Pane's tile size
            btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            tilePane.getChildren().add(btn);
            buttons.add(btn);
            setButtonsDisabled();
        }

        for (Button btn : buttons) {
            btn.setOnAction(event -> {
                btn.setText("1");
                shoot(btn, Integer.parseInt(btn.getId()));
            });
        }

    }

    private void shoot(Button btn, int position) {
        setButtonsDisabled();
        board.setCellValue(position);
    }

    private void setButtonsDisabled() {
        for (Button btn : buttons) {
            if (btn.getText().equals(Integer.toString(1))) {
                btn.setDisable(true);
            }
        }
    }

}
