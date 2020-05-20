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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mhmsbmrapp.DashboardBmr.In_Patient_Dashboard.adapter.RecyclerView_Adapter_IpReffered;
import com.example.mhmsbmrapp.DashboardBmr.In_Patient_Dashboard.model.Anime_Ip_Reffered;

import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpTherapy.model.AnimeOpTherapy;
import com.example.mhmsbmrapp.Login.SessionInformation;
import com.example.mhmsbmrapp.R;
import com.example.mhmsbmrapp.model.Therapy;
import com.example.mhmsbmrapp.utility.TherapyUtility;
import com.example.mhmsbmrapp.utility.ip.IPUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Reffered_To_Ip extends Fragment {

    private final String JSON_URL = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json" ;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<Anime_Ip_Reffered> lstAnime_Ip_Reffered ;
    private RecyclerView recyclerView ;

    public Reffered_To_Ip() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.opassessemethistory, container, false);

        lstAnime_Ip_Reffered = new ArrayList<>() ;
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
                        Anime_Ip_Reffered anime = new Anime_Ip_Reffered() ;
                        anime.setName(jsonObject.getString("name"));
                        anime.setDescription(jsonObject.getString("description"));
                        anime.setRating(jsonObject.getString("Rating"));
                        anime.setCategorie(jsonObject.getString("categorie"));
                        anime.setNb_episode(jsonObject.getInt("episode"));
                        anime.setStudio(jsonObject.getString("studio"));
                        anime.setImage_url(jsonObject.getString("img"));
                        lstAnime_Ip_Reffered.add(anime);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                setuprecyclerview(lstAnime_Ip_Reffered);

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
                JSONArray referredPatientList = new IPUtility().getorgpatientReferredToIP(loginToken, SessionInformation.orgUUID);

                try {
                    for (int i = 0; i < referredPatientList.length(); i++) {
                        Anime_Ip_Reffered anime = new Anime_Ip_Reffered();
                        JSONObject referredPatient = referredPatientList.getJSONObject(i);
                        anime.setName(referredPatient.getString("patientName"));
                        anime.setCategorie(referredPatient.getString("patientPhone"));
                        anime.setImage_url(referredPatient.getString("referredBy"));
                        anime.setRating(referredPatient.getString("patientCity"));
                        System.out.println(referredPatient.getString("patientPhone"));
                        System.out.println(referredPatient.getString("patientName"));
                        System.out.println(referredPatient.getString("referredBy"));
                        lstAnime_Ip_Reffered.add(anime);
                    }
                }catch (Exception e) {e.printStackTrace();}
            }
        };
        thread.start();
        setuprecyclerview(lstAnime_Ip_Reffered);
    }

    private void setuprecyclerview(List<Anime_Ip_Reffered> lstAnime_Ip_Reffered) {

        RecyclerView_Adapter_IpReffered myadapter = new RecyclerView_Adapter_IpReffered(getActivity(),lstAnime_Ip_Reffered) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myadapter);

    }
}
