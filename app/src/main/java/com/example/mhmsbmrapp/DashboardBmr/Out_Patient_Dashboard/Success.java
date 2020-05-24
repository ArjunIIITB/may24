package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mhmsbmrapp.DashboardBmr.MainActivity;
import com.example.mhmsbmrapp.R;

public class Success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        System.out.println("===================================================");
        System.out.println("===================================================");
        System.out.println("Reaching success page");
        System.out.println("===================================================");
        System.out.println("===================================================");
        final Intent intent = new Intent(this, MainActivity.class);

        Button btn = findViewById(R.id.Success_OK);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Success.this.startActivity(intent);
            }
        });

    }
}
