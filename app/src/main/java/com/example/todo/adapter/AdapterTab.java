package com.example.todo.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.todo.DailyFragment;
import com.example.todo.MonthlyFragment;
import com.example.todo.WeeklyFragment;

public class AdapterTab extends FragmentPagerAdapter {
    private Context mContext;
    private int totalTab;

    public AdapterTab(Context context, FragmentManager fm, int totalTab) {
        super(fm);
        mContext =  context;
        this.totalTab = totalTab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new DailyFragment();
            case 1:
                return new WeeklyFragment();
            case 2:
                return new MonthlyFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTab;
    }
}
