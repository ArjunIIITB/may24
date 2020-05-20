package com.example.mhmsbmrapp.utility;

import com.example.mhmsbmrapp.Login.GlobalVariables;
import com.example.mhmsbmrapp.model.Composition;
import com.example.mhmsbmrapp.model.RestraintMonitoring;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RestraintMonitoringUtility {

    OkHttpClient client = new OkHttpClient();


    public List<RestraintMonitoring> getHistory(String loginToken, String sessionToken, String patientId, String orgUUID) {
        JSONArray children;
        List<RestraintMonitoring> historyList = new ArrayList<>();
        List<RestraintMonitoring> restraintMonitoringList = new ArrayList<>();
        try {

            children = new BmrUtility().getVirtualFolderByPersonId(loginToken, patientId, orgUUID).getJSONArray("children");

            for(int index = 0; index < children.length(); index++) {
                if(children.getJSONObject(index).getString("name").contains("Restraint") == false)
                    continue;
                JSONObject virtualFolder = children.getJSONObject(index).getJSONObject("virtualFolderData");
                JSONArray data = virtualFolder.getJSONArray("data");
                RestraintMonitoring restraintMonitoring = new RestraintMonitoring();
                for(int i=0;i<data.length();i++) {

                    JSONObject object = data.getJSONObject(i);
                    //System.out.println(object);
                    try {
                        if (object.getString("name").equals("restraint_monitoring_matches_compositionIDs")) {
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
                            restraintMonitoring.setBreathingProblem(composition.getString("breathingProblems"));
                            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+composition.getString("breathingProblems"));
                            restraintMonitoring.setInjuries2(composition.getString("injuries"));
                            restraintMonitoring.setNoBloodSupply(composition.getString("noBloodSupplyToLimbs"));
                            restraintMonitoring.setMonitoringDate(composition.getString("observationTime"));
                            restraintMonitoring.setPulse(composition.getString("pulse"));
                            restraintMonitoring.setRespiratoryRate(composition.getString("respiratoryRate"));
                            restraintMonitoring.setTemperature(composition.getString("temperature"));

                        } else if (object.getString("name").equals("restraint_application_matches_compositionIDs")) {
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
                            restraintMonitoring.setOthers(composition.getString("complication"));
                            restraintMonitoring.setDuration(composition.getString("duration"));
                            restraintMonitoring.setInchargePsychiatrist(composition.getString("inChargePsychiatrist"));
                            restraintMonitoring.setInformedToNR(composition.getString("intimation"));
                            restraintMonitoring.setNominatedRepresentativeName(composition.getString("intimationName"));
                            restraintMonitoring.setSetting(composition.getString("programContext"));
                            restraintMonitoring.setReason(composition.getString("reason"));
                        }
                    }catch (Exception e) {e.printStackTrace();}
                }//for
                System.out.println("jfldksjflkjasdlfkjaldkfjlksdjf"+restraintMonitoring.toString());
                restraintMonitoringList.add(restraintMonitoring);

            }

        }catch(Exception e) {e.printStackTrace();}

        return restraintMonitoringList;
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
        //System.out.println(composition.toString());
        return composition;
    } //getComposition (POST)

    public JSONObject getrMonitoringTemplate(String loginToken) {
        final String RELATIVE_PATH = "rMonitoringTemplate/";


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
    } //getrMonitoringTemplate (GET)

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
            System.out.println(returnObject);
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    } //getDiagnosisTemplate (GET)

    public JSONObject getrMonitoringApplicationTemplate(String loginToken) {
        final String RELATIVE_PATH = "rMonitoringApplicationTemplate/";


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
    } //getrMonitoringApplicationTemplate (GET)

    public Composition createCompositionEHRC_Restraint_monitoringv3(String values[], String loginToken, String sessionToken, String userUUID, String orgUUID, String mheName, String patientId) {

        Composition returnComposition = null;
        final String RELATIVE_PATH = "createComposition/";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject payload = new JSONObject();
        try {
            int index = 0;
            payload.put("authorization", sessionToken);
            JSONObject composition = new RestraintMonitoringUtility().getrMonitoringTemplate(loginToken);
                //composition.put("restraint_monitoring/composer|id", "775b8c3e-6742-4b30-b443-c7d6aa6ec4ac");
            composition.put("restraint_monitoring/composer|id", userUUID);
                composition.put("restraint_monitoring/context/end_time", "2020-05-04T11:45:26.273Z");
                //composition.put("restraint_monitoring/context/health_care_facility|id", "4cc74280-efe5-4016-b41e-f29472a4ec12");
            composition.put("restraint_monitoring/context/health_care_facility|id", orgUUID);
                //composition.put("restraint_monitoring/context/health_care_facility|name", "psm321op");
            composition.put("restraint_monitoring/context/health_care_facility|name", mheName);
                //composition.put("restraint_monitoring/context/start_time", "2020-05-04T11:45:26.273Z");
            composition.put("restraint_monitoring/context/start_time", "2020-05-04T11:45:26.273Z");

                //composition.put("restraint_monitoring/restraint_monitoring:0/observations:0/breathing_problems", true);
            composition.put("restraint_monitoring/restraint_monitoring:0/observations:0/breathing_problems", values[index++]);
                //composition.put("restraint_monitoring/restraint_monitoring:0/observations:0/injuries:0", "injuries 21");
            composition.put("restraint_monitoring/restraint_monitoring:0/observations:0/injuries:0", values[index++]);
                //composition.put("restraint_monitoring/restraint_monitoring:0/observations:0/no_blood_supply_to_limbs", true);
            composition.put("restraint_monitoring/restraint_monitoring:0/observations:0/no_blood_supply_to_limbs", values[index++]);
                //composition.put("restraint_monitoring/restraint_monitoring:0/observations:0/pulse|magnitude", "18");
            composition.put("restraint_monitoring/restraint_monitoring:0/observations:0/pulse|magnitude", values[index++]);
                composition.put("restraint_monitoring/restraint_monitoring:0/observations:0/pulse|unit", "1/min");
                //composition.put("restraint_monitoring/restraint_monitoring:0/observations:0/respiratory_rate|magnitude", "20");
            composition.put("restraint_monitoring/restraint_monitoring:0/observations:0/respiratory_rate|magnitude", values[index++]);
                composition.put("restraint_monitoring/restraint_monitoring:0/observations:0/respiratory_rate|unit", "1/min");
                //composition.put("restraint_monitoring/restraint_monitoring:0/observations:0/temperature|magnitude", "19");
            composition.put("restraint_monitoring/restraint_monitoring:0/observations:0/temperature|magnitude", values[index++]);
                composition.put("restraint_monitoring/restraint_monitoring:0/observations:0/temperature|unit", "°F");
                //composition.put("restraint_monitoring/restraint_monitoring:0/observations:0/time_of_observation", "17/04/2020");
            composition.put("restraint_monitoring/restraint_monitoring:0/observations:0/time_of_observation", values[index]);


            payload.put("composition", composition);
            payload.put("format", "FLAT");
            payload.put("personId", patientId);
            payload.put("templateId", "EHRC - Restraint monitoring.v3");

            System.out.println("0000000000000000"+payload.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestBody formBody = RequestBody.create(JSON, payload.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST + RELATIVE_PATH)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + loginToken)
                .build();

        Response response = null;
        JSONObject compositionUidObject = null;
        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            //Log.e("why is it null", "");
            compositionUidObject = new JSONObject(rb.string());
            System.out.println(compositionUidObject.toString());
            String compositionUid = compositionUidObject.getString("compositionUid");
            returnComposition = new Composition("restraint_monitoring_matches_compositionIDs", "EHRC - Restraint monitoring.v3", compositionUid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnComposition;
    } //createCompositionEHRC_Restraint_monitoringv3

    public Composition createDiagnosisTemplate(String values[], String loginToken, String sessionToken, String userUUID, String orgUUID, String mheName, String patientId) {
        Composition returnComposition = null;
        final String RELATIVE_PATH = "createComposition/";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject payload = new JSONObject();
        try {
            int index = 0;
            payload.put("authorization", sessionToken);
            JSONObject composition = new RestraintMonitoringUtility().getDiagnosisTemplate(loginToken);
                //composition.put("diagnosis/composer|id", "775b8c3e-6742-4b30-b443-c7d6aa6ec4ac");
            composition.put("diagnosis/composer|id", userUUID);
                composition.put("diagnosis/composer|name", "prashant");
                composition.put("diagnosis/context/end_time", "2020-05-04T11:46:09.506Z");
                //composition.put("diagnosis/context/health_care_facility|id", "4cc74280-efe5-4016-b41e-f29472a4ec12");
            composition.put("diagnosis/context/health_care_facility|id", orgUUID);
                //composition.put("diagnosis/context/health_care_facility|name", "psm321op");
            composition.put("diagnosis/context/health_care_facility|name", mheName);
                composition.put("diagnosis/context/start_time", "2020-05-04T11:46:09.506Z");
                composition.put("diagnosis/problem_diagnosis:0/diagnostic_certainty", "Psychiatric Diagnosis");
                composition.put("diagnosis/problem_diagnosis:0/problem_diagnosis_name|code","Undefined ICD Code");
                //composition.put("diagnosis/problem_diagnosis:0/problem_diagnosis_name|value", "psychiatric diagnosis seven");
            composition.put("diagnosis/problem_diagnosis:0/problem_diagnosis_name|value", values[index]);

            payload.put("composition", composition);
            payload.put("format", "FLAT");
            payload.put("personId", patientId);
            payload.put("templateId", "EHRC - Diagnosis.v0");

            //System.out.println(payload.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestBody formBody = RequestBody.create(JSON, payload.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST + RELATIVE_PATH)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + loginToken)
                .build();

        Response response = null;
        JSONObject compositionUidObject = null;
        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            //Log.e("why is it null", "");
            compositionUidObject = new JSONObject(rb.string());
            System.out.println(compositionUidObject.toString());
            String compositionUid = compositionUidObject.getString("compositionUid");
            returnComposition = new Composition("diagnosis_matches_compositionIDs", "EHRC - Diagnosis.v0", compositionUid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnComposition;
    } //createDiagnosisTemplate

    public Composition createEHRC_Proceduresv3(String values[], String loginToken, String sessionToken, String userUUID, String orgUUID, String mheName, String patientId) {
        Composition returnComposition = null;
        final String RELATIVE_PATH = "createComposition/";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject payload = new JSONObject();
        try {
            int index = 0;
            payload.put("authorization", sessionToken);
            JSONObject composition = new RestraintMonitoringUtility().getrMonitoringApplicationTemplate(loginToken);
                //composition.put("/composer|identifier", "775b8c3e-6742-4b30-b443-c7d6aa6ec4ac");
            composition.put("/composer|identifier", userUUID);
                composition.put("/composer|name", "prashant");
                if(values[index++].equals("Yes"))
                    composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0006, 'complication/value#1']", "Injury");
                if(values[index++].equals("Yes"))
                    composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0006, 'complication/value#2']", "Limb ISchaemia");
                //composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0006, 'complication/value#3']", "others sixteen");
            composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0006, 'complication/value#3']", values[index++]);
                //composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0014]|value", "reason thirteen");
            composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0014]|value", values[index++]);
            // duration and duration type
            String at0061 = "P"+values[index++]+values[index++].charAt(0);
                composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/description[at0001]/items[at0061]|value", at0061);
                //composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/protocol[at0053]/items[openEHR-EHR-CLUSTER.intimation.v0]/items[at0002]|value", "nominated representative name one");
            composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/protocol[at0053]/items[openEHR-EHR-CLUSTER.intimation.v0]/items[at0002]|value", values[index++]);
                //composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/protocol[at0053]/items[openEHR-EHR-CLUSTER.intimation.v0]/items[at0004]|value", "true");
            composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/protocol[at0053]/items[openEHR-EHR-CLUSTER.intimation.v0]/items[at0004]|value", values[index++]);
                //composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/protocol[at0053]/items[openEHR-EHR-CLUSTER.provider.v0]/items[at0002]|value", "incharge psychiatrist three");
            composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/protocol[at0053]/items[openEHR-EHR-CLUSTER.provider.v0]/items[at0002]|value", values[index++]);
                composition.put("/content[openEHR-EHR-ACTION.procedure.v1]/time|value", "2020-04-08T18:30:00.000Z");
                composition.put("/context/end_time", "2020-05-04T11:45:26.273Z");
                composition.put("/context/health_care_facility|identifier", orgUUID);
                composition.put("/context/health_care_facility|name", mheName);
                composition.put("/context/location", "null");
                composition.put("/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.ehrc_metadata.v0]/items[at0006]/items[at0007]|value", values[index]);
                composition.put("/context/setting", "openehr::238|"+values[index]+"|");
                composition.put("/context/start_time", "2020-05-04T11:45:26.273Z");

            payload.put("composition", composition);
            payload.put("format", "ECISFLAT");
            payload.put("personId", patientId);
            payload.put("templateId", "EHRC - Procedures.v3");

            System.out.println("00000000000000000000000000"+payload.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n\n\n\n\n\n\n"+payload.toString());

        RequestBody formBody = RequestBody.create(JSON, payload.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST + RELATIVE_PATH)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + loginToken)
                .build();

        Response response = null;
        JSONObject compositionUidObject = null;
        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            //Log.e("why is it null", "");
            compositionUidObject = new JSONObject(rb.string());
            System.out.println(compositionUidObject.toString());
            String compositionUid = compositionUidObject.getString("compositionUid");
            returnComposition = new Composition("restraint_application_matches_compositionIDs", "EHRC - Procedures.v3", compositionUid);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnComposition;
    } //createEHRC_Proceduresv3

    public JSONObject saveAllAssessmentCompositions(List<Composition> compositionList, String loginToken, String sessionToken, String orgUUID, String patientId, String userUUID) {
        final String RELATIVE_PATH = "saveAllAssessmentCompositions/Restraint";
        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();
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
            //Log.e("why is it null", "");
            returnObject = new JSONObject(rb.string());


        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("IFODIAUFOADFJLKJFD"+returnObject);
        return returnObject;
    } //saveAllAssessmentCompositions

    public RestraintMonitoring getVisitRecord(String loginToken, String sessionToken, String patientId, JSONObject visit) {
        RestraintMonitoring restraintMonitoring = new RestraintMonitoring();
        try {
                JSONObject virtualFolder = visit.getJSONObject("virtualFolderData");
                JSONArray data = virtualFolder.getJSONArray("data");
                for(int i=0;i<data.length();i++) {

                    JSONObject object = data.getJSONObject(i);
                    //System.out.println(object);
                    try {
                        if (object.getString("name").equals("restraint_monitoring_matches_compositionIDs")) {
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
                            restraintMonitoring.setBreathingProblem(composition.getString("breathingProblems"));
                            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+composition.getString("breathingProblems"));
                            restraintMonitoring.setInjuries2(composition.getString("injuries"));
                            restraintMonitoring.setNoBloodSupply(composition.getString("noBloodSupplyToLimbs"));
                            restraintMonitoring.setMonitoringDate(composition.getString("observationTime"));
                            restraintMonitoring.setPulse(composition.getString("pulse"));
                            restraintMonitoring.setRespiratoryRate(composition.getString("respiratoryRate"));
                            restraintMonitoring.setTemperature(composition.getString("temperature"));

                        } else if (object.getString("name").equals("restraint_application_matches_compositionIDs")) {
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
                            restraintMonitoring.setOthers(composition.getString("complication"));
                            restraintMonitoring.setDuration(composition.getString("duration"));
                            restraintMonitoring.setInchargePsychiatrist(composition.getString("inChargePsychiatrist"));
                            restraintMonitoring.setInformedToNR(composition.getString("intimation"));
                            restraintMonitoring.setNominatedRepresentativeName(composition.getString("intimationName"));
                            restraintMonitoring.setSetting(composition.getString("programContext"));
                            restraintMonitoring.setReason(composition.getString("reason"));
                        }
                    }catch (Exception e) {e.printStackTrace();}
                }//for
                System.out.println("jfldksjflkjasdlfkjaldkfjlksdjf"+restraintMonitoring.toString());

            } catch(Exception e) {e.printStackTrace();}
        return restraintMonitoring;
    }

}
