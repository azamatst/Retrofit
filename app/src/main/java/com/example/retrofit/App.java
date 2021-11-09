package com.example.retrofit;

import android.app.Application;

import com.example.retrofit.data.remote.FilmApi;
import com.example.retrofit.data.remote.RetrofitClient;

public class App extends Application {
    private RetrofitClient retrofitClient;
    public static FilmApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        api = retrofitClient.provideFilmApi();
    }
}
