package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.adapter.RecyclerViewAdapter;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.model.Anime;
import com.example.mhmsbmrapp.Login.MHPFlow;
import com.example.mhmsbmrapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Out_Patientbmr extends Fragment{




    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<Anime> lstAnime ;
    private RecyclerView recyclerView ;

    private String loginToken;
    private String orgUUID;
    private String userUUID;



    public Out_Patientbmr() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.op_bmr_queued_list, container, false);


        lstAnime = new ArrayList<>() ;
        recyclerView = v.findViewById(R.id.recyclerviewid);
        jsonrequest();

        //json//
        return v;
    }


    private void jsonrequest() {


        //Log.e("^^^^^^^^^^^ ","inside jsonrequst() Out_Patientbmr");

        System.out.println(getActivity().toString());
        System.out.println(getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE));
        System.out.println(getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).getString("loginToken", ""));



        loginToken = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).getString("loginToken", "");
         String sessionToken = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).getString("sessionToken", "");

        try{
            orgUUID = new JSONObject(MHPFlow.decoded(loginToken)).getString("orgUUID");
            userUUID = new JSONObject(MHPFlow.decoded(loginToken)).getString("userUUID");
        } catch (Exception e) {}




        /*StringRequest requestString = new StringRequest(Request.Method.GET, url,

                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        System.out.println("inside on response()");
                        Log.e("","4444444444444444444444444444444");
                        Log.e("response", response);


                        try {
                            JSONArray arr = new JSONArray(response);

                            for (int i = 0 ; i < arr.length(); i++ ) {
                                JSONObject jsonObject = new JSONObject(arr.get(i).toString());
                                Log.e(i+"",jsonObject.toString());
                                Anime anime = new Anime();
                                anime.setGivenName(jsonObject.getString("patientId"));
                                anime.setMiddleName(jsonObject.getString("userId"));
                                anime.setEmail(jsonObject.getString("assignedmhpName"));
                                anime.setPhoneNumber(jsonObject.getString("orgId"));
                                //anime.setDateOfBirth(jsonObject.getInt("dateOfBirth"));
                                anime.setPersonId(jsonObject.getString("admissionStatus"));
                                anime.setPatientName(jsonObject.getString("patientName"));
                                //anime.setImage_url(jsonObject.getString("img"));
                                lstAnime.add(anime);
                            }

                        } catch (JSONException e) {
                            Log.e("error occurred", e.getMessage());
                            e.printStackTrace();
                        }

                        setuprecyclerview(lstAnime);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("inside error response", "VolleyError error");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + loginToken);
                params.put("Accept", "application/json");

                return params;
            }
        };


        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(requestString) ;*/


        Thread thread = new Thread(){

            public void run() {
                try {
                    JSONArray arr = new MHPFlow().getWatingPatients(orgUUID, userUUID, loginToken);
                    if(arr != null) {
                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject jsonObject = new JSONObject(arr.get(i).toString());
                            //Log.e(i + "", jsonObject.toString());
                            Anime anime = new Anime();
                            anime.setGivenName(jsonObject.getString("patientName"));
                            anime.setMiddleName(jsonObject.getString("userId"));
                            anime.setEmail(jsonObject.getString("assignedmhpName"));
                            anime.setPhoneNumber(jsonObject.getString("patientPhone"));
                            anime.setPersonId(jsonObject.getString("admissionStatus"));
                            anime.setPatientName(jsonObject.getString("patientName"));
                            lstAnime.add(anime);
                        }
                    }

                } catch (JSONException e) {
                    Log.e("error occurred", e.getMessage());
                    e.printStackTrace();
                }

                setuprecyclerview(lstAnime);
            }

        };
        thread.start();

    }

    private void setuprecyclerview(List<Anime> lstAnime) {


        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(getActivity(),lstAnime) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myadapter);

    }
}