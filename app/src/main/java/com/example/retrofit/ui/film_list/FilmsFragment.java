package com.example.retrofit.ui.film_list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retrofit.App;
import com.example.retrofit.R;
import com.example.retrofit.data.models.Film;
import com.example.retrofit.databinding.FragmentFilmsBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsFragment extends Fragment implements FilmsAdapter.OnItemClickListener {

    private FragmentFilmsBinding binding;
    private FilmsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FilmsAdapter();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmsBinding.inflate(inflater,
                container,
                false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter.setListener(this);
        binding.recycler.setAdapter(adapter);

        App.api.getFilms().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(@NonNull Call<List<Film>> call,
                                   @NonNull Response<List<Film>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("ololo", "onResponse: " + response.body());
                    adapter.setFilms(response.body());
                } else {
                    Log.e("TAG", "onFailure: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Film>> call, @NonNull Throwable t) {
                Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onClick(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("bio",id);
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.singleFilmFragment,bundle);
    }


}