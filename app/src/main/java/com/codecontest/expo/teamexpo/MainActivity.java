package com.codecontest.expo.teamexpo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchHex();
    }

    void fetchHex() {
        Log.d("Response DATA:", "Mark");
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://catcoder.codingcontest.org/api/contest/2060/rest/1066/movement/1")
                        .get()
                        .addHeader("authorization", "Basic dGVhbV9FeHBvOlRlYW0xMjM0NQ==")
                        .build();



                try {
                    Response response = client.newCall(request).execute();
                    String responseString = response.body().string();
                    Log.d("Response DATA:", responseString);
//                    try {
//                        JSONObject jsonData = new JSONObject(responseString);
//                        JSONObject testData = new JSONObject(jsonData.getString("outputs"));
//                        String testData2 = testData.getString("poa");
//                        String acDataString = testData2.substring(1);
//                        Log.d("testData", testData2);
//
//                        final String []acData = acDataString.split(",");
//                        // Log.d("DataInStringArray", acData[13]);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                            Log.d( "run()", Arrays.toString(acData) );
//
//                            }
//                        });
//
//
//                        //TODO we should make an array that accepts the data coming from the JSON response
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    return response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();

    }
}
