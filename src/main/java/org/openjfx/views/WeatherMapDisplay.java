package org.openjfx.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.openjfx.errors.LocationNotFoundException;
import org.openjfx.geotag.Geotag;
import org.openjfx.model.Location;
import org.openjfx.openweather.Weather;

public class WeatherMapDisplay {

    public String locationInput;

    public WeatherMapDisplay(){

    }
    public WeatherMapDisplay(String LocationInput){
        this.locationInput = LocationInput;
    }
    public Scene getScene(Stage stage, DropShadow Dropshadow){
        //---scene3 objects---
        //---Vboxes created---
        VBox vbox1 = new VBox();
        VBox vbox2 = new VBox(10);
        //---Stackpane2 created---
        StackPane stack2 = new StackPane();

        //---margins, paddings and alignment of vbox1 + vbox2 in hbox---
        vbox2.setPadding(new Insets(5));
        vbox1.setPadding(new Insets(7));
        vbox2.setAlignment(Pos.CENTER);
        vbox1.setMaxWidth(200);
        vbox1.setMinWidth(100);
        vbox1.setMaxHeight(200);
        vbox1.setMinHeight(170);

        //---Adding the shadow to vbox1---
        vbox1.setEffect(Dropshadow);





        //---btn1 created---
        Button btn1 = new Button("Back");
        btn1.setFont(new Font(15));
        btn1.setMinSize(130, 32);
        btn1.setBackground(new Background(new BackgroundFill(Color.DARKCYAN,
                new CornerRadii(40),
                Insets.EMPTY)));
        VBox.setMargin(btn1, new Insets(0, 0, 18, 0));

        //---btn1 shadow---
        setBtnShadow(btn1, Dropshadow);
        // ---btn1 action back to scene2---
        BrowseLocation browse = new BrowseLocation();
        btn1.setOnAction(event -> stage.setScene(browse.getScene(stage, Dropshadow)));


        final WebView mapResultsPage = getMapWebview();
        final WebView weatherResultsPage = getWebview();
        //---Adding the shadow to browser---
        weatherResultsPage.setEffect(Dropshadow);
        //---browser into vbox1---
        vbox1.getChildren().add(weatherResultsPage);
        vbox1.setBackground(new Background(new BackgroundFill(Color.DARKGREY, new CornerRadii(10), Insets.EMPTY)));
        stack2.getChildren().addAll(mapResultsPage, vbox1);
        StackPane.setMargin(vbox1, new Insets(150, 540, 90, 10));
        StackPane.setAlignment(vbox1, Pos.CENTER_LEFT);
        vbox2.getChildren().addAll(stack2, btn1);

        setBgImage(vbox2);

        loadMap(locationInput,mapResultsPage);
        loadWeatherInformation(locationInput,weatherResultsPage);

        return new Scene(vbox2);
    }

    public void setBtnShadow(Button btn, DropShadow shadow){
        btn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            btn.setEffect(shadow);
        });
        btn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            btn.setEffect(null);
        });
    }

    public WebView getWebview(){
        //---Web view creation---
        return new WebView();
    }

    public WebView getMapWebview(){
        //---Web view + web engine objects created---
        return new WebView();
    }

    public void loadMap(String myLocation, WebView mapWebView){
        //---url sent in request if the map---
        String url = "https://www.google.com/maps/place/" + myLocation + "/";

        final WebEngine mapWebE = mapWebView.getEngine();
        mapWebE.load(url);
    }

    public void loadWeatherInformation (String myLocation, WebView weatherWebView){
        //---Location into geotag constructor---
        Geotag tag = new Geotag();
        String htmlResponse;
        try {
            //---callme method in geotag to get lat and lon response---
            Location coordinates = tag.getCoordinates(myLocation);
            //---lat + lon sent in request in SendHttpRequest2---
            Weather api = new Weather();
            System.out.println(" ####### MAIN : api ########");
            htmlResponse = api.getStats(coordinates);
            System.out.println("#################### HTML: " + htmlResponse);
        } catch (Exception | LocationNotFoundException e1) {
            //---Exception response---
            htmlResponse = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\" dir=\"ltr\">\n" +
                    "  <head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Exception</title>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "      <h1 style = \"font-size:18px;\">Oops! Location entered could not be determined, Please try again.</h1>\n" +
                    "  </body>\n" +
                    "</html>";
        }

        final WebEngine weatherWebE = weatherWebView.getEngine();
        weatherWebE.loadContent(htmlResponse);
    }

    public WebEngine getWebengine(WebView Wview){
        //---Web engine creation---
        return Wview.getEngine();
    }

    public WebEngine getMapWebengine(WebView MWview){
        return MWview.getEngine();
    }

    public void setBgImage(VBox pane){
        pane.setStyle("\"-fx-background-image: url('https://cdn.pixabay.com/photo/2018/08/23/07/35/thunderstorm-3625405_960_720.jpg')\"");
    }

}

