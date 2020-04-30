package com.example.mhmsbmrapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mhmsbmrapp.DashboardBmr.MainActivity;
import com.example.mhmsbmrapp.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelectMhe extends AppCompatActivity {

    private Spinner spinnerMhe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mhe);

        Intent intent = getIntent();

        //System.out.println("###################################################value = " + ((ArrayList<String>)intent.getSerializableExtra("list")).toString());
        setContentView(R.layout.activity_select_mhe);

        spinnerMhe = findViewById(R.id.spinnerMhe);

        List<String> categories = (ArrayList<String>)intent.getSerializableExtra("list");


        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinnerMhe.setAdapter(dataAdapter);

        spinnerMhe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                final String userName = getIntent().getStringExtra("userName");
                final String password = getIntent().getStringExtra("password");


                if(parent.getItemAtPosition(position).equals("MHE/OP*"))
                {
                    //do nothing
                }
                else
                {
                    Log.e("inside else", getIntent().getStringExtra("userName")+" "+getIntent().getStringExtra("password"));

                    //on selecting spinner item
                    final String selectedMHE = parent.getItemAtPosition(position).toString();

                    //show spinner selected item
                    Toast.makeText(parent.getContext(), "Selected" + selectedMHE, Toast.LENGTH_SHORT).show();

                    Log.e("the three values are", userName+" "+ password +" "+selectedMHE);

                    Runnable runnable = new Runnable() {
                        public void run() {

                            String loginToken = new MHPFlow().loginOP(userName, password, selectedMHE);
                            String sessionToken = null;
                            try{
                                sessionToken = new JSONObject(MHPFlow.decoded(loginToken)).getString("sessionToken");
                            } catch (Exception e) { e.printStackTrace(); }


                            Log.e("check" ,"________________________^^^^^^^^^^put in sharedPreferences^^^^^^^^^^^^^^______________________________");
                            System.out.println("loginToken with or without token = " + loginToken);
                            System.out.println("sessionToken with or wihout token = " + sessionToken);
                            Log.e("chekc", "________________________^^^^^^^^^^^^^^^^^^^^^^^^______________________________");

                            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("loginToken", loginToken);
                            editor.putString("sessionToken", sessionToken);
                            editor.putString("mhpName", selectedMHE);

                            editor.commit();


                            Intent intent = new Intent(SelectMhe.this, MainActivity.class);
                            SelectMhe.this.startActivity(intent);
                        }
                    };
                    new Thread(runnable).start();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}

