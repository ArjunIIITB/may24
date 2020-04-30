package com.example.mhmsbmrapp.AddPatientFolder;

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
import com.example.mhmsbmrapp.Login.MHPFlow;
import com.example.mhmsbmrapp.R;
import com.example.mhmsbmrapp.utility.MHPAssignmentUtility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AssignPatientTest extends AppCompatActivity {

    private Spinner spinnerAssign;
    String loginToken;
    String sessionToken;
    JSONObject patient;
    JSONObject parentOrg;
    JSONArray registeredMHPs;
    List<String> registeredMHPsNames = new ArrayList<>();
    String userUUID;
    String orgUUID;
    MHPAssignmentUtility utility = new MHPAssignmentUtility();




    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignpatient);

        Thread thread = new Thread(){
            public void run() {
                System.out.println("reaching this point");
                final String patientString = getIntent().getStringExtra("patient");

                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                loginToken = sharedPreferences.getString("loginToken", "");
                sessionToken = sharedPreferences.getString("sessionToken", "");


                System.out.println("aaya hua patient " + patientString);
                System.out.println();
                System.out.println();

                try {
                    patient = new JSONObject(patientString);
                    parentOrg = utility.getParentOrg(loginToken);
                    String loginDecodedToken = MHPFlow.decoded(loginToken);
                    userUUID = new JSONObject(loginDecodedToken).getString("userUUID");
                    orgUUID = new JSONObject(loginDecodedToken).getString("orgUUID");
                    registeredMHPs = utility.getRegisteredMHPs(loginToken);

                    spinnerAssign = findViewById(R.id.spinnerAssign);

                    registeredMHPsNames.add("MHP");
                    for (int index = 1; index <= registeredMHPs.length(); index++) {
                        registeredMHPsNames.add(registeredMHPs.getJSONObject(index).getString("mhpName"));
                        Log.e("registered MHP "+(index+1), registeredMHPsNames.get(index));
                    }
                } catch (Exception e) {e.printStackTrace();}


            }
        };thread.start();



        spinnerAssign = findViewById(R.id.spinnerAssign);


        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, registeredMHPsNames);

        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinnerAssign.setAdapter(dataAdapter);

        spinnerAssign.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("MHP"))
                {
                    //do nothing
                }
                else
                {
                    //on selecting spinner item
                    Log.e("inside else", "some item selected");
                    final int itemPosition = parent.getSelectedItemPosition();
                    String item = parent.getItemAtPosition(position).toString();
                    //show spinner selected item
                    System.out.println(itemPosition+"   "+item);
                    Toast.makeText(parent.getContext(), "" + itemPosition + "  " + item, Toast.LENGTH_SHORT).show();


                    Runnable runnable = new Runnable() {
                        public void run() {
                            try {
                                JSONObject mhp = registeredMHPs.getJSONObject(itemPosition);
                                System.out.println("selected MHP from drop down " + mhp.getString("mhpName"));
                                utility.updateIPPatientQueueWithUserID(patient, parentOrg, mhp, loginToken, userUUID);
                                Intent intent = new Intent(AssignPatientTest.this, MainActivity.class);
                                startActivity(intent);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }; new Thread(runnable).start();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}



/*   // @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignpatient);

        Thread thread = new Thread() {
            public void run() {

                System.out.println("reaching this point");
                final String patientString = getIntent().getStringExtra("patient");

                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String loginToken = sharedPreferences.getString("loginToken", "");
                String sessionToken = sharedPreferences.getString("sessionToken", "");

                System.out.println(patientString);

                MHPAssignmentUtility utility = new MHPAssignmentUtility();
                JSONObject patient;
                JSONObject parentOrg;
                JSONArray registeredMHPs;
                List<String> registeredMHPsNames = new ArrayList<>();

                try {
                    patient = new JSONObject(patientString);
                    parentOrg = utility.getParentOrg(loginToken);
                    String loginDecodedToken = MHPFlow.decoded(loginToken);
                    String userUUID = new JSONObject(loginDecodedToken).getString("userUUID");
                    String orgUUID = new JSONObject(loginDecodedToken).getString("orgUUID");
                    registeredMHPs = utility.getRegisteredMHPs(loginToken);

                    spinnerAssign = findViewById(R.id.spinnerAssign);


                    for (int index = 0; index < registeredMHPs.length(); index++) {
                        registeredMHPsNames.add(registeredMHPs.getJSONObject(index).getString("mhpName"));
                    }



                    ArrayAdapter<String> dataAdapter;
                    dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, kjhgj);

                    dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                    spinnerAssign.setAdapter(dataAdapter);

                    spinnerAssign.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(parent.getItemAtPosition(position).equals("MHP"))
                            {
                                //do nothing
                            }
                            else
                            {
                                //on selecting spinner item
                                String item = parent.getItemAtPosition(position).toString();

                                //show spinner selected item
                                Toast.makeText(parent.getContext(), ""  + item, Toast.LENGTH_SHORT).show();
                                if(parent.getItemAtPosition(position).equals("mhp"));
                                {
                                    Intent intent = new Intent(AssignPatientTest.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });




                    Log.e("message", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println(patient.toString());
                    Log.e("message", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println(parentOrg.toString());
                    Log.e("message", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println(userUUID);
                    Log.e("message", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println(orgUUID);
                    Log.e("message", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                    JSONObject mhp = registeredMHPs.getJSONObject(0);
                    System.out.println(mhp.getString("mhpName"));

                    Log.e("message", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println(utility.getPatientByPatientId(loginToken, sessionToken, patient.getString("personId")));
                    Log.e("message", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    utility.updateIPPatientQueueWithUserID(patient, parentOrg, mhp, loginToken, userUUID);

                } catch (
                        Exception e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();

    }
    */
