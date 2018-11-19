package com.example.abdu.dawadozforecasting;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Abdu on 11/16/2018.
 */

public class Temperature extends SugarRecord implements Serializable {

    private double temp;
    private String time;
    private String description;
    private String cityName;
    Temperature(double T, String ti, String desc, String city){
        temp = T;
        time = ti;
        description = desc;
        cityName = city;
    }
    Temperature(){

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
    public String getCityName(){
        return cityName;
    }
    public void setCityName(String name){
        cityName = name;
    }
}
