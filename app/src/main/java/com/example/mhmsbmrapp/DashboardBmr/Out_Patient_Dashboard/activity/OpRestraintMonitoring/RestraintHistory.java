package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpRestraintMonitoring;

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

import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpRestraintMonitoring.adapter.RecyclerViewAdapterOpRestraint;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpRestraintMonitoring.model.AnimeOpRestraint;
import com.example.mhmsbmrapp.Login.SessionInformation;
import com.example.mhmsbmrapp.R;
import com.example.mhmsbmrapp.model.RestraintMonitoring;
import com.example.mhmsbmrapp.utility.RestraintMonitoringUtility;

import java.util.ArrayList;
import java.util.List;

public class RestraintHistory extends Fragment {
    private static final String TAG = "Fragment3";

    private List<AnimeOpRestraint> lstAnimeOpRestraint ;
    private RecyclerView recyclerView ;

    public RestraintHistory() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.op_restraint_history, container, false);



        lstAnimeOpRestraint = new ArrayList<>() ;
        recyclerView = v.findViewById(R.id.recyclerviewid);
        jsonrequest();
        return v;
    }


    public void jsonrequest() {

        Thread thread = new Thread() {
            public void run() {

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String loginToken = sharedPreferences.getString("loginToken", "");
                Log.e("loginToken is", loginToken);
                String patientId = sharedPreferences.getString("patientId", "");

                System.out.println("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");

                System.out.println("======================================");
                System.out.println("======================================");


                List<RestraintMonitoring> historyList = new RestraintMonitoringUtility().getHistory(loginToken, SessionInformation.sessionToken, patientId, SessionInformation.orgUUID);
                for(RestraintMonitoring rm : historyList) {
                    if(rm != null) {
                        System.out.println("____________________________________________________________");
                        System.out.println(rm.toString());
                    }

                    try{
                        if(rm != null) {
                            System.out.println(rm.toString());
                            AnimeOpRestraint anime = new AnimeOpRestraint();

                            anime.setName(rm.getSetting());
                            anime.setCategorie(rm.getNominatedRepresentativeName());
                            anime.setImage_url(rm.getInchargePsychiatrist());
                            lstAnimeOpRestraint.add(anime);
                        }

                    }catch(Exception e) { e.printStackTrace();}

                }



                System.out.println("======================================");
                System.out.println("======================================");
                System.out.println(loginToken);
                System.out.println(SessionInformation.sessionToken);
            }
        };
        thread.start();
        setuprecyclerview(lstAnimeOpRestraint);
    }


    private void setuprecyclerview(List<AnimeOpRestraint> lstAnime) {

        RecyclerViewAdapterOpRestraint myadapter = new RecyclerViewAdapterOpRestraint(getActivity(),lstAnime) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myadapter);

    }
}
