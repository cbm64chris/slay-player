package com.fluffyluffs.slayplayer;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SlayPlayer extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(new URL(SlayPlayer.class.getResource("/fxml/SlayPlayer.fxml").toExternalForm()));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
