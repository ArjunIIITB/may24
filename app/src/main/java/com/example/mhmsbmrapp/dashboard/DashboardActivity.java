package com.example.mhmsbmrapp.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mhmsbmrapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class DashboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        LinearLayout linearLayout = findViewById(R.id.editTextContainer);
        JSONArray waitList = null;
        try {
            JSONArray completeList = new JSONArray(getIntent().getStringExtra("completePatient"));

            if(completeList != null) {
                for (int i = 0; i < completeList.length(); i++) {
                    JSONObject patient = completeList.getJSONObject(i);


                    final EditText editText1 = new EditText(this);
                    editText1.setText(patient.getString("patientId"));
                    editText1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    editText1.setPadding(20, 20, 20, 20);

                    final EditText editText2 = new EditText(this);
                    editText2.setText(patient.getString("assignedmhpId"));
                    editText2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    editText2.setPadding(20, 20, 20, 20);

                    final EditText editText3 = new EditText(this);
                    editText3.setText(patient.getString("assignedmhpName"));
                    editText3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    editText3.setPadding(20, 20, 20, 20);


                    linearLayout.addView(editText1);
                    linearLayout.addView(editText2);
                    linearLayout.addView(editText3);

                }
            }

        }catch(Exception e){}


        final EditText space = new EditText(this);
        space.setText("Comlete list ends here, waiting list starts");
        space.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        space.setPadding(20, 50, 20, 50);

        linearLayout.addView(space);

        try {
            JSONArray waitingList = new JSONArray(getIntent().getStringExtra("waitingPatient"));
            waitList = new JSONArray(getIntent().getStringExtra("waitingPatient"));
            if(waitingList != null) {
                for (int i = 0; i < waitingList.length(); i++) {
                    JSONObject patient = waitingList.getJSONObject(i);

                    final EditText editText1 = new EditText(this);
                    editText1.setText(patient.getString("patientId"));
                    editText1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    editText1.setPadding(20, 20, 20, 20);

                    final EditText editText2 = new EditText(this);
                    editText2.setText(patient.getString("assignedmhpId"));
                    editText2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    editText2.setPadding(20, 20, 20, 20);

                    final EditText editText3 = new EditText(this);
                    editText3.setText(patient.getString("assignedmhpName"));
                    editText3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    editText3.setPadding(20, 20, 20, 20);


                    linearLayout.addView(editText1);
                    linearLayout.addView(editText2);
                    linearLayout.addView(editText3);


                    Thread.sleep(1000);

                }
            }
        }catch(Exception e){e.printStackTrace();}

        System.out.println("after sleep");


        Intent intent = new Intent(DashboardActivity.this, SearchPatient.class);
        try {
            JSONObject patient = (JSONObject) waitList.get(0); //depends on the selection of user
            intent.putExtra("patientId", patient.getString("patientId"));
            Log.e("DashBoard PatientId", patient.getString("patientId"));
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("patientId", patient.getString("patientId"));

        }catch (Exception e){}
        DashboardActivity.this.startActivity(intent);

    }
}
