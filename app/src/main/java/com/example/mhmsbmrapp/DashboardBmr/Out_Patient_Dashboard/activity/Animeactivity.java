package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpAssessement.Fragment2;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpBmrTab.Fragment1;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpTherapy.Fragment3;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpRestraintMonitoring.Fragment4;
import com.example.mhmsbmrapp.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class Animeactivity extends AppCompatActivity {


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
        setContentView(R.layout.animeactivitytest);


        // hide the default actionbar


        // Recieve data

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
        //ImageView img = findViewById(R.id.aa_thumbnail);

        // setting values to each view

        tv_name.setText(name);
        tv_categorie.setText(category);
        tv_description.setText(description);
        tv_rating.setText(rating);
        tv_studio.setText(studio);
        tv_patientName.setText(patientName);


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
                (Animeactivity.this).setViewPager(0);
            }
        });

        btnNavFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (Animeactivity.this).setViewPager(1);
            }
        });

        btnNavFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (Animeactivity.this).setViewPager(2);
            }
        });
        btnNavFrag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (Animeactivity.this).setViewPager(3);
            }
        });

        /*btnNavSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Animeactivity.this, nextactivity.class);
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


    }