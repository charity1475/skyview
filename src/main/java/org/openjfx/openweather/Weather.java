package org.openjfx.openweather;

import org.openjfx.model.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather {

    public Weather() {
    }

    public String getStats(Location area) throws IOException {
        String url = String.valueOf(new StringBuilder("http://api.openweathermap.org/data/2.5/weather?lat=")
                .append(area.latitude).append("&lon=").append(area.longitude)
                .append("&units=metric&mode=html&uk&APPID=fdacfb08c4978528e68a59264890dc6e"));
        System.out.println(" ###################### URL : "+ url + "   ##################");
        URL urlObject = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        System.out.println(" ---- bfore status --- ");

        if (connection.getResponseCode() != 200) {
            System.out.println(" not 200 ");
            throw new RuntimeException(" request to open weather failed ");
        }
        System.out.println(" ----- bf buffer --- ");
        BufferedReader inStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        System.out.println(" ######## BFSTREAM #######");
        String inputLine;
        StringBuffer response = new StringBuffer();
        System.out.println(" --- apppended ---- ");
        while ((inputLine = inStream.readLine()) != null) {
            System.out.println("********** INPUTLINE **************");
            response.append(inputLine);
        }
        inStream.close();
        System.out.println("*********** "+ response+ "  ******");
        return response.toString();
    }
}
