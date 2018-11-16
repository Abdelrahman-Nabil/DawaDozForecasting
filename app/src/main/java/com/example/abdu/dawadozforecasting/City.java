package com.example.abdu.dawadozforecasting;

import java.util.ArrayList;

/**
 * Created by Abdu on 11/16/2018.
 */

public class City {

    private ArrayList<Integer> temperatures;
    private String name;

    City(String name, int temp) {
        temperatures = new ArrayList<>();
        this.name = name;
        temperatures.add(temp);
    }

    public ArrayList<Integer> getTemperatures() {
        return temperatures;
    }

    public String getName() {
        return name;
    }
    public int getTodayTemp(){
        return temperatures.get(0);
    }


}
