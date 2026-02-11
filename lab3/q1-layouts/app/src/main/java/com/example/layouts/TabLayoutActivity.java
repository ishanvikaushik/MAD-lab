package com.example.layouts;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class TabLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_tab_layout);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);

        viewPager.setAdapter(new FragmentPagerAdapter(
                getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0: return TabFragment.newInstance("This is Artists tab");
                    case 1: return TabFragment.newInstance("This is Albums tab");
                    default: return TabFragment.newInstance("This is Songs tab");
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return position == 0 ? "Artists"
                        : position == 1 ? "Albums" : "Songs";
            }
        });

        tabLayout.setupWithViewPager(viewPager);
    }
}
