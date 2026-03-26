package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SnacksFragment extends Fragment {
    private ListView listView;
    private SnackAdapter adapter;
    private ArrayList<Snack> snackList;

    private String movieName;
    private int selectedSeatsCount;
    private int ticketPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_snacks, container, false);
        listView = view.findViewById(R.id.lvSnacks);

        if (getArguments() != null) {
            movieName = getArguments().getString("movie_name", "");
            selectedSeatsCount = getArguments().getInt("selectedSeatsCount", 0);
            ticketPrice = getArguments().getInt("ticketPrice", 0);
        }

        snackList = new ArrayList<>();
        snackList.add(new Snack(R.drawable.snack1, "Popcorn", 8));
        snackList.add(new Snack(R.drawable.snack2, "Nachos", 7));
        snackList.add(new Snack(R.drawable.snack3, "Soft Drink", 5));
        snackList.add(new Snack(R.drawable.snack4, "Candy Mix", 6));

        adapter = new SnackAdapter(getContext(), snackList);
        listView.setAdapter(adapter);

        Button btnConfirm = view.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(v -> {
            int snacksTotal = adapter.getTotalPrice();

            TicketSummaryFragment fragment = new TicketSummaryFragment();
            Bundle bundle = new Bundle();
            bundle.putString("movie_name", movieName);
            bundle.putInt("selectedSeatsCount", selectedSeatsCount);
            bundle.putInt("ticketPrice", ticketPrice);
            bundle.putInt("snacksTotal", snacksTotal);
            fragment.setArguments(bundle);

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit();
        });
        return view;
    }
}