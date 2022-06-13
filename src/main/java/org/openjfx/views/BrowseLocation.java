package org.openjfx.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BrowseLocation {
    public BrowseLocation(){

    }

    public Scene getScene(Stage stage, DropShadow Dropshadow){
        //---Scene2 objects---
        //---Anchor pane created---
        AnchorPane anchor1 = new AnchorPane();
        AnchorPane backAnchor = new AnchorPane();
        //---  blur ---
        GaussianBlur blur = new GaussianBlur();
        blur.setRadius(10);
        backAnchor.setEffect(blur);
        //---stack pane created---
        StackPane stack1 = new StackPane();
        //---Grid creation---
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setEffect(Dropshadow);
        //--- Grid pane nodes ---
        //---Label creation---
        Label locationText = new Label();
        locationText.setFont(new Font("Arial", 25));
        locationText.setText("Location:");
        //---Text Field creation---
        TextField location = new TextField();
        //---Button creation---
        JFXButton btn = new JFXButton();
        btn.setText("Browse");
        btn.setBackground(new Background(new BackgroundFill(Color.DARKCYAN,
                new CornerRadii(40),
                Insets.EMPTY)));
        //---shadow for btn---
        setBtnShadow(btn, Dropshadow);

        //---btn button action---
        location.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                //---Get typed location---
                String myLocation = location.getText();
                locationField(anchor1, blur, stack1, myLocation, stage, Dropshadow);
            }
        });

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {

                //---Get typed location---
                String myLocation = location.getText();
                locationField(anchor1, blur, stack1, myLocation, stage, Dropshadow);
            }
        });

        //---Button, Text field and label into Grid---
        grid.add(locationText, 0, 0);
        grid.add(location, 1, 0);
        grid.add(btn, 1, 1);
        grid.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, new CornerRadii(5), Insets.EMPTY)));
        JFXButton tointro = new JFXButton("Back");
        tointro.setFont(new Font(15));
        tointro.setBackground(new Background(new BackgroundFill(Color.DARKCYAN,
                new CornerRadii(40),
                Insets.EMPTY)));

        //---Tointro shadow---
        setBtnShadow(tointro, Dropshadow);

        Welcome page = new Welcome();
        tointro.setOnAction(event1 -> stage.setScene(page.getScene(stage, Dropshadow)));

        //--- Grid into anchor---
        anchor1.getChildren().addAll(grid, tointro);
        AnchorPane.setBottomAnchor(grid, 70.0);
        AnchorPane.setLeftAnchor(grid, 220.0);
        AnchorPane.setRightAnchor(grid, 220.0);
        AnchorPane.setBottomAnchor(tointro, 20.0);
        AnchorPane.setLeftAnchor(tointro, 320.0);
        AnchorPane.setRightAnchor(tointro, 320.0);

        setBgImage(stack1);

        //---backanchor + anchor1 into stack1---
        stack1.getChildren().addAll(backAnchor, anchor1);
        //---Scene Creation---

        return new Scene(stack1);
    }

    private void locationField(AnchorPane anchor1, GaussianBlur blur, StackPane stack1, String myLocation, Stage stage, DropShadow Dropshadow) {
        if (myLocation.equals("")) {

            System.out.println("befor check==============");

            JFXDialogLayout Dialoglayout = new JFXDialogLayout();
            JFXDialog dialog = new JFXDialog(stack1, Dialoglayout, JFXDialog.DialogTransition.LEFT);
            JFXButton closeD = new JFXButton("OK");
            closeD.setMinSize(150, 32);
            closeD.setBackground(new Background(new BackgroundFill(Color.DARKCYAN,
                    new CornerRadii(40),
                    Insets.EMPTY)));
            Label text = new Label("A location must be entered");
            text.setFont(new Font(25));
            Dialoglayout.setHeading(text);
            Dialoglayout.setActions(closeD);
            dialog.show();
            anchor1.setEffect(blur);
            closeD.setOnAction(event1 -> dialog.close());
            dialog.setOnDialogClosed(event1 -> anchor1.setEffect(null));

            System.out.println("after check ============");
        }else{
            WeatherMapDisplay result = new WeatherMapDisplay(myLocation);
            stage.setScene(result.getScene(stage, Dropshadow));
            stage.setTitle("Weather");
            stage.show();
        }

    }

    public void setBtnShadow(Button btn, DropShadow shadow){
        btn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            btn.setEffect(shadow);
        });
        btn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            btn.setEffect(null);
        });
    }

    public void setBgImage(StackPane pane){
        pane.setStyle("\"-fx-background-image: url('https://cdn.pixabay.com/photo/2018/08/23/07/35/thunderstorm-3625405_960_720.jpg')\"");
    }

}
