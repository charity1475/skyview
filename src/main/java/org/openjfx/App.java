package org.openjfx;

import javafx.application.Application;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;
import org.openjfx.views.Welcome;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        //--- Drop shadow created ---
        DropShadow Dropshadow = new DropShadow();

        Welcome welcomePage = new Welcome();

        stage.setScene(welcomePage.getScene(stage, Dropshadow));
        //---Setting the stage---
        stage.setTitle("Introduction");
        stage.setWidth(800);
        stage.setHeight(500);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}