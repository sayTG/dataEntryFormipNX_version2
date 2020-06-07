package com.example.dataentryformipnx_2.ui.data_entry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dataentryformipnx_2.R;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class PreventiveMaintenance extends AppCompatActivity {
    private TextInputLayout text_issue, text_risk, text_location, text_element_affected, text_measures;

    private RadioGroup radiogroup_1, radiogroup_2;
    private RadioButton radio_vi, radio_lekki, radio_ikoyi, radio_mainland, radio_island,
            radio_mst, radio_distribution, radio_feeder, radio_general_1, radio_general_2;

    private Button btn_save;
    private ImageButton btn_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preventive_maintenance);

        text_issue = (TextInputLayout)findViewById(R.id.text_issue);
        text_risk = (TextInputLayout)findViewById(R.id.text_risk);
        text_location = (TextInputLayout)findViewById(R.id.text_location);
        text_element_affected = (TextInputLayout)findViewById(R.id.text_element_affected);
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

            }
        });

        radiogroup_2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radio_general_2 = findViewById(checkedId);
                Toast.makeText(PreventiveMaintenance.this, radio_general_2.getText(), Toast.LENGTH_SHORT).show();

            }
        });

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


    }




}
