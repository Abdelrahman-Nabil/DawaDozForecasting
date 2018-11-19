package com.example.abdu.dawadozforecasting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class CityTemperatures extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_temperatures);

        Intent intent = getIntent();
        ListView tempList = findViewById(R.id.list);
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Temperature> temps = (ArrayList<Temperature>) args.getSerializable("ARRAYLIST");
        final TemperatureAdapter adapter = new TemperatureAdapter(
                this, temps);

        tempList.setAdapter(adapter);

    }
}
