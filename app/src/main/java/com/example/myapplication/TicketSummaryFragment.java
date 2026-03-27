package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TicketSummaryFragment extends Fragment {
    private TextView tvMovie, tvSeats, tvPrice;
    private Button btnSend;

    private String movieName;
    private int numSeats, ticketPrice, snacksPrice, totalPrice;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket_summary, container, false);

        tvMovie = view.findViewById(R.id.tvSummaryMovie);
        tvSeats = view.findViewById(R.id.tvSummarySeats);
        tvPrice = view.findViewById(R.id.tvSummaryPrice);
        btnSend = view.findViewById(R.id.btnSendTicket);

        if (getArguments() != null) {
            movieName = getArguments().getString("movie_name");
            numSeats = getArguments().getInt("selectedSeatsCount");
            ticketPrice = getArguments().getInt("ticketPrice");
            snacksPrice = getArguments().getInt("snacksTotal");
        }

        totalPrice = (ticketPrice * numSeats) + snacksPrice;

        tvMovie.setText(movieName);
        tvSeats.setText("Seats: " + numSeats);
        tvPrice.setText("Total Price: $" + totalPrice);

        saveBooking();

        btnSend.setOnClickListener(v -> {

            String ticketText = "Movie: " + movieName +
                    "\nSeats: " + numSeats +
                    "\nTotal Price: $" + totalPrice;

            Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
            whatsappIntent.setType("text/plain");
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, ticketText);
            whatsappIntent.setPackage("com.whatsapp");

            Intent gmailIntent = new Intent(Intent.ACTION_SEND);
            gmailIntent.setType("text/plain");
            gmailIntent.putExtra(Intent.EXTRA_TEXT, ticketText);
            gmailIntent.putExtra(Intent.EXTRA_SUBJECT, "Ticket Details");
            gmailIntent.setPackage("com.google.android.gm");

            Intent chooser = Intent.createChooser(whatsappIntent, "Share Ticket via");
            chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{gmailIntent});

            try {
                startActivity(chooser);
            }
            catch (ActivityNotFoundException e) {
                Toast.makeText(getContext(), "WhatsApp or Gmail not installed", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void saveBooking() {
        SharedPreferences prefs = getActivity().getSharedPreferences("BookingPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("movie", movieName);
        editor.putInt("seats", numSeats);
        editor.putInt("price", totalPrice);
        editor.apply();
    }
}