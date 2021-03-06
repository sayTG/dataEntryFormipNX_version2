package com.example.dataentryformipnx_2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dataentryformipnx_2.R;
import com.example.dataentryformipnx_2.login_package.Choose;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewPreventive extends AppCompatActivity {

    ListView listView;
    ListAdapter adapter;
    ProgressDialog progressDialog;
    ImageView imageView;

    final String precPearl = "https://script.google.com/macros/s/AKfycbxjpctS3flQQeM9ftp39aouJbpXYWkt7xJvYeXbKAsy9VNOAY0/exec?action=getPreventive";
    final String sumarus = "https://script.google.com/macros/s/AKfycbxABv4KNCtwvK-OEDLxWqIbH49_zcm2JID0mc1_xp3HjpvWDv4/exec?action=getPreventive" ;
    final String waasek = "https://script.google.com/macros/s/AKfycbxvyylP_xV3sMdRkRJnwM5Jq90dBT2cyHsa0K4QtkZffXRwzCaj/exec?action=getPreventive";
    final String others = "https://script.google.com/macros/s/AKfycbzNCKkM_wXL94pt-XozBJADGxVUHJLLpwTldE08gLvSWag0ZaY/exec?action=getPreventive";

    String updatedUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_preventive);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        String message = prefs.getString("keyName", null);

        if(message == null){
            Toast.makeText(getApplicationContext(), "You have not chosen where you belong to", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), Choose.class);
            startActivity(intent);
        } else{
            switch (message) {
                case "PrecPearl":
                    updatedUrl = precPearl;
                    break;
                case "Sumarus":
                    updatedUrl = sumarus;
                    break;
                case "Waasek":
                    updatedUrl = waasek;
                    break;
                case "Others":
                    updatedUrl = others;
                    break;
            }

        }

        listView = findViewById(R.id.list_view);
        getItems();

        imageView = findViewById(R.id.imageView);
    }

    private void getItems(){

        progressDialog = progressDialog.show(this, "Loading", "Please wait", false, true);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                updatedUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseItems(response);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        int socketTimeout = 30000;

        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void parseItems(String jsonResponse){

        ArrayList<HashMap<String, String >> list = new ArrayList<>();
        try{
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray jsonArray = jsonObject.getJSONArray("items");

            for(int i =0; i < jsonArray.length(); i++){

                JSONObject object = jsonArray.getJSONObject(i);

                String sID = object.getString("sID");
                String sAffectedRegion = object.getString("sAffectedRegion");
                String sType = object.getString("sType");
                String sAffected = object.getString("sAffected");
                String sLocation = object.getString("sLocation");
                String sRFO = object.getString("sRFO");
                String sUserImage = object.getString("sUserImage");

                HashMap<String, String > item = new HashMap<>();
                item.put("sID", sID);
                item.put("sAffectedRegion", sAffectedRegion);
                item.put("sType", sType);
                item.put("sAffected", sAffected);
                item.put("sLocation", sLocation);
                item.put("sRFO", sRFO);
                item.put("sUserImage", sUserImage);

                list.add(item);
            }

        }
        catch (JSONException e){
            e.printStackTrace();
        }




        adapter = new CorrectiveAdapter(this, list,  R.layout.corrective_row,
                new String[]{"sID", "sAffectedRegion", "sType", "sAffected", "sLocation", "sRFO"}, new int[]{R.id.textView_id,
                R.id.textView_region, R.id.textView_type, R.id.textView_pon, R.id.textView_location, R.id.textView_rfo});

        listView.setAdapter(adapter);

        progressDialog.dismiss();





    }

    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
