package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpBmrTab;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import static com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpBmrTab.Fragment1.int_items;

public class MyAdapter extends FragmentPagerAdapter {

    int tabCount;


    //Constructor to the class
    public MyAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount = tabCount;
    }

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentOP();
            case 1:
                return new FragmenOphistory();

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
                return "OP-BMR";
            case 1:
                return "OP-BMR History";


        }

        return null;
    }
}
