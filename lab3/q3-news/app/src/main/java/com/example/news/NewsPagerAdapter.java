package com.example.news;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class NewsPagerAdapter extends FragmentStateAdapter {

    public NewsPagerAdapter(@NonNull FragmentActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) return new TopStoriesFragment();
        if (position == 1) return new SportsFragment();
        return new EntertainmentFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
