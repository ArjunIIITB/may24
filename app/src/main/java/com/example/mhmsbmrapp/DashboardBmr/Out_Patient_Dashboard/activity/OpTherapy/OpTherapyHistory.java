package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpTherapy;

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

import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpTherapy.adapter.RecyclerViewAdapterOpTherapy;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpTherapy.model.AnimeOpTherapy;
import com.example.mhmsbmrapp.Login.SessionInformation;
import com.example.mhmsbmrapp.R;
import com.example.mhmsbmrapp.model.Therapy;
import com.example.mhmsbmrapp.utility.TherapyUtility;

import java.util.ArrayList;
import java.util.List;

public class OpTherapyHistory extends Fragment {
    private static final String TAG = "Fragment3";

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

        Thread thread = new Thread() {
            public void run() {

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String loginToken = sharedPreferences.getString("loginToken", "");
                Log.e("loginToken is", loginToken);
                String patientId = sharedPreferences.getString("patientId", "");


                System.out.println("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
                List<Therapy> therapyList = new TherapyUtility().getHistory(loginToken, SessionInformation.sessionToken, patientId, SessionInformation.orgUUID);

                System.out.println("======================================");
                System.out.println("======================================");
                for (Therapy o : therapyList) {
                    try {
                        AnimeOpTherapy anime = new AnimeOpTherapy();
                        System.out.println();

                        Log.e("key issue", o.getKeyIssue());
                        Log.e("session objective", o.getObjectiveSession());
                        Log.e("supervisor name", o.getSupervisorName());
                        anime.setName(o.getKeyIssue());
                        anime.setCategorie(o.getObjectiveSession());
                        anime.setImage_url(o.getSupervisorName());
                        lstAnimeOpTherapy.add(anime);

                        System.out.println();

                    }catch (Exception e) {e.printStackTrace();}

                }
                System.out.println("======================================");
                System.out.println("======================================");
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
