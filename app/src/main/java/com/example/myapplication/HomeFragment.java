package com.example.myapplication;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import androidx.appcompat.app.AlertDialog;
import android.content.Context;

public class HomeFragment extends Fragment {
    private ImageButton btnThreeDots;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager2 viewPager = view.findViewById(R.id.viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        TabLayoutMediator mediator;
        mediator = new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Now Showing");
                            break;
                        case 1:
                            tab.setText("Coming Soon");
                            break;
                    }
                }
        );
        mediator.attach();

        btnThreeDots = view.findViewById(R.id.btnThreeDots);
        btnThreeDots.setColorFilter(Color.WHITE);
        btnThreeDots.setOnClickListener(v -> showOptions());

        return view;
    }

    private void showOptions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Options");

        builder.setMessage("View Last Booking");

        builder.setPositiveButton("View", (dialog, which) -> {
            showLastBooking();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }
    private void showLastBooking() {
        SharedPreferences prefs = requireActivity().getSharedPreferences("BookingPrefs", Context.MODE_PRIVATE);

        String movie = prefs.getString("movie", null);
        int seats = prefs.getInt("seats", 0);
        int price = prefs.getInt("price", 0);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Last Booking");

        if (movie == null) {
            builder.setMessage("No previous booking found");
        }
        else {
            String message = "Movie: " + movie +
                    "\nSeats: " + seats +
                    "\nTotal Price: $" + price;
            builder.setMessage(message);
        }

        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
}