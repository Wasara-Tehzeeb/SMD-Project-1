package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Parcelable;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TicketSummaryActivity extends AppCompatActivity {

    TextView tvMovie, tvSeats, tvPrice;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_summary);

        tvMovie = findViewById(R.id.tvSummaryMovie);
        tvSeats = findViewById(R.id.tvSummarySeats);
        tvPrice = findViewById(R.id.tvSummaryPrice);
        btnSend = findViewById(R.id.btnSendTicket);

        Intent intent = getIntent();
        String movieName = intent.getStringExtra("movieName");
        int numSeats = intent.getIntExtra("selectedSeatsCount", 0);
        int ticketPrice = intent.getIntExtra("ticketPrice", 0);
        int snacksPrice = intent.getIntExtra("snacksTotal", 0);

        int totalPrice = (ticketPrice * numSeats) + snacksPrice;

        tvMovie.setText("Movie: " + movieName);
        tvSeats.setText("Seats: " + numSeats);
        tvPrice.setText("Total Price: $" + totalPrice);

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
                Toast.makeText(this, "WhatsApp or Gmail not installed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}