package com.example.abdu.dawadozforecasting;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        ArrayList<City> Cities = new ArrayList<>();
        Cities.add(new City("San Francisco", 2));
        Cities.add(new City("Giza", 31));
        Cities.add(new City("Prague", 16));
        ListView CitiesList = findViewById(R.id.list);
        final CitiesAdapter adapter = new CitiesAdapter(
                this, Cities);
        CitiesList.setAdapter(adapter);
        CitiesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                City city = adapter.getItem(i);
                Intent showTemps = new Intent(CitiesActivity.this, CityTemperatures.class);
                showTemps.putExtra("city", city.getName());
                showTemps.putExtra("temps", city.getTemperatures());
                CitiesActivity.this.startActivity(showTemps);
            }
        });
    }
}
