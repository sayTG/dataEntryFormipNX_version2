package com.example.dataentryformipnx_2.ui.data_entry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dataentryformipnx_2.MainActivity;
import com.example.dataentryformipnx_2.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;

public class CorrectiveMaintenance extends AppCompatActivity {

    private TextInputLayout text_incident, text_pon, text_location, text_rfo, text_measures, text_failure;

    private RadioGroup radiogroup_1, radiogroup_2;
    private RadioButton radio_vi, radio_lekki, radio_ikoyi, radio_mainland, radio_island,
            radio_mst, radio_distribution, radio_feeder, radio_general_1, radio_general_2;

    private Button btn_save;
    private ImageButton btn_img;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corrective_maintenance);

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

            }
        });


    }

}
