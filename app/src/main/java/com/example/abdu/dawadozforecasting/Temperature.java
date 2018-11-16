package com.example.abdu.dawadozforecasting;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Abdu on 11/16/2018.
 */

public class Temperature implements Serializable{
    private double temp;
    private String time;
    private String description;

    Temperature(double T, String ti, String desc){
        temp = T;
        time = ti;
        description = desc;
    }

    public double getTemp(){
        return temp;
    }
    public void setTemp(double T){
        temp = T;
    }
    public String getTime(){
        return time;
    }
    public void setTime(String T) {
        time = T;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String name){
        description = name;
    }
}
