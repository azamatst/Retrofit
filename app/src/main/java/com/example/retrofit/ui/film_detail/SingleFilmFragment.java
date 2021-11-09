package com.example.retrofit.ui.film_detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retrofit.App;
import com.example.retrofit.R;
import com.example.retrofit.data.models.Film;
import com.example.retrofit.databinding.FragmentSingleFilmBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SingleFilmFragment extends Fragment {
    private FragmentSingleFilmBinding binding;
    private String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSingleFilmBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = getArguments().getString("bio");
        if (id != null) {
            App.api.getFilm(id).enqueue(new Callback<Film>() {
                @Override
                public void onResponse(Call<Film> call, Response<Film> response) {
                    if(response.isSuccessful() && response.body() != null){
                        setDataInUi(response.body());
                    }else {
                        Log.e("TAG", "onFailure: " + response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<Film> call, Throwable t) {
                    Log.e("TAG", "onFailure: " + t.getLocalizedMessage());

                }
            });
        }
    }

    private void setDataInUi(Film body) {
        binding.releaseDate.setText(body.getReleaseDate());
        binding.tvOriginalTitle.setText(body.getOriginalTitle());
        binding.tvTitle.setText(body.getTitle());
        binding.tvDirector.setText(body.getDirector());
        binding.tvDescription.setText(body.getDescription());
    }


}