package com.example.abdu.dawadozforecasting;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Abdu on 11/16/2018.
 */

public class Temperature extends SugarRecord implements Serializable {

    public double temp;
    public String time;
    public String description;
    public String cityName;             //public to be able to access it in DB
    public Temperature(){

    }
    Temperature(double T, String ti, String desc, String city){
        temp = T;
        time = ti;
        description = desc;
        cityName = city;
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
