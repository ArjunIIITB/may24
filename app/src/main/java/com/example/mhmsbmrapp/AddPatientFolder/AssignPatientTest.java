package com.example.mhmsbmrapp.AddPatientFolder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.mhmsbmrapp.Login.MHPFlow;
import com.example.mhmsbmrapp.R;
import com.example.mhmsbmrapp.utility.MHPAssignmentUtility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AssignPatientTest extends AppCompatActivity {

    @Override
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


                try {
                    patient = new JSONObject(patientString);
                    parentOrg = utility.getParentOrg(loginToken);
                    String loginDecodedToken = MHPFlow.decoded(loginToken);
                    String userUUID = new JSONObject(loginDecodedToken).getString("userUUID");
                    String orgUUID = new JSONObject(loginDecodedToken).getString("orgUUID");
                    registeredMHPs = utility.getRegisteredMHPs(loginToken);

                    List<String> registeredMHPsNames = new ArrayList<String>();
                    for(int index=0;index<registeredMHPs.length();index++){
                        registeredMHPsNames.add(registeredMHPs.getJSONObject(index).getString("mhpName"));
                    }


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
}


/*
    String loginDecodedToken = MHPFlow.decoded(loginToken);
    String usrName = new JSONObject(loginDecodedToken).getString("userName");
    String userUUID = new JSONObject(loginDecodedToken).getString("userUUID");
    String orgUUID = new JSONObject(loginDecodedToken).getString("orgUUID");
    String givenName = new MHPFlow().getuserbyuuid(loginToken,userUUID).getJSONObject("person").getString("givenName");
    JSONObject mhp = new JSONObject();
            mhp.put("givenName", givenName);
                    mhp.put("userName", usrName);
                    mhp.put("composer_identifier", userUUID);
                    mhp.put("facility_identifier", orgUUID);
*/
