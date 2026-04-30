package com.example.myapplication;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class NowShowingFragment extends Fragment {

    private RecyclerView rvMovies;
    private MovieAdapter adapter;
    private ArrayList<Movie> movieList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_showing, container, false);

        rvMovies = view.findViewById(R.id.rvMovies);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovies.setHasFixedSize(true);

        movieList = JsonUtils.loadMoviesByType(requireContext(), "now_showing");

        adapter = new MovieAdapter(getContext(), movieList, new MovieAdapter.OnMovieClickListener() {
            @Override
            public void onBookClick(Movie movie) {
                SeatSelectionFragment fragment = new SeatSelectionFragment();
                Bundle bundle = new Bundle();
                bundle.putString("movie_name", movie.getName());
                bundle.putString("trailer_url", movie.getTrailerUrl());
                bundle.putString("movie_type", "Now Showing");
                fragment.setArguments(bundle);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        rvMovies.setAdapter(adapter);

        return view;
    }
}