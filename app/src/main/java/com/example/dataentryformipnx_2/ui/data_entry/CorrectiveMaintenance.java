package com.example.dataentryformipnx_2.ui.data_entry;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CorrectiveMaintenance extends AppCompatActivity {

    private TextInputLayout text_incident, text_pon, text_location, text_rfo, text_measures, text_failure;

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corrective_maintenance);

        firebaseAuth = FirebaseAuth.getInstance();

        email= firebaseAuth.getCurrentUser().getEmail();

//        Intent intent = getIntent();
//        message = intent.getStringExtra("keyName") ;
        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        message = prefs.getString("keyName", null);
        sheetMessage = prefs.getString("sheetName", null);

//        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        if(message == null){
            Toast.makeText(getApplicationContext(), "You have not chosen where you belong to", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(CorrectiveMaintenance.this, Choose.class);
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


        text_incident = (TextInputLayout)findViewById(R.id.text_incident);
        text_pon = (TextInputLayout)findViewById(R.id.text_pon);
        text_location = (TextInputLayout)findViewById(R.id.text_location);
        text_rfo = (TextInputLayout)findViewById(R.id.text_rfo);
        text_measures = (TextInputLayout)findViewById(R.id.text_measures);
        text_failure = (TextInputLayout)findViewById(R.id.text_failure);

        radio_vi = (RadioButton)findViewById(R.id.radio_vi);
        radio_lekki = (RadioButton)findViewById(R.id.radio_lekki);
        radio_ikoyi = (RadioButton)findViewById(R.id.radio_ikoyi);
        radio_mainland = (RadioButton)findViewById(R.id.radio_mainland);
        radio_island = (RadioButton)findViewById(R.id.radio_island);
        radio_mst = (RadioButton)findViewById(R.id.radio_mst);
        radio_distribution = (RadioButton)findViewById(R.id.radio_distribution);
        radio_feeder = (RadioButton)findViewById(R.id.radio_feeder);

        btn_img = (ImageButton)findViewById(R.id.btn_img);
        btn_save = (Button)findViewById(R.id.btn_save);

        radiogroup_1 = (RadioGroup)findViewById(R.id.radiogroup_1);
        radiogroup_2 = (RadioGroup)findViewById(R.id.radiogroup_2);

//        if (TextUtils.isEmpty(text_incident.getEditText().getText()) || TextUtils.isEmpty(text_pon.getEditText().getText())
//                || TextUtils.isEmpty(text_location.getEditText().getText())  || TextUtils.isEmpty(text_rfo.getEditText().getText())
//                || TextUtils.isEmpty(text_measures.getEditText().getText())  || TextUtils.isEmpty(text_failure.getEditText().getText())){
//
//            btn_save.setEnabled(false);
//        }


        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileChooser();
            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(text_incident.getEditText().getText())){
                    text_incident.setError("*Yet to be filled");
                    btn_save.setEnabled(false);
                } else if (TextUtils.isEmpty(text_pon.getEditText().getText())){
                    text_pon.setError("*Yet to be filled");
                    btn_save.setEnabled(false);
                } else if (TextUtils.isEmpty(text_location.getEditText().getText())){
                    text_location.setError("*Yet to be filled");
                    btn_save.setEnabled(false);
                } else if (TextUtils.isEmpty(text_rfo.getEditText().getText())){
                    text_rfo.setError("*Yet to be filled");
                    btn_save.setEnabled(false);
                } else if (TextUtils.isEmpty(text_measures.getEditText().getText())){
                    text_measures.setError("*Yet to be filled");
                    btn_save.setEnabled(false);
                } else if (TextUtils.isEmpty(text_failure.getEditText().getText())){
                    text_failure.setError("*Yet to be filled");
                    btn_save.setEnabled(false);
                } else {
                    addItemToSheet();
                }
                btn_save.setEnabled(true);
            }

        });

        radiogroup_1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radio_general_1 = findViewById(checkedId);
                Toast.makeText(CorrectiveMaintenance.this,  radio_general_1.getText(), Toast.LENGTH_SHORT).show();
                switch (radiogroup_1.getCheckedRadioButtonId())
                {
                    case R.id.radio_vi:

                    case R.id.radio_lekki:

                    case R.id.radio_ikoyi:

                    case R.id.radio_mainland:

                    case R.id.radio_island:
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
                Toast.makeText(CorrectiveMaintenance.this,  radio_general_2.getText(), Toast.LENGTH_SHORT).show();
                switch (radiogroup_2.getCheckedRadioButtonId())
                {
                    case R.id.radio_mst:

                    case  R.id.radio_feeder:

                    case R.id.radio_distribution:
                        text_pon.setHint("Affected " + radio_general_2.getText());
                    text_rfo.setHint(radio_general_2.getText() + " RFO");
                    break;

                }
                regionType = radio_general_2.getText().toString().trim();

            }
        });


    }

    private void addItemToSheet() {
        final String sIncident = text_incident.getEditText().getText().toString().trim();
        final String sPON = text_pon.getEditText().getText().toString().trim();
        final String sLocation = text_location.getEditText().getText().toString().trim();
        final String sRFO = text_rfo.getEditText().getText().toString().trim();
        final String sMeasures = text_measures.getEditText().getText().toString().trim();
        final String sPoint = text_failure.getEditText().getText().toString().trim();
        final String sType = regionType;
        final String sName = regionName;

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
                    params.put("sIncident", sIncident);
                    params.put("sPON", sPON);
                    params.put("sLocation", sLocation);
                    params.put("sRFO", sRFO);
                    params.put("sMeasures", sMeasures);
                    params.put("sPoint", sPoint);
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
            Toast.makeText(CorrectiveMaintenance.this, "Image not added", Toast.LENGTH_SHORT).show();
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



}
