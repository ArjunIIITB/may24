package com.example.mhmsbmrapp.utility;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.mhmsbmrapp.Login.GlobalVariables;
import com.example.mhmsbmrapp.model.Composition;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

@RequiresApi(api = Build.VERSION_CODES.O)
public class BmrCreateCompositionUtility {

    OkHttpClient client = new OkHttpClient();

    public JSONObject createComposition_EHRC_Complaintsv0(String[] values, Map<String, String> map, String loginToken, String sessionToken) {

        final String RELATIVE_PATH = "createComposition/";


        //String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587232756366#-301314980#869";

        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();
        String time = "2020-04-23T11:38:01.109Z";

        String templateId = "EHRC - Complaints.v0";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {

            JSONObject composition = new JSONObject();

            composition.put("/language", "en");
            composition.put("/territory", "IN");
            composition.put("/content[openEHR-EHR-OBSERVATION.story.v1]/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign.v1]/items[at0001]|value", values[0]);
            composition.put("/content[openEHR-EHR-OBSERVATION.story.v1]/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign.v1]/items[at0003]|value", values[1]);

            composition.put("/composer|name", map.get("composer_name"));
            composition.put("/composer|identifier", map.get("composer_identifier"));
            composition.put("/context/health_care_facility|identifier", map.get("facility_identifier"));
            composition.put("/context/health_care_facility|name", map.get("facility_name"));
            composition.put("/context/start_time", time);
            composition.put("/context/end_time", time);
            composition.put("/context/location", map.get("location"));


            composition.put("content[openEHR-EHR-OBSERVATION.story.v1]/data[at0001]/events[at0002]/time|value", time);
            composition.put("content[openEHR-EHR-OBSERVATION.story.v1]/data[at0001]/origin|value", time);

            jsonObject.put("composition", composition);

            jsonObject.put("authorization", sessionToken);
            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", map.get("personId"));
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

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //createComposition_EHRC_Complaintsv0 (POST)


    public JSONObject createCompostion_EHRC_Clinical_historyv0(String value, Map<String, String> map, String loginToken, String sessionToken) {

        final String RELATIVE_PATH = "createComposition/";

        String templateId = "EHRC - Clinical history.v0";

        //String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587232756366#-301314980#869";

        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();
        String time = "2020-04-23T11:38:01.109Z";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {

            JSONObject composition = new JSONObject();

            composition.put("/language", "en");
            composition.put("/territory", "IN");
            composition.put("/content[openEHR-EHR-EVALUATION.clinical_history.v0]/data[at0001]/items[at0002]|value", value);

            composition.put("/composer|name", map.get("composer_name"));
            composition.put("/composer|identifier", map.get("composer_identifier"));
            composition.put("/context/health_care_facility|identifier", map.get("facility_identifier"));
            composition.put("/context/health_care_facility|name", map.get("facility_name"));
            composition.put("/context/start_time", time);
            composition.put("/context/end_time", time);
            composition.put("/context/location", map.get("location"));

            jsonObject.put("composition", composition);

            jsonObject.put("authorization", sessionToken);
            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", map.get("personId"));
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




    } //createCompostionEHRC_Clinical_historyv0 (POST)


    public JSONObject createComposition_EHRC_Summary_of_illnessv0(String value, Map<String, String> map, String loginToken, String sessionToken) {


        final String RELATIVE_PATH = "createComposition/";

        String templateId = "EHRC - Summary of illness.v0";

        //String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587232756366#-301314980#869";

        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();
        String time = "2020-04-23T11:38:01.109Z";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {

            JSONObject composition = new JSONObject();

            composition.put("/language", "en");
            composition.put("/territory", "IN");
            composition.put("/content[openEHR-EHR-EVALUATION.summary_of_illness.v0]/data[at0001]/items[at0002]|value", value);

            composition.put("/composer|name", map.get("composer_name"));
            composition.put("/composer|identifier", map.get("composer_identifier"));
            composition.put("/context/health_care_facility|identifier", map.get("facility_identifier"));
            composition.put("/context/health_care_facility|name", map.get("facility_name"));
            composition.put("/context/start_time", time);
            composition.put("/context/end_time", time);
            composition.put("/context/location", map.get("location"));
            jsonObject.put("composition", composition);

            jsonObject.put("authorization", sessionToken);
            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", map.get("personId"));
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


        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;



    } //createCompositionEHRC_Summary_of_illnessv0 (POST)


    public JSONObject createComposition_EHRC_CGI_Scalev0(String value, Map<String, String> map, String loginToken, String sessionToken) {

        final String RELATIVE_PATH = "createComposition/";

        String templateId = "EHRC - CGI Scale.v0";

        //String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587232756366#-301314980#869";

        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();
        String time = "2020-04-23T11:38:01.109Z";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {

            JSONObject composition = new JSONObject();

            composition.put("/language", "en");
            composition.put("/territory", "IN");
            composition.put("/content[openEHR-EHR-EVALUATION.cgi_scale.v0]/data[at0001]/items[at0006]|value", value);
            composition.put("/composer|name", map.get("composer_name"));
            composition.put("/composer|identifier", map.get("composer_identifier"));
            composition.put("/context/health_care_facility|identifier", map.get("facility_identifier"));
            composition.put("/context/health_care_facility|name", map.get("facility_name"));
            composition.put("/context/start_time", time);
            composition.put("/context/end_time", time);
            composition.put("/context/location", map.get("location"));
            jsonObject.put("composition", composition);

            jsonObject.put("authorization", sessionToken);
            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", map.get("personId"));
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


        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;

    } //createComposition_EHRC_CGI_Scalev0 (POST)


    public JSONObject createCompositionEHRC_Medication_orderv0(String[] values, Map<String, String> map, String loginToken, String sessionToken) {


        final String RELATIVE_PATH = "createComposition/";

        String templateId = "EHRC - Medication order.v0";

        //String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587238304431#93780176#874";

        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();
        String time = "2020-04-23T11:38:01.109Z";


        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {

            JSONObject composition = new JSONObject();

            composition.put("/language", "en");
            composition.put("/territory", "IN");

            composition.put("/content[openEHR-EHR-SECTION.medication_order_list.v0]/items[openEHR-EHR-INSTRUCTION.medication_order.v2]/activities[at0001]/description[at0002]/items[at0070]|value", values[0] +" " + values[1]);
            composition.put("/content[openEHR-EHR-SECTION.medication_order_list.v0]/items[openEHR-EHR-INSTRUCTION.medication_order.v2]/activities[at0001]/description[at0002]/items[openEHR-EHR-CLUSTER.therapeutic_direction.v1]/items[openEHR-EHR-CLUSTER.dosage.v1]/items[openEHR-EHR-CLUSTER.timing_daily.v1]/items[at0027]|value", values[2]);
            composition.put("/content[openEHR-EHR-SECTION.medication_order_list.v0]/items[openEHR-EHR-INSTRUCTION.medication_order.v2]/activities[at0001]/description[at0002]/items[at0173]|value", " ");
            composition.put("/content[openEHR-EHR-SECTION.medication_order_list.v0]/items[openEHR-EHR-INSTRUCTION.medication_order.v2]/activities[at0001]/description[at0002]/items[openEHR-EHR-CLUSTER.therapeutic_direction.v1]/items[at0066]|value", "P"+values[3]+values[4].toUpperCase().charAt(0) ); // here P is prefix, 1(integer) is for duration and W(D,W,M) is for days, weeks or months;
            composition.put("/content[openEHR-EHR-SECTION.medication_order_list.v0]/items[openEHR-EHR-INSTRUCTION.medication_order.v2]/activities[at0001]/description[at0002]/items[at0009]|value", values[5]);

            composition.put("/composer|name", map.get("composer_name"));
            composition.put("/composer|identifier", map.get("composer_identifier"));
            composition.put("/context/health_care_facility|identifier", map.get("facility_identifier"));
            composition.put("/context/health_care_facility|name", map.get("facility_name"));
            composition.put("/context/start_time", time);
            composition.put("/context/end_time", time);
            composition.put("/context/location", map.get("location"));

            jsonObject.put("composition", composition);

            jsonObject.put("authorization", sessionToken);
            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", map.get("personId"));
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

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;





    } //createCompositionEHRC_Medication_orderv0 (POST)


    public JSONObject createCompositionEHRC_Clinical_notesv0(String value, Map<String, String> map, String loginToken, String sessionToken) {

        final String RELATIVE_PATH = "createComposition/";

        String templateId = "EHRC - Clinical notes.v0";

        //String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587238304431#93780176#874";

        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();
        String time = "2020-04-23T11:38:01.109Z";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {

            JSONObject composition = new JSONObject();

            composition.put("/language", "en");
            composition.put("/territory", "IN");
            composition.put("/content[openEHR-EHR-EVALUATION.clinical_synopsis.v1]/data[at0001]/items[at0002]|value", value);
            composition.put("/composer|name", map.get("composer_name"));
            composition.put("/composer|identifier", map.get("composer_identifier"));
            composition.put("/context/health_care_facility|identifier", map.get("facility_identifier"));
            composition.put("/context/health_care_facility|name", map.get("facility_name"));
            composition.put("/context/start_time", time);
            composition.put("/context/end_time", time);
            composition.put("/context/location", map.get("location"));

            jsonObject.put("composition", composition);

            jsonObject.put("authorization", sessionToken);
            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", map.get("personId"));
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


        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;

    } //createCompositionEHRC_Clinical_notesv0 (POST)


    public JSONObject createComposition_EHRC_Diagnosisv0(String[] values, Map<String, String> map, String loginToken, String sessionToken) {

        final String RELATIVE_PATH = "createComposition/";

        String templateId = "EHRC - Diagnosis.v0";

        //String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587287659468#-1356064576#898";

        String time = "2020-04-23T11:38:01.109Z";


        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("authorization", sessionToken);

                JSONObject composition = getDiagnosisTemplate(loginToken);


                composition.put("ctx/language", "en");
                composition.put("diagnosis/composer|id", map.get("composer_name"));
                composition.put("diagnosis/context/end_time", time);
                composition.put("diagnosis/context/health_care_facility|id", map.get("composer_identifier"));
                composition.put("diagnosis/context/health_care_facility|name", map.get("facility_name"));
                composition.put("diagnosis/context/start_time", time);
                composition.put("diagnosis/problem_diagnosis:0/diagnostic_certainty", values[0]);
                composition.put("diagnosis/problem_diagnosis:0/problem_diagnosis_name|code", values[2]);
                composition.put("diagnosis/problem_diagnosis:0/problem_diagnosis_name|value", values[1]);

            jsonObject.put("composition", composition);

            jsonObject.put("format", "FLAT");
            jsonObject.put("personId", map.get("personId"));
            jsonObject.put("templateId", templateId);




        }catch (Exception e) { e.printStackTrace();}

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


        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;

    } //createComposition_EHRC_Diagnosisv0 (POST)

// createComposition_EHRC_Diagnosisv0 HELPER
    public JSONObject getDiagnosisTemplate(String loginToken) {

        final String RELATIVE_PATH = "diagnosisTemplate/";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .get()
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


        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;

    } //getDiagnosisTemplate (GET)


    public JSONObject createComposition_EHRC_Service_requestv0(String values[], Map<String, String> map, String loginToken, String sessionToken) {
        final String RELATIVE_PATH = "createComposition/";

        String templateId = "EHRC - Service request.v0";

        //String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587232756366#-301314980#869";

        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();
        String time = "2020-04-23T11:38:01.109Z";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("authorization", sessionToken);
            JSONObject composition = new JSONObject();


                composition.put("/composer|identifier", map.get("composer_identifier"));
                composition.put("/composer|name", map.get("composer_name"));
                composition.put("/content[openEHR-EHR-INSTRUCTION.service_request.v1]/activities[at0001]/description[at0009]/items[at0062]|value", values[0]);
                composition.put("/content[openEHR-EHR-INSTRUCTION.service_request.v1]/activities[at0001]/description[at0009]/items[at0064]|value", values[1]);
                composition.put("/content[openEHR-EHR-INSTRUCTION.service_request.v1]/activities[at0001]/description[at0009]/items[at0121]|value", values[2]);
                composition.put("/content[openEHR-EHR-INSTRUCTION.service_request.v1]/activities[at0001]/description[at0009]/items[at0148]|value", "Referral");
                composition.put("/content[openEHR-EHR-INSTRUCTION.service_request.v1]/protocol[at0008]/items[openEHR-EHR-CLUSTER.organisation.v0]/items[at0001]|value", values[4]);
                composition.put("/content[openEHR-EHR-INSTRUCTION.service_request.v1]/protocol[at0008]/items[openEHR-EHR-CLUSTER.organisation.v0]/items[at0005]/items[openEHR-EHR-CLUSTER.person_name.v0]/items[at0001]|value",values[5]);
                composition.put("/context/end_time", time);
                composition.put("/context/health_care_facility|identifier", map.get("facility_identifier"));
                composition.put("/context/health_care_facility|name", map.get("facility_name"));
                composition.put("/context/location", map.get("location"));
                composition.put("/context/start_time", time);
                composition.put("/language", "en");
                composition.put("/territory", "IN");

                jsonObject.put("composition", composition);

            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", map.get("personId"));
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


        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //createComposition_EHRC_Service_requestv0 (POST)


    public JSONObject createComposition_EHRC_Service_requestv01(String values[], Map<String, String> map, String loginToken, String sessionToken) {
        final String RELATIVE_PATH = "createComposition/";

        String templateId = "EHRC - Service request.v0";

        //String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587232756366#-301314980#869";

        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();
        String time = "2020-04-23T11:38:01.109Z";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("authorization", sessionToken);
            JSONObject composition = new JSONObject();


            composition.put("/composer|identifier", map.get("composer_identifier"));
            composition.put("/composer|name", map.get("composer_name"));

            composition.put("/content[openEHR-EHR-INSTRUCTION.service_request.v1]/activities[at0001]/description[at0009]/items[at0040]|value", values[0]);
            composition.put("/content[openEHR-EHR-INSTRUCTION.service_request.v1]/activities[at0001]/description[at0009]/items[at0148]|value", "Followup");

            composition.put("/context/end_time", time);
            composition.put("/context/health_care_facility|identifier", map.get("facility_identifier"));
            composition.put("/context/health_care_facility|name", map.get("facility_name"));
            composition.put("/context/location", map.get("location"));
            composition.put("/context/start_time", time);
            composition.put("/language", "en");
            composition.put("/territory", "IN");

            jsonObject.put("composition", composition);

            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", map.get("personId"));
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


        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;


    } //




//______________________________________________________________________________________________________________________________________________________________________________________

















    public JSONObject saveAllCompositions(Map<String,String> map, List<Composition> compositionList, String loginToken, String sessionToken) {

        final String RELATIVE_PATH = "saveAllCompositions/";

        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject payload = new JSONObject();

        try {

            JSONObject jsonObject = new JSONObject();

            payload.put("orgUUID", map.get("facility_identifier"));
            payload.put("patientId", map.get("personId"));
            payload.put("token", sessionToken);

                JSONArray uidata = new JSONArray();
                for(int i=0;i< compositionList.size();i++) {
                    JSONObject composition = new JSONObject();

                    Composition object = compositionList.get(i);
                    composition.put("compositionUid", object.getCompositionUid());
                    composition.put("name", object.getName());
                    composition.put("templateId", object.getTemplateId());
                    composition.put("time", "1587358504256");
                    if(object.getTemplateId().equals("EHRC - Service request.v0")) {
                        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                        System.out.println(object.toString());
                        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                        composition.put("serviceRequest", object.getServiceRequest());
                    }
                    uidata.put(i, composition);
                }

            payload.put("uidata", uidata);
            payload.put("uuid", map.get("composer_identifier"));

        }catch (Exception e) { e.printStackTrace(); }

        RequestBody formBody = RequestBody.create(JSON, payload.toString());

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


        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;

    }

}
