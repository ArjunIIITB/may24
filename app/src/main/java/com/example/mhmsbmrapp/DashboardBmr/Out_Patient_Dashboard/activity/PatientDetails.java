package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.request.RequestOptions;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpAssessement.Fragment2;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpBmrTab.Fragment1;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpTherapy.Fragment3;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpRestraintMonitoring.Fragment4;
import com.example.mhmsbmrapp.Login.SessionInformation;
import com.example.mhmsbmrapp.R;
import com.example.mhmsbmrapp.utility.PatientUtility;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PatientDetails extends AppCompatActivity {

    String patientName;
    String patientFatherName;
    String phoneNumber;
    String address;
    String mhmsId;
    String age;
    String gender;
    String email;


    private Button btnNavFrag1;
    private Button btnNavFrag2;
    private Button btnNavFrag3;
    private Button btnNavFrag4;
    //private Button btnNavSecondActivity;

    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);


        // hide the default actionbar


        // Recieve data

        String patientId = getIntent().getExtras().getString("anime_patientId") ;
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("patientId", patientId);
        editor.commit();
        getData(sharedPreferences.getString("loginToken", ""), patientId);

        String name  = getIntent().getExtras().getString("anime_givenName");
        String description = getIntent().getExtras().getString("anime_middleName");
        String studio = getIntent().getExtras().getString("anime_personId") ;
        String category = getIntent().getExtras().getString("anime_phoneNumber");
        //int nb_episode = getIntent().getExtras().getInt("anime_dateOfBirth") ;
        String rating = getIntent().getExtras().getString("anime_email") ;
        String patientName = getIntent().getExtras().getString("anime_patientName") ;


        //String image_url = getIntent().getExtras().getString("anime_img");

        // ini views

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_name = findViewById(R.id.aa_anime_name);
        TextView tv_studio = findViewById(R.id.aa_studio);
        TextView tv_categorie = findViewById(R.id.aa_categorie) ;
        TextView tv_description = findViewById(R.id.aa_description);
        TextView tv_rating  = findViewById(R.id.aa_rating) ;
        TextView tv_patientName  = findViewById(R.id.aa_patientName) ;
        TextView tv_patientId  = findViewById(R.id.aa_patientId) ;
        //ImageView img = findViewById(R.id.aa_thumbnail);

        // setting values to each view

        tv_name.setText(name);
        tv_categorie.setText(category);
        tv_description.setText(description);
        tv_rating.setText(rating);
        tv_studio.setText(studio);
        tv_patientName.setText(patientName);
        tv_patientId.setText(patientId);


        collapsingToolbarLayout.setTitle(name);


        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


        // set image using Glide
        //Glide.with(this).load(image_url).apply(requestOptions).into(img);


        //navbar for op-bmr buttons
        btnNavFrag1 = (Button) findViewById(R.id.btnNavFrag1);
        btnNavFrag2 = (Button) findViewById(R.id.btnNavFrag2);
        btnNavFrag3 = (Button) findViewById(R.id.btnNavFrag3);
        btnNavFrag4 = (Button) findViewById(R.id.btnNavFrag4);
        //btnNavSecondActivity = (Button) findViewById(R.id.btnNavSecondActivity);

        btnNavFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (PatientDetails.this).setViewPager(0);
            }
        });

        btnNavFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (PatientDetails.this).setViewPager(1);
            }
        });

        btnNavFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (PatientDetails.this).setViewPager(2);
            }
        });
        btnNavFrag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (PatientDetails.this).setViewPager(3);
            }
        });

        /*btnNavSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientDetails.this, nextactivity.class);
                startActivity(intent);
            }
        });*/

        mSectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.containter);
        //setup the pager
        setupViewPager(mViewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment1(), "Fragment1");
        adapter.addFragment(new Fragment2(), "Fragment2");
        adapter.addFragment(new Fragment3(), "Fragment3");
        adapter.addFragment(new Fragment4(), "Fragment4");
        viewPager.setAdapter(adapter);
    }
    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

    public void getData(final String loginToken, String patientId) {

        final String sessionToken = SessionInformation.sessionToken;
        final String logTok = loginToken;
        final String patid = patientId;


        Thread thread = new Thread(){
            public void run() {
                Log.e("show card", "$$$$$$$$$$$$$$$$$$$$$$$$$");
                try {
                    JSONObject patient = new PatientUtility().getPatient(logTok, patid, sessionToken);
                    patientName = patient.getString("prefix")+" "+patient.getString("givenName")+" "+patient.getString("middleName");

                    patientFatherName = patient.getJSONObject("emergencyContact").getString("contactName");
                    phoneNumber = patient.getString("phoneNumber");
                    email = patient.getString("email");
                    gender = patient.getJSONObject("gender").getString("genderCode");
                    mhmsId = patient.getJSONArray("idproof").getJSONObject(0).getString("idNumber");
                    age = new PatientUtility().getPatientAge(loginToken, patient.getString("dateOfBirth"));

                    JSONObject addressobj = patient.getJSONArray("address").getJSONObject(0);
                    address = addressobj.getString("address1")+" "+addressobj.getString("address2")+addressobj.getString("city")+" "+addressobj.getString("district")+addressobj.getString("postalCode")+" "+addressobj.getString("state");

                    System.out.println(patientName);
                    System.out.println(patientFatherName);
                    System.out.println(phoneNumber);
                    System.out.println(email);
                    System.out.println(gender);
                    System.out.println(mhmsId);
                    System.out.println(age);

                } catch (JSONException e) { e.printStackTrace(); }
                Log.e("show card", "$$$$$$$$$$$$$$$$$$$$$$$$$");
            }
        };thread.start();
    }

}