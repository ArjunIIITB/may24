package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpAssessement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mhmsbmrapp.AddPatientFolder.AddPatients;
import com.example.mhmsbmrapp.R;

public class AssessementMain extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.opassessementmain, container, false);

        return view;
    }
}


