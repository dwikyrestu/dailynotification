package com.media.aruna.dailyquotesnotification;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;
    private Toolbar toolbar;
    private ImageView toolbarTitle;
    private Button mButtonDo;
    private TextView mTextView;
    private String mJSONURLString = "https://talaikis.com/api/quotes/random/";
    private ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbarTitle = (ImageView) findViewById(R.id.toolbar_title);
//set toolbar
        setSupportActionBar(toolbar);
//menghilangkan titlebar bawaan
        if (toolbar != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // Get the application context
        mContext = getApplicationContext();
        mActivity = MainActivity.this;

        // Get the widget reference from XML layout
        mButtonDo = (Button) findViewById(R.id.btn01);
        mTextView = (TextView) findViewById(R.id.textView1);

        // Set a click listener for button widget
        mButtonDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Empty the TextView
                mTextView.setText("");
                /*menampilkan progressbar saat mengirim data*/
                final ProgressDialog pd = new ProgressDialog(MainActivity.this);
                pd.setIndeterminate(true);
                pd.setCancelable(false);
                pd.setInverseBackgroundForced(false);
                pd.setCanceledOnTouchOutside(false);
                pd.setTitle("Info");
                pd.setMessage("Sedang mengambil data");
                pd.show();
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
                                    mTextView.append(quote +" " + author);
                                    pd.dismiss();
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error){
                                // Do something when error occurred
                                Toast.makeText(MainActivity.this,"Error", Toast.LENGTH_LONG).show();
                            }
                        }
                );
                // Add JsonObjectRequest to the RequestQueue
                requestQueue.add(jsonObjectRequest);
            }
        });

        ImageView iv = (ImageView) findViewById(R.id.ivHome);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Toast.makeText(MainActivity.this,"This Is Home, Mate !!!", Toast.LENGTH_LONG).show();
            }
        });

    }
}
