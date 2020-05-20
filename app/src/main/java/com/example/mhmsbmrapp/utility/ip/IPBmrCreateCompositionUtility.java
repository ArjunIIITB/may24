package com.example.mhmsbmrapp.utility.ip;

import android.util.Log;

import com.example.mhmsbmrapp.Login.GlobalVariables;
import com.example.mhmsbmrapp.model.Composition;
import com.example.mhmsbmrapp.model.ip.IPBmr;
import com.example.mhmsbmrapp.utility.PatientUtility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class IPBmrCreateCompositionUtility {
    OkHttpClient client = new OkHttpClient();

    public JSONObject createCompositionEHRC_Mental_capacityv0(IPBmr ip, JSONObject compositionCopy, String loginToken, String sessionToken, String personId) {

        final String RELATIVE_PATH = "createComposition/";

        String templateId = "EHRC - Mental capacity.v0";

        //String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587238304431#93780176#874";
        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String time = sdf.format(System.currentTimeMillis());
        System.out.println(time);
        String time2 = sdf.format(System.currentTimeMillis()+(1000*60*60*24*7));
        //2020-05-12T10:47:56.393Z


        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {
            JSONObject composition = new JSONObject(compositionCopy.toString());

            jsonObject.put("authorization", sessionToken);

            composition.put("/content[openEHR-EHR-EVALUATION.mental_capacity.v0]/data[at0001]/items[at0002]|value", ip.getSectionOfAdmission());
            composition.put("/content[openEHR-EHR-EVALUATION.mental_capacity.v0]/data[at0001]/items[at0003]|value", "Independent Admission");
            composition.put("/content[openEHR-EHR-EVALUATION.mental_capacity.v0]/data[at0001]/items[at0004]|value", ip.getCourseInHospital());
            composition.put("/content[openEHR-EHR-INSTRUCTION.service_request.v1]/activities[at0001]/description[at0009]/items[at0040]|value", time+"::"+time2);


            jsonObject.put("composition", composition);

            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", personId);
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
            if(response.code() != 200)
                return null;
            ResponseBody rb = response.body();
            returnObject = new JSONObject(rb.string());

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //createCompositionEHRC_Mental_capacityv0 (POST)

    public JSONObject createCompositionEHRC_Complaintsv0(IPBmr ip, JSONObject compositionCopy, String loginToken, String sessionToken, String personId) {

        final String RELATIVE_PATH = "createComposition/";

        String templateId = "EHRC - Complaints.v0";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String time = sdf.format(System.currentTimeMillis());
        //System.out.println(time);

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {
            JSONObject composition = new JSONObject(compositionCopy.toString());

            jsonObject.put("authorization", sessionToken);

            composition.put("/content[openEHR-EHR-OBSERVATION.story.v1]/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign.v1]/items[at0001]|value", ip.getComplaints());
            composition.put("/content[openEHR-EHR-OBSERVATION.story.v1]/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.symptom_sign.v1]/items[at0003]|value", ip.getDuration());
            composition.put("/content[openEHR-EHR-OBSERVATION.story.v1]/data[at0001]/events[at0002]/time|value", time);
            composition.put("/content[openEHR-EHR-OBSERVATION.story.v1]/data[at0001]/origin|value", time);

            jsonObject.put("composition", composition);

            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", personId);
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
            if(response.code() != 200)
                return null;
            ResponseBody rb = response.body();
            Log.e("why is it null", "");
            returnObject = new JSONObject(rb.string());

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //createCompositionEHRC_Complaintsv0 (POST)

    public JSONObject createCompositionEHRC_Vitalsv0(IPBmr ip, JSONObject compositionCopy, String loginToken, String sessionToken, String personId) {

        final String RELATIVE_PATH = "createComposition/";

        String templateId = "EHRC - Vitals.v0";

        //String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587238304431#93780176#874";

        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();
        String time = "2020-04-23T11:38:01.109Z";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {
            JSONObject composition = new JSONObject(compositionCopy.toString());
            jsonObject.put("authorization", sessionToken);

            composition.put("/content[openEHR-EHR-SECTION.vital_signs.v0]/items[openEHR-EHR-EVALUATION.clinical_synopsis.v1]/data[at0001]/items[at0002]|value", ip.getSummaryOfVitals());

            jsonObject.put("composition", composition);

            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", personId);
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
            if(response.code() != 200)
                return null;
            ResponseBody rb = response.body();
            Log.e("why is it null", "");
            returnObject = new JSONObject(rb.string());

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //createCompositionEHRC_Vitalsv0 (POST)

    public JSONObject createCompositionEHRC_Physical_examinationv0(IPBmr ip, JSONObject compositionCopy, String loginToken, String sessionToken, String personId) {

        final String RELATIVE_PATH = "createComposition/";

        String templateId = "EHRC - Physical examination.v0";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {
            JSONObject composition = new JSONObject(compositionCopy.toString());

            jsonObject.put("authorization", sessionToken);

            System.out.println(ip.getSummaryOfGenPhyExam());
            composition.put("/content[openEHR-EHR-OBSERVATION.exam.v1]/data[at0001]/events[at0002]/data[at0003]/items[at0004]|value", ip.getSummaryOfGenPhyExam());

            jsonObject.put("composition", composition);

            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", personId);
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
            if(response.code() != 200)
                return null;
            ResponseBody rb = response.body();
            Log.e("why is it null", "");
            returnObject = new JSONObject(rb.string());

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //createCompositionEHRC_Physical_examinationv0 (POST)

    public JSONObject createCompositionEHRC_Mental_examinationv0(IPBmr ip, JSONObject compositionCopy, String loginToken, String sessionToken, String personId) {

        final String RELATIVE_PATH = "createComposition/";

        String templateId = "EHRC - Mental examination.v0";

        //String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587238304431#93780176#874";

        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();
        String time = "2020-04-23T11:38:01.109Z";


        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {
            JSONObject composition = new JSONObject(compositionCopy.toString());
            jsonObject.put("authorization", sessionToken);

            composition.put("/content[openEHR-EHR-OBSERVATION.mental_examinaton.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0006]|value", ip.getSummaryOfMenStatusExam());

            jsonObject.put("composition", composition);

            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", personId);
            jsonObject.put("templateId", templateId);


        }catch (Exception e) {e.printStackTrace();}

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
            if(response.code() != 200)
                return null;
            ResponseBody rb = response.body();
            Log.e("why is it null", "");
            returnObject = new JSONObject(rb.string());

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //createCompositionEHRC_Mental_examinationv0 (POST)

    public JSONObject createCompositionEHRC_Lab_investigationsv0(IPBmr ip, JSONObject compositionCopy, String loginToken, String sessionToken, String personId) {

        final String RELATIVE_PATH = "createComposition/";

        String templateId = "EHRC - Lab investigations.v0";

        //String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587238304431#93780176#874";

        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();
        String time = "2020-04-23T11:38:01.109Z";


        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {

            JSONObject composition = new JSONObject();

            jsonObject.put("authorization", sessionToken);

            composition.put("/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0057]|value", ip.getSummaryOfInvestigation());

            jsonObject.put("composition", composition);

            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", personId);
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
            if(response.code() != 200)
                return null;
            ResponseBody rb = response.body();
            Log.e("why is it null", "");
            returnObject = new JSONObject(rb.string());

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //createCompositionEHRC_Lab_investigationsv0 (POST)

    public JSONObject createCompositionEHRC_Attachmentsv0(IPBmr ip, JSONObject compositionCopy, String loginToken, String sessionToken, String personId) {

        final String RELATIVE_PATH = "createComposition/";
        String templateId = "EHRC - Attachments.v0";

        //String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587238304431#93780176#874";
        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();
        String time = "2020-04-23T11:38:01.109Z";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {
            JSONObject composition = new JSONObject(compositionCopy.toString());

            jsonObject.put("authorization", sessionToken);

            composition.put("/content[openEHR-EHR-OBSERVATION.container.v0]/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.multimedia.v0]/items[at0002]|value", "Screenshot01.png");
            composition.put("/content[openEHR-EHR-OBSERVATION.container.v0]/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.multimedia.v0]/items[at0005]|value", "Past Prescription File");
            composition.put("/content[openEHR-EHR-OBSERVATION.container.v0]/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.multimedia.v0]/items[at0008]|value", "2fd76d3f-4f5d-48b7-93a9-47556e131cf7");

            jsonObject.put("composition", composition);

            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", personId);
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
            if(response.code() != 200)
                return null;
            ResponseBody rb = response.body();
            Log.e("why is it null", "");
            returnObject = new JSONObject(rb.string());

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //createCompositionEHRC_Attachmentsv0 (POST)

    public JSONObject createCompositionEHRC_Clinical_historyv0(IPBmr ip, JSONObject compositionCopy, String loginToken, String sessionToken, String personId) {

        final String RELATIVE_PATH = "createComposition/";

        String templateId = "EHRC - Clinical history.v0";

        //String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587238304431#93780176#874";

        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();
        String time = "2020-04-23T11:38:01.109Z";


        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {
            JSONObject composition = new JSONObject(compositionCopy.toString());

            jsonObject.put("authorization", sessionToken);

            composition.put("/content[openEHR-EHR-EVALUATION.clinical_history.v0]/data[at0001]/items[at0002]|value", ip.getHistoryOfIllness());

            jsonObject.put("composition", composition);

            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", personId);
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
            if(response.code() != 200)
                return null;
            ResponseBody rb = response.body();
            returnObject = new JSONObject(rb.string());

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //createCompositionEHRC_Clinical_historyv0 (POST)

    public JSONObject createCompositionEHRC_Past_prescriptionv0(IPBmr ip, JSONObject compositionCopy, String loginToken, String sessionToken, String personId) {

        final String RELATIVE_PATH = "createComposition/";
        String templateId = "EHRC - Past prescription.v0";

        //String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587238304431#93780176#874";
        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();
        String time = "2020-04-23T11:38:01.109Z";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {
            JSONObject composition = new JSONObject(compositionCopy.toString());

            jsonObject.put("authorization", sessionToken);

            composition.put("/content[openEHR-EHR-EVALUATION.medication_summary.v0]/data[at0001]/items[at0007]|value", ip.getPastPrescription());

            jsonObject.put("composition", composition);

            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", personId);
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
            if(response.code() != 200)
                return null;
            ResponseBody rb = response.body();
            Log.e("why is it null", "");
            returnObject = new JSONObject(rb.string());

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //createCompositionEHRC_Past_prescriptionv0 (POST)

    public JSONObject createCompositionEHRC_Medication_orderv0(IPBmr ip, JSONObject compositionCopy, String loginToken, String sessionToken, String personId) {

        final String RELATIVE_PATH = "createComposition/";
        String templateId = "EHRC - Medication order.v0";


        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {
            JSONObject composition = new JSONObject(compositionCopy.toString());

            jsonObject.put("authorization", sessionToken);

            composition.put("/content[openEHR-EHR-SECTION.medication_order_list.v0]/items[openEHR-EHR-INSTRUCTION.medication_order.v2]/activities[at0001]/description[at0002]/items[at0009]|value", ip.getRemarks());
            composition.put("/content[openEHR-EHR-SECTION.medication_order_list.v0]/items[openEHR-EHR-INSTRUCTION.medication_order.v2]/activities[at0001]/description[at0002]/items[at0070]|value", ip.getMedicineName()+"_"+ip.getDosage());
            composition.put("/content[openEHR-EHR-SECTION.medication_order_list.v0]/items[openEHR-EHR-INSTRUCTION.medication_order.v2]/activities[at0001]/description[at0002]/items[at0173]|value", " ");
            composition.put("/content[openEHR-EHR-SECTION.medication_order_list.v0]/items[openEHR-EHR-INSTRUCTION.medication_order.v2]/activities[at0001]/description[at0002]/items[openEHR-EHR-CLUSTER.therapeutic_direction.v1]/items[at0066]|value", "P"+ip.getMedDuration()+ip.getDurationType().charAt(0));
            composition.put("/content[openEHR-EHR-SECTION.medication_order_list.v0]/items[openEHR-EHR-INSTRUCTION.medication_order.v2]/activities[at0001]/description[at0002]/items[openEHR-EHR-CLUSTER.therapeutic_direction.v1]/items[openEHR-EHR-CLUSTER.dosage.v1]/items[openEHR-EHR-CLUSTER.timing_daily.v1]/items[at0027]|value", ip.getDosingTime());


            jsonObject.put("composition", composition);

            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", personId);
            jsonObject.put("templateId", templateId);

        }catch (Exception e) {e.printStackTrace();}

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
            if(response.code() != 200)
                return null;
            ResponseBody rb = response.body();
            returnObject = new JSONObject(rb.string());
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //createCompositionEHRC_Medication_orderv0 (POST)

    public JSONObject createCompositionWOTP(IPBmr ip, String loginToken, String sessionToken, String personId) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String time = sdf.format(System.currentTimeMillis());
        System.out.println(time);
        //2020-05-12T10:47:56.393Z
        //2020-05-11T08:29:06.031Z

        final String RELATIVE_PATH = "updatepatientWOTP/";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new PatientUtility().getPatient(loginToken, personId, sessionToken);
        System.out.println(jsonObject.toString());
        try{
            jsonObject.put("dateChanged", time);
            jsonObject.getJSONArray("physicalIdentificationMark").getJSONObject(0).put("mark", ip.getIdentificationMarks());
            jsonObject.put("token", sessionToken);
            System.out.println(jsonObject.toString());
        }catch (Exception e){e.printStackTrace();}

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
            if(response.code() != 200) {

                System.out.println(response.code());

            }
            ResponseBody rb = response.body();
            returnObject = new JSONObject(rb.string());
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    }


    public JSONObject createComposition_EHRC_Diagnosisv0(IPBmr ip, JSONObject compositionCopy, String loginToken, String sessionToken, String personId) {

        Composition returnComposition = null;
        final String RELATIVE_PATH = "createComposition/";

        String templateId = "EHRC - Diagnosis.v0";

        String time = "2020-04-23T11:38:01.109Z";


        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("authorization", sessionToken);

            JSONObject composition = getDiagnosisTemplate(loginToken);


            composition.put("ctx/language", "en");
            composition.put("diagnosis/composer|id", compositionCopy.getString("/composer|identifier"));
            composition.put("diagnosis/context/end_time", time);
            composition.put("diagnosis/context/health_care_facility|id", compositionCopy.getString("/context/health_care_facility|name"));
            composition.put("diagnosis/context/health_care_facility|name", compositionCopy.getString("/context/health_care_facility|name"));
            composition.put("diagnosis/context/start_time", time);
            composition.put("diagnosis/problem_diagnosis:0/diagnostic_certainty", ip.getDiagnosisType());
            composition.put("diagnosis/problem_diagnosis:0/problem_diagnosis_name|code", ip.getIcdCode());
            composition.put("diagnosis/problem_diagnosis:0/problem_diagnosis_name|value", ip.getIcdDescription());

            jsonObject.put("composition", composition);

            jsonObject.put("format", "FLAT");
            jsonObject.put("personId", personId);
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











    //_____________________________________________________________________________________________________________





    public JSONObject saveAllCompositions(List<Composition> compositionList, String loginToken, String sessionToken, String personId) {

        OkHttpClient client = new OkHttpClient();
        final String RELATIVE_PATH = "createIpVirtualFolder/";

        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject payload = new JSONObject();

        try {

            JSONObject jsonObject = new JSONObject();

            payload.put("orgUUID", "4748dafc-7464-4535-9cd0-29cc52efcc0f");
            payload.put("patientId", personId);
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
            payload.put("uuid", "98078d12-3ffa-4e58-8d27-80559cedb082");

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
            if(response.code() != 200)
                return null;
            ResponseBody rb = response.body();
            returnObject = new JSONObject(rb.string());

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;

    }


}
