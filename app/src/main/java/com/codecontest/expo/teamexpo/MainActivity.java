package com.codecontest.expo.teamexpo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchHex();
    }

    void fetchHex() {
//        Log.d("Response DATA:", "Mark");
//        new AsyncTask<Void, Void, String>() {
//            @Override
//            protected String doInBackground(Void... params) {
//                OkHttpClient client = new OkHttpClient();
//
//                Request request = new Request.Builder()
//                        .url("https://catcoder.codingcontest.org/api/contest/2060/rest/1067/playground/1")
//                        .get()
//                        .addHeader("authorization", "Basic dGVhbV9FeHBvOlRlYW0xMjM0NQ==")
//                        .build();
//
//
//
//                try {
//                    Response response = client.newCall(request).execute();
//                    InputStream instream = response.body().byteStream();
//
//                    StringBuilder buffer = new StringBuilder();
//
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
//
//                    String line = null;
//                    try {
//                        while ((line = reader.readLine()) != null) {
//                            buffer.append(line);
//                        }
//
//                    } finally {
//                        instream.close();
//                        reader.close();
//                    }
//
//                    try {
//                        JSONObject jsonData = new JSONObject(buffer.toString());
//                        JSONObject testData = new JSONObject(jsonData.getString("userTraces"));
//                        Log.d("Mark", testData.toString());
//////                        final String []acData = acDataString.split(",");
////                        // Log.d("DataInStringArray", acData[13]);
////                        runOnUiThread(new Runnable() {
////                            @Override
////                            public void run() {
//////                            Log.d( "run()", Arrays.toString(acData) );
////
////                            }
////                        });
//
//
//                        //TODO we should make an array that accepts the data coming from the JSON response
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    return response.body().string();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        }.execute();


        String json = null;
        try {
            InputStream is = getAssets().open("movement.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
        String json2 = null;
        try {
            InputStream is2 = getAssets().open("playground.json");
            int size = is2.available();
            byte[] buffer2 = new byte[size];
            is2.read(buffer2);
            is2.close();
            json2 = new String(buffer2, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        Gson gson = new Gson();
        try {
            JsonElement obj = gson.fromJson(json, JsonElement.class);
            JsonArray obj2 = obj.getAsJsonArray();

            JsonElement playobj = gson.fromJson(json2, JsonElement.class);
            JsonArray playobj2 = playobj.getAsJsonArray();
            String res = "";
            for(int i=0; i<obj2.get(0).getAsJsonObject().get("userTraces").getAsJsonArray().size();i++){

            JsonArray arr = obj2.get(0).getAsJsonObject().get("userTraces").getAsJsonArray().get(i).getAsJsonArray();
                for(int j=0; j<arr.size();j++){
                    JsonObject arr2 = arr.get(j).getAsJsonObject();
                    double x = arr2.get("x").getAsDouble();
                    double y = arr2.get("y").getAsDouble();
                    Point p = new Point(x,y);
//                    Log.d("Mark", x + " " + y);
                    for(int k = 0; k<playobj2.size();k++){
                        Hexagon hex = new Hexagon(playobj2.get(k).getAsJsonObject().get("center").
                                getAsJsonObject().get("x").getAsDouble(),playobj2.get(k).getAsJsonObject().get("center").
                                getAsJsonObject().get("y").getAsDouble(),
                               30, playobj2.get(k).getAsJsonObject().get("id").getAsInt(),1,1,1);
                        boolean contain = hex.contains(new GameCoordinate(x,y));
                        if(contain && !res.contains(hex.getId()+"")){
                            res += hex.getId() + " ";

                        }
                    }

                }


            }

            Log.d("Mark", res);

            //Log.d("Mark", obj2.get(0).getAsJsonObject().get("userTraces").getAsJsonArray().get(0) + "");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
