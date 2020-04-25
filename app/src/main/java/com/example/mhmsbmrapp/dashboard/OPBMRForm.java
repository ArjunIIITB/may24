package com.example.mhmsbmrapp.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mhmsbmrapp.R;

public class OPBMRForm extends AppCompatActivity {

    private String sessionToken;
    private String loginToken;
    private String loginDecodedToken;
    private String patientId;
    private String orgUuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_p_b_m_r_form);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        loginToken = sharedPreferences.getString("loginToken", "");


        //sessionToken = sharedPreferences.getString("sessionToken", "");

        /*try {
            loginDecodedToken = MHPFlow.decoded(loginToken);
            sessionToken = new JSONObject(loginDecodedToken).getString("sessionToken");
            orgUuid = new JSONObject(loginDecodedToken).getString("orgUUID");
            patientId = getIntent().getStringExtra("patientId");
        } catch(Exception e){ e.printStackTrace(); }



        *//*SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        final String loginToken = sharedPreferences.getString("loginToken", "");

        try {
            loginDecodedToken = MHPFlow.decoded(loginToken);
            userUUID = new JSONObject(loginDecodedToken).getString("userUUID");
        } catch(Exception e){ e.printStackTrace(); }

        patientId = getIntent().getStringExtra("patientId");

        Log.e("userId patientId", userUUID+" "+patientId);*//*

Thread thread = new Thread(){

    public void run(){

        System.out.println("inside thread");


         //patientId = "a7864f44-7ba8-4bfa-b8c2-de9afa84d30d";
         //orgUuid = "4cc74280-efe5-4016-b41e-f29472a4ec12";
        List<JSONObject> composition = new ArrayList<JSONObject>();

        ArrayList<List<JSONObject>> returnList = new ArrayList<List<JSONObject>>();

        JSONObject virtualFolder = new BmrUtility().getVirtualFolderByPersonId(loginToken, patientId, orgUuid);
        System.out.println(virtualFolder.toString());
        System.out.println("Iterator starts from here");
        List<String[][]> list = new BmrUtility().fetchNameTemplateIdAndCompositionUid(virtualFolder);
        Iterator itr = list.iterator();
        int index=-1;
        while(itr.hasNext()){
            index ++;
            returnList.add(new ArrayList<JSONObject>());
            String[][] nameAndTemplateId = (String[][])itr.next();
            for(int i=0; i<nameAndTemplateId.length;i++){
                String name = nameAndTemplateId[i][0];
                String templateId = nameAndTemplateId[i][1];
                String compositionIDList = nameAndTemplateId[i][2];
                //System.out.println("check here --------" + name + " " + templateId +" " + compositionIDList);
                JSONObject newComposition = new BmrUtility().getComposition(name, templateId, compositionIDList, patientId, sessionToken, loginToken);
                returnList.get(index).add(newComposition);
            }
        }

        for(List item : returnList){

            for(int i=0; i<item.size();i++){
                System.out.println(item.get(i).toString());
            }
            System.out.println("next item starts from here onwards");
        }

        System.out.println("iterator ends here");

    }


};
thread.start();*/

    }
}
