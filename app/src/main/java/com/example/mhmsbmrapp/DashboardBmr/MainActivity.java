package com.example.mhmsbmrapp.DashboardBmr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.mhmsbmrapp.Login.MHPFlow;
import com.example.mhmsbmrapp.R;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FragmentManager FM;
    FragmentTransaction FT;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("inside MainActivity");


        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        final String loginToken = sharedPreferences.getString("loginToken", "");
        final String orgUUID ;
        final String userUUID;

        try {
            /*String loginDecodedToken = MHPFlow.decoded(loginToken);
            Log.e("loginDecodedToken", loginDecodedToken);
            orgUUID = new JSONObject(loginDecodedToken).getString("orgUUID");
            userUUID = new JSONObject(loginDecodedToken).getString("userUUID");
            Log.e("three values    ",  orgUUID +"   " + userUUID+"  "+ loginToken);*/

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    //new MHPFlow().getWatingPatients(orgUUID, userUUID, loginToken);
                    //new MHPFlow().getCompletedPatients(orgUUID, userUUID, loginToken);
                }
            };
            new Thread(runnable).start();

        }catch(Exception e){e.printStackTrace();}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView= (NavigationView) findViewById(R.id.sidenavigation);

        FM= getSupportFragmentManager();
        FT= FM.beginTransaction();
        FT.replace(R.id.containerView, new TabFragment()).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();

                if (item.getItemId()== R.id.nav_item_sent) {
                    FragmentTransaction fragmentTransaction= FM.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new SentFragment()).commit();
                }

                if (item.getItemId()== R.id.nav_item_sent1) {
                    FragmentTransaction fragmentTransaction= FM.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Select_Another_MHE()).commit();
                }
                if (item.getItemId()== R.id.nav_item_aboutkmhms) {
                    FragmentTransaction fragmentTransaction= FM.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new About_kmhms()).commit();
                }

                if (item.getItemId()==R.id.nav_item_inbox)
                {
                    FragmentTransaction fragmentTransaction1=FM.beginTransaction();
                    fragmentTransaction1.replace(R.id.containerView,new TabFragment()).commit();

                }
                return false;
            }
        });


        androidx.appcompat.widget.Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


    }
}
