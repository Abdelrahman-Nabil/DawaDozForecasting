package com.example.abdu.dawadozforecasting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class CityTemperatures extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_temperatures);

        Intent intent = getIntent();
        ListView tempList = findViewById(R.id.list);
        final TemperatureAdapter adapter = new TemperatureAdapter(
                this, intent.getIntegerArrayListExtra("temps"));
        tempList.setAdapter(adapter);

    }
}
