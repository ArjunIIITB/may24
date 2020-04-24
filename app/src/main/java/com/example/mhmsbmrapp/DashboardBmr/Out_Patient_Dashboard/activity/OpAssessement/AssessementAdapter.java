package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpAssessement;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import static com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpAssessement.Fragment2.int_items;

public class AssessementAdapter extends FragmentPagerAdapter {

    int tabCount1;


    //Constructor to the class
    public AssessementAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount1 = tabCount;
    }

    public AssessementAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AssessementMain();
            case 1:
                return new AssessementHistory();

        }
        return null;
    }

    @Override
    public int getCount() {


        return int_items;
    }


    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Assessement";
            case 1:
                return "Assessement History";


        }

        return null;
    }
}
