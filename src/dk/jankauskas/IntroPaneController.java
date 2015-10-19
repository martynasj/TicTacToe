package dk.jankauskas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class IntroPaneController {

    @FXML
    private Button newGameBtn;

    @FXML
    private Button loadGameBtn;

    public void initialize() {

        newGameBtn.setOnAction(event -> {
            System.out.println("veikia");
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

}
