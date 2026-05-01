package com.example.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyBookingsFragment extends Fragment {

    private RecyclerView recyclerView;
    private BookingAdapter adapter;
    private List<Bookings> bookingList;
    private TextView tvEmptyState;
    private EditText etSearch;
    private DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_bookings, container, false);

        recyclerView = view.findViewById(R.id.rvBookings);
        tvEmptyState = view.findViewById(R.id.tvEmptyState);
        etSearch = view.findViewById(R.id.etSearchBooking);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bookingList = new ArrayList<>();

        adapter = new BookingAdapter(getContext(), bookingList, bookingId -> {
            cancelBooking(bookingId);
        });

        recyclerView.setAdapter(adapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
                checkEmptyState();
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        fetchBookings();
        return view;
    }

    private void checkEmptyState() {
        if (adapter.getItemCount() == 0) {
            tvEmptyState.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvEmptyState.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void fetchBookings() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            Toast.makeText(getContext(), "Not logged in!", Toast.LENGTH_SHORT).show();
            return;
        }
        String userId = currentUser.getUid();

        mDatabase.child("bookings").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookingList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Bookings booking = ds.getValue(Bookings.class);
                    if (booking != null) {
                        booking.setBookingId(ds.getKey());
                        bookingList.add(booking);
                    }
                }
                adapter.filter(etSearch.getText().toString());
                checkEmptyState();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Failed to load bookings.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cancelBooking(String bookingId) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) return;

        mDatabase.child("bookings").child(currentUser.getUid()).child(bookingId).removeValue()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(getContext(), "Booking Cancelled Successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Failed to cancel.", Toast.LENGTH_SHORT).show();
                });
    }
}