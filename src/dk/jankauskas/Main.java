package dk.jankauskas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    static Receiver receiver;
    private static Board board;
    static String appTitle;
    private static GameBoardController gameBoardController;
    private static Player player;
    private static Player opponent;

    static StackPane stackPane;

    @Override
    //todo better way of changing stackpane scenes
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("BorderPane.fxml"));
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("IntroPane.fxml"));
            BorderPane borderPane = loader1.load();
            AnchorPane introPane = loader2.load();
            stackPane = (StackPane) borderPane.getCenter();
            stackPane.getChildren().add(introPane);
            primaryStage.setTitle(appTitle);
            Scene scene = new Scene(borderPane);
            primaryStage.setScene(scene);
            // Not resizable window
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // close all threads
    @Override
    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        board = new Board();
        int port = Integer.parseInt(args[0]);
        appTitle = args[1];

        createPlayers();

        receiver = new Receiver(port);
        Thread networkThread = new Thread(receiver);
        networkThread.start();
        launch(args);
    }

    private static void createPlayers() {
        // Assigns player and opponent symbols based on program args
        if (appTitle.equals("App1")) {
            player = new Player("X");
            opponent = new Player("O");
        } else {
            player = new Player("O");
            opponent = new Player("X");

        }
    }

    public static Board getBoard() {
        return board;
    }

    public static Player getPlayer() {
        return player;
    }

    public static Player getOpponent() {
        return opponent;
    }

    public static void setGameBoardController(GameBoardController gameBoardController) {
        Main.gameBoardController = gameBoardController;
    }

    public static GameBoardController getGameBoardController() {
        return gameBoardController;
    }
}
