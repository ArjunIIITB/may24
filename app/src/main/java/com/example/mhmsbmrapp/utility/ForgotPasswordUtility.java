package com.example.mhmsbmrapp.utility;

import android.util.Log;

import com.example.mhmsbmrapp.Login.GlobalVariables;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ForgotPasswordUtility {
    OkHttpClient client = new OkHttpClient();

    public String forgotPassword(String userName) {
        final String RELATIVE_PATH = "forgotpassword/";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("username", userName);
        }catch (Exception e) {}

        RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = null;
        JSONObject result = null;
        String message = null;
        try {
            response = client.newCall(request).execute();
            if(response.code()!=200){
                Log.e("forgot_pwrd link failed", "response code is "+response.code());
                return null;
            }
            ResponseBody rb = response.body();
            result = new JSONObject(rb.string());
            message = result.getString("message");
        }catch(Exception e){
            e.printStackTrace();
        }
        return message;
    } //forgotPassword (POST)

    public String forgotUsername(String emailId) {
        final String RELATIVE_PATH = "forgotusername/"+emailId;
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();
        RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = null;
        JSONObject result = null;
        String message = null;
        try {
            response = client.newCall(request).execute();
            if(response.code()!=200){
                Log.e("forgot_uid link failed", "response code is "+response.code());
                return null;
            }
            ResponseBody rb = response.body();
            result = new JSONObject(rb.string());
            message = result.getString("message");
        }catch(Exception e){
            e.printStackTrace();
        }
        return message;
    } //forgotUsername (POST)

}
