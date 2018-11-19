package com.example.abdu.dawadozforecasting;


import java.util.ArrayList;

/**
 * Created by Abdu on 11/16/2018.
 */

public class City {

    private ArrayList<Temperature> temperatures;
    private String name;

    City() {
        temperatures = new ArrayList<>();
    }

    City(String n, ArrayList<Temperature> temps) {
        name = n;
        temperatures = temps;
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

    public void addTemperature(Temperature temp) {

        temperatures.add(temp);
    }


}
