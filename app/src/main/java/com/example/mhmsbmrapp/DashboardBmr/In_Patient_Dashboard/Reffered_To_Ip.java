package com.example.mhmsbmrapp.DashboardBmr.In_Patient_Dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.mhmsbmrapp.DashboardBmr.In_Patient_Dashboard.RefferedIP.model.IPReferredModel;
import com.example.mhmsbmrapp.DashboardBmr.In_Patient_Dashboard.adapter.RecyclerView_Adapter_IpReffered;


import com.example.mhmsbmrapp.Login.SessionInformation;
import com.example.mhmsbmrapp.R;
import com.example.mhmsbmrapp.utility.ip.IPUtility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Reffered_To_Ip extends Fragment {

    private final String JSON_URL = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json" ;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<IPReferredModel> lstIPReferredModel ;
    private RecyclerView recyclerView ;

    public Reffered_To_Ip() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.opassessemethistory, container, false);

        lstIPReferredModel = new ArrayList<>() ;
        recyclerView = v.findViewById(R.id.recyclerviewid);
        jsonrequest();

        //json//
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
                JSONArray referredPatientList = new IPUtility().getorgpatientReferredToIP(loginToken, SessionInformation.orgUUID);

                try {
                    for (int i = 0; i < referredPatientList.length(); i++) {
                        IPReferredModel anime = new IPReferredModel();
                        JSONObject referredPatient = referredPatientList.getJSONObject(i);
                        anime.setName(referredPatient.getString("patientName"));
                        anime.setCategorie(referredPatient.getString("patientPhone"));
                        anime.setImage_url(referredPatient.getString("referredBy"));
                        anime.setRating(referredPatient.getString("patientCity"));
                        System.out.println(referredPatient.getString("patientPhone"));
                        System.out.println(referredPatient.getString("patientName"));
                        System.out.println(referredPatient.getString("referredBy"));
                        lstIPReferredModel.add(anime);
                    }
                }catch (Exception e) {e.printStackTrace();}
            }
        };
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setuprecyclerview(lstIPReferredModel);
    }

    private void setuprecyclerview(List<IPReferredModel> lstIPReferredModel) {

        RecyclerView_Adapter_IpReffered myadapter = new RecyclerView_Adapter_IpReffered(getActivity(),lstIPReferredModel) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myadapter);

    }
}
