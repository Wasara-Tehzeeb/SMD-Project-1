package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class SnackAdapter extends ArrayAdapter<Snack> {
    private Context context;
    private ArrayList<Snack> snackList;

    public SnackAdapter(@NonNull Context context, ArrayList<Snack> list) {
        super(context, 0, list);
        this.context = context;
        this.snackList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.item_snack, parent, false);
        }

        Snack currentSnack = snackList.get(position);

        ImageView imgSnack = v.findViewById(R.id.imgSnack);
        imgSnack.setImageResource(currentSnack.getImageResId());

        TextView tvName = v.findViewById(R.id.tvSnackName);
        tvName.setText(currentSnack.getName());

        TextView tvPrice = v.findViewById(R.id.tvSnackPrice);
        tvPrice.setText("$" + currentSnack.getPrice());

        TextView tvQty = v.findViewById(R.id.tvQuantity);
        tvQty.setText(String.valueOf(currentSnack.getQuantity()));

        Button btnPlus = v.findViewById(R.id.btnPlus);
        Button btnMinus = v.findViewById(R.id.btnMinus);

        btnPlus.setOnClickListener(view -> {
            currentSnack.setQuantity(currentSnack.getQuantity() + 1);
            tvQty.setText(String.valueOf(currentSnack.getQuantity()));
        });

        btnMinus.setOnClickListener(view -> {
            if (currentSnack.getQuantity() > 0) {
                currentSnack.setQuantity(currentSnack.getQuantity() - 1);
                tvQty.setText(String.valueOf(currentSnack.getQuantity()));
            }
        });

        return v;
    }
    public int getTotalPrice() {
        int total = 0;
        for (Snack s : snackList) {
            total += s.getPrice() * s.getQuantity();
        }
        return total;
    }

}
