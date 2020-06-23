package com.example.dataentryformipnx_2.login_package;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.dataentryformipnx_2.MainActivity;
import com.example.dataentryformipnx_2.R;
import com.example.dataentryformipnx_2.choose_sheet.Others;
import com.example.dataentryformipnx_2.choose_sheet.PrecPearl;
import com.example.dataentryformipnx_2.choose_sheet.Summarus;
import com.example.dataentryformipnx_2.choose_sheet.Waasek;
import com.example.dataentryformipnx_2.ui.data_entry.CorrectiveMaintenance;
import com.example.dataentryformipnx_2.ui.home.HomeFragment;

public class Choose extends AppCompatActivity {
    private Button btn_prec, btn_summarus, btn_waasek, btn_others;

    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn_prec = (Button)findViewById(R.id.btn_prec);
        btn_summarus = (Button)findViewById(R.id.btn_summarus);
        btn_waasek = (Button)findViewById(R.id.btn_waasek);
        btn_others = (Button)findViewById(R.id.btn_others);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        btn_prec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String precPearl = "PrecPearl";
                Intent precIntent = new Intent(Choose.this, MainActivity.class);
                editor.putString("keyName", precPearl);
                editor.apply();
//                Intent intent = new Intent(Choose.this, CorrectiveMaintenance.class);
//                intent.putExtra("keyName", precPearl );
                startActivity(precIntent);
                finish();


            }
        });

        btn_summarus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sumarus = "Sumarus";
                Intent summarusIntent = new Intent(Choose.this, MainActivity.class);
                editor.putString("keyName", sumarus);
                editor.apply();
//                Intent intent = new Intent(Choose.this, CorrectiveMaintenance.class);
//                intent.putExtra("keyName", sumarus );
                startActivity(summarusIntent);
                finish();
            }
        });

        btn_waasek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String waasek = "Waasek";
                Intent waasekIntent = new Intent(Choose.this, MainActivity.class);
                editor.putString("keyName", waasek);
                editor.apply();
//                Intent intent = new Intent(Choose.this, CorrectiveMaintenance.class);
//                intent.putExtra("keyName", waasek );
                startActivity(waasekIntent);
                finish();

            }
        });

        btn_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String others = "Others";
                Intent othersIntent = new Intent(Choose.this, MainActivity.class);
                editor.putString("keyName", others);
                editor.apply();
//                Intent intent = new Intent(Choose.this, CorrectiveMaintenance.class);
//                intent.putExtra("keyName", others );
                startActivity(othersIntent);
                finish();

            }
        });

//        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
//        message = prefs.getString("keyName", null);
//
//        if (message != null){
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
//        }
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
