package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpTherapy;

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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpAssessement.model.AnimeOpAssessement;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpTherapy.adapter.RecyclerViewAdapterOpTherapy;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpTherapy.model.AnimeOpTherapy;
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
import java.util.List;

public class OpTherapyHistory extends Fragment {
    private static final String TAG = "Fragment3";

    Button button;
    TextView addpatienttext;

    private String orgUUID = SessionInformation.orgUUID;
    private String userUUID = SessionInformation.userUUID;
    private String sessionToken = SessionInformation.sessionToken;


    private final String JSON_URL = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json" ;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<AnimeOpTherapy> lstAnimeOpTherapy ;
    private RecyclerView recyclerView ;

    public OpTherapyHistory() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.op_therapy_history, container, false);



        lstAnimeOpTherapy = new ArrayList<>() ;
        recyclerView = v.findViewById(R.id.recyclerviewid);
        jsonrequest();

        //json//
        return v;
    }


    private void jsonrequest() {

        /*request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject  = null ;

                for (int i = 0 ; i < response.length(); i++ ) {


                    try {
                        jsonObject = response.getJSONObject(i) ;
                        AnimeOpTherapy anime = new AnimeOpTherapy() ;
                        anime.setName(jsonObject.getString("name"));
                        anime.setDescription(jsonObject.getString("description"));
                        anime.setRating(jsonObject.getString("Rating"));
                        anime.setCategorie(jsonObject.getString("categorie"));
                        anime.setNb_episode(jsonObject.getInt("episode"));
                        anime.setStudio(jsonObject.getString("studio"));
                        anime.setImage_url(jsonObject.getString("img"));
                        lstAnimeOpTherapy.add(anime);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                setuprecyclerview(lstAnimeOpTherapy);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request) ;*/




        Thread thread = new Thread() {
            public void run() {

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String loginToken = sharedPreferences.getString("loginToken", "");
                Log.e("loginToken is", loginToken);
                String patientId = sharedPreferences.getString("patientId", "");


                System.out.println("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
                List<Therapy> therapyList = new TherapyUtility().getHistory(loginToken, sessionToken, patientId, orgUUID);

                for (Therapy o : therapyList) {
                    try {
                        AnimeOpTherapy anime = new AnimeOpTherapy();

                        //JSONObject obj = new JSONObject(item.get(i).toString());
                        //JSONObject o = obj.getJSONArray("resultSet").getJSONObject(0);
                        System.out.println("#@#@#@#@@@@@@@@@#@@@#@#@##@@#@#@##@"+o.getKeyIssue()+"     "+o.getObjectiveSession()+"     "+o.getSupervisorName());
                        anime.setName(o.getKeyIssue());
                        anime.setCategorie(o.getObjectiveSession());
                        anime.setImage_url(o.getSupervisorName());
                            /*if (o.has("symptomName")) {
                                System.out.println(i + "  " + o.getString("symptomName"));
                                anime.setName(o.getString("symptomName"));
                            }
                            else if (o.has("diagnosticCertainity")) {
                                System.out.println(i + "  " + o.getString("diagnosticCertainity"));
                                System.out.println(i + "  " + o.getString("problemDiagnosis"));
                                System.out.println(i + "  " + o.getString("problemTerminology"));
                                anime.setDescription(o.getString("diagnosticCertainity"));
                            } else if (o.has("clinicalHistory")) {
                                System.out.println(i + "  " + o.getString("clinicalHistory"));
                                anime.setRating(o.getString("clinicalHistory"));
                            } else if (o.has("illnessSummary")) {
                                System.out.println(i + "  " + o.getString("illnessSummary"));
                                anime.setCategorie(o.getString("illnessSummary"));
                            } else if (o.has("clinicalSynopsis")) {
                                System.out.println(i + "  " + o.getString("clinicalSynopsis"));
                            } else if (o.has("medicationItem")) {
                                System.out.println(i + "  " + o.getString("medicationItem"));
                                System.out.println(i + "  " + o.getString("directionDuration"));
                                System.out.println(i + "  " + o.getString("overallDirectionDescription"));
                                System.out.println(i + "  " + o.getString("timingDescription"));
                            }*/

                        Log.e("message", "next item starts from here onwards");
                        lstAnimeOpTherapy.add(anime);
                    }catch (Exception e) {e.printStackTrace();}

                }
            }
        };
        thread.start();
        setuprecyclerview(lstAnimeOpTherapy);




    }

    private void setuprecyclerview(List<AnimeOpTherapy> lstAnime) {


        RecyclerViewAdapterOpTherapy myadapter = new RecyclerViewAdapterOpTherapy(getActivity(),lstAnime) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myadapter);

    }
}
