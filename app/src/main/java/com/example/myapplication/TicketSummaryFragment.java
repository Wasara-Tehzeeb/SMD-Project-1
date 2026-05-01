package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TicketSummaryFragment extends Fragment {
    private TextView tvMovie, tvSeats, tvPrice;
    private Button btnSend;

    private String movieName;
    private int numSeats, ticketPrice, snacksPrice, totalPrice;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket_summary, container, false);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

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
        saveBookingToFirebase();

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
    private void saveBookingToFirebase() {
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            Toast.makeText(getContext(), "Please login to save booking", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = currentUser.getUid();
        String bookingId = mDatabase.child("bookings").child(userId).push().getKey();

        if (bookingId == null) {
            Toast.makeText(getContext(), "Failed to generate booking ID", Toast.LENGTH_SHORT).show();
            return;
        }

        String dateTime = new SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(new Date());
        long timestamp = System.currentTimeMillis() + (24 * 60 * 60 * 1000);

        String posterName = "";
        if (getArguments() != null) {
            posterName = getArguments().getString("movie_poster", "");
        }

        Bookings booking = new Bookings(userId, movieName, posterName, numSeats, totalPrice, dateTime, timestamp);

        mDatabase.child("bookings")
                .child(userId)
                .child(bookingId)
                .setValue(booking, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if (error == null) {
                            Toast.makeText(getContext(), "Booking saved!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Save failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}