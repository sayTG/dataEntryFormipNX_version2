package com.example.dataentryformipnx_2.ui.data_entry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.dataentryformipnx_2.R;
import com.google.android.material.textfield.TextInputLayout;

import static android.content.Context.MODE_PRIVATE;

public class Data_Entry extends Fragment {




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       final View view = inflater.inflate(R.layout.fragment_data_entry, container,false);

        SharedPreferences pref = getContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

       Button preventive = (Button) view.findViewById(R.id.btn_preventive);
               preventive.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               editor.putString("sheetName", "preventive");
               editor.apply();
               Intent preventiveIntent = new Intent(getContext(), PreventiveMaintenance.class);
               startActivity(preventiveIntent);

           }
       });

        Button corrective = (Button) view.findViewById(R.id.btn_corrective);
        corrective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("sheetName", "corrective");
                editor.apply();
                Intent correctiveIntent = new Intent(getContext(), CorrectiveMaintenance.class);
                startActivity(correctiveIntent);


            }
        });






       return view;
    }

}
