package dk.jankauskas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application {

    int[] board = new int[9];

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        // Not resizable window
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        Thread networkThread = new Thread(new Receiver(port));
        networkThread.start();
        launch(args);
    }
}
