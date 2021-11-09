package com.example.retrofit.data.remote;

import com.example.retrofit.data.models.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FilmApi {


    @GET("films")
    Call<List<Film>> getFilms();

    @GET("films/{id}")
    Call<Film> getFilm(
            @Path("id") String id
    );


}
