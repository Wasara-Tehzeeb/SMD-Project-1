package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SnacksActivity extends AppCompatActivity {

    String movieName;
    int selectedSeatsCount;
    int ticketPrice;

    int qty1 = 0, qty2 = 0, qty3 = 0, qty4 = 0;
    int price1 = 8, price2 = 7, price3 = 5, price4 = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snacks);

        movieName = getIntent().getStringExtra("movieName");
        selectedSeatsCount = getIntent().getIntExtra("selectedSeatsCount", 0);
        ticketPrice = getIntent().getIntExtra("ticketPrice", 0);

        Button btnPlus1 = findViewById(R.id.btnPlus1);
        Button btnMinus1 = findViewById(R.id.btnMinus1);
        TextView tvQty1 = findViewById(R.id.tvQuantity1);

        btnPlus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty1++;
                tvQty1.setText("" + qty1);
            }
        });
        btnMinus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qty1 > 0) {
                    qty1--;
                }
                tvQty1.setText("" + qty1);
            }
        });

        Button btnPlus2 = findViewById(R.id.btnPlus2);
        Button btnMinus2 = findViewById(R.id.btnMinus2);
        TextView tvQty2 = findViewById(R.id.tvQuantity2);

        btnPlus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty2++;
                tvQty2.setText("" + qty2);
            }
        });
        btnMinus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qty2 > 0) {
                    qty2--;
                }
                tvQty2.setText("" + qty2);
            }
        });

        Button btnPlus3 = findViewById(R.id.btnPlus3);
        Button btnMinus3 = findViewById(R.id.btnMinus3);
        TextView tvQty3 = findViewById(R.id.tvQuantity3);

        btnPlus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty3++;
                tvQty3.setText("" + qty3);
            }
        });

        btnMinus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qty3 > 0) {
                    qty3--;
                }
                tvQty3.setText("" + qty3);
            }
        });

        Button btnPlus4 = findViewById(R.id.btnPlus4);
        Button btnMinus4 = findViewById(R.id.btnMinus4);
        TextView tvQty4 = findViewById(R.id.tvQuantity4);

        btnPlus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty4++;
                tvQty4.setText("" + qty4);
            }
        });

        btnMinus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qty4 > 0) {
                    qty4--;
                }
                tvQty4.setText("" + qty4);
            }
        });

        Button btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int snacksTotal = (qty1 * price1) + (qty2 * price2) + (qty3 * price3) + (qty4 * price4);

                Intent intent = new Intent(SnacksActivity.this, TicketSummaryActivity.class);
                intent.putExtra("movieName", movieName);
                intent.putExtra("selectedSeatsCount", selectedSeatsCount);
                intent.putExtra("ticketPrice", ticketPrice);
                intent.putExtra("snacksTotal", snacksTotal);

                startActivity(intent);
            }
        });
    }
}