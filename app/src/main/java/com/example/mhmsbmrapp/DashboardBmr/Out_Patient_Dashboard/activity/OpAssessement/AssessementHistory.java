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




    Button button;
    TextView addpatienttext;


    private String orgUUID = SessionInformation.orgUUID;
    private String userUUID = SessionInformation.userUUID;
    private String sessionToken = SessionInformation.sessionToken;

    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
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

        //json//
        return v;
    }





    private void jsonrequest() {

        /*Log.e(" ", "Just before jsonrequest() calling");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Log.e("message", sharedPreferences.getString("loginToken", ""));
        Log.e("message", sharedPreferences.getString("sessionToken", ""));

        final String loginToken = sharedPreferences.getString("loginToken", "");

        Log.e(" ","inside jsonrequst()");
        StringRequest requestString = new StringRequest(Request.Method.GET, JSON_URL,

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

                                Log.e(i+"   ",jsonObject.toString());
                                AnimeOpAssessement anime = new AnimeOpAssessement();
                                anime.setName(jsonObject.getString("patientName"));
                                anime.setDescription(jsonObject.getString("assignedmhpName"));
                                anime.setRating(jsonObject.getString("assignedmhpName"));
                                anime.setStudio(jsonObject.getString("orgId"));
                                //anime.setDateOfBirth(jsonObject.getInt("dateOfBirth"));
                                anime.setImage_url(jsonObject.getString("admissionStatus"));
                                //anime.setImage_url(jsonObject.getString("img"));
                                lstAnimeOpAssessement.add(anime);
                            }

                        } catch (JSONException e) {
                            Log.e("error occurred", e.getMessage());
                            e.printStackTrace();
                        }

                        setuprecyclerview(lstAnimeOpAssessement);
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


        Thread thread = new Thread() {
            public void run() {

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String loginToken = sharedPreferences.getString("loginToken", "");
                Log.e("loginToken is", loginToken);
                String patientId = sharedPreferences.getString("patientId", "");


                System.out.println("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
                List<Assessment> therapyList = new AssessmentUtility().getHistory(loginToken, sessionToken, patientId, orgUUID);

                for (Assessment o : therapyList) {
                    try {
                        AnimeOpAssessement anime = new AnimeOpAssessement();

                        //JSONObject obj = new JSONObject(item.get(i).toString());
                        //JSONObject o = obj.getJSONArray("resultSet").getJSONObject(0);
                        System.out.println("#@#@#@#@@@@@@@@@#@@@#@#@##@@#@#@##@"+o.getReasonForReferral()+"     "+o.getImpression()+"     "+o.getSupervisorName());
                        anime.setName(o.getReasonForReferral());
                        anime.setCategorie(o.getImpression());
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
                        lstAnimeOpAssessement.add(anime);
                    }catch (Exception e) {e.printStackTrace();}

                }
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
