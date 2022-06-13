package org.openjfx.views;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Welcome{

    public Welcome(){

    }

    public Scene getScene(Stage stage, DropShadow Dropshadow) {
        //---Scene1 objects / Welcome page ---
        AnchorPane anchor = new AnchorPane();

        Label welcome = new Label("Welcome");
        welcome.setFont(new Font(50));

        JFXButton continueBtn = new JFXButton("Continue");
        continueBtn.setFont(new Font(15));
        continueBtn.setBackground(new Background(new BackgroundFill(Color.DARKCYAN,
                new CornerRadii(40), Insets.EMPTY)));

        //---cont button action---
        continueBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //---Scene into Stage---
                BrowseLocation browse = new BrowseLocation();
                stage.setScene(browse.getScene(stage, Dropshadow));
                //---Setting the stage---
                stage.setTitle("Location");
                stage.show();
            }
        });

        AnchorPane.setTopAnchor(welcome, 100.0);
        AnchorPane.setRightAnchor(welcome, 100.0);
        AnchorPane.setLeftAnchor(welcome, 300.0);

        AnchorPane.setBottomAnchor(continueBtn, 70.0);
        AnchorPane.setRightAnchor(continueBtn, 320.0);
        AnchorPane.setLeftAnchor(continueBtn, 320.0);
        anchor.getChildren().addAll(welcome, continueBtn);

        setBgImage(anchor);

        return new Scene(anchor);
    }

    public void setBgImage(AnchorPane pane){
        pane.setStyle("\"-fx-background-image: url('https://cdn.pixabay.com/photo/2018/08/23/07/35/thunderstorm-3625405_960_720.jpg')\"");
    }

}
