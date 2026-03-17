package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SeatSelectionActivity extends AppCompatActivity {

    TextView tvMovieName, tvSelectedSeats, tvTotalPrice;
    LinearLayout seatContainer;
    Button btnProceedSnacks, btnBookSeats;

    int ticketPrice = 10;
    ArrayList<Integer> selectedSeats = new ArrayList<>();
    ArrayList<Integer> bookedSeats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);

        tvMovieName = findViewById(R.id.tvMovieName);
        tvSelectedSeats = findViewById(R.id.tvSelectedSeats);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        seatContainer = findViewById(R.id.seatContainer);
        btnProceedSnacks = findViewById(R.id.btnProceedSnacks);
        btnBookSeats = findViewById(R.id.btnBookSeats);

        String movieName = getIntent().getStringExtra("movieName");
        tvMovieName.setText(movieName);

        bookedSeats.clear();
        int[] preBooked = {5,6,7,12,15,18,20,22,25,28,30,32,35,57,58,59};
        for(int s : preBooked) bookedSeats.add(s);

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
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setPadding(0, 12, 0, 12);

            for (int i = 0; i < layoutPattern[r][0]; i++) {
                Button seat = createSeatButton(seatNumber);
                row.addView(seat);
                seatNumber++;
            }

            LinearLayout aisle = new LinearLayout(this);
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

        btnProceedSnacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedSeats.isEmpty()) {
                    Toast.makeText(SeatSelectionActivity.this, "Please select at least 1 seat", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(SeatSelectionActivity.this, SnacksActivity.class);
                intent.putExtra("movieName", movieName);
                intent.putExtra("selectedSeatsCount", selectedSeats.size());
                intent.putExtra("ticketPrice", ticketPrice);
                startActivity(intent);
            }
        });

        btnBookSeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedSeats.isEmpty()) {
                    Toast.makeText(SeatSelectionActivity.this, "Please select at least 1 seat", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(SeatSelectionActivity.this,
                        "Booking " + selectedSeats.size() + " seats",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SeatSelectionActivity.this, TicketSummaryActivity.class);
                intent.putExtra("movieName", movieName);
                intent.putExtra("selectedSeatsCount", selectedSeats.size());
                intent.putExtra("ticketPrice", ticketPrice);
                intent.putExtra("snacksTotal", 0);
                startActivity(intent);
            }
        });

        updateSeatInfo();
    }

    private Button createSeatButton(int seatNumber) {
        Button seat = new Button(this);
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

        seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bookedSeats.contains(seatNumber)) {
                    Toast.makeText(SeatSelectionActivity.this, "Seat already booked", Toast.LENGTH_SHORT).show();
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
            }
        });


        return seat;
    }

    private void updateSeatInfo() {
        tvSelectedSeats.setText("Selected Seats: " + selectedSeats.size());
        tvTotalPrice.setText("Total Price: $" + (selectedSeats.size() * ticketPrice));
        btnProceedSnacks.setEnabled(!selectedSeats.isEmpty());
    }
}