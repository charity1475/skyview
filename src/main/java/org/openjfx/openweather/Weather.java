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
                .append("&units=metric&mode=html&uk&APPID=ee77e7d6ae6f74f04ca1c48f4c2948d2"));
        URL urlObject = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException(" request to open weather failed ");
        }
        BufferedReader inStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = inStream.readLine()) != null) {
            response.append(inputLine);
        }
        inStream.close();
        return response.toString();
    }
}
