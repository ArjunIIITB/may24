package com.example.mhmsbmrapp.Login;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ForTesting {



    OkHttpClient client = new OkHttpClient();


    public String sendOTP(String contactType, String contactVal, String purpose){
        final String RELATIVE_PATH = "sendOTP/";
        String returnString = null;
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("contactType", contactType);
            jsonObject.put("contactVal", contactVal);
            jsonObject.put("purpose", purpose);
        }catch (Exception e){
            e.printStackTrace();
        }

        RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_USER+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();

            JSONObject jsonObjectResult = new JSONObject(rb.string());
            System.out.println(jsonObjectResult.toString());
            try {
                System.out.println(jsonObjectResult.getString("tokenID"));
                returnString = jsonObjectResult.getString("tokenID");
            }catch (Exception e){
                System.out.println(jsonObjectResult.getString("message"));
            } //inner catch ends here
        }catch(Exception e){
            e.printStackTrace();
        }

        return returnString;
    }// sendOTP ends here





    public String verifyOTP(String contactType, String contactVal, String purpose, String tokenID, String otp){

        final String RELATIVE_PATH = "verifyOTP/";
        String returnString = null;
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("contactType", contactType);
            jsonObject.put("contactVal", contactVal);
            jsonObject.put("purpose", purpose);
            jsonObject.put("tokenID", tokenID);
            jsonObject.put("otp", otp);
        }catch (Exception e){
            e.printStackTrace();
        }

        RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_USER+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            JSONObject jsonObjectResult = new JSONObject(rb.string());
            System.out.println(jsonObject.toString());
            System.out.println(jsonObjectResult.getInt("code"));
            Integer returnInt = jsonObjectResult.getInt("code");
            returnString = returnInt.toString();
            //System.out.println("responseCode" + returnString);

        }catch(Exception e){
            e.printStackTrace();
        }

        return returnString;

    }// verifyOTP ends



    public void createUser(String userName, String password, String phoneNumber, String email, String name,String tokenIDMobile, String tokenIDEmail){



        final String RELATIVE_PATH = "createuser/";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("username", userName);
            jsonObject.put("password", password);
            jsonObject.put("phoneNumber", phoneNumber);
            jsonObject.put("email", email);
            jsonObject.put("name", name);
            jsonObject.put("mobToken", tokenIDMobile);
            jsonObject.put("emailToken", tokenIDEmail);
        }catch (Exception e){
            e.printStackTrace();
        }

        RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            //JSONObject jsonObjectResult = new JSONObject(rb.string());
            System.out.println(rb.string());
            //System.out.println(jsonObjectResult.getString("tokenID"));
        }catch(Exception e){
            e.printStackTrace();
        }



    }// createUser ends here






//--------------------------------------------------------------------------------------------------------------------





}// class ends here