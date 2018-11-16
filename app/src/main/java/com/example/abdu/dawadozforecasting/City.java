package com.example.abdu.dawadozforecasting;

import java.util.ArrayList;

/**
 * Created by Abdu on 11/16/2018.
 */

public class City {

    private ArrayList<Temperature> temperatures;
    private String name;

    City(){
        temperatures = new ArrayList<>();
    }

    public ArrayList<Temperature> getTemperatures() {
        return temperatures;
    }

    public String getName() {
        return name;
    }
    public void setName(String Name) {
        name = Name;
    }

    public void addTemperature(double temp, String desc, String time){

        temperatures.add(new Temperature(temp, desc, time));
    }



}
