package com.example.mhmsbmrapp.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mhmsbmrapp.AddPatientFolder.AddPatients;
import com.example.mhmsbmrapp.Login.MHPFlow;
import com.example.mhmsbmrapp.R;
import com.example.mhmsbmrapp.utility.PatientUtility;

import org.json.JSONObject;

public class SearchPatient extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    Button button;
    Button opBmr;
    JSONObject patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_patient);
        button = (Button) findViewById(R.id.searchButton);
        button.setOnClickListener(this);

        opBmr = (Button)findViewById(R.id.op_bmr);
        button.setOnClickListener(this);


    } // oncreate


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.op_bmr){
            System.out.println("opbmr called");
            Log.e("Button id", "Button clicked is OP BMR");

            String patientId = getIntent().getStringExtra("patientId");
            Log.e("SearchPatient patientId", patientId);
            Intent intent = new Intent(SearchPatient.this, OPBMRForm.class);
            intent.putExtra("patientId", patientId);
            SearchPatient.this.startActivity(intent);

        }

        else {

            LinearLayout linearLayout1 = findViewById(R.id.editTextContainer1);
            EditText editText = new EditText(this);
            final Context context = SearchPatient.this;

            editText.setText("hello it is working");
            linearLayout1.addView(editText);
            Thread thread = new Thread() {
                public void run() {
                    String sessionToken = null;
                    String patientId;
                    Log.e("search module", "inside onClcik listener");
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    String loginToken = sharedPreferences.getString("loginToken", "");
                    try {
                        String loginDecodedToken = MHPFlow.decoded(loginToken);
                        sessionToken = new JSONObject(loginDecodedToken).getString("sessionToken");
                    } catch (Exception e) {
                    }
                    patientId = getIntent().getStringExtra("patientId");
                    patient = new PatientUtility().getPatient(loginToken, patientId, sessionToken);
                }
            };
            thread.start();

            LinearLayout linearLayout = findViewById(R.id.editTextContainer1);
            try {
                final EditText editText1 = new EditText(context);
                editText1.setText(patient.getString("dateOfBirth"));
                Log.e("dob", patient.getString("dateOfBirth"));
                editText1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText1.setPadding(20, 20, 20, 20);

                final EditText editText2 = new EditText(context);
                editText2.setText(patient.getString("phoneNumber"));
                Log.e("phone number", patient.getString("phoneNumber"));
                editText2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText2.setPadding(20, 20, 20, 20);

                final EditText editText3 = new EditText(context);
                editText3.setText(patient.getString("prefix") + " " + patient.getString("givenName") + " " + patient.getString("middleName"));
                Log.e("name", patient.getString("prefix") + " " + patient.getString("givenName") + " " + patient.getString("middleName"));
                editText3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText3.setPadding(20, 20, 20, 20);


                linearLayout.addView(editText1);
                linearLayout.addView(editText2);
                linearLayout.addView(editText3);


                Log.e("April 15 ", patient.getString("personId"));
                Intent intent = new Intent(SearchPatient.this, OPBMRForm.class);
                intent.putExtra("personId", patient.getString("personId"));
                this.startActivity(intent);


            } catch (Exception e) { e.printStackTrace(); }

        }

    } // onclick


    public void goToCreatePatientForm(View v){
        Intent intent = new Intent(SearchPatient.this, AddPatients.class);
        this.startActivity(intent);
        //Log.e("message", "00000000000000000000000000000000000000000000000000000000000000");
        //Intent intent = new Intent(SearchPatient.this, Test.class);
        //this.startActivity(intent);
        //Log.e("message", "00000000000000000000000000000000000000000000000000000000000000");
    }


    public void addClinicalData(View v) {
        Log.e("message", "00000000000000000000000000000000000000000000000000000000000000");
        Intent intent = new Intent(SearchPatient.this, Test.class);
        this.startActivity(intent);
        Log.e("message", "00000000000000000000000000000000000000000000000000000000000000");
    }




} //class
