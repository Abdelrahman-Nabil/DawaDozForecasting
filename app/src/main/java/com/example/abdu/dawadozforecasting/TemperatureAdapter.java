package com.example.abdu.dawadozforecasting;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Abdu on 11/16/2018.
 */

public class TemperatureAdapter extends ArrayAdapter<Temperature> {
    TemperatureAdapter(Activity context, ArrayList<Temperature> Items){ super(context, 0, Items);}
    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    public int gettemperatureColor(double temperature) {
        int temperatureColorResourceId;
        int temperatureFloor = (int) Math.floor(temperature);
        if(temperatureFloor < 37){
            temperatureColorResourceId = R.color.temperature1;
        }
        else if(isBetween(temperatureFloor, 37, 50)){
            temperatureColorResourceId = R.color.temperature2;
        }
        else if(isBetween(temperatureFloor, 51, 80)){
            temperatureColorResourceId = R.color.temperature3;
        }
        else if(isBetween(temperatureFloor, 81, 130)){
            temperatureColorResourceId = R.color.temperature4;
        }
        else if(isBetween(temperatureFloor, 131, 170)){
            temperatureColorResourceId = R.color.temperature5;
        }
        else if(isBetween(temperatureFloor, 171, 250)){
            temperatureColorResourceId = R.color.temperature7;
        }
        else{
            temperatureColorResourceId = R.color.temperature8;
        }


        return ContextCompat.getColor(getContext(), temperatureColorResourceId);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // check if the current view is reused else inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.temperatures, parent, false);
        }

        Temperature temperature = getItem(position);
        TextView strength =  listItemView.findViewById(R.id.temperature);
        int temp = (int) temperature.getTemp();
        strength.setText(Integer.toString(temp));
        GradientDrawable temperatureCircle = (GradientDrawable) strength.getBackground();

        // Get the appropriate background color based on the current temperature
        int temperatureColor = gettemperatureColor(temperature.getTemp());

        // Set the color on the temperature circle
        temperatureCircle.setColor(temperatureColor);

        TextView description = listItemView.findViewById(R.id.description);
        description.setText(temperature.getDescription());
        TextView time =  listItemView.findViewById(R.id.time);
        time.setText(temperature.getTime());


        return listItemView;
    }

}
