package com.example.myapplication;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class ComingSoonFragment extends Fragment {

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
        movieList.add(new Movie(R.drawable.movie11, "The Dark Knight", "Action • 2h 32m", "https://www.youtube.com/watch?v=EXeTwQWrcwY"));
        movieList.add(new Movie(R.drawable.movie12, "Inception", "Sci-Fi • 2h 28m", "https://www.youtube.com/watch?v=YoHD9XEInc0"));
        movieList.add(new Movie(R.drawable.movie13, "Interstellar", "Sci-Fi • 2h 49m", "https://www.youtube.com/watch?v=zSWdZVtXT7E"));
        movieList.add(new Movie(R.drawable.movie14, "The Shawshank Redemption", "Drama • 2h 22m", "https://www.youtube.com/watch?v=PLl99DlL6b4"));

        adapter = new MovieAdapter(getContext(), movieList, new MovieAdapter.OnMovieClickListener() {
            @Override
            public void onBookClick(Movie movie) {
                SeatSelectionFragment fragment = new SeatSelectionFragment();
                Bundle bundle = new Bundle();
                bundle.putString("movie_name", movie.getName());
                bundle.putString("trailer_url", movie.getTrailerUrl());
                bundle.putString("movie_type", "Coming Soon");
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