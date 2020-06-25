package com.example.dataentryformipnx_2.ui.critical;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;
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
import com.example.dataentryformipnx_2.SendGridAsyncTask;
import com.example.dataentryformipnx_2.login_package.Choose;
import com.example.dataentryformipnx_2.ui.data_entry.CorrectiveMaintenance;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


public class Critical extends Fragment {

    TextInputLayout text_location, text_risk, text_element;

    Button btn_save;

    ImageButton btn_img;

    private int PICK_IMAGE_REQUEST = 1;

    String sUserImage ;

    Bitmap rBitMap;

    String email;

    final String precPearl = "https://script.google.com/macros/s/AKfycbxjpctS3flQQeM9ftp39aouJbpXYWkt7xJvYeXbKAsy9VNOAY0/exec";
    final String sumarus = "https://script.google.com/macros/s/AKfycbxABv4KNCtwvK-OEDLxWqIbH49_zcm2JID0mc1_xp3HjpvWDv4/exec" ;
    final String waasek = "https://script.google.com/macros/s/AKfycbxvyylP_xV3sMdRkRJnwM5Jq90dBT2cyHsa0K4QtkZffXRwzCaj/exec";
    final String others = "https://script.google.com/macros/s/AKfycbzNCKkM_wXL94pt-XozBJADGxVUHJLLpwTldE08gLvSWag0ZaY/exec";

    String updatedUrl, message;

    String body, subject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_critical, container, false);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        email= firebaseAuth.getCurrentUser().getEmail();

        SharedPreferences prefs = getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        message = prefs.getString("keyName", null);

        if(message == null){
            Toast.makeText(getContext(), "You have not chosen where you belong to", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getContext(), Choose.class);
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

        text_location = (TextInputLayout)view.findViewById(R.id.text_location);
        text_risk = (TextInputLayout)view.findViewById(R.id.text_risk);
        text_element = (TextInputLayout)view.findViewById(R.id.text_element_affected);

        btn_img = (ImageButton)view.findViewById(R.id.btn_img);
        btn_save = (Button)view.findViewById(R.id.btn_save);

        final String[] to = new String[]{"busarithienkie28@gmail.com", "aoyebanji@ipnxnigeria.net"};

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                body =  "Risk: " + text_risk.getEditText().getText().toString().trim() + "\nElement Affected: "
                        + text_element.getEditText().getText().toString().trim();

                subject = "ipNX: Critical/Urgent Attention Needed @ " + text_location.getEditText().getText().toString().trim();

                addItemToSheet();
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0; i < to.length; i++) {
                            sendGridMail("busarithienkie@gmail.com", to[i],
                                    subject, body);
                        }
                    }
                });

            }
        });

        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileChooser();
            }
        });


        return view;
    }

    private void sendGridMail(String from, String to, String subject, String mailBody) {
        Hashtable<String, String > params = new Hashtable<>();
        params.put("to", to);
        params.put("from", from);
        params.put("subject", subject);
        params.put("text", mailBody);


        SendGridAsyncTask email = new SendGridAsyncTask();
        try{
            email.execute(params);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void addItemToSheet() {
        final String sLocation = text_location.getEditText().getText().toString().trim();
        final String sRisk = text_risk.getEditText().getText().toString().trim();
        final String sElement = text_element.getEditText().getText().toString().trim();

        Log.e("null", "values" + sUserImage);

        if (sUserImage == null) {
            sUserImage = "";

        }
        if ("" != sUserImage) {
            final ProgressDialog loading = ProgressDialog.show(getContext(), "Adding Data", "Please Wait");


            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    updatedUrl,

                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();
                            Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            loading.dismiss();
                            Toast.makeText(getContext(), "Poor or No Internet Connection", Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("action", "critical");
                    params.put("sEmail", email);
                    params.put("sLocation", sLocation);
                    params.put("sRisk", sRisk);
                    params.put("sElement", sElement);
                    params.put("sUserImage", sUserImage);

                    return params;
                }
            };

            int socketTimeOut = 30000;

            RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            stringRequest.setRetryPolicy(retryPolicy);

            RequestQueue queue = Volley.newRequestQueue(getContext());

            queue.add(stringRequest);
        } else {
            Toast.makeText(getContext(), "Image not added", Toast.LENGTH_SHORT).show();
        }
    }

    private void fileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select the Image"), PICK_IMAGE_REQUEST);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null){

            Uri filePath = data.getData();
            Toast.makeText(getContext(), "Image added Successfully", Toast.LENGTH_LONG).show();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filePath);
                rBitMap = getResizedBitmap(bitmap,250);
                sUserImage = getStringImage(rBitMap);
//                Toast.makeText(getContext(), sUserImage, Toast.LENGTH_LONG).show();
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
