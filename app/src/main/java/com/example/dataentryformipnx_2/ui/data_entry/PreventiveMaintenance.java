package com.example.dataentryformipnx_2.ui.data_entry;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dataentryformipnx_2.MainActivity;
import com.example.dataentryformipnx_2.R;
import com.example.dataentryformipnx_2.login_package.Choose;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PreventiveMaintenance extends AppCompatActivity {
    private TextInputLayout text_issue, text_risk, text_location, text_measures, textbox_element_affected;

    private RadioGroup radiogroup_1, radiogroup_2;
    private RadioButton radio_vi, radio_lekki, radio_ikoyi, radio_mainland, radio_island,
            radio_mst, radio_distribution, radio_feeder, radio_general_1, radio_general_2;

    private Button btn_save;
    private ImageButton btn_img;

    FirebaseAuth firebaseAuth;

    private int PICK_IMAGE_REQUEST = 1;

    String sUserImage ;
    Bitmap rBitMap;

    String regionName = "Victoria Island";
    String regionType = "Feeder";
    String message, sheetMessage;
    String email;

    final String precPearl = "https://script.google.com/macros/s/AKfycbxjpctS3flQQeM9ftp39aouJbpXYWkt7xJvYeXbKAsy9VNOAY0/exec";
    final String sumarus = "https://script.google.com/macros/s/AKfycbxABv4KNCtwvK-OEDLxWqIbH49_zcm2JID0mc1_xp3HjpvWDv4/exec" ;
    final String waasek = "https://script.google.com/macros/s/AKfycbxvyylP_xV3sMdRkRJnwM5Jq90dBT2cyHsa0K4QtkZffXRwzCaj/exec";
    final String others = "https://script.google.com/macros/s/AKfycbzNCKkM_wXL94pt-XozBJADGxVUHJLLpwTldE08gLvSWag0ZaY/exec";

    String updatedUrl;

    String sElement;

    String text_element_affected;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preventive_maintenance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();

        email= firebaseAuth.getCurrentUser().getEmail();

        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        message = prefs.getString("keyName", null);
        sheetMessage = prefs.getString("sheetName", null);

        Spinner spinner = (Spinner)findViewById(R.id.textsp_element_affected);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.element_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("Select Affected Element");
        spinner.setAdapter(adapter);



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

        text_issue = (TextInputLayout)findViewById(R.id.text_issue);
        text_risk = (TextInputLayout)findViewById(R.id.text_risk);
        text_location = (TextInputLayout)findViewById(R.id.text_location);
        textbox_element_affected = (TextInputLayout)findViewById(R.id.textbox_element_affected);
        text_measures = (TextInputLayout)findViewById(R.id.text_measures);

        radio_vi = (RadioButton)findViewById(R.id.radio_vi);
        radio_lekki = (RadioButton)findViewById(R.id.radio_lekki);
        radio_ikoyi = (RadioButton)findViewById(R.id.radio_ikoyi);
        radio_mainland = (RadioButton)findViewById(R.id.radio_mainland);
        radio_island = (RadioButton)findViewById(R.id.radio_island);
        radio_mst = (RadioButton)findViewById(R.id.radio_mst);
        radio_distribution = (RadioButton)findViewById(R.id.radio_distribution);
        radio_feeder = (RadioButton)findViewById(R.id.radio_feeder);

        radiogroup_1 = (RadioGroup)findViewById(R.id.radiogroup_1);
        radiogroup_2 = (RadioGroup)findViewById(R.id.radiogroup_2);

        btn_save = (Button)findViewById(R.id.btn_save);
        btn_img = (ImageButton)findViewById(R.id.btn_img);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textbox_element_affected.setEnabled(false);
                switch (position){
                    case 0:
                        btn_save.setEnabled(false);
                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        btn_save.setEnabled(true);
                        text_element_affected = parent.getItemAtPosition(position).toString();
                        Toast.makeText(getApplicationContext(), "Selected: " + text_element_affected , Toast.LENGTH_SHORT).show();
                        break;
                    case 7:
                        btn_save.setEnabled(true);
                        textbox_element_affected.setEnabled(true);
                        text_element_affected = textbox_element_affected.getEditText().getText().toString().trim();
                        break;
                }

//                if (position > 0 && position < 7){
//                    btn_save.setEnabled(true);
//                    text_element_affected = parent.getItemAtPosition(position).toString();
//                    Toast.makeText(getApplicationContext(), "Selected: " + text_element_affected , Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    if (parent.getItemAtPosition(0).toString().equals("Select Affected Element")){
//                        btn_save.setEnabled(false);
//                    }
//                    if (parent.getItemAtPosition(7).toString().equals("Others")) {
//                    btn_save.setEnabled(true);
//                    textbox_element_affected.setEnabled(true);
//                    text_element_affected = textbox_element_affected.getEditText().getText().toString().trim();
//                    }
//                }
//                Toast.makeText(getApplicationContext(), text_element_affected, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        radiogroup_1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radio_general_1 = findViewById(checkedId);
                Toast.makeText(PreventiveMaintenance.this,  radio_general_1.getText(), Toast.LENGTH_SHORT).show();
                switch (radiogroup_1.getCheckedRadioButtonId())
                {
                    case R.id.radio_vi:

                    case R.id.radio_lekki:

                    case R.id.radio_ikoyi:

                    case R.id.radio_mainland:

                    case R.id.radio_island:
                        text_issue.setHint("What is the risk in " + radio_general_1.getText() + "?");
                    text_location.setHint("Where is the Location in " + radio_general_1.getText());
                    break;

                }
                regionName = radio_general_1.getText().toString().trim();

            }
        });

        radiogroup_2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radio_general_2 = findViewById(checkedId);
                Toast.makeText(PreventiveMaintenance.this, radio_general_2.getText(), Toast.LENGTH_SHORT).show();
                regionType = radio_general_2.getText().toString().trim();

            }
        });

        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileChooser();

            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(text_issue.getEditText().getText())){
                    text_issue.setError("*Yet to be filled");
                    btn_save.setEnabled(false);
                } else if (TextUtils.isEmpty(text_risk.getEditText().getText())){
                    text_risk.setError("*Yet to be filled");
                    btn_save.setEnabled(false);
                } else if (TextUtils.isEmpty(text_location.getEditText().getText())){
                    text_location.setError("*Yet to be filled");
                    btn_save.setEnabled(false);
                }
//                else if (TextUtils.isEmpty(text_element_affected.getEditText().getText())){
//                    text_element_affected.setError("*Yet to be filled");
//                    btn_save.setEnabled(false);
//                }
                else if (TextUtils.isEmpty(text_measures.getEditText().getText())){
                    text_measures.setError("*Yet to be filled");
                    btn_save.setEnabled(false);
                } else {
                    addItemToSheet();
                }
                btn_save.setEnabled(true);
            }

        });

    }

    private void addItemToSheet() {
        final String sIssue = text_issue.getEditText().getText().toString().trim();
        final String sRisk = text_risk.getEditText().getText().toString().trim();
        final String sLocation = text_location.getEditText().getText().toString().trim();
        sElement = text_element_affected;
        final String sMeasures = text_measures.getEditText().getText().toString().trim();
        final String sType = regionType;
        final String sName = regionName;

        if(sElement.equals("")){
            sElement = textbox_element_affected.getEditText().getText().toString().trim();
        }

        Log.e("null", "values" + sUserImage);

        if (sUserImage == null) {
            sUserImage = "";

        }
        if ("" != sUserImage) {
            final ProgressDialog loading = ProgressDialog.show(this, "Adding Data", "Please Wait");


            StringRequest stringRequest = new StringRequest(Request.Method.POST, updatedUrl
                    ,

                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), "Poor or No Internet Connection", Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("action", sheetMessage);
                    params.put("sIssue", sIssue);
                    params.put("sRisk", sRisk);
                    params.put("sLocation", sLocation);
                    params.put("sElement", sElement);
                    params.put("sMeasures", sMeasures);
                    params.put("sType", sType);
                    params.put("sName", sName);
                    params.put("sUserImage", sUserImage);
                    params.put("sEmail", email);
                    return params;
                }
            };

            int socketTimeOut = 30000;

            RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            stringRequest.setRetryPolicy(retryPolicy);

            RequestQueue queue = Volley.newRequestQueue(this);

            queue.add(stringRequest);
        } else {
            Toast.makeText(getApplicationContext(), "Image not added", Toast.LENGTH_SHORT).show();
        }
    }


    private void fileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select the Image"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){

            Uri filePath = data.getData();
            Toast.makeText(this, "Image added Successfully", Toast.LENGTH_LONG).show();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                rBitMap = getResizedBitmap(bitmap,250);
                sUserImage = getStringImage(rBitMap);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize){
        int width = image.getWidth();
        int height = image.getHeight();

        float bitMapRatio = (float) width / (float) height;

        if (bitMapRatio > 1){
            width = maxSize;
            height = (int) (width/bitMapRatio);

        }
        else {
            height = maxSize;
            width = (int) (height * bitMapRatio);

        }
        return Bitmap.createScaledBitmap(image,width,height,true);

    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }




}
