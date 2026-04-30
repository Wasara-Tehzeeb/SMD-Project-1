package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SeatSelectionFragment extends Fragment {
    TextView tvMovieName, tvSelectedSeats, tvTotalPrice;
    LinearLayout seatContainer;
    Button btnProceedSnacks, btnBookSeats;

    int ticketPrice = 10;
    ArrayList<Integer> selectedSeats = new ArrayList<>();
    ArrayList<Integer> bookedSeats = new ArrayList<>();

    boolean isComingSoon = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seat_selection, container, false);

        tvMovieName = view.findViewById(R.id.tvMovieName);
        tvSelectedSeats = view.findViewById(R.id.tvSelectedSeats);
        tvTotalPrice = view.findViewById(R.id.tvTotalPrice);
        seatContainer = view.findViewById(R.id.seatContainer);
        btnProceedSnacks = view.findViewById(R.id.btnProceedSnacks);
        btnBookSeats = view.findViewById(R.id.btnBookSeats);

        Bundle bundle = getArguments();
        String movieName = "";
        String movieType = "";
        String trailerUrl = "";

        if (bundle != null) {
            movieName = bundle.getString("movie_name");
            trailerUrl = bundle.getString("trailer_url");
            movieType = bundle.getString("movie_type");
        }
        tvMovieName.setText(movieName);

        if (movieType != null && movieType.equalsIgnoreCase("Coming Soon")) {
            isComingSoon = true;
        }

        bookedSeats.clear();

        if (!isComingSoon) {
            int[] preBooked = {5, 6, 7, 12, 15, 18, 20, 22, 25, 28, 30, 32, 35, 57, 58, 59};
            for (int s : preBooked) bookedSeats.add(s);
        }

        int[][] layoutPattern = {
                {4, 4},
                {4, 4},
                {4, 4},
                {4, 4},
                {4, 4},
                {4, 4},
                {4, 4},
                {4, 4},
                {4, 4},
                {4, 4}
        };

        int seatNumber = 1;
        for (int r = 0; r < layoutPattern.length; r++) {
            LinearLayout row = new LinearLayout(getContext());
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setPadding(0, 12, 0, 12);

            for (int i = 0; i < layoutPattern[r][0]; i++) {
                Button seat = createSeatButton(seatNumber);
                row.addView(seat);
                seatNumber++;
            }

            LinearLayout aisle = new LinearLayout(getContext());
            LinearLayout.LayoutParams aisleParams = new LinearLayout.LayoutParams(60, LinearLayout.LayoutParams.WRAP_CONTENT);
            aisle.setLayoutParams(aisleParams);
            row.addView(aisle);

            for (int i = 0; i < layoutPattern[r][1]; i++) {
                Button seat = createSeatButton(seatNumber);
                row.addView(seat);
                seatNumber++;
            }

            seatContainer.addView(row);
        }
        if (isComingSoon) {
            btnBookSeats.setText("Coming Soon");
            btnBookSeats.setEnabled(false);
            btnProceedSnacks.setText("Watch Trailer");

            final String trailerURL = trailerUrl;
            btnProceedSnacks.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailerURL));
                startActivity(intent);
            });

        }
        else {
            btnProceedSnacks.setText("Proceed to Snacks");
            btnBookSeats.setText("Book Seats");
            btnBookSeats.setEnabled(true);

            final String MovieName = movieName;
            btnProceedSnacks.setOnClickListener(v -> {
                if (selectedSeats.isEmpty()) {
                    Toast.makeText(getContext(), "Please select at least 1 seat", Toast.LENGTH_SHORT).show();
                    return;
                }

                SnacksFragment fragment = new SnacksFragment();
                Bundle b = new Bundle();
                b.putString("movie_name", MovieName);
                b.putInt("selectedSeatsCount", selectedSeats.size());
                b.putInt("ticketPrice", ticketPrice);
                fragment.setArguments(b);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
            });

            btnBookSeats.setOnClickListener(v -> {
                if (selectedSeats.isEmpty()) {
                    Toast.makeText(getContext(), "Please select at least 1 seat", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(getContext(), "Booking Confirmed!", Toast.LENGTH_SHORT).show();

                TicketSummaryFragment fragment = new TicketSummaryFragment();
                Bundle b = new Bundle();
                b.putString("movie_name", MovieName);
                b.putInt("selectedSeatsCount", selectedSeats.size());
                b.putInt("ticketPrice", ticketPrice);
                b.putInt("snacksTotal", 0);
                fragment.setArguments(b);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
            });
        }

        updateSeatInfo();

        return view;
    }

    private Button createSeatButton(int seatNumber) {
        Button seat = new Button(getContext());
        seat.setText("");
        seat.setAllCaps(false);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(8);
        if (bookedSeats.contains(seatNumber)) {
            drawable.setColor(Color.RED);
        }
        else {
            drawable.setColor(Color.TRANSPARENT);
            drawable.setStroke(3, Color.GRAY);
        }
        seat.setBackground(drawable);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        params.setMargins(4, 4, 4, 4);
        seat.setLayoutParams(params);
        seat.setMinHeight(0);
        seat.setMinimumHeight(0);
        seat.setMinWidth(0);
        seat.setMinimumWidth(0);
        seat.setPadding(0, 12, 0, 12);

        seat.setOnClickListener(v -> {
            if (isComingSoon) {
                Toast.makeText(getContext(), "Seats not available for Coming Soon movies", Toast.LENGTH_SHORT).show();
                return;
            }

            if (bookedSeats.contains(seatNumber)) {
                Toast.makeText(getContext(), "Seat already booked", Toast.LENGTH_SHORT).show();
            }
            else if (selectedSeats.contains(seatNumber)) {
                selectedSeats.remove(Integer.valueOf(seatNumber));
                drawable.setColor(Color.TRANSPARENT);
                drawable.setStroke(3, Color.GRAY);
                seat.setBackground(drawable);
            }
            else {
                selectedSeats.add(seatNumber);
                drawable.setColor(Color.GREEN);
                seat.setBackground(drawable);
            }
            updateSeatInfo();
        });

        return seat;
    }
    private void updateSeatInfo() {
        if (!isComingSoon) {
            tvSelectedSeats.setText("Selected Seats: " + selectedSeats.size());
            tvTotalPrice.setText("Total Price: $" + (selectedSeats.size() * ticketPrice));
            btnProceedSnacks.setEnabled(!selectedSeats.isEmpty());
        }
    }
}