package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.ComingSoonFragment;
import com.example.myapplication.NowShowingFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new NowShowingFragment();
            case 1:
                return new ComingSoonFragment();
            default:
                return new NowShowingFragment(); // fallback, should not happen
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
