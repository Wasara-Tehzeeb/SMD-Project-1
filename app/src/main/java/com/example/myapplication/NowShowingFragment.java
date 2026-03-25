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

        movieList = new ArrayList<>();
        movieList.add(new Movie(R.drawable.movie1, "The Sixth Sense", "Horror • 1h 47m", "https://www.youtube.com/watch?v=3-ZP95NF_Wk"));
        movieList.add(new Movie(R.drawable.movie2, "The Wizard of Oz", "Fantasy • 1h 52m", "https://www.youtube.com/watch?v=njdreZRjvpc"));
        movieList.add(new Movie(R.drawable.movie3, "The Omen", "Horror • 1h 50m", "https://www.youtube.com/watch?v=kqAYR6z7yAU"));
        movieList.add(new Movie(R.drawable.movie4, "Misery", "Thriller • 2h 10m", "https://www.youtube.com/watch?v=XHQ9CPRfDsw"));

        adapter = new MovieAdapter(getContext(), movieList, new MovieAdapter.OnMovieClickListener() {
            @Override
            public void onBookClick(Movie movie) {
                SeatSelectionFragment fragment = new SeatSelectionFragment();
                Bundle bundle = new Bundle();
                bundle.putString("movie_name", movie.getName());
                bundle.putInt("movie_poster", movie.getPoster());
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