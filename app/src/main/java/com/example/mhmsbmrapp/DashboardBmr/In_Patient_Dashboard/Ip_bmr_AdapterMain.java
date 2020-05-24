package com.example.mhmsbmrapp.DashboardBmr.In_Patient_Dashboard;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mhmsbmrapp.DashboardBmr.In_Patient_Dashboard.AdmittedToIP.Ip_Admitted;
import com.example.mhmsbmrapp.DashboardBmr.In_Patient_Dashboard.DischargedIP.Ip_Discharged;

import static com.example.mhmsbmrapp.DashboardBmr.In_Patient_Dashboard.In_Patientbmr_TabFragment.int_items;

public class Ip_bmr_AdapterMain extends FragmentPagerAdapter {

    int tabCount1;


    //Constructor to the class
    public Ip_bmr_AdapterMain(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount1 = tabCount;
    }

    public Ip_bmr_AdapterMain(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Reffered_To_Ip();
            case 1:
                return new Ip_Admitted();
            case 2:
                return new Ip_Discharged();

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
                return "Ip Reffered";
            case 1:
                return "Ip Admitted";
            case 2:
                return "Ip Discharged";


        }

        return null;
    }
}