package com.example.mhmsbmrapp.utility;

import com.example.mhmsbmrapp.Login.GlobalVariables;
import com.example.mhmsbmrapp.model.Composition;
import com.example.mhmsbmrapp.model.Therapy;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class TherapyUtility {

    private OkHttpClient client = new OkHttpClient();


    public JSONObject postCall(String loginToken) {
        final String RELATIVE_PATH = "getpatient/";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("patientId", "");
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
            ResponseBody rb = response.body();
            returnObject = new JSONObject(rb.string());
            System.out.println(returnObject);
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    }

    public JSONObject getCall(String loginToken) {
        final String RELATIVE_PATH = "getpatient/";


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
            returnObject = new JSONObject(rb.string());
            System.out.println(returnObject);
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    }

    public List<Therapy> getHistory(String loginToken, String sessionToken, String patientId, String orgUUID) {
        JSONArray children;
        List<JSONObject> historyList = new ArrayList<>();
        List<Therapy> therapyList = new ArrayList<>();
        try {

            children = new BmrUtility().getVirtualFolderByPersonId(loginToken, patientId, orgUUID).getJSONArray("children");

            for(int index = 0; index < children.length(); index++) {
                if(children.getJSONObject(index).getString("name").contains("Therapy") == false)
                    continue;
                JSONObject virtualFolder = children.getJSONObject(index).getJSONObject("virtualFolderData");
                JSONArray data = virtualFolder.getJSONArray("data");
                Therapy therapy = new Therapy();
                for(int i=0;i<data.length();i++) {

                    JSONObject object = data.getJSONObject(i);
                    //System.out.println(object);
                    if(object.getString("name").equals("therapy_reporting_matches_compositionIDs")){
                        String compositionUid = object.getString("compositionUid");
                        String templateId = object.getString("templateId");
                        String name = object.getString("name");
                        if(getComposition(loginToken, sessionToken, name, compositionUid, templateId, patientId) == null) {
                            System.out.println("NOT OK");
                            continue;
                        }
                        else
                            System.out.println("OK");
                        //historyList.add(getComposition(loginToken, sessionToken, name, compositionUid, templateId, patientId));
                        JSONObject composition = getComposition(loginToken, sessionToken, name, compositionUid, templateId, patientId);
                        therapy.setKeyIssue(composition.getString("keyIssue"));
                        therapy.setObjectiveSession(composition.getString("objective"));
                        therapy.setTherapistObservation(composition.getString("observationReflection"));
                        therapy.setSessionNumber(composition.getString("sessionNumber"));
                        therapy.setSupervisorName(composition.getString("supervisorName"));
                        therapy.setSupervisorQualification(composition.getString("supervisorQualification"));
                        therapy.setTherapistName(composition.getString("therapistName"));
                        therapy.setTherapistQualification(composition.getString("therapistQualification"));
                        therapy.setTherapyMethod(composition.getString("therapyMethod"));
                        therapy.setTherapyTechnique(composition.getString("therapyTechnique"));
                        therapy.setTypeOfTherapy(composition.getString("therapyType"));
                    }
                    else if(object.getString("name").equals("diagnosis_matches_compositionIDs")){
                        String compositionUid = object.getString("compositionUid");
                        String templateId = object.getString("templateId");
                        String name = object.getString("name");
                        //historyList.add(getComposition(loginToken, sessionToken, name, compositionUid, templateId, patientId));
                        if(getComposition(loginToken, sessionToken, name, compositionUid, templateId, patientId) == null) {
                            System.out.println("NOT OK");
                            continue;
                        }
                        else
                            System.out.println("OK");
                        JSONObject composition = getComposition(loginToken, sessionToken, name, compositionUid, templateId, patientId);
                        therapy.setPsychiatricDiagnosis(composition.getString("problemDiagnosis"));

                    }
                }
                System.out.println(therapy.toString());
                therapyList.add(therapy);

            }

        }catch(Exception e) {e.printStackTrace();}

        return therapyList;
    } //getHistory()

    public JSONObject getComposition(String loginToken, String sessionToken, String name, String compositionIDList, String templateId, String patientId) {

        List<JSONObject> historyList = new ArrayList<JSONObject>();
        JSONObject composition = null;
        final String RELATIVE_PATH = "getComposition/";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObjectResult = null;

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("name", name);
            jsonObject.put("personId", patientId);
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
            jsonObjectResult = new JSONObject(rb.string());
                composition = jsonObjectResult.getJSONArray("resultSet").getJSONObject(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return composition;
    } //getComposition (POST)

    public JSONObject getDiagnosisTemplate(String loginToken) {
        final String RELATIVE_PATH = "diagnosisTemplate/";


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
            returnObject = new JSONObject(rb.string());
            //System.out.println(returnObject);
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //getDiagnosisTemplate (GET)

    public JSONObject getFollowUpTemplate(String loginToken) {
        final String RELATIVE_PATH = "followUpTemplate/";

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
            returnObject = new JSONObject(rb.string());
            //System.out.println(returnObject);
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //getFollowUpTemplate (GET)

    public JSONObject getTherapyTemplate(String loginToken) {
        final String RELATIVE_PATH = "therapyTemplate/";

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
            returnObject = new JSONObject(rb.string());
            //System.out.println(returnObject);
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //getTherapyTemplate (GET)

    public Composition createCompositionEHRC_Diagnosisv0(String values[], String loginToken, String sessionToken, String userUUID, String orgUUID, String patientId) {
        Composition returnComposition = null;
        final String RELATIVE_PATH = "createComposition/";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject payload = new JSONObject();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            String time = sdf.format(System.currentTimeMillis());
            System.out.println(time);
            int index = 0;
            payload.put("authorization", sessionToken);
            JSONObject composition = new TherapyUtility().getDiagnosisTemplate(loginToken);
                composition.put("diagnosis/composer|id", userUUID);
                composition.put("diagnosis/composer|name", values[index++]);
                composition.put("diagnosis/context/end_time", time);
                composition.put("diagnosis/context/health_care_facility|id", orgUUID);
                composition.put("diagnosis/context/health_care_facility|name", values[index++]);
                composition.put("diagnosis/context/start_time", time);
                composition.put("diagnosis/problem_diagnosis:0/diagnostic_certainty", "Psychiatric Diagnosis");
                composition.put("diagnosis/problem_diagnosis:0/problem_diagnosis_name|code", "Undefined ICD Code");
                composition.put("diagnosis/problem_diagnosis:0/problem_diagnosis_name|value", values[index]);

            payload.put("composition", composition);
            payload.put("format", "FLAT");
            payload.put("personId", patientId);
            payload.put("templateId", "EHRC - Diagnosis.v0");

            //System.out.println(payload.toString());
        }catch(Exception e) {e.printStackTrace();}

        RequestBody formBody = RequestBody.create(JSON, payload.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+loginToken)
                .build();

        Response response = null;
        JSONObject compositionUidObject = null;
        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            //Log.e("why is it null", "");
            compositionUidObject = new JSONObject(rb.string());
            System.out.println("#########################"+compositionUidObject.toString());
            String compositionUid = compositionUidObject.getString("compositionUid");
            returnComposition = new Composition("diagnosis_matches_compositionIDs", "EHRC - Diagnosis.v0", compositionUid);

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnComposition;
    } //createCompositionEHRC_Diagnosisv0

    public Composition createCompositionEHRC_Service_requestv0(String values[], String loginToken, String sessionToken, String userUUID, String orgUUID, String patientId) {
        Composition returnComposition = null;
        final String RELATIVE_PATH = "createComposition/";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject payload = new JSONObject();
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            String time = sdf.format(System.currentTimeMillis());
            System.out.println(time);

            payload.put("authorization", sessionToken);
            JSONObject composition = new TherapyUtility().getFollowUpTemplate(loginToken);
                int index = 0;
                composition.put("/language", "en");
                composition.put("/territory", "IN");
                composition.put("/composer|identifier", userUUID);
                composition.put("diagnosis/composer|name", values[index++]);
                composition.put("/content[openEHR-EHR-INSTRUCTION.service_request.v1]/activities[at0001]/description[at0009]/items[at0040]|value", values[index++]);
                composition.put("/content[openEHR-EHR-INSTRUCTION.service_request.v1]/activities[at0001]/description[at0009]/items[at0121]|value", values[index++]);

                composition.put("/context/end_time", time);
                //composition.put("/context/health_care_facility|identifier", orgUUID);
                composition.put("diagnosis/context/health_care_facility|id", orgUUID);
                composition.put("/context/health_care_facility|name", values[index]);
                composition.put("/context/location", "Karnataka");

                composition.put("/context/start_time", time);

            payload.put("composition", composition);
            payload.put("format", "ECISFLAT");
            payload.put("personId", patientId);
            payload.put("templateId", "EHRC - Service request.v0");
        }catch(Exception e) {e.printStackTrace();}
        //System.out.println("payload is " + payload.toString());
        RequestBody formBody = RequestBody.create(JSON, payload.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+loginToken)
                .build();

        Response response = null;
        JSONObject compositionUidObject = null;
        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            //Log.e("why is it null", "");
            compositionUidObject = new JSONObject(rb.string());
            System.out.println("#########################"+compositionUidObject.toString());
            String compositionUid = compositionUidObject.getString("compositionUid");
            returnComposition = new Composition("service_request_matches_compositionIDs", "EHRC - Service request.v0", "Followup",compositionUid);

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnComposition;
    } //createCompositionEHRC_Service_requestv0

    public Composition createCompositionEHRC_Proceduresv2(String values[], String loginToken, String sessionToken, String patientId) {
        Composition returnComposition = null;
        final String RELATIVE_PATH = "createComposition/";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject payload = new JSONObject();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            String time = sdf.format(System.currentTimeMillis());
            System.out.println(time);

            int index = 0;
            payload.put("authorization", sessionToken);
            JSONObject composition = new TherapyUtility().getTherapyTemplate(loginToken);

                //composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0005]|value", "therapy used ELEVEN");
                composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0005]|value", values[index++]);
                //composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0014]|value", "objective of session ELEVEN");
                composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0014]|value", values[index++]);
                //composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0048]|value", "therapist reflection ELEVEN");
                composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0048]|value", values[index++]);
                //composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0049]|value", "key issue ELEVEN");
                composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0049]|value", values[index++]);
                //composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0065]|value", "Individual ELEVEN");
                composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0065]|value", values[index++]);
                //composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0067]|value", "therapy ELEVEN");
                composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0067]|value", values[index++]);
                //composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/protocol[at0053]/items[openEHR-EHR-CLUSTER.provider-supervisor.v0]/items[at0002]|value", "full name ELEVEN");
                composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/protocol[at0053]/items[openEHR-EHR-CLUSTER.provider-supervisor.v0]/items[at0002]|value", values[index++]);
                //composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/protocol[at0053]/items[openEHR-EHR-CLUSTER.provider-supervisor.v0]/items[at0003]|value", "qualification ELEVEN");
                composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/protocol[at0053]/items[openEHR-EHR-CLUSTER.provider-supervisor.v0]/items[at0003]|value", values[index++]);
                //composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/protocol[at0053]/items[openEHR-EHR-CLUSTER.provider.v0]/items[at0002]|value", "Prashant SinghPrashant SinghPrashant SinghPrashant");
                composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/protocol[at0053]/items[openEHR-EHR-CLUSTER.provider.v0]/items[at0002]|value", values[index++]);
                //composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/protocol[at0053]/items[openEHR-EHR-CLUSTER.provider.v0]/items[at0003]|value", "qualification ELEVEN");
                composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/protocol[at0053]/items[openEHR-EHR-CLUSTER.provider.v0]/items[at0003]|value", values[index++]);
                //composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/protocol[at0053]/items[openEHR-EHR-CLUSTER.sequence.v0]/items[at0001]|value", "101");
                composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/protocol[at0053]/items[openEHR-EHR-CLUSTER.sequence.v0]/items[at0001]|value", values[index++]);
                composition.put("/context/end_time", time);
                composition.put("/context/location", "Karnataka");
                composition.put("/context/start_time", time);


            payload.put("composition", composition);
            payload.put("format", "ECISFLAT");
            payload.put("personId", patientId);
            payload.put("templateId", "EHRC - Procedures.v2");
        }catch(Exception e) {e.printStackTrace();}

        RequestBody formBody = RequestBody.create(JSON, payload.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+loginToken)
                .build();

        Response response = null;
        JSONObject compositionUidObject = null;
        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            //Log.e("why is it null", "");
            compositionUidObject = new JSONObject(rb.string());
            System.out.println("#########################"+compositionUidObject.toString());

            String compositionUid = compositionUidObject.getString("compositionUid");
            returnComposition = new Composition("therapy_reporting_matches_compositionIDs", "EHRC - Procedures.v2",compositionUid);

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnComposition;
    } //createCompositionEHRC_Proceduresv2

    public JSONObject saveAllAssessmentCompositionsTherapy(List<Composition> compositionList, String loginToken, String sessionToken, String orgUUID, String patientId, String userUUID) {
        final String RELATIVE_PATH = "saveAllAssessmentCompositions/Therapy";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");



        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orgUUID", orgUUID);
            jsonObject.put("patientId", patientId);
            jsonObject.put("token", sessionToken);
            JSONArray uidata = new JSONArray();

            for(int i=0;i< compositionList.size();i++) {
                JSONObject composition = new JSONObject();

                Composition object = compositionList.get(i);
                composition.put("compositionUid", object.getCompositionUid());
                composition.put("name", object.getName());
                composition.put("templateId", object.getTemplateId());
                //composition.put("time", "1587358504256");
                if(object.getTemplateId().equals("EHRC - Service request.v0")) {
                    //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                    //System.out.println(object.toString());
                    //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                    composition.put("serviceRequest", object.getServiceRequest());
                }
                uidata.put(i, composition);
            }
            jsonObject.put("uidata", uidata);
            jsonObject.put("uuid", userUUID);

            //System.out.println(jsonObject.toString());

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
            ResponseBody rb = response.body();
            returnObject = new JSONObject(rb.string());
            System.out.println(returnObject);
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //saveAllAssessmentCompositionsTherapy

    public Therapy getVisitRecord(String loginToken, String sessionToken, String patientId, JSONObject visit) {
        Therapy therapy = new Therapy();
        try {
            JSONObject virtualFolder = visit.getJSONObject("virtualFolderData");
            JSONArray data = virtualFolder.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {

                JSONObject object = data.getJSONObject(i);
                //System.out.println(object);
                if (object.getString("name").equals("therapy_reporting_matches_compositionIDs")) {
                    String compositionUid = object.getString("compositionUid");
                    String templateId = object.getString("templateId");
                    String name = object.getString("name");
                    if (getComposition(loginToken, sessionToken, name, compositionUid, templateId, patientId) == null) {
                        System.out.println("NOT OK");
                        continue;
                    } else
                        System.out.println("OK");
                    //historyList.add(getComposition(loginToken, sessionToken, name, compositionUid, templateId, patientId));
                    JSONObject composition = getComposition(loginToken, sessionToken, name, compositionUid, templateId, patientId);
                    therapy.setKeyIssue(composition.getString("keyIssue"));
                    therapy.setObjectiveSession(composition.getString("objective"));
                    therapy.setTherapistObservation(composition.getString("observationReflection"));
                    therapy.setSessionNumber(composition.getString("sessionNumber"));
                    therapy.setSupervisorName(composition.getString("supervisorName"));
                    therapy.setSupervisorQualification(composition.getString("supervisorQualification"));
                    therapy.setTherapistName(composition.getString("therapistName"));
                    therapy.setTherapistQualification(composition.getString("therapistQualification"));
                    therapy.setTherapyMethod(composition.getString("therapyMethod"));
                    therapy.setTherapyTechnique(composition.getString("therapyTechnique"));
                    therapy.setTypeOfTherapy(composition.getString("therapyType"));
                } else if (object.getString("name").equals("diagnosis_matches_compositionIDs")) {
                    String compositionUid = object.getString("compositionUid");
                    String templateId = object.getString("templateId");
                    String name = object.getString("name");
                    //historyList.add(getComposition(loginToken, sessionToken, name, compositionUid, templateId, patientId));
                    if (getComposition(loginToken, sessionToken, name, compositionUid, templateId, patientId) == null) {
                        System.out.println("NOT OK");
                        continue;
                    } else
                        System.out.println("OK");
                    JSONObject composition = getComposition(loginToken, sessionToken, name, compositionUid, templateId, patientId);
                    therapy.setPsychiatricDiagnosis(composition.getString("problemDiagnosis"));

                }
            }
        }catch (Exception e) {e.printStackTrace();}
        return therapy;
    }

}
