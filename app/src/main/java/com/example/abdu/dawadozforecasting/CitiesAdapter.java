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

public class CitiesAdapter extends ArrayAdapter<City>{
    CitiesAdapter(Activity context, ArrayList<City> Items){ super(context, 0, Items);}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // check if the current view is reused else inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.city, parent, false);
        }

        City item = getItem(position);
        TextView strength =  listItemView.findViewById(R.id.temperature);
        char temperature = item.getName().charAt(0);
        strength.setText(Character.toString(temperature));
        GradientDrawable temperatureCircle = (GradientDrawable) strength.getBackground();

        // Get the appropriate background color based on the current temperature
        int temperatureColor = ContextCompat.getColor(getContext(), R.color.colorPrimary);;

        // Set the color on the temperature circle
        temperatureCircle.setColor(temperatureColor);
        TextView city =  listItemView.findViewById(R.id.city);

        city.setText(item.getName());

        return listItemView;
    }

}
