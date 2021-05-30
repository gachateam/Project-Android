package com.gacha.quizapp.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gacha.quizapp.fragments.SignInFragment;
import com.gacha.quizapp.fragments.SignUpFragment;

public class SignInAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;

    public SignInAdapter(FragmentManager fm, Context context, int totalTabs){
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    @NonNull
    public Fragment getItem(int position){
        switch (position){
            case 0:
                return new SignInFragment();
            case 1:
                return new SignUpFragment();
            default:
                return null;
        }
    }
}
