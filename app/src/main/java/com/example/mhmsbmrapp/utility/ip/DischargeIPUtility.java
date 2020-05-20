package com.example.mhmsbmrapp.utility.ip;

import com.example.mhmsbmrapp.Login.GlobalVariables;
import com.example.mhmsbmrapp.model.Composition;
import com.example.mhmsbmrapp.model.ip.Discharge;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

//prashantsinghmangat5@gmail.com
public class DischargeIPUtility {

    OkHttpClient client = new OkHttpClient();
    public Composition createCompositionEHRC_Discharge_notesv0(Discharge ip, JSONObject compositionCopy, String loginToken, String sessionToken, String personId) {

        Composition returnComposition = null;
        final String RELATIVE_PATH = "createComposition/";
        String templateId = "EHRC - Discharge notes.v0";

        //String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587238304431#93780176#874";
        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {
            JSONObject composition = new JSONObject(compositionCopy.toString());

            jsonObject.put("authorization", sessionToken);

            composition.put("/content[openEHR-EHR-EVALUATION.discharge.v0]/data[at0001]/items[at0002]|value", ip.getTypeOfDischarge());
            composition.put("/content[openEHR-EHR-EVALUATION.discharge.v0]/data[at0001]/items[at0003]|value", ip.getConditionAtDischarge());
            composition.put("/content[openEHR-EHR-EVALUATION.discharge.v0]/data[at0001]/items[at0004]|value", ip.getConditionDescription());
            composition.put("/content[openEHR-EHR-EVALUATION.discharge.v0]/data[at0001]/items[at0006]|value", ip.getFollowUpRecommendation());
            composition.put("/content[openEHR-EHR-EVALUATION.discharge.v0]/data[at0001]/items[at0008]/items[at0010]|value", ip.getNameOfDoctor());
            composition.put("/content[openEHR-EHR-EVALUATION.discharge.v0]/data[at0001]/items[at0008]/items[at0011]|value", ip.getDesignation());



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
        JSONObject compositionUidObject = null;
        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            //Log.e("why is it null", "");
            compositionUidObject = new JSONObject(rb.string());
            System.out.println(compositionUidObject.toString());
            String compositionUid = compositionUidObject.getString("compositionUid");
            returnComposition = new Composition("discharge_notes_matches_compositionIDs", templateId, compositionUid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnComposition;
    } //createCompositionEHRC_Discharge_notesv0 (POST)

    public Composition createCompositionEHRC_EHRC_Medication_orderv0(Discharge ip, JSONObject compositionCopy, String loginToken, String sessionToken, String personId) {

        Composition returnComposition = null;

        final String RELATIVE_PATH = "createComposition/";
        String templateId = "EHRC - Medication order.v0";

        //String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587238304431#93780176#874";
        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();
        String time = "2020-04-23T11:38:01.109Z";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {
            JSONObject composition = new JSONObject(compositionCopy.toString());

            jsonObject.put("authorization", sessionToken);

            composition.put("/content[openEHR-EHR-SECTION.medication_order_list.v0]/items[openEHR-EHR-INSTRUCTION.medication_order.v2]/activities[at0001]/description[at0002]/items[at0009]|value", ip.getRemarks());
            composition.put("/content[openEHR-EHR-SECTION.medication_order_list.v0]/items[openEHR-EHR-INSTRUCTION.medication_order.v2]/activities[at0001]/description[at0002]/items[at0070]|value", ip.getMedicine()+"_"+ip.getDosage());
            composition.put("/content[openEHR-EHR-SECTION.medication_order_list.v0]/items[openEHR-EHR-INSTRUCTION.medication_order.v2]/activities[at0001]/description[at0002]/items[at0173]|value", " ");
            composition.put("/content[openEHR-EHR-SECTION.medication_order_list.v0]/items[openEHR-EHR-INSTRUCTION.medication_order.v2]/activities[at0001]/description[at0002]/items[openEHR-EHR-CLUSTER.therapeutic_direction.v1]/items[at0066]|value", "P"+ip.getDuration()+ip.getDurationType().charAt(0));
            composition.put("/content[openEHR-EHR-SECTION.medication_order_list.v0]/items[openEHR-EHR-INSTRUCTION.medication_order.v2]/activities[at0001]/description[at0002]/items[openEHR-EHR-CLUSTER.therapeutic_direction.v1]/items[openEHR-EHR-CLUSTER.dosage.v1]/items[openEHR-EHR-CLUSTER.timing_daily.v1]/items[at0027]|value", ip.getDosingTime());

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
        JSONObject compositionUidObject = null;
        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            compositionUidObject = new JSONObject(rb.string());
            System.out.println(compositionUidObject.toString());
            String compositionUid = compositionUidObject.getString("compositionUid");
            returnComposition = new Composition("medication_order_matches_compositionIDs", templateId, compositionUid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnComposition;
    } //createCompositionEHRC_EHRC_Medication_orderv0 (POST)

    public Composition createCompositionEHRC_EHRC_Service_requestv0(Discharge ip, JSONObject compositionCopy, String loginToken, String sessionToken, String personId) {

        Composition returnComposition = null;

        final String RELATIVE_PATH = "createComposition/";
        String templateId = "EHRC - Service request.v0";

        //String sessionToken = "SessionId:172.31.5.13#prashant:4cc74280-efe5-4016-b41e-f29472a4ec12:MHMS:MHProfessional#1587238304431#93780176#874";
        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();
        String time = "2020-04-23T11:38:01.109Z";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {
            JSONObject composition = new JSONObject(compositionCopy.toString());

            jsonObject.put("authorization", sessionToken);

            composition.put("/content[openEHR-EHR-INSTRUCTION.service_request.v1]/activities[at0001]/description[at0009]/items[at0040]|value", "2020-05-13T07:46:34.686Z::2020-06-09T18:30:00.000Z");
            composition.put("/content[openEHR-EHR-INSTRUCTION.service_request.v1]/activities[at0001]/description[at0009]/items[at0148]|value", "Followup");

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
        JSONObject compositionUidObject = null;
        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            //Log.e("why is it null", "");
            compositionUidObject = new JSONObject(rb.string());
            System.out.println(compositionUidObject.toString());
            String compositionUid = compositionUidObject.getString("compositionUid");
            returnComposition = new Composition("service_request_matches_compositionIDs", templateId, "Followup",compositionUid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnComposition;
    } //createCompositionEHRC_EHRC_Service_requestv0 (POST)

    public JSONObject saveAllCompositions(List<Composition> compositionList, String loginToken, String sessionToken, String orgUUID, String patientId, String userUUID) {

        OkHttpClient client = new OkHttpClient();
        final String RELATIVE_PATH = "createIpDischargeVirtualFolder/";

        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject payload = new JSONObject();

        try {

            JSONObject jsonObject = new JSONObject();

            payload.put("folder_id", "d860077e-55c7-4bce-b041-4597311be9c2");
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
            if(response.code() != 200)
                return null;
            ResponseBody rb = response.body();
            returnObject = new JSONObject(rb.string());

        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;

    }

    public JSONObject getIPpatient(String loginToken, String patientId) {
        final String RELATIVE_PATH = "getIPpatient/"+patientId;


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

    public JSONObject updateIPPatientQueue(String loginToken, String patientId, String dischargeTIme) {
        final String RELATIVE_PATH = "updateIPPatientQueue/";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = getIPpatient(loginToken, patientId);
        try {
            jsonObject.put("admissionStatus", "Discharged");
            jsonObject.put("dischargeTime", dischargeTIme);
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


}
