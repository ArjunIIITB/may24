package com.example.mhmsbmrapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.mindrot.jbcrypt.BCrypt;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.example.mhmsbmrapp.R;

public class LoginBmr extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private ProgressBar spinner;
    private TextView Info;
    private Button Login;
    private ImageView image;
    private int counter = 5;
    OkHttpClient client = new OkHttpClient();

    /*private final String GlobalVariables = "http://13.126.27.50/MHMS_DEV/user/";
    private final String GlobalVariablesRest = "http://13.126.27.50/MHMS_DEV/rest/";
    private final String CORS = "http://13.126.27.50";*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_bmr);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);

        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        Info.setText("No of attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for loader circle
                spinner.setVisibility(View.VISIBLE);

                System.out.println("inside on click listener");
                final String MyPreferences = "MyPrefs";

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        final SharedPreferences sharedPreferences;
                        MHPFlow mhpFlow = new MHPFlow();
                        String information = null;
                        final String userName = ((EditText) findViewById(R.id.etName)).getText().toString();
                        final String password = ((EditText) findViewById(R.id.etPassword)).getText().toString();
                        System.out.println(userName+"     --------------------------     " +password);

                        String jwtToken = mhpFlow.login(userName, password);


                        try {
                            Log.e("try","inside LoginBmr try----------------------------------");
                            //Log.e("JWT Token", jwtToken);
                            JSONObject jsonObjectbject = new JSONObject(jwtToken);
                            String token = jsonObjectbject.getString("token");
                            Log.e("Token", token);

                            JSONObject decodedResult = new JSONObject(MHPFlow.decoded(jwtToken));
                            //Log.e("decoded String (result)", decodedResult.toString());
                            //Log.e("getAssociatedOrg()",mhpFlow.getAssociatedOrg(token, decodedResult.getString("sessionToken")).toString());
                            JSONArray jsonArray = mhpFlow.getAssociatedOrg(token, decodedResult.getString("sessionToken"));
                            //System.out.println(jsonArray.getJSONObject(jsonArray.length()-1));
                            int cnt = 0;
                            ArrayList<String> list = new ArrayList<String>();
                            list.add("MHE/OP*");
                            while(cnt < jsonArray.length()){
                                JSONObject mheObject = jsonArray.getJSONObject(cnt);
                                list.add(mheObject.getString("name"));
                                cnt++;
                            }
                            //Log.e("MHE list", list.toString());





                           /* Intent intent = new Intent(LoginBmr.this, MainActivity.class);
                            intent.putExtra("user", user.toString());
                            intent.putExtra("decodedResult", decodedResult.toString());
                            LoginBmr.this.startActivity(intent);

                            */

                            Log.e("try", "outside LoginBmr try----------------------------------");
                            Intent intent = new Intent(LoginBmr.this, SelectMhe.class);
                            intent.putExtra("userName", userName);
                            intent.putExtra("password", password);
                            intent.putExtra("list", list);
                            LoginBmr.this.startActivity(intent);

                        }catch(Exception e){
                            e.printStackTrace();
                        }


                    }

                };
                new Thread(runnable).start();
            }
        });
    }

} //Login ends


/*
public void newThread(View view) {
    // do something long
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            test4(236);
        }
    };
    new Thread(runnable).start();
}







    public void validate(String s, String ss){}


    // getting unique id and displaying the select information of user
    public void test4(int search){
        System.out.println("method test4() invoked");

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url("http://13.126.27.50/MHMS_DEV/rest/searchpatient/givenName/akshay?indent=4").build();
        okhttp3.Response response = null;

        try {
            response = client.newCall(request).execute();
            okhttp3.ResponseBody rb = response.body();

            JSONArray arr = new JSONArray(rb.string());


            HashMap<Integer, String[]> userMap = new HashMap<Integer, String[]>();
            for(int i=0; i<arr.length(); i++){
                int value = arr.getJSONObject(i).getInt("personId");
                String []stringArray = new String[3];
                stringArray[0] = arr.getJSONObject(i).getString("givenName");
                stringArray[1] = arr.getJSONObject(i).getString("email");
                stringArray[2] = arr.getJSONObject(i).getString("phoneNumber");
                userMap.put(value, stringArray);
            }
            System.out.println("__________________________________________________________________________");
            if(userMap.containsKey(search)) {
                System.out.println();
                String[] userData = (userMap.get(search));
                for (String data : userData) {
                    System.out.println(data);
                }
            }else{
                System.out.println("No user with given id exists");
            }
            System.out.println("__________________________________________________________________________");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("method test4() last line");
    } //test4() ends



    public String[] getSalt(String userName){

        String returnString[] = new String[2];

        String url = "http://13.126.27.50/MHMS_DEV/user/genSalt/"+userName;
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url).build();
        okhttp3.Response response = null;
        try {
            response = client.newCall(request).execute();
            JSONObject obj = new JSONObject(response.body().string());
            String fixed = obj.getString("fixedSalt");
            String dynamic = obj.getString("dynamicSalt");
            System.out.println(fixed +"         "+ dynamic);
            returnString[0] = fixed;
            returnString[1] = dynamic;
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnString;

    } //getSalt() ends


    public void loginTest(String[] salts, String userName, String password){

        String relativePath = "applogin/";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        String hash = BCrypt.hashpw(password, salts[0]);
        String hash1 = BCrypt.hashpw(hash, salts[1]);
        System.out.println(hash);
        System.out.println("hash 1" + hash1);

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("username", userName);
            jsonObject.put("password", hash1);
        }catch (Exception e){
            e.printStackTrace();
        }

        RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

                Request request = new Request.Builder()
                .url(GlobalVariables+relativePath)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Access-Control-Allow-Origin", CORS )
                .build();



        Response response = null;

        try {
            response = client.newCall(request).execute();
            String whatIGot = response.body().string();
            System.out.println("_______________________________________________________________________________");
            System.out.println(whatIGot);
            JSONObject obj = new JSONObject(whatIGot);
        }catch(Exception e){
            e.printStackTrace();
        }

    }// loginTest ends here




    public void getCaptcha(){
        String relativePath = "createCaptcha/LOGIN";

        RequestBody formBody = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url(GlobalVariables+relativePath)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Access-Control-Allow-Origin", CORS )
                .build();



        Response response = null;

                try {
                    response = client.newCall(request).execute();
                    String whatIGot = response.body().string();
                    System.out.println(whatIGot);
                    JSONObject obj = new JSONObject(whatIGot);
                    obj.getString("captchaID");
                    String imageString = obj.getString("encodedImage");
                    System.out.println(imageString);

                    byte []imageBytes = Base64.decode(imageString, Base64.DEFAULT);
                    Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                    //image is of type ImageView and it needs to be fetched using getElementById(id) equivalent of android
                    image.setImageBitmap(decodedImage);



                }catch(Exception e){
                    e.printStackTrace();
                }
    } //getCaptcha ends



    public void getAssociatedOrganisation(String bearer){
        bearer = "eyJEZXZlbG9wZWQgQnkiOiJlLUhlYWx0aCBSZXNlYXJjaCBDZW50ZXIsIElJSVQgQmFuZ2Fsb3JlIiwiSG9zdCI6Ikthcm5hdGFrYSBNZW50YWwgSGVhbHRoIE1hbmFnZW1lbnQgU3lzdGVtIiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJwcm9mZXNzaW9uIjoiTUhNU1BzeWNob2xvZ2lvaW9pc3RzdGlzaXRhY2NlcHRpbmciLCJzdWIiOiJNSE1TIFNlY3VyaXR5IFRva2VuIiwic2Vzc2lvbkVuZFRpbWUiOjE1ODM4MDY5NTEsImlzcyI6IktNSE1TIiwic2Vzc2lvblN0YXJ0VGltZSI6MTU4Mzc2Mzc1MSwic2Vzc2lvbklkIjoiNjhjMmQ5NDYtODEwZi00MDc5LWFkZjItYzRlYjVhMGQ4ODAyIiwidXNlck5hbWUiOiJwcmFzaGFudCIsIm9yZ1VVSUQiOiJhMjFiODg1ZS0yZjNhLTQ0MjUtOGI1Yi0wZDI3NGI0MmFmMjYiLCJuYmYiOjE1ODM3NjM3NTEsIm9yZ1JvbGUiOiJNSEVBZG1pbiIsInNlc3Npb25Ub2tlbiI6IlNlc3Npb25JZDoxNzIuMzEuNS4xMyNwcmFzaGFudDphMjFiODg1ZS0yZjNhLTQ0MjUtOGI1Yi0wZDI3NGI0MmFmMjY6TUhNUzpNSEVBZG1pbiMxNTgzNzYzNzUxMzE3Iy0xMjI5NDQzNDgjMTkiLCJwZXJzb25JZCI6IjkyNWQ2N2NkLTdkM2MtNDA3OC04OWZiLTY5NjNjNDdiNDk2YSIsInVzZXJVVUlEIjoiNzc1YjhjM2UtNjc0Mi00YjMwLWI0NDMtYzdkNmFhNmVjNGFjIiwiZXhwIjoxNTgzNzk5NzUxLCJpYXQiOjE1ODM3NjM3NTF9.QoTSq5cq_QCIJtCraDdyhnU3ynNQFeUq81cFm4pIjNM";
        String relativePath = "getAssociatedOrg";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("user_token","SessionId:172.31.5.13#prashant:a21b885e-2f3a-4425-8b5b-0d274b42af26:MHMS:MHEAdmin#1583763751317#-122944348#19");
        }catch (Exception e){
            e.printStackTrace();
        }

        RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());


        Request request = new Request.Builder()
                .url(GlobalVariablesRest+relativePath)
                //.url("http://13.126.27.50/MHMS_DEV/rest/getAssociatedOrg")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+bearer)
                //.addHeader("Access-Control-Allow-Origin", CORS )
                .post(formBody)

                .build();



        Response response = null;

        try {
            response = client.newCall(request).execute();
            String whatIGot = response.body().string();
            System.out.println("_______________________________________________________________________________");
            System.out.println(whatIGot);
            //JSONObject obj = new JSONObject(whatIGot);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void call(){
        //LoginBmr.enccriptData("Test@123");
        System.out.println("hello");
    }







*/
