package com.example.mhmsbmrapp.utility;



import android.util.Log;

import com.example.mhmsbmrapp.Login.GlobalVariables;
import com.example.mhmsbmrapp.Login.SessionInformation;
import com.example.mhmsbmrapp.model.Assessment;
import com.example.mhmsbmrapp.model.Composition;

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

public class AssessmentUtility {



    OkHttpClient client = new OkHttpClient();

    public List<Assessment> getHistory(String loginToken, String sessionToken, String patientId, String orgUUID) {
        JSONArray children;
        List<JSONObject> historyList = new ArrayList<>();
        List<Assessment> assessmentHistory = new ArrayList<>();
        try {
            //System.out.println(new BmrUtility().getVirtualFolderByPersonId(loginToken, patientId, orgUUID));
            children = new BmrUtility().getVirtualFolderByPersonId(loginToken, patientId, orgUUID).getJSONArray("children");


            for(int index = 0; index < children.length(); index++) {
                if(children.getJSONObject(index).getString("name").contains("Assessment") == false)
                    continue;
                JSONObject virtualFolder = children.getJSONObject(index).getJSONObject("virtualFolderData");
                JSONArray data = virtualFolder.getJSONArray("data");
                Assessment assessment = new Assessment();
                for(int i=0;i<data.length();i++) {
                    JSONObject object = data.getJSONObject(i);
                    if(object.getString("name").equals("psychological_assessment_matches_compositionIDs")){
                        String compositionUid = object.getString("compositionUid");
                        String templateId = object.getString("templateId");
                        String name = object.getString("name");
                        System.out.println();
                        historyList.add(getComposition(loginToken, sessionToken, name, compositionUid, templateId, patientId));
                        JSONObject composition = getComposition(loginToken, sessionToken, name, compositionUid, templateId, patientId);
                        assessment.setTestedLanguage(composition.getString("assessmentLanguage"));
                        assessment.setBackgroundInformation(composition.getString("backgroundInformation"));
                        assessment.setSalientBehaviouralObservation(composition.getString("silentBehaviouralObservation"));
                        assessment.setImpression(composition.getString("impression"));
                        assessment.setTestScale(composition.getString("testAdministered"));
                        assessment.setTestScores(composition.getString("score"));
                        assessment.setRecommendation(composition.getString("recommendation"));
                        assessment.setReliability(composition.getString("reliability"));
                        assessment.setAdequacy(composition.getString("adequacy"));
                        assessment.setInformant(composition.getString("informantName"));
                        assessment.setAssessorName(composition.getString("assessorName"));
                        assessment.setSupervisorName(composition.getString("supervisorName"));
                    }
                }


                System.out.println(assessment);
                assessmentHistory.add(assessment);
            }
        }catch(Exception e) {e.printStackTrace();}

        return assessmentHistory;

    } //getHistory()

    public JSONObject getComposition(String loginToken, String sessionToken, String name, String compositionIDList, String templateId, String patientId) {

        List<JSONObject> historyList = new ArrayList<JSONObject>();
        JSONObject composition = new JSONObject();
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

    public Composition createCompositionEHRC_Service_requestv0(String values[], String loginToken, String sessionToken, String patientId, String mheName) {
        Composition returnComposition = null;

        final String RELATIVE_PATH = "createComposition/";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String time = sdf.format(System.currentTimeMillis());
        //System.out.println(time);

        JSONObject jsonObject = new JSONObject();

        try {
            int index = 0;
            //System.out.println("******************************"+values[index]);
            JSONObject composition = new JSONObject();

                composition.put("/language", "en");
                composition.put("/territory", "IN");
                //composition.put("/composer|name", "prashant");
                composition.put("/composer|name", SessionInformation.userName);

                //composition.put("/content[openEHR-EHR-INSTRUCTION.service_request.v1]/activities[at0001]/description[at0009]/items[at0062]|value", "IQ Assessment");
                composition.put("/content[openEHR-EHR-INSTRUCTION.service_request.v1]/activities[at0001]/description[at0009]/items[at0062]|value", values[index++]);

                //composition.put("/content[openEHR-EHR-INSTRUCTION.service_request.v1]/activities[at0001]/description[at0009]/items[at0064]|value", "referral note three");
                composition.put("/content[openEHR-EHR-INSTRUCTION.service_request.v1]/activities[at0001]/description[at0009]/items[at0064]|value", values[index++]);

                //composition.put("/content[openEHR-EHR-INSTRUCTION.service_request.v1]/protocol[at0008]/items[openEHR-EHR-CLUSTER.organisation.v0]/items[at0005]/items[openEHR-EHR-CLUSTER.person_name.v0]/items[at0001]|value", "referred by three");
                composition.put("/content[openEHR-EHR-INSTRUCTION.service_request.v1]/protocol[at0008]/items[openEHR-EHR-CLUSTER.organisation.v0]/items[at0005]/items[openEHR-EHR-CLUSTER.person_name.v0]/items[at0001]|value", values[index++]);



                composition.put("/composer|identifier", SessionInformation.userUUID);
                composition.put("/context/health_care_facility|identifier", SessionInformation.orgUUID);

                //composition.put("/context/health_care_facility|name", "psm321op");
                composition.put("/context/health_care_facility|name", mheName);
                composition.put("/context/start_time", time);
                composition.put("/context/end_time", time);
                composition.put("/context/location", "Bengaluru");

            jsonObject.put("composition", composition);

            jsonObject.put("authorization", sessionToken);
            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", patientId);
            jsonObject.put("templateId", "EHRC - Service request.v0");


        }catch (Exception e) {}

        RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

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
            System.out.println("####################"+compositionUidObject);
            String compositionUid = compositionUidObject.getString("compositionUid");
            returnComposition = new Composition("service_request_matches_compositionIDs", "EHRC - Service request.v0","Referral", compositionUid);

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnComposition;
    } //createCompositionEHRC_Service_requestv0

    public Composition createCompositionEHRC_Psychological_assessmentv0(String values[], String loginToken, String sessionToken, String patientId, String mheName) {

        Composition returnComposition = null;
        final String RELATIVE_PATH = "createComposition/";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String time = sdf.format(System.currentTimeMillis());
        System.out.println(time);

        JSONObject jsonObject = new JSONObject();

        try {
            int index = 0;
            JSONObject composition = new JSONObject();

                composition.put("/language", "en");
                composition.put("/territory", "IN");
                //composition.put("/composer|name", "prashant");
                composition.put("/composer|name", SessionInformation.userName);

                //composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/data[at0001]/items[at0003]|value", "salient obeser ELEVEN ");
                composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/data[at0001]/items[at0003]|value", values[index++]);

                //composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/data[at0001]/items[at0004]|value", "back info ten");
                composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/data[at0001]/items[at0004]|value", values[index++]);

                //composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/data[at0001]/items[at0005]/items[at0006]|value", "test scale ELEVEN");
                composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/data[at0001]/items[at0005]/items[at0006]|value", values[index++]);

                //composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/data[at0001]/items[at0005]/items[at0007]|value", "test score ELEVEN");
                composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/data[at0001]/items[at0005]/items[at0007]|value", values[index++]);

                //composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/data[at0001]/items[at0008]|value", "imprssion ELEVEN");
                composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/data[at0001]/items[at0008]|value", values[index++]);

                //composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/data[at0001]/items[at0009]|value", "recomm ELEVEN");
                composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/data[at0001]/items[at0009]|value", values[index++]);

                //composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/data[at0001]/items[at0010]|value", "2020-05-02T16:39:42.536Z");

                composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/data[at0001]/items[at0010]|value", "2020-05-02T16:39:42.536Z");

                //composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/protocol[at0002]/items[at0011]|value", "Kannada");
                composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/protocol[at0002]/items[at0011]|value", values[index++]);

                //composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/protocol[at0002]/items[at0012]/items[at0013, 'Informant#0']/items[at0014]|value", "informant ELEVEN");
                composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/protocol[at0002]/items[at0012]/items[at0013, 'Informant#0']/items[at0014]|value", values[index++]);

                //composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/protocol[at0002]/items[at0012]/items[at0013, 'Informant#0']/items[at0015]|value", "informant relationship ELEVEN");
                composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/protocol[at0002]/items[at0012]/items[at0013, 'Informant#0']/items[at0015]|value", values[index++]);


                //composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/protocol[at0002]/items[at0012]/items[at0016]|value", "Yes");
                composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/protocol[at0002]/items[at0012]/items[at0016]|value", values[index++]);

                //composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/protocol[at0002]/items[at0012]/items[at0017]|value", "Yes");
                composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/protocol[at0002]/items[at0012]/items[at0017]|value", values[index++]);

                //composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/protocol[at0002]/items[at0018]/items[at0019]|value", "Prashant SinghPrashant SinghPrashant SinghPrashant");
                composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/protocol[at0002]/items[at0018]/items[at0019]|value", values[index++]);

                //composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/protocol[at0002]/items[at0018]/items[at0020]|value", "qualification ELEVEN");
                composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/protocol[at0002]/items[at0018]/items[at0020]|value", values[index++]);

                //composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/protocol[at0002]/items[at0021]/items[at0022]|value", "full name fouELEVENr");
                composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/protocol[at0002]/items[at0021]/items[at0022]|value", values[index++]);

                //composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/protocol[at0002]/items[at0021]/items[at0023]|value", "q ELEVEN");
                composition.put("/content[openEHR-EHR-EVALUATION.psychological_assessment.v0]/protocol[at0002]/items[at0021]/items[at0023]|value", values[index++]);



                composition.put("/composer|identifier", SessionInformation.userUUID);
                composition.put("/context/health_care_facility|identifier", SessionInformation.orgUUID);
                composition.put("/context/health_care_facility|name", mheName);
                composition.put("/context/start_time", time);
                composition.put("/context/end_time", time);
                composition.put("/context/location", "Bengaluru");

            jsonObject.put("composition", composition);

            jsonObject.put("authorization", sessionToken);
            jsonObject.put("format", "ECISFLAT");
            jsonObject.put("personId", patientId);
            jsonObject.put("templateId", "EHRC - Psychological assessment.v0");


        }catch (Exception e) {}

        RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

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
            System.out.println(compositionUidObject);
            String compositionUid = compositionUidObject.getString("compositionUid");
            returnComposition = new Composition("psychological_assessment_matches_compositionIDs", "EHRC - Psychological assessment.v0", compositionUid);

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnComposition;

    } //createCompositionEHRC_Psychological_assessmentv0

    public JSONObject updatepatientWOTP(String values[], String loginToken, String sessionToken, String patientId) {
        final String RELATIVE_PATH = "updatepatientWOTP/";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject patient = getPatient(loginToken, sessionToken, patientId);
        try {
            System.out.println("before ----->"+patient);
            patient.getJSONArray("qualification").getJSONObject(0).put("qualification", "updated qualification");
            patient.getJSONArray("qualification").getJSONObject(0).put("specialization", "updated specialization");
            System.out.println("after ----->"+patient);
            patient.put("token", sessionToken);
        }catch (Exception e) {}

        RequestBody formBody = RequestBody.create(JSON, patient.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+loginToken)
                .build();
        JSONObject returnObject = null;
        Response response = null;
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

    public JSONObject saveAllAssessmentCompositions(List<Composition> compositionList, String loginToken, String sessionToken, String orgUUID, String patientId, String userUUID) {
        final String RELATIVE_PATH = "saveAllAssessmentCompositions/Assessment";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject payload = new JSONObject();

        try {

            payload.put("orgUUID", orgUUID);
            payload.put("patientId", patientId);
            payload.put("token", sessionToken);

            JSONArray uidata = new JSONArray();
            for(int i=0;i< compositionList.size();i++) {
                JSONObject composition = new JSONObject();

                Composition object = compositionList.get(i);
                composition.put("compositionUid", object.getCompositionUid());
                composition.put("name", object.getName());
                composition.put("templateId", object.getTemplateId());
                //composition.put("time", "1587358504256");
                if(object.getTemplateId().equals("EHRC - Service request.v0")) {
                    System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                    System.out.println(object.toString());
                    System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                    composition.put("serviceRequest", object.getServiceRequest());
                }
                uidata.put(i, composition);
            }

            payload.put("uidata", uidata);
            payload.put("uuid", userUUID);

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
            returnObject = new JSONObject(rb.string());


        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //saveAllAssessmentCompositions

    public JSONObject getPatient(String loginToken, String sessionToken, String patientId) {
        final String RELATIVE_PATH = "getpatient/";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("patientId", patientId);
            jsonObject.put("token", sessionToken);
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
            Log.e("why is it null", "");
            returnObject = new JSONObject(rb.string());
            System.out.println(returnObject);
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //getPatient

    public Assessment getVisitRecord(String loginToken, String sessionToken, String patientId, JSONObject visit) {
        Assessment assessment = new Assessment();
        try {
            JSONObject virtualFolder = visit.getJSONObject("virtualFolderData");
            JSONArray data = virtualFolder.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject object = data.getJSONObject(i);
                if (object.getString("name").equals("psychological_assessment_matches_compositionIDs")) {
                    String compositionUid = object.getString("compositionUid");
                    String templateId = object.getString("templateId");
                    String name = object.getString("name");
                    JSONObject composition = getComposition(loginToken, sessionToken, name, compositionUid, templateId, patientId);
                    assessment.setTestedLanguage(composition.getString("assessmentLanguage"));
                    assessment.setBackgroundInformation(composition.getString("backgroundInformation"));
                    assessment.setSalientBehaviouralObservation(composition.getString("silentBehaviouralObservation"));
                    assessment.setImpression(composition.getString("impression"));
                    assessment.setTestScale(composition.getString("testAdministered"));
                    assessment.setTestScores(composition.getString("score"));
                    assessment.setRecommendation(composition.getString("recommendation"));
                    assessment.setReliability(composition.getString("reliability"));
                    assessment.setAdequacy(composition.getString("adequacy"));
                    assessment.setInformant(composition.getString("informantName"));
                    assessment.setAssessorName(composition.getString("assessorName"));
                    assessment.setSupervisorName(composition.getString("supervisorName"));
                }
            }

        }catch (Exception e){e.printStackTrace();}
        return assessment;
    }





    public JSONObject getIPpatient(String loginToken, String patientId) {
        final String RELATIVE_PATH = "getIPpatient/"+patientId;

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
            //jsonObjectResult = new JSONObject(rb.string());
            System.out.println("why is it null-----------------------------------"+returnObject);

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //getIPpatient

    public JSONObject updateIPPatientQueue(String loginToken, String patientId) {
        final String RELATIVE_PATH = "updateIPPatientQueue/";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = getIPpatient(loginToken, patientId);
        try {
            jsonObject.put("admissionStatus", "Completed");
            jsonObject.put("assessmentStatus", "Assessment");
        }catch (Exception e) {}

        RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+loginToken)
                .build();
        JSONObject returnObject = null;
        Response response = null;
        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            Log.e("why is it null", "");
            returnObject = new JSONObject(rb.string());
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //updateIPPatientQueue


}
