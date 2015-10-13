package dk.jankauskas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application {

    static Receiver receiver;
    private static Board board;
    static String appTitle;
    private static Controller controller;
    private static Player player;
    private static Player opponent;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        primaryStage.setTitle(appTitle);
        primaryStage.setScene(new Scene(root));
        // Not resizable window
        primaryStage.setResizable(false);
        primaryStage.show();

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

    public static Controller getController() {
        return controller;
    }
}
