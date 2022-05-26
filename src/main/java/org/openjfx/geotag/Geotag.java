package org.openjfx.geotag;

import org.openjfx.errors.LocationNotFoundException;
import org.openjfx.model.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class Geotag {
    private Double latitude;
    private Double longitude;

    public Geotag() {
    }

    public Location getCoordinates(String location) throws IOException, LocationNotFoundException {
        String url = getUrl(location);
        URL urlObject = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        if (connection.getResponseCode() != 200) {
            throw new LocationNotFoundException(connection.getErrorStream().toString());
        }
        BufferedReader inStream = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = inStream.readLine()) != null) {
            response.append(inputLine);
        }
        inStream.close();
        return Serializer(response.toString());
    }

    private Location Serializer(String response) {
        JSONObject object = new JSONObject(response);
        latitude = object.getJSONArray("results")
                .getJSONObject(0).getJSONArray("locations")
                .getJSONObject(0).getJSONObject("latLng").getDouble("lat");
        longitude = object.getJSONArray("results")
                .getJSONObject(0).getJSONArray("locations")
                .getJSONObject(0).getJSONObject("latLng").getDouble("lng");
        return new Location(latitude, longitude);
    }

    private String getUrl(String location) {
        String api = String.valueOf(new StringBuilder("https://www.mapquestapi.com/geocoding/v1/address?")
                .append("key=GEqmjo8muuQ4L04lMvEUWdUxSYNGR19N&inFormat=kvp&outFormat=json&location=")
                .append(location).append("&thumbMaps=false"));
        return api;
    }
}
