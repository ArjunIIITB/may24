package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpBmrTab;

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
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpBmrTab.adapter.RecyclerViewAdapterOpbmrtab;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.activity.OpBmrTab.model.AnimeOpBmrTab;
import com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.model.Anime;
import com.example.mhmsbmrapp.Login.MHPFlow;
import com.example.mhmsbmrapp.R;
import com.example.mhmsbmrapp.model.MHP;
import com.example.mhmsbmrapp.utility.BmrUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FragmenOphistory extends Fragment {
    private static final String TAG = "Fragment3";

    Button button;
    TextView addpatienttext;

    private final String JSON_URL = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json" ;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<AnimeOpBmrTab> lstAnimeOpBmrTab ;
    private RecyclerView recyclerView ;

    public FragmenOphistory() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ophistory, container, false);



        lstAnimeOpBmrTab = new ArrayList<>() ;
        recyclerView = v.findViewById(R.id.recyclerviewid);
        jsonrequest();

        //json//
        return v;
    }


    private void jsonrequest1() {

        Thread thread = new Thread() {
            public void run() {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                Log.e("message", sharedPreferences.getString("loginToken", ""));
                Log.e("message", sharedPreferences.getString("sessionToken", ""));
                String loginToken = sharedPreferences.getString("loginToken", "");
                JSONArray response = new MHPFlow().getCompletedPatients("4cc74280-efe5-4016-b41e-f29472a4ec12", "775b8c3e-6742-4b30-b443-c7d6aa6ec4ac", loginToken);
                Log.e("is null", response.toString());
                for (
                        int i = 0; i < response.length(); i++) {
                    JSONObject jsonObject = null;

                    try {
                        jsonObject = response.getJSONObject(i);
                        AnimeOpBmrTab anime = new AnimeOpBmrTab();

                        anime.setDescription(jsonObject.getString("patientName"));
                        anime.setRating(jsonObject.getString("patientName"));
                        anime.setCategorie(jsonObject.getString("patientName"));
                        anime.setStudio(jsonObject.getString("patientName"));
                        anime.setImage_url(jsonObject.getString("patientName"));
                        lstAnimeOpBmrTab.add(anime);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }

        };
        thread.start();

        setuprecyclerview(lstAnimeOpBmrTab);

    }

    private void jsonrequest() {

       Thread thread = new Thread() {
           public void run() {

               SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
               String loginToken = sharedPreferences.getString("loginToken", "");
               Log.e("loginToken is", loginToken);
               String sessionToken = sharedPreferences.getString("sessionToken", "");
               String orgUUID = null;
               try {
                   orgUUID = new JSONObject(MHPFlow.decoded(loginToken)).getString("orgUUID");
               }catch (Exception e) {e.printStackTrace();}
               String patientId = "a7864f44-7ba8-4bfa-b8c2-de9afa84d30d";


               ArrayList<List<JSONObject>> returnList;

               System.out.println("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
                returnList = new BmrUtility().getHistory(loginToken, sessionToken, patientId, orgUUID);
               for (List item : returnList) {
                   try {
                       AnimeOpBmrTab anime = new AnimeOpBmrTab();
                       for (int i = 0; i < item.size(); i++) {
                           JSONObject obj = new JSONObject(item.get(i).toString());
                           JSONObject o = obj.getJSONArray("resultSet").getJSONObject(0);

                           if (o.has("symptomName")) {
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
                           }
                       }
                       Log.e("message", "next item starts from here onwards");
                       lstAnimeOpBmrTab.add(anime);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }

               }
           }
       };
       thread.start();
        setuprecyclerview(lstAnimeOpBmrTab);


    }

    private void setuprecyclerview(List<AnimeOpBmrTab> lstAnime) {


        RecyclerViewAdapterOpbmrtab myadapter = new RecyclerViewAdapterOpbmrtab(getActivity(),lstAnimeOpBmrTab) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myadapter);

    }
}
