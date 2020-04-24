package com.example.mhmsbmrapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mhmsbmrapp.DashboardBmr.MainActivity;
import com.example.mhmsbmrapp.R;

import java.util.ArrayList;
import java.util.List;

public class SelectMhe extends AppCompatActivity {

    private Spinner spinnerMhe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mhe);

        Intent intent = getIntent();

        System.out.println("###################################################value = " + ((ArrayList<String>)intent.getSerializableExtra("list")).toString());
        setContentView(R.layout.activity_select_mhe);

        spinnerMhe = findViewById(R.id.spinnerMhe);

        List<String> categories = (ArrayList<String>)intent.getSerializableExtra("list");
        /*categories.add(0,"MHE/OP*");
        categories.add("Ted");
        categories.add("Kartik clinic");
        categories.add("metro");
        categories.add("SRM");*/

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinnerMhe.setAdapter(dataAdapter);

        spinnerMhe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("MHE/OP*"))
                {
                    //do nothing
                }
                else
                {
                    //on selecting spinner item
                    String item = parent.getItemAtPosition(position).toString();

                    //show spinner selected item
                    Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                    if(parent.getItemAtPosition(position).equals("metro"));
                    {
                        Intent intent = new Intent(SelectMhe.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
