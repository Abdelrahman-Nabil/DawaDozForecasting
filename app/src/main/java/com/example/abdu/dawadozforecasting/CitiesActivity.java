package com.example.abdu.dawadozforecasting;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.orm.SugarDb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CitiesActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<City>> {

    private static final String _URL = "https://samples.openweathermap.org/data/2.5/forecast?id=3067696&APPID={f70c0764ff0fbf5b2a19b45a150e5fda}";
    public static ProgressBar spinner;
    private static ArrayList<City> Cities = new ArrayList<>();
    private CitiesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        SugarDb db = new SugarDb(this);
        db.onCreate(db.getWritableDatabase());
        ListView CitiesList = findViewById(R.id.list);
        adapter = new CitiesAdapter(
                this, Cities);
        CitiesList.setAdapter(adapter);
        spinner = findViewById(R.id.progressBar1);
        CitiesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                City city = adapter.getItem(i);
                Intent showTemps = new Intent(CitiesActivity.this, CityTemperatures.class);

                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST", (Serializable) city.getTemperatures());
                showTemps.putExtra("BUNDLE", args);

                CitiesActivity.this.startActivity(showTemps);
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CitiesActivity.this, ContactUs.class);
                startActivity(intent);
            }
        });

        ConnectivityManager connMgr = (ConnectivityManager)
                CitiesActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            spinner.setVisibility(View.VISIBLE);

            Temperature.deleteAll(Temperature.class);
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(1, null, this);


        } else {
            Toast.makeText(this, "No internet access, attempting to retrieve data..", Toast.LENGTH_SHORT).show();
            List<Temperature> temps = null;
            spinner.setVisibility(View.VISIBLE);
            temps = Temperature.listAll(Temperature.class);
            Cities.clear();
            ArrayList<String> C = new ArrayList<>();
            ArrayList<City> citiesList = new ArrayList<>();
            for (int i = 0; i < temps.size(); i++) {
                if (!C.contains(temps.get(i).getCityName())) {
                    C.add(temps.get(i).getCityName());
                }
            }
            String cityName;
            ArrayList<Temperature> cityTemps;
            for (int j = 0; j < C.size(); j++) {
                cityName = C.get(j);
                cityTemps = new ArrayList<>();
                for (int k = 0; k < temps.size(); k++) {
                    if (temps.get(k).getCityName().equals(C.get(j))) {
                        cityTemps.add(temps.get(k));

                    }

                }
                citiesList.add(new City(cityName, cityTemps));
            }
            adapter = new CitiesAdapter(
                    this, citiesList);
            CitiesList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            spinner.setVisibility(View.GONE);


        }
    }

    @Override
    public Loader<List<City>> onCreateLoader(int id, Bundle args) {
        return new CitiesLoader(CitiesActivity.this, _URL);
    }

    @Override
    public void onLoadFinished(Loader<List<City>> loader, List<City> data) {
        adapter.clear();

        if (data != null && !data.isEmpty()) {
            adapter.addAll(data);
            data.clear();
        }
        spinner.setVisibility(View.GONE);

    }

    @Override
    public void onLoaderReset(Loader<List<City>> loader) {
        adapter.clear();
    }


}
