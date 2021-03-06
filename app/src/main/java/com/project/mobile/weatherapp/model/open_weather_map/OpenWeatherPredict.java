package com.project.mobile.weatherapp.model.open_weather_map;

import android.util.Log;

import java.util.ArrayList;

public class OpenWeatherPredict {
    private java.util.List<ListOfWeather> listOfWeather;

    public java.util.List<ListOfWeather> getListWeather() {
        return listOfWeather;
    }

    private void setListWeather(java.util.List<ListOfWeather> listOfWeather) {
        this.listOfWeather = listOfWeather;
    }

    public OpenWeatherPredict(OpenWeather5Days3Hours openWeather5Days3Hours) {
        java.util.List<ListOfWeather> weather5Days = new ArrayList<ListOfWeather>();
        Log.d("check",openWeather5Days3Hours.getList().size() + "");
        for( int i = 0; i < openWeather5Days3Hours.getList().size(); i += 8) {
            double temp_max = 0;
            double temp_min = openWeather5Days3Hours.getList().get(0).getMain().getTemp_min() ;
            for(int j = i; j < i + 8; j++) {
                if (openWeather5Days3Hours.getList().get(j).getMain().getTemp_max() > temp_max) {
                    temp_max = openWeather5Days3Hours.getList().get(j).getMain().getTemp_max();
                }
                if (openWeather5Days3Hours.getList().get(j).getMain().getTemp_min() < temp_min) {
                    temp_min = openWeather5Days3Hours.getList().get(j).getMain().getTemp_min();
                }
            }
            ListOfWeather listOfWeather = new ListOfWeather();
            listOfWeather.setTemp_max(temp_max);
            listOfWeather.setTemp_min(temp_min);
            listOfWeather.setClouds(openWeather5Days3Hours.getList().get(i + 2).getClouds());
            listOfWeather.setMain(openWeather5Days3Hours.getList().get(i + 2).getMain());
            String dt_txt = openWeather5Days3Hours.getList().get(i + 2).getDt_txt();
            String time = dt_txt.split(" ")[0];
            dt_txt = time + " 06:00:00";
            listOfWeather.setDt_txt(dt_txt);
            listOfWeather.setWeather(openWeather5Days3Hours.getList().get(i + 2).getWeather());
            listOfWeather.setWind(openWeather5Days3Hours.getList().get(i + 2).getWind());
            weather5Days.add(listOfWeather);
        }
        this.setListWeather(weather5Days);
    }
}


