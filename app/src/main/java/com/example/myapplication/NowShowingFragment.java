package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;

public class NowShowingFragment extends Fragment {

    Button btnTrailer1, btnTrailer2, btnTrailer3, btnTrailer4;
    Button btnBook1, btnBook2, btnBook3, btnBook4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_home, container, false);

        btnTrailer1 = view.findViewById(R.id.btnTrailer1);
        btnTrailer2 = view.findViewById(R.id.btnTrailer2);
        btnTrailer3 = view.findViewById(R.id.btnTrailer3);
        btnTrailer4 = view.findViewById(R.id.btnTrailer4);

        btnTrailer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=3-ZP95NF_Wk")));
            }
        });

        btnTrailer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=njdreZRjvpc")));
            }
        });

        btnTrailer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=kqAYR6z7yAU")));
            }
        });

        btnTrailer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=XHQ9CPRfDsw")));
            }
        });

        btnBook1 = view.findViewById(R.id.btnBook1);
        btnBook2 = view.findViewById(R.id.btnBook2);
        btnBook3 = view.findViewById(R.id.btnBook3);
        btnBook4 = view.findViewById(R.id.btnBook4);

        btnBook1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SeatSelectionActivity.class);
                intent.putExtra("movieName", "The Sixth Sense");
                startActivity(intent);
            }
        });

        btnBook2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SeatSelectionActivity.class);
                intent.putExtra("movieName", "The Wizard of Oz");
                startActivity(intent);
            }
        });

        btnBook3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SeatSelectionActivity.class);
                intent.putExtra("movieName", "The Omen");
                startActivity(intent);
            }
        });

        btnBook4.setOnClickListener(new View.OnClickListener() {
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