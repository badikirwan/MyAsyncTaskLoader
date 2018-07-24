package com.badikirwan.dicoding.myasynctaskloader;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class WeatherItems {

    private int id;
    private String nama, currentWeather, description, temprature;

    public WeatherItems(JSONObject object) {

        try {
            int id = object.getInt("id");
            String name = object.getString("name");
            String currentWeather = object.getJSONArray("weather").getJSONObject(0).getString("main");
            String description = object.getJSONArray("weather").getJSONObject(0).getString("description");
            double tempKelvin = object.getJSONObject("main").getDouble("temp");
            double tempInCelcius = tempKelvin - 273;
            String teperature = new DecimalFormat("##.##").format(tempInCelcius);
            this.id = id;
            this.nama = name;
            this.currentWeather = currentWeather;
            this.description = description;
            this.temprature = teperature;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(String currentWeather) {
        this.currentWeather = currentWeather;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemprature() {
        return temprature;
    }

    public void setTemprature(String temprature) {
        this.temprature = temprature;
    }
}
