package com.ft.repository;

import com.ft.entity.TruckDetail;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TruckDataRepository {
    private static final String SOCRATA_TRUCK_URL = "http://data.sfgov.org/resource/bbb8-hzi6.json";

    public TruckDetail[] getData() throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(SOCRATA_TRUCK_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        Gson gson = new Gson();
        return gson.fromJson(result.toString(), TruckDetail[].class);

    }
}
