package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpbmrCompletedList;

import android.content.Context;
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
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpbmrCompletedList.opcompletelistadapter.Out_Completed_list_RecyclerViewAdapter;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpbmrCompletedList.opcompletelistmodel.Out_Completed_list_AnimeOpBmrTab;
import com.example.mhmsbmrapp.Login.MHPFlow;
import com.example.mhmsbmrapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Out_Completed_list extends Fragment {


    private final String JSON_URL = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json" ;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<Out_Completed_list_AnimeOpBmrTab> lstOut_Completed_list_AnimeOpBmrTab ;
    private RecyclerView recyclerView ;




    //private List<Out_Completed_list_AnimeOpBmrTab> lstOut_Completed_list_AnimeOpBmrTab ;
    //private RecyclerView recyclerView ;


    private String loginToken;
    private String orgUUID;
    private String userUUID;


    public Out_Completed_list() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.ophistory, container, false);

        lstOut_Completed_list_AnimeOpBmrTab = new ArrayList<>() ;
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
                        Out_Completed_list_AnimeOpBmrTab anime = new Out_Completed_list_AnimeOpBmrTab() ;
                        anime.setName(jsonObject.getString("name"));
                        anime.setDescription(jsonObject.getString("description"));
                        anime.setRating(jsonObject.getString("Rating"));
                        anime.setCategorie(jsonObject.getString("categorie"));
                        anime.setNb_episode(jsonObject.getInt("episode"));
                        anime.setStudio(jsonObject.getString("studio"));
                        anime.setImage_url(jsonObject.getString("img"));
                        lstOut_Completed_list_AnimeOpBmrTab.add(anime);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                setuprecyclerview(lstOut_Completed_list_AnimeOpBmrTab);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });*/


        //requestQueue = Volley.newRequestQueue(getActivity());
        //requestQueue.add(request) ;


        Log.e("^^^^^^^^^^^ ","inside jsonrequst() Out_Patientbmr");

        System.out.println(getActivity().toString());
        System.out.println(getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE));
        System.out.println(getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).getString("loginToken", ""));


        loginToken = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).getString("loginToken", "");
        String sessionToken = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).getString("sessionToken", "");

        try{
            orgUUID = new JSONObject(MHPFlow.decoded(loginToken)).getString("orgUUID");
            userUUID = new JSONObject(MHPFlow.decoded(loginToken)).getString("userUUID");
        } catch (Exception e) {}


        Thread thread = new Thread(){

            public void run() {
                try {
                    JSONArray arr = new MHPFlow().getCompletedPatients(orgUUID, userUUID, loginToken);
                    if(arr != null) {
                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject jsonObject = new JSONObject(arr.get(i).toString());
                            //Log.e(i + "", jsonObject.toString());
                            Out_Completed_list_AnimeOpBmrTab anime = new Out_Completed_list_AnimeOpBmrTab();
                            anime.setName(jsonObject.getString("patientName"));
                            anime.setCategorie(jsonObject.getString("assignedmhpName"));
                            anime.setDescription(jsonObject.getString("patientPhone"));
                            anime.setRating(jsonObject.getString("admissionStatus"));
                            lstOut_Completed_list_AnimeOpBmrTab.add(anime);
                        }
                    }

                } catch (JSONException e) {
                    Log.e("error occurred", e.getMessage());
                    e.printStackTrace();
                }

                setuprecyclerview(lstOut_Completed_list_AnimeOpBmrTab);
            }

        };
        thread.start();


    }

    private void setuprecyclerview(List<Out_Completed_list_AnimeOpBmrTab> lstOut_Completed_list_AnimeOpBmrTab) {


        Out_Completed_list_RecyclerViewAdapter myadapter = new Out_Completed_list_RecyclerViewAdapter(getActivity(),lstOut_Completed_list_AnimeOpBmrTab) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myadapter);

    }
}
