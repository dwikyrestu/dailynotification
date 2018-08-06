package com.media.aruna.dailyquotesnotification;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Splash extends AppCompatActivity {
    private String mJSONURLString = "https://talaikis.com/api/quotes/random/";
    private Context mContext;
    private Activity mActivity;
    public TextView t1;
    //Set waktu lama splashscreen
    private static int splashInterval = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        mContext = getApplicationContext();
        mActivity = Splash.this;
        t1 = (TextView)findViewById(R.id.textView);

        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        // Initialize a new JsonObjectRequest instance
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                mJSONURLString,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            String quote = response.getString("quote");
                            String author = response.getString("author");
                            //t1.setText(quote);
                            //Toast.makeText(Splash.this,"Can Connect to Server", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Splash.this, MainActivity.class);
                            startActivity(i);
                            //jeda selesai Splashscreen
                            //this.finish();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Toast.makeText(Splash.this,"Cant Connect to Server", Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
//        new Handler().postDelayed(new Runnable() {
//
//
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                Intent i = new Intent(Splash.this, MainActivity.class);
//                startActivity(i); // menghubungkan activity splashscren ke main activity dengan intent
//
//
//                //jeda selesai Splashscreen
//                this.finish();
//            }
//
//            private void finish() {
//                // TODO Auto-generated method stub
//
//            }
//        }, splashInterval);

    };
}
