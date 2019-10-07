package com.example.diseasedetector.adapter;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.diseasedetector.fragments.CheckupRecordFragment;
import com.example.diseasedetector.fragments.PatientRecordFragment;

public class TabAdapter extends FragmentStatePagerAdapter{

    private Context myContext;
    int totalTabs;
    private Fragment[] childFragments;


    public TabAdapter(Context context, FragmentManager fm) {
        super(fm);
        myContext = context;
        childFragments = new Fragment[] {
                new PatientRecordFragment(), //0
                new CheckupRecordFragment() //1
        };
    }

    @Override
    public Fragment getItem(int position) {
        return childFragments[position];
    }


    @Override
    public int getCount() {
        return childFragments.length; //3 items
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 1:
                return "Check Up Record";

            case 0:
                return "Patient Record";
        }

        return null;
    }


}
