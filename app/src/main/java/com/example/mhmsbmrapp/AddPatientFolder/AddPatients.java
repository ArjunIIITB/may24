package com.example.mhmsbmrapp.AddPatientFolder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.Out_Patientbmr;
import com.example.mhmsbmrapp.Login.GlobalVariables;
import com.example.mhmsbmrapp.Login.MHPFlow;
import com.example.mhmsbmrapp.Login.SessionInformation;
import com.example.mhmsbmrapp.R;
import com.example.mhmsbmrapp.utility.MHPAssignmentUtility;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class AddPatients extends AppCompatActivity {


    boolean idProof = true;

    EditText id_number;
    EditText patient_First_name;
    EditText patient_Last_name;
    EditText salutation;
    EditText Address1;
    EditText Address2;
    EditText Date_of_birth;
    EditText gender;
    EditText salutation_father;
    EditText Father_name;
    EditText City;
    EditText district;
    EditText State;
    EditText pincode;
    EditText Email;
    EditText Mobile_number;

    EditText mobileText;


    String patient_First_name_string;
    String idNumber;
    String patient_Last_name_string;
    String salutation_string;
    String Address1_string;
    String Address2_string;
    String Date_of_birth_string;
    String gender_string;
    String salutation_father_string;
    String Father_name_string;
    String City_string;
    String district_string;
    String State_string;
    String pincode_string;
    String Email_string;
    String Mobile_number_string;

    JSONObject OTPResponse ;

    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_patient);

    } //onCreate method


    public JSONObject sendOTP(View v) {

        Thread thread = new Thread() {

            public void run() {


                EditText mobileText = (EditText)findViewById(R.id.Mobile_number);

                String contactVal = mobileText.getText().toString();
                /*contactType: "mobile"
                contactVal: "8404973134"
                purpose: "CONSENT"*/
                OkHttpClient client = new OkHttpClient();
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                final String loginToken = sharedPreferences.getString("loginToken", "");


                final String RELATIVE_PATH = "sendOTP/";

                final MediaType JSON
                        = MediaType.parse("application/json; charset=utf-8");


                JSONObject jsonObject = new JSONObject();
                try {

                    jsonObject.put("contactType", "mobile");
                    jsonObject.put("contactVal", contactVal);
                    jsonObject.put("purpose", "CONSENT");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

                Request request = new Request.Builder()
                        .url(GlobalVariables.GLOBAL_PATH_USER + RELATIVE_PATH)
                        .post(formBody)
                        .addHeader("Authorization", "Bearer " + loginToken)
                        .addHeader("Content-Type", "application/json")
                        .build();

                Response response = null;

                try {
                    response = client.newCall(request).execute();
                    ResponseBody rb = response.body();
                    //System.out.println(rb.string());
                    OTPResponse = new JSONObject(rb.string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        return OTPResponse;

    } // sendOTP method



    public void verifyOTP(View v) {

        /*contactType: "mobile"
        contactVal: "8404973134"
        purpose: "CONSENT"*/

        Thread thread = new Thread() {


            public void run() {

                OkHttpClient client = new OkHttpClient();
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                final String loginToken = sharedPreferences.getString("loginToken", "");


                final String RELATIVE_PATH = "verifyOTP/";
                final MediaType JSON
                        = MediaType.parse("application/json; charset=utf-8");


                JSONObject jsonObject = OTPResponse;
                try {

                    Log.e("The OTPResponse Object ", OTPResponse.toString());

                    EditText OTP = (EditText) findViewById(R.id.editText19);
                    String otp = OTP.getText().toString();

                    Log.e("OTP----------", otp);

                    jsonObject.remove("otp");
                    jsonObject.put("otp", otp);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

                Request request = new Request.Builder()
                        .url(GlobalVariables.GLOBAL_PATH_USER + RELATIVE_PATH)
                        .post(formBody)
                        .addHeader("Authorization", "Bearer " + loginToken)
                        .addHeader("Content-Type", "application/json")
                        .build();

                Response response = null;

                try {
                    response = client.newCall(request).execute();
                    ResponseBody rb = response.body();
                    System.out.println(rb.string());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    } // verifyOTP method



    public void createPatient(View v){

        setValues();

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        final String loginToken = sharedPreferences.getString("loginToken", "");
        try {
            String loginDecodedToken = MHPFlow.decoded(loginToken);
            Log.e("loginDecodedToken", loginDecodedToken);

            jsonObject = new JSONObject();

            jsonObject.put("userToken", SessionInformation.sessionToken);
            jsonObject.put("userUuid", SessionInformation.userUUID);
            jsonObject.put("orgUuid", SessionInformation.orgUUID);
            if(idProof) {
                jsonObject.put("idType", "AB-ARK ID");
                Log.e(" id number", idNumber);
            }
            jsonObject.put("idNumber", idNumber);
            jsonObject.put("prefix", salutation_string);
            Log.e("patient name", patient_First_name_string);
            jsonObject.put("givenName", patient_First_name_string);
            jsonObject.put("middleName", patient_Last_name_string);

            JSONObject genderObject = new JSONObject();
                genderObject.put("genderCode", "Male");

            jsonObject.put("gender", genderObject);

            JSONObject emergencyContactObject = new JSONObject();
                emergencyContactObject.put("contactName", Father_name_string);
                emergencyContactObject.put("telephone", "1234567890");

            jsonObject.put("emergencyContact", emergencyContactObject);
            jsonObject.put("phoneNumber", Mobile_number_string);
            jsonObject.put("email", Email_string);
            jsonObject.put("dateOfBirth", Date_of_birth_string);
            jsonObject.put("status", "Visiting");

            JSONObject addressObject = new JSONObject();
            addressObject.put("district", district_string);
            addressObject.put("city", City_string);
            addressObject.put("state", State_string);
            addressObject.put("pinCode", pincode_string);
            addressObject.put("postalCode", "444444");
            addressObject.put("address1", Address1_string);
            addressObject.put("address2", Address2_string);

            JSONArray addressArrayObject = new JSONArray();
            addressArrayObject.put(addressObject);

            jsonObject.put("address", addressArrayObject);

            String tokenId = OTPResponse.getString("tokenID");

            jsonObject.put("mobToken", tokenId);

            Log.e("create patient mobToken", tokenId);

            Thread thread = new Thread() {

                public void run() {

                    OkHttpClient client = new OkHttpClient();

                    final String RELATIVE_PATH = "createpatient/";
                    final MediaType JSON
                            = MediaType.parse("application/json; charset=utf-8");

                    RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());
                    Request request = new Request.Builder()
                            .url(GlobalVariables.GLOBAL_PATH_REST + RELATIVE_PATH)
                            .post(formBody)
                            .addHeader("Authorization", "Bearer " + loginToken)
                            .addHeader("Content-Type", "application/json")
                            .build();

                    Response response = null;

                    try {
                        response = client.newCall(request).execute();
                        ResponseBody rb = response.body();

                        //String personUUID = new JSONObject(rb.string()).getString("personUUID");
                        JSONObject pat = new JSONObject(rb.string());
                        System.out.println(pat.toString());
                        String personUUID = pat.getString("personUUID");
                        JSONObject patient = new MHPAssignmentUtility().getPatientByPatientId(loginToken, SessionInformation.sessionToken, personUUID);

                        System.out.println("created Patient details "+patient.toString());

                        Intent intent = new Intent(AddPatients.this, AssignPatientTest.class);
                        intent.putExtra("patient", patient.toString());
                        AddPatients.this.startActivity(intent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            thread.start();

        } catch(Exception e) {e.printStackTrace();}

    } // createPatient method


    public void setValues() {

        patient_First_name = findViewById(R.id.First_Name);
        patient_First_name_string = patient_First_name.getText().toString();

        id_number = findViewById(R.id.Enter_ID_Number);
        idNumber = id_number.getText().toString();

        patient_Last_name = findViewById(R.id.Last_Name);
        patient_Last_name_string = patient_Last_name.getText().toString();

        salutation = findViewById(R.id.Salutation);
        salutation_string = salutation.getText().toString();

        Address1 = findViewById(R.id.Address_1);
        Address1_string = Address1.getText().toString();

        Address2 = findViewById(R.id.Address_2);
        Address2_string = Address2.getText().toString();

        Date_of_birth = findViewById(R.id.Date_of_Birth);
        Date_of_birth_string = Date_of_birth.getText().toString();

        gender = findViewById(R.id.Gender);
        gender_string = gender.getText().toString();

        salutation_father = findViewById(R.id.Salutation_father);
        salutation_father_string = salutation_father.getText().toString();

        Father_name = findViewById(R.id.Father_Name);
        Father_name_string = Father_name.getText().toString();

        City = findViewById(R.id.City);
        City_string = City.getText().toString();

        district = findViewById(R.id.District);
        district_string = district.getText().toString();

        State = findViewById(R.id.State);
        State_string = State.getText().toString();

        pincode = findViewById(R.id.Pincode);
        pincode_string = pincode.getText().toString();

        Email = findViewById(R.id.Email);
        Email_string = Email.getText().toString();

        Mobile_number = findViewById(R.id.Mobile_number);
        Mobile_number_string = Mobile_number.getText().toString();


    }



} // CreatePatientForm class