package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpBmrTab;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mhmsbmrapp.R;

public class AFragment extends Fragment {
    EditText firstName;
    EditText lastName;
    Button submitBtn;

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;

    public AFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a, container, false);

        textView = rootView.findViewById(R.id.text_view_selected);



        firstName = (EditText) rootView.findViewById(R.id.firstname);
        lastName = (EditText) rootView.findViewById(R.id.lastname);
        submitBtn = (Button) rootView.findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first = firstName.getText().toString();
                String last = lastName.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("FirstName",first);
                bundle.putString("LastName",last);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                BFragment bFragment = new BFragment();
                bFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.frame_container,bFragment);
                fragmentTransaction.commit();


            }
        });
        return rootView;
    }
}










