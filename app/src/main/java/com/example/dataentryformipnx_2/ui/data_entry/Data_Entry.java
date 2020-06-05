package com.example.dataentryformipnx_2.ui.data_entry;

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

import com.example.dataentryformipnx_2.R;
import com.google.android.material.textfield.TextInputLayout;

public class Data_Entry extends Fragment {

    private TextInputLayout text_incident, text_pon, text_location, text_rfo, text_measures, text_failure;

    private RadioGroup radiogroup_1, radiogroup_2;
    private RadioButton radio_vi, radio_lekki, radio_ikoyi, radio_mainland, radio_island,
                        radio_mst, radio_distribution, radio_feeder, radio_general_1, radio_general_2;

    private Button btn_save;
    private ImageButton btn_img;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       final View view = inflater.inflate(R.layout.fragment_data_entry, container,false);

       text_incident = (TextInputLayout)view.findViewById(R.id.text_incident);
       text_pon = (TextInputLayout)view.findViewById(R.id.text_pon);
       text_location = (TextInputLayout)view.findViewById(R.id.text_location);
       text_rfo = (TextInputLayout)view.findViewById(R.id.text_rfo);
       text_measures = (TextInputLayout)view.findViewById(R.id.text_measures);
       text_failure = (TextInputLayout)view.findViewById(R.id.text_failure);

       btn_img = (ImageButton)view.findViewById(R.id.btn_img);
       btn_save = (Button)view.findViewById(R.id.btn_save);

       radiogroup_1 = (RadioGroup)view.findViewById(R.id.radiogroup_1);
       radiogroup_2 = (RadioGroup)view.findViewById(R.id.radiogroup_2);

       btn_img.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


           }
       });


       btn_save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


           }
       });

       radiogroup_1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               radio_general_1 = view.findViewById(checkedId);
               Toast.makeText(getContext(), "You clicked: "+ radio_general_1.getText(), Toast.LENGTH_SHORT).show();
           }
       });

       radiogroup_2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               radio_general_2 = view.findViewById(checkedId);
               Toast.makeText(getContext(), "You clicked: "+ radio_general_2.getText(), Toast.LENGTH_SHORT).show();

           }
       });






       return view;
    }


}
