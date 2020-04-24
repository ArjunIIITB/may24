package com.example.mhmsbmrapp.Login;

import android.util.Base64;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Utility {


    OkHttpClient client = new OkHttpClient();


    public JSONObject login(String userName, String password){

        final String RELATIVE_PATH = "applogin/";
        String returnString = null;
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObjectResult = null;

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("username", userName);
            jsonObject.put("password", password);
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
            jsonObjectResult = new JSONObject(rb.string());
            System.out.println(jsonObject.toString());
            //System.out.println(jsonObjectResult.getString("message"));
            //System.out.println(jsonObjectResult.getInt("code"));
            //Integer returnInt = jsonObjectResult.getInt("code");
            //returnString = returnInt.toString();
            System.out.println("jsonObjectResult.toString() " + jsonObjectResult.toString());


        }catch(Exception e){
            e.printStackTrace();
        }
        return jsonObjectResult;
    }// login ends here


    public static void decoded(String JWTEncoded) throws Exception {
        try {
            String[] split = JWTEncoded.split("\\.");

            System.out.println("inside decode module");
            for(String s : split){
                byte[] b = s.getBytes("UTF-8");
                String base64 = Base64.encodeToString(b, Base64.DEFAULT);
                System.out.println(getJson(base64));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        /*} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }*/
    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException{
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }





}
