package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private Context context;
    private List<Bookings> fullList;
    private List<Bookings> filteredList;
    private OnCancelClickListener cancelClickListener;

    public interface OnCancelClickListener {
        void onCancelClick(String bookingId);
    }

    public BookingAdapter(Context context, List<Bookings> bookingList, OnCancelClickListener listener) {
        this.context = context;
        this.fullList = bookingList;
        this.filteredList = new ArrayList<>(bookingList);
        this.cancelClickListener = listener;
    }

    public void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(fullList);
        }
        else {
            String lowerCaseQuery = query.toLowerCase();
            for (Bookings booking : fullList) {
                if (booking.getMovieName().toLowerCase().contains(lowerCaseQuery)) {
                    filteredList.add(booking);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_booking, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Bookings booking = filteredList.get(position);

        holder.tvMovieName.setText(booking.getMovieName());
        holder.tvDateTime.setText(booking.getDateTime());
        holder.tvNumTickets.setText(booking.getSeats() + " Tickets");

        if (booking.getMoviePoster() != null && !booking.getMoviePoster().isEmpty()) {
            int resId = context.getResources().getIdentifier(booking.getMoviePoster(), "drawable", context.getPackageName());
            if (resId != 0) {
                holder.ivMoviePoster.setImageResource(resId);
            }
            else {
                holder.ivMoviePoster.setImageResource(android.R.drawable.ic_menu_gallery);
            }
        }

        holder.ivCancelBooking.setOnClickListener(v -> {
            if (booking.getTimestamp() > System.currentTimeMillis()) {
                new AlertDialog.Builder(context)
                        .setTitle("Cancel Booking")
                        .setMessage("Are you sure you want to cancel this booking?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            cancelClickListener.onCancelClick(booking.getBookingId());
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
            else {
                new AlertDialog.Builder(context)
                        .setTitle("Cannot Cancel")
                        .setMessage("Cannot cancel past bookings.")
                        .setPositiveButton("OK", null)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMoviePoster, ivCancelBooking;
        TextView tvMovieName, tvDateTime, tvNumTickets;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMoviePoster = itemView.findViewById(R.id.ivMoviePoster);
            ivCancelBooking = itemView.findViewById(R.id.ivCancelBooking);
            tvMovieName = itemView.findViewById(R.id.tvMovieName);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            tvNumTickets = itemView.findViewById(R.id.tvNumTickets);
        }
    }
}