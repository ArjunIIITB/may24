package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpAssessement;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpAssessement.adapter.RecyclerViewAdapterassessement;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpAssessement.model.AnimeOpAssessement;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpRestraintMonitoring.model.AnimeOpRestraint;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpTherapy.model.AnimeOpTherapy;
import com.example.mhmsbmrapp.Login.MHPFlow;
import com.example.mhmsbmrapp.Login.SessionInformation;
import com.example.mhmsbmrapp.R;
import com.example.mhmsbmrapp.model.Assessment;
import com.example.mhmsbmrapp.model.Therapy;
import com.example.mhmsbmrapp.utility.AssessmentUtility;
import com.example.mhmsbmrapp.utility.TherapyUtility;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssessementHistory extends Fragment {
    private static final String TAG = "Fragment3";


    private List<AnimeOpAssessement> lstAnimeOpAssessement ;
    private RecyclerView recyclerView ;

    public AssessementHistory() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.opassessemethistory, container, false);


        lstAnimeOpAssessement = new ArrayList<>() ;
        recyclerView = v.findViewById(R.id.recyclerviewid);
        Log.e(" ", "Just before jsonrequest() calling");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Log.e("message", sharedPreferences.getString("loginToken", ""));
        Log.e("message", sharedPreferences.getString("sessionToken", ""));
        jsonrequest();

        return v;
    }





    private void jsonrequest() {

        Thread thread = new Thread() {
            public void run() {

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String loginToken = sharedPreferences.getString("loginToken", "");
                Log.e("loginToken is", loginToken);
                String patientId = sharedPreferences.getString("patientId", "");


                System.out.println("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
                List<Assessment> therapyList = new AssessmentUtility().getHistory(loginToken, SessionInformation.sessionToken, patientId, SessionInformation.orgUUID);

                System.out.println("======================================");
                System.out.println("======================================");
                for (Assessment o : therapyList) {
                    try {
                        AnimeOpAssessement anime = new AnimeOpAssessement();

                        System.out.println();

                        Log.e("language", o.getTestedLanguage());
                        Log.e("reliability", o.getReliability());
                        Log.e("informant name", o.getInformant());
                        anime.setName(o.getTestedLanguage());
                        anime.setCategorie(o.getReliability());
                        anime.setImage_url(o.getInformant());
                        lstAnimeOpAssessement.add(anime);

                        System.out.println();

                    }catch (Exception e) {e.printStackTrace();}

                }
                System.out.println("======================================");
                System.out.println("======================================");
            }
        };
        thread.start();
        setuprecyclerview(lstAnimeOpAssessement);

    }


    private void setuprecyclerview(List<AnimeOpAssessement> lstAnimeOpAssessement) {


        RecyclerViewAdapterassessement myadapter = new RecyclerViewAdapterassessement(getActivity(),lstAnimeOpAssessement) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myadapter);

    }
}
