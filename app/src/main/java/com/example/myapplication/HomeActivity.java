package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button btnTrailer1, btnTrailer2, btnTrailer3, btnTrailer4;
    Button btnBook1, btnBook2, btnBook3, btnBook4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnTrailer1 = findViewById(R.id.btnTrailer1);
        btnTrailer2 = findViewById(R.id.btnTrailer2);
        btnTrailer3 = findViewById(R.id.btnTrailer3);
        btnTrailer4 = findViewById(R.id.btnTrailer4);

        btnTrailer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=3-ZP95NF_Wk"));
                startActivity(intent);
            }
        });

        btnTrailer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=njdreZRjvpc"));
                startActivity(intent);
            }
        });

        btnTrailer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=kqAYR6z7yAU"));
                startActivity(intent);
            }
        });

        btnTrailer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=XHQ9CPRfDsw"));
                startActivity(intent);
            }
        });

        btnBook1 = findViewById(R.id.btnBook1);
        btnBook2 = findViewById(R.id.btnBook2);
        btnBook3 = findViewById(R.id.btnBook3);
        btnBook4 = findViewById(R.id.btnBook4);

        btnBook1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SeatSelectionActivity.class);
                intent.putExtra("movieName", "The Sixth Sense");
                startActivity(intent);
            }
        });

        btnBook2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SeatSelectionActivity.class);
                intent.putExtra("movieName", "The Wizard of Oz");
                startActivity(intent);
            }
        });

        btnBook3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SeatSelectionActivity.class);
                intent.putExtra("movieName", "The Omen");
                startActivity(intent);
            }
        });

        btnBook4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SeatSelectionActivity.class);
                intent.putExtra("movieName", "Misery");
                startActivity(intent);
            }
        });
    }
}