package com.example.abdu.dawadozforecasting;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import java.util.List;

public class CitiesLoader extends AsyncTaskLoader<List<City>> {
    private String Url;

    public CitiesLoader(Context context, String url) {
        super(context);
        Url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<City> loadInBackground() {
        if (Url == null) {
            return null;
        }
        return Query.extractCity(Url);

    }
}
