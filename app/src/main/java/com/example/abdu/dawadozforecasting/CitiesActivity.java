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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.example.abdu.dawadozforecasting.Query.citiesResult;

public class CitiesActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<City>> {

    private static final String _URL = "https://samples.openweathermap.org/data/2.5/forecast?id=3067696&APPID={f70c0764ff0fbf5b2a19b45a150e5fda}";
    private CitiesAdapter adapter;
    private static ArrayList<City> Cities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        ListView CitiesList = findViewById(R.id.list);
        adapter = new CitiesAdapter(
                this, Cities);
        CitiesList.setAdapter(adapter);
        ImageView emptyView = findViewById(R.id.empty_view);
        CitiesList.setEmptyView(emptyView);
        CitiesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                City city = adapter.getItem(i);
                Intent showTemps = new Intent(CitiesActivity.this, CityTemperatures.class);

                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)city.getTemperatures());
                showTemps.putExtra("BUNDLE",args);

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
            //City.deleteAll(City.class);
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getSupportLoaderManager();

            Log.e("loaderManager", loaderManager.toString());
            loaderManager.initLoader(1, null, this);
            /*for(int i =0 ;i<adapter.getCount(); i++){
                for(int j =0;j<adapter.getItem(i).getTemperatures().size(); j++){
                    adapter.getItem(i).getTemperatures().get(j).save();
                }
            }*/

        }
        else {
            Toast.makeText(this, "No internet access, attempting to retrieve data..", Toast.LENGTH_SHORT).show();
            //emptyView.setVisibility(View.VISIBLE);
            /*List<Temperature>  temps = null;

            temps= Temperature.listAll(Temperature.class);
            Cities.clear();
        for(int i =0; i<temps.size(); i++){
            Cities.add(new City(cities.get(i).getName(), cities.get(i).getTemperatures()));
        }
        adapter.notifyDataSetChanged();
            ArrayList<City> C = new ArrayList<>();
            for(int i =0; i<temps.size(); i++){
                City c = new City();
                if(!C.contains(temps.get(i).getCityName())) {
                    c.setName(temps.get(i).getCityName());
                    for (int j = 0; j < temps.size(); j++) {
                        if(temps.get(j).getCityName().equals(c.getName())){
                            c.addTemperature(temps.get(j).getTemp(), temps.get(j).getTime(),temps.get(j).getDescription(), temps.get(j).getCityName());
                        }
                    }
                }
                C.add(c);

            }
            adapter = new CitiesAdapter(
                    this, C);
            CitiesList.setAdapter(adapter);
            adapter.notifyDataSetChanged();*/



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
    }

    @Override
    public void onLoaderReset(Loader<List<City>> loader) {
        adapter.clear();
    }
}
