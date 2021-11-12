package com.example.retrofit.ui.film_detail;

import static android.service.controls.ControlsProviderService.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.retrofit.App;
import com.example.retrofit.data.models.Film;
import com.example.retrofit.databinding.FragmentSingleFilmBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SingleFilmFragment extends Fragment {
    private FragmentSingleFilmBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSingleFilmBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.progressBar.setVisibility(View.VISIBLE);
        String id = getArguments().getString("bio");
        if (id != null){
            App.api.getFilm(id).enqueue(new Callback<Film>() {
                @Override
                public void onResponse(Call<Film> call, Response<Film> response) {
                    if (response.isSuccessful() && response.body() != null){
                        setData(response.body());
                        binding.progressBar.setVisibility(View.GONE);
                    }else {
                        Log.e(TAG, "onFAILURE: " + response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<Film> call, Throwable t) {
                    Log.e("TAG", "onFailure: " + t.getLocalizedMessage());

                }
            });
        }
//        id = getArguments().getString("bio");
//        if (id != null) {
//            App.api.getFilm(id).enqueue(new Callback<Film>() {
//                @Override
//                public void onResponse(Call<Film> call, Response<Film> response) {
//                    if(response.isSuccessful() && response.body() != null){
//                        setDataOnUi(response.body());
//                        binding.progressBar.setVisibility(View.GONE);
//                    }else {
//                        Log.e("TAG", "onFailure: " + response.errorBody().toString());
//                    }
//                }
//
//
//                @Override
//                public void onFailure(Call<Film> call, Throwable t) {
//                    Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
//
//                }
//            });
//        }
    }

    private void setData(Film film) {
        binding.releaseDate.setText(film.getReleaseDate());
        binding.tvOriginalTitle.setText(film.getOriginalTitle());
        binding.tvTitle.setText(film.getTitle());
        binding.tvDirector.setText(film.getDirector());
        binding.tvDescription.setText(film.getDescription());
        Glide.with(this).load(film.getImage()).into(binding.image);

    }

    private void setDataOnUi(Film film) {
        binding.releaseDate.setText(film.getReleaseDate());
        binding.tvOriginalTitle.setText(film.getOriginalTitle());
        binding.tvTitle.setText(film.getTitle());
        binding.tvDirector.setText(film.getDirector());
        binding.tvDescription.setText(film.getDescription());
        Glide.with(this).load(film.getImage()).into(binding.image);
    }


}