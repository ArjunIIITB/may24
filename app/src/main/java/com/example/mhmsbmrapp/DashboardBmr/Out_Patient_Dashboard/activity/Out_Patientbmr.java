package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.example.mhmsbmrapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Out_Patientbmr extends Fragment{


    private final String loginToken = "eyJEZXZlbG9wZWQgQnkiOiJlLUhlYWx0aCBSZXNlYXJjaCBDZW50ZXIsIElJSVQgQmFuZ2Fsb3JlIiwiSG9zdCI6Ikthcm5hdGFrYSBNZW50YWwgSGVhbHRoIE1hbmFnZW1lbnQgU3lzdGVtIiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJwcm9mZXNzaW9uIjoiTUhNU1BzeWNoaWF0cmlzdCIsInN1YiI6Ik1ITVMgU2VjdXJpdHkgVG9rZW4iLCJsYXN0TG9naW5PcmdJZCI6ImEyMWI4ODVlLTJmM2EtNDQyNS04YjViLTBkMjc0YjQyYWYyNiIsInNlc3Npb25FbmRUaW1lIjoxNTg3ODQwNTMyLCJpc3MiOiJLTUhNUyIsInNlc3Npb25TdGFydFRpbWUiOjE1ODc3OTczMzIsInNlc3Npb25JZCI6ImE4MTA1NjEyLWE4ZTctNDA3NC1iOWEwLWFhZjNiM2ZiZWY5NSIsInVzZXJOYW1lIjoicHJhc2hhbnQiLCJvcmdVVUlEIjoiNGNjNzQyODAtZWZlNS00MDE2LWI0MWUtZjI5NDcyYTRlYzEyIiwibmJmIjoxNTg3Nzk3MzMyLCJvcmdSb2xlIjoiTUhQcm9mZXNzaW9uYWwiLCJzZXNzaW9uVG9rZW4iOiJTZXNzaW9uSWQ6MTcyLjMxLjUuMTMjcHJhc2hhbnQ6NGNjNzQyODAtZWZlNS00MDE2LWI0MWUtZjI5NDcyYTRlYzEyOk1ITVM6TUhQcm9mZXNzaW9uYWwjMTU4Nzc5NzMzMjM5NiMyNjMxNzU3ODYjMTUxMSIsInBlcnNvbklkIjoiOTI1ZDY3Y2QtN2QzYy00MDc4LTg5ZmItNjk2M2M0N2I0OTZhIiwidXNlclVVSUQiOiI3NzViOGMzZS02NzQyLTRiMzAtYjQ0My1jN2Q2YWE2ZWM0YWMiLCJleHAiOjE1ODc4MzMzMzIsImlhdCI6MTU4Nzc5NzMzMn0.ePKPfbkUA4HZkwMswdTKM8uwW8wfqUC6NLFbkEoirJM";
    private String orgUUID = "4cc74280-efe5-4016-b41e-f29472a4ec12";
    private String userUUID = "775b8c3e-6742-4b30-b443-c7d6aa6ec4ac";
    private String url = "http://13.126.27.50/MHMS_DEV/rest/getDoctorPatients/"+orgUUID+"/"+userUUID+"/Waiting";

    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<Anime> lstAnime ;
    private RecyclerView recyclerView ;


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
        Log.e(" ","inside jsonrequst()");
        StringRequest requestString = new StringRequest(Request.Method.GET, url,

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
        requestQueue.add(requestString) ;


    }

    private void setuprecyclerview(List<Anime> lstAnime) {


        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(getActivity(),lstAnime) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myadapter);

    }
}