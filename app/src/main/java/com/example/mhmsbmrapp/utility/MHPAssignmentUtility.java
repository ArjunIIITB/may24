package com.example.mhmsbmrapp.utility;



import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import com.example.mhmsbmrapp.Login.GlobalVariables;
import com.example.mhmsbmrapp.Login.MHPFlow;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MHPAssignmentUtility {
    OkHttpClient client = new OkHttpClient();

    public JSONArray getRegisteredMHPs(String loginToken){

        String orgUuid = "fdf96e72-1550-441e-ae44-9cd2c9e928c4";

          try {
              JSONObject decodedToken = new JSONObject(MHPFlow.decoded(loginToken));
              orgUuid = decodedToken.getString("orgUUID");
          }catch (Exception e) {e.printStackTrace();}

        final String RELATIVE_PATH = "getInviteByMHE/" + orgUuid;
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");
// This is GET request
        JSONArray jsonArrayResult = null;
        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .get()
                .addHeader("Authorization", "Bearer "+ loginToken)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            jsonArrayResult = new JSONArray(rb.string());
            System.out.println(jsonArrayResult.toString());

        }catch(Exception e){
            e.printStackTrace();
        }
        return jsonArrayResult;
    } //getRegisteredMHPs (GET)


    public JSONObject getParentOrg(String loginToken){
        JSONObject parentOrg = null;
        String orgUuid = "fdf96e72-1550-441e-ae44-9cd2c9e928c4";
        String  user_token = "SessionId:172.31.5.13#arjun01:fdf96e72-1550-441e-ae44-9cd2c9e928c4:MHMS:MHRegistryProfessional#1587628797367#247849754#1278";

        try {
            JSONObject decodedToken = new JSONObject(MHPFlow.decoded(loginToken));
            orgUuid = decodedToken.getString("orgUUID");
            user_token = decodedToken.getString("sessionToken");
        } catch (Exception e){e.printStackTrace();}


        final String RELATIVE_PATH = "getParentOrg/";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");



        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("orguuid", orgUuid);
            jsonObject.put("user_token", user_token);

        }catch (Exception e){
            e.printStackTrace();
        }

        RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Authorization", "Bearer " + loginToken)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = null;

        try {


            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            //jsonObjectResult = new JSONArray(rb.string());

            //response = client.newCall(request).execute();

            parentOrg = new JSONObject(rb.string());
            System.out.println(parentOrg.toString());


        }catch(Exception e){
            e.printStackTrace();
        }

        return parentOrg;

    } //getParentOrg (POST)


    public JSONObject getPatientByPatientId(String loginToken, String sessionToken,String patientId) {


        JSONObject patient = null;

        final String RELATIVE_PATH = "getpatient/";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");



        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("patientId", patientId);
            jsonObject.put("token", sessionToken);

        }catch (Exception e){
            e.printStackTrace();
        }

        RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Authorization", "Bearer " + loginToken)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = null;

        try {


            response = client.newCall(request).execute();
            ResponseBody rb = response.body();

            patient = new JSONObject(rb.string());
            System.out.println(patient.toString());

        }catch(Exception e){
            e.printStackTrace();
        }

        return patient;

    } //getPatientByPatientId (POST)


    public void getPatientAge(String loginToken, String dob){
        final String RELATIVE_PATH = "getAge/";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");



        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("age", dob);
        }catch (Exception e){
            e.printStackTrace();
        }

        RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Authorization", "Bearer " + loginToken)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = null;

        try {


            response = client.newCall(request).execute();
            ResponseBody rb = response.body();

            JSONObject object = new JSONObject(rb.string());
            System.out.println(object.toString());

        }catch(Exception e){
            e.printStackTrace();
        }
    } //getPatientAge (POST)


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateIPPatientQueue(JSONObject patient , JSONObject parentOrg, JSONObject MHP, String loginToken) {


        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();
        String time = "1587358504256";
        final String RELATIVE_PATH = "updateIPPatientQueue/";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject payload = null;

        try {
            payload = new JSONObject();
            //payload.put("patientName", "Mr. Advance Directive");
            payload.put("patientName", patient.getString("prefix")+" "+patient.getString("givenName")+" "+patient.getString("middleName"));
            //payload.put("patientPhone", "8404973134");
            payload.put("patientPhone", patient.getString("phoneNumber"));
            //payload.put("patientDob", "2000-12-30T18:30:00.000Z");
            payload.put("patientDob", patient.getString("dateOfBirth"));
            payload.put("patientCity", patient.getJSONArray("address").getJSONObject(0).getString("city"));
            //payload.put("patientId", "a7864f44-7ba8-4bfa-b8c2-de9afa84d30d");
            payload.put("patientId", patient.getString("personId"));
            //payload.put("userId", "775b8c3e-6742-4b30-b443-c7d6aa6ec4ac");
            payload.put("userId", MHP.getString("mhpUuid"));
            //payload.put("assignedMhpId", "775b8c3e-6742-4b30-b443-c7d6aa6ec4ac");
            payload.put("assignedMhpId", MHP.getString("mhpUuid"));
            //payload.put("assignedmhpName", "Prashant SinghPrashant SinghPrashant SinghPrashant");
            payload.put("assignedmhpName", MHP.getString("mhpName"));
            //payload.put("orgId", "4cc74280-efe5-4016-b41e-f29472a4ec12");
            payload.put("orgId", MHP.getString("mheUuid"));
            payload.put("admissionTime", time);
            payload.put("dischargeTime", "");
            payload.put("assessmentStaus", "OP");
            payload.put("admissionStatus", "Waiting");
            payload.put("capacity", "");
            payload.put("referredBy", "");
            payload.put("guardianName", "");
            payload.put("relation", "");
            payload.put("guardianPhone", "");
            //payload.put("mhrbId", "a21b885e-2f3a-4425-8b5b-0d274b42af26");
            payload.put("mhrbId", parentOrg.getString("parentOrgUuid"));
        } catch (Exception e) {}

        Log.d("json object", payload.toString());
        System.out.println(payload.toString());

        RequestBody formBody = RequestBody.create(JSON, payload.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Authorization", "Bearer " + loginToken)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = null;

        try {


            response = client.newCall(request).execute();
            ResponseBody rb = response.body();

            //JSONObject object = new JSONObject(rb.string());
            System.out.println(rb.string());

        }catch(Exception e){
            e.printStackTrace();
        }

    } // updateIPPatientQueue (POST)


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateIPPatientQueue(JSONObject patient , JSONObject parentOrg, JSONObject mhp, String loginToken, String admissionStatus) {


        //String time = new Timestamp(System.currentTimeMillis()).toInstant().toString();
        String time = "1587358504256";
        final String RELATIVE_PATH = "updateIPPatientQueue/";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject payload = null;

        try {
            payload = new JSONObject();
            //payload.put("patientName", "Mr. Advance Directive");
            payload.put("patientName", patient.getString("prefix")+" "+patient.getString("givenName")+" "+patient.getString("middleName"));
            //payload.put("patientPhone", "8404973134");
            payload.put("patientPhone", patient.getString("phoneNumber"));
            //payload.put("patientDob", "2000-12-30T18:30:00.000Z");
            payload.put("patientDob", patient.getString("dateOfBirth"));
            payload.put("patientCity", patient.getJSONArray("address").getJSONObject(0).getString("city"));
            //payload.put("patientId", "a7864f44-7ba8-4bfa-b8c2-de9afa84d30d");
            payload.put("patientId", patient.getString("personId"));
            //payload.put("userId", "775b8c3e-6742-4b30-b443-c7d6aa6ec4ac");
            payload.put("userId", mhp.getString("composer_identifier"));
            //payload.put("assignedMhpId", "775b8c3e-6742-4b30-b443-c7d6aa6ec4ac");
            payload.put("assignedMhpId", mhp.getString("composer_identifier"));
            //payload.put("assignedmhpName", "Prashant SinghPrashant SinghPrashant SinghPrashant");
            payload.put("assignedmhpName", mhp.getString("givenName"));
            //payload.put("orgId", "4cc74280-efe5-4016-b41e-f29472a4ec12");
            payload.put("orgId", mhp.getString("facility_identifier"));
            if(admissionStatus.equals("Waiting"))
                payload.put("admissionTime", time);
            if(admissionStatus.equals("Completed"));
                payload.put("dischargeTime", time);
            payload.put("assessmentStaus", "OP");
            payload.put("admissionStatus", admissionStatus);
            payload.put("capacity", "");
            payload.put("referredBy", "");
            payload.put("guardianName", "");
            payload.put("relation", "");
            payload.put("guardianPhone", "");
            //payload.put("mhrbId", "a21b885e-2f3a-4425-8b5b-0d274b42af26");
            if(admissionStatus.equals("Waiting"))
                payload.put("mhrbId", parentOrg.getString("parentOrgUuid"));
        } catch (Exception e) {}

        Log.d("json object", payload.toString());
        System.out.println(payload.toString());

        RequestBody formBody = RequestBody.create(JSON, payload.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Authorization", "Bearer " + loginToken)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = null;

        try {


            response = client.newCall(request).execute();
            ResponseBody rb = response.body();

            //JSONObject object = new JSONObject(rb.string());
            System.out.println(rb.string());

        }catch(Exception e){
            e.printStackTrace();
        }

    } // updateIPPatientQueue (POST)


}