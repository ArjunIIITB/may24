package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpBmrTab;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mhmsbmrapp.DashboardBmr.MainActivity;
import com.example.mhmsbmrapp.R;

import org.w3c.dom.Text;

/**
 * Created by User on 3/2/2018.
 */

public class BFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_b, container, false);



        Bundle bundle = getArguments();

        String firstName = bundle.getString("FirstName");
        String lastName = bundle.getString("LastName");

        TextView firstText = (TextView) rootView.findViewById(R.id.firstname);
        TextView lastText = (TextView) rootView.findViewById(R.id.lastname);

        firstText.setText(firstName);
        lastText.setText(lastName);

        return rootView;
    }

}








