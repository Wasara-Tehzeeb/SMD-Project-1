package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;

public class ComingSoonFragment extends Fragment {

    Button btnTrailer12, btnTrailer22, btnTrailer32, btnTrailer42;
    Button btnBook12, btnBook22, btnBook32, btnBook42;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_home, container, false);

        btnTrailer12 = view.findViewById(R.id.btnTrailer12);
        btnTrailer22 = view.findViewById(R.id.btnTrailer22);
        btnTrailer32 = view.findViewById(R.id.btnTrailer32);
        btnTrailer42 = view.findViewById(R.id.btnTrailer42);

        btnTrailer12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=3-ZP95NF_Wk")));
            }
        });

        btnTrailer22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=njdreZRjvpc")));
            }
        });

        btnTrailer32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=kqAYR6z7yAU")));
            }
        });

        btnTrailer42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=XHQ9CPRfDsw")));
            }
        });

        btnBook12 = view.findViewById(R.id.btnBook12);
        btnBook22 = view.findViewById(R.id.btnBook22);
        btnBook32 = view.findViewById(R.id.btnBook32);
        btnBook42 = view.findViewById(R.id.btnBook42);

        btnBook12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SeatSelectionActivity.class);
                intent.putExtra("movieName", "The Sixth Sense");
                startActivity(intent);
            }
        });

        btnBook22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SeatSelectionActivity.class);
                intent.putExtra("movieName", "The Wizard of Oz");
                startActivity(intent);
            }
        });

        btnBook32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SeatSelectionActivity.class);
                intent.putExtra("movieName", "The Omen");
                startActivity(intent);
            }
        });

        btnBook42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SeatSelectionActivity.class);
                intent.putExtra("movieName", "Misery");
                startActivity(intent);
            }
        });

        return view;
    }
}