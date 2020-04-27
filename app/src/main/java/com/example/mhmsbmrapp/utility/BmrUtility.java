package com.example.mhmsbmrapp.utility;

import android.util.Log;

import com.example.mhmsbmrapp.Login.GlobalVariables;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class BmrUtility {

    OkHttpClient client = new OkHttpClient();

    public JSONObject getVirtualFolderByPersonId(String loginToken, String patientId, String orgUuid) {
        System.out.println("inside getVirtualFolderByPersonId");

        final String RELATIVE_PATH = "getVirtalFolderByPersonId/";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH+"/"+patientId+"/"+orgUuid)
                .get()
                .addHeader("Authorization", "Bearer "+loginToken)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = null;
        JSONObject returnObject = null;
        Log.e("brfore try", "before try");
        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            returnObject = new JSONObject(rb.string());


        }catch(Exception e){
            e.printStackTrace();
        }

        return returnObject;

    } //getVirtualFolderByPersonId (GET)


    public List<String[][]> fetchNameTemplateIdAndCompositionUid(JSONObject virtualFolder){

        ArrayList<String[][]> list = new ArrayList<String[][]>();
        try {
            System.out.println(virtualFolder.getJSONArray("children").getJSONObject(0).getJSONObject("virtualFolderData").getJSONArray("data").getJSONObject(0).get("compositionUid"));


            JSONArray children = virtualFolder.getJSONArray("children");
            for(int i=0;i<children.length();i++){
                JSONArray data = children.getJSONObject(i).getJSONObject("virtualFolderData").getJSONArray("data");
                list.add(new String[data.length()][3]);
                for(int j=0;j<data.length();j++){
                    String[][] s = list.get(i);

                    s[j][0] = data.getJSONObject(j).getString("name");
                    s[j][1] = data.getJSONObject(j).getString("templateId");
                    s[j][2] = data.getJSONObject(j).getString("compositionUid");
                    //System.out.println("---------------------------->>>>>>>>>>>>>>>>>> compositionUid "+data.getJSONObject(j).getString("compositionUid"));

                }
            }


        } catch(Exception e) {}

        return list;
    } //fetchNameTemplateIdAndCompositionUid


    public JSONObject getComposition(String name, String templateId, String compositionIDList, String personId, String sessionToken, String loginToken) {

        final String RELATIVE_PATH = "getComposition/";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObjectResult = null;

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("name", name);
            jsonObject.put("personId", personId);
            jsonObject.put("templateId", templateId);
            jsonObject.put("token", sessionToken);

                JSONObject query_parameters = new JSONObject();
                query_parameters.put("CompositionIDList", "\"{'"+compositionIDList+"'}\"");

            jsonObject.put("query-parameters", query_parameters);

        }catch (Exception e){
            e.printStackTrace();
        }

        RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+loginToken)
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            Log.e("why is it null", "");
            jsonObjectResult = new JSONObject(rb.string());
            //System.out.println("why is it null-----------------------------------"+rb.string());

        }catch(Exception e){
            e.printStackTrace();
        }
        return jsonObjectResult;
    } //getComposition (POST)


    public ArrayList<List<JSONObject>> getHistory(String loginToken, String sessionToken, String patientId, String orgUuid) {
        ArrayList<List<JSONObject>> returnList = new ArrayList<List<JSONObject>>();

        JSONObject virtualFolder = new BmrUtility().getVirtualFolderByPersonId(loginToken, patientId, orgUuid);
        System.out.println(virtualFolder.toString());

        //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

        //System.out.println("Iterator starts from here\n");
        List<String[][]> list = new BmrUtility().fetchNameTemplateIdAndCompositionUid(virtualFolder);
        Iterator itr = list.iterator();
        int index=-1;
        while(itr.hasNext()){
            index ++;
            returnList.add(new ArrayList<JSONObject>());
            String[][] nameAndTemplateId = (String[][])itr.next();
            for(int i=0; i<nameAndTemplateId.length;i++){
                //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                String name = nameAndTemplateId[i][0];
                String templateId = nameAndTemplateId[i][1];
                String compositionIDList = nameAndTemplateId[i][2];
                System.out.println("check here --------" + name + " " + templateId +" " + compositionIDList);
                JSONObject newComposition = new BmrUtility().getComposition(name, templateId, compositionIDList, patientId, sessionToken, loginToken);
                //System.out.println("newComposition " +newComposition);
                if(newComposition != null) {
                    returnList.get(index).add(newComposition);
                }
            }
        }
        return  returnList;
    }


    public JSONObject createCompositon(String value, String templateId, String loginToken) {

        final String RELATIVE_PATH = "createComposition/";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {

                    JSONObject composition = new JSONObject();

                    composition.put("/language", "en");
                    composition.put("/territory", "IN");
                    composition.put("/content[openEHR-EHR-EVALUATION.clinical_history.v0]/data[at0001]/items[at0002]|value", value);
                    composition.put("/composer|name", "prashant");
                    composition.put("/composer|identifier", "775b8c3e-6742-4b30-b443-c7d6aa6ec4ac");
                    composition.put("/context/health_care_facility|identifier", "4cc74280-efe5-4016-b41e-f29472a4ec12");
                    composition.put("/context/health_care_facility|name", "psm321op");
                    composition.put("/context/start_time", "2020-04-18T06:19:30.179Z");
                    composition.put("/context/end_time", "2020-04-18T06:19:30.179Z");
                    composition.put("/context/location", "Bengaluru");

            jsonObject.put("composition", composition);

            jsonObject.put("authorization", "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587230145962#1543441#867");
            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", "25424093-ef2c-4ff4-a6a4-09afc1fb8c08");
            jsonObject.put("templateId", templateId);


        }catch (Exception e) {}

        RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+loginToken)
                .build();

        Response response = null;
        JSONObject returnObject = null;
        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            Log.e("why is it null", "");
            returnObject = new JSONObject(rb.string());
            //jsonObjectResult = new JSONObject(rb.string());
            //System.out.println("why is it null-----------------------------------"+rb.string());

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    }

}
