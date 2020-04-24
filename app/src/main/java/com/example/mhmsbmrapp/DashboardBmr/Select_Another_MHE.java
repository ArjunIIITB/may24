package com.example.mhmsbmrapp.DashboardBmr;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.mhmsbmrapp.Login.SelectMhe;
import com.example.mhmsbmrapp.R;

import java.util.ArrayList;
import java.util.List;

public class Select_Another_MHE extends Fragment {
    private Spinner spinnerMhe;

    public Select_Another_MHE() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_select_mhe, container, false);

        spinnerMhe = view.findViewById(R.id.spinnerMhe);

        List<String> categories = new ArrayList<>();
        categories.add(0, "MHE/OP*");
        categories.add("Ted");
        categories.add("Kartik clinic");
        categories.add("metro");
        categories.add("SRM");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinnerMhe.setAdapter(dataAdapter);

        spinnerMhe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("MHE/OP*")) {
                    //do nothing
                } else {
                    //on selecting spinner item
                    String item = parent.getItemAtPosition(position).toString();

                    //show spinner selected item
                    Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                    if (parent.getItemAtPosition(position).equals("metro")) ;
                    {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }
}


