package com.example.mhmsbmrapp.DashboardBmr.In_Patient_Dashboard.AdmittedToIP;

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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mhmsbmrapp.DashboardBmr.In_Patient_Dashboard.AdmittedToIP.admittedToIPAdapter.admittedToIPRecycleAdapter;
import com.example.mhmsbmrapp.DashboardBmr.In_Patient_Dashboard.AdmittedToIP.admittedtoIPModel.admittedToIPModelfile;
import com.example.mhmsbmrapp.DashboardBmr.In_Patient_Dashboard.RefferedIP.model.IPReferredModel;
import com.example.mhmsbmrapp.DashboardBmr.In_Patient_Dashboard.adapter.RecyclerView_Adapter_IpReffered;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpRestraintMonitoring.adapter.RecyclerViewAdapterOpRestraint;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpRestraintMonitoring.model.AnimeOpRestraint;
import com.example.mhmsbmrapp.Login.SessionInformation;
import com.example.mhmsbmrapp.R;
import com.example.mhmsbmrapp.utility.ip.IPUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Ip_Admitted extends Fragment {

    private static final String TAG = "Fragment3";

    private List<admittedToIPModelfile> lstadmittedToIPModelfile ;
    private RecyclerView recyclerView ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.opassessemethistory, container, false);

        lstadmittedToIPModelfile = new ArrayList<>() ;
        recyclerView = v.findViewById(R.id.recyclerviewid);
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
                JSONArray referredPatientList = new IPUtility().getDoctorPatients(loginToken, SessionInformation.orgUUID, SessionInformation.userUUID,"Admited%20To%20IP");

                try {
                    for (int i = 0; i < referredPatientList.length(); i++) {
                        admittedToIPModelfile anime = new admittedToIPModelfile();
                        JSONObject referredPatient = referredPatientList.getJSONObject(i);
                        anime.setName(referredPatient.getString("patientName"));
                        anime.setCategorie(referredPatient.getString("patientPhone"));
                        anime.setImage_url(referredPatient.getString("referredBy"));
                        anime.setRating(referredPatient.getString("patientCity"));
                        System.out.println(referredPatient.getString("patientPhone"));
                        System.out.println(referredPatient.getString("patientName"));
                        System.out.println(referredPatient.getString("referredBy"));
                        lstadmittedToIPModelfile.add(anime);
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
        setuprecyclerview(lstadmittedToIPModelfile);
    }

    private void setuprecyclerview(List<admittedToIPModelfile> lstadmittedToIPModelfile) {

        admittedToIPRecycleAdapter myadapter = new admittedToIPRecycleAdapter(getActivity(),lstadmittedToIPModelfile) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myadapter);

    }






}
