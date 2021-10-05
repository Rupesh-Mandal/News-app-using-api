package com.kali_corporation.financual_study_guide.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kali_corporation.financual_study_guide.fragment.EntertainmentFragment;
import com.kali_corporation.financual_study_guide.fragment.HealthFragment;
import com.kali_corporation.financual_study_guide.fragment.HomeFragment;
import com.kali_corporation.financual_study_guide.fragment.ScienceFragment;
import com.kali_corporation.financual_study_guide.fragment.Business;
import com.kali_corporation.financual_study_guide.fragment.TechFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new Business();
            case 2:
                return new HealthFragment();
            case 3:
                return new ScienceFragment();
            case 4:
                return new EntertainmentFragment();
            case 5:
                return new TechFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
