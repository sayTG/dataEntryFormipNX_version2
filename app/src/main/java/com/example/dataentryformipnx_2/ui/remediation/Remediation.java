package com.example.dataentryformipnx_2.ui.remediation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import com.example.dataentryformipnx_2.ui.data_entry.CorrectiveMaintenance;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Remediation extends Fragment {

    TextInputLayout text_location, text_observation, text_latitude, text_longitude, text_recommendation, text_status, text_element;

    Button btn_save;

    ImageButton btn_img;

    private int PICK_IMAGE_REQUEST = 1;

    String sUserImage ;

    Bitmap rBitMap;

    String email, message;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remediation, container,false);

        SharedPreferences prefs = getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        message = prefs.getString("keyName", null);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        email= firebaseAuth.getCurrentUser().getEmail();

        text_location = (TextInputLayout)view.findViewById(R.id.text_location);
        text_observation = (TextInputLayout)view.findViewById(R.id.text_observation);
        text_latitude = (TextInputLayout)view.findViewById(R.id.text_latitude);
        text_longitude = (TextInputLayout)view.findViewById(R.id.text_logitude);
        text_recommendation = (TextInputLayout)view.findViewById(R.id.text_recommendation);
        text_status = (TextInputLayout)view.findViewById(R.id.text_status);
        text_element = (TextInputLayout)view.findViewById(R.id.text_element_affected);

        btn_img = (ImageButton)view.findViewById(R.id.imageButton);

        btn_save = (Button)view.findViewById(R.id.button);

        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileChooser();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (message) {
                    case "PrecPearl":
                    case "Sumarus":
                    case "Waasek":
                       Toast.makeText(getContext(), "You are not allowed to post on this sheet \n Please use preventive sheet", Toast.LENGTH_LONG).show();
                       break;
                    case "Others":
                        addItemToSheet();
                        break;
                }

            }
        });


        return view;
    }

    private void addItemToSheet() {
        final String sLocation = text_location.getEditText().getText().toString().trim();
        final String sObservation = text_observation.getEditText().getText().toString().trim();
        final String sLatitude = text_latitude.getEditText().getText().toString().trim();
        final String sLongitude = text_longitude.getEditText().getText().toString().trim();
        final String sRecommendation = text_recommendation.getEditText().getText().toString().trim();
        final String sStatus = text_status.getEditText().getText().toString().trim();
        final String sElement = text_element.getEditText().getText().toString().trim();

        Log.e("null", "values" + sUserImage);

        if (sUserImage == null) {
            sUserImage = "";

        }
        if ("" != sUserImage) {
            final ProgressDialog loading = ProgressDialog.show(getContext(), "Adding Data", "Please Wait");


            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    "https://script.google.com/macros/s/AKfycbzNCKkM_wXL94pt-XozBJADGxVUHJLLpwTldE08gLvSWag0ZaY/exec",

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
                    params.put("action", "remediation");
                    params.put("sEmail", email);
                    params.put("sLocation", sLocation);
                    params.put("sObservation", sObservation);
                    params.put("sLatitude", sLatitude);
                    params.put("sLongitude", sLongitude);
                    params.put("sRecommendation", sRecommendation);
                    params.put("sStatus", sStatus);
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
