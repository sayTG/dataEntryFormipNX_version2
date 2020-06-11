package com.example.dataentryformipnx_2.login_package;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dataentryformipnx_2.MainActivity;
import com.example.dataentryformipnx_2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Log_in_class extends AppCompatActivity {
    private TextInputLayout text_email, text_password;
    private Button btn_login;

    private FirebaseAuth firebaseAuth;

    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_class);

        text_email = (TextInputLayout)findViewById(R.id.text_email);
        text_password = (TextInputLayout)findViewById(R.id.text_password);

        btn_login = (Button)findViewById(R.id.btn_login);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = text_email.getEditText().getText().toString().trim();
                String password = text_password.getEditText().getText().toString().trim();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    login(email, password);
                }
            }
        });

        updateUI();
        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        message = prefs.getString("keyName", null);



    }

    private void updateUI() {

        if (firebaseAuth.getCurrentUser() != null){

//            if(message != null) {
                Intent mainIntent = new Intent(Log_in_class.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
//            } else {
//                Intent mainIntent = new Intent(Log_in_class.this, Choose.class);
//                startActivity(mainIntent);

//            }
        }
    }



    private void login(String email, String password){
        final ProgressDialog loading = ProgressDialog.show(this, "Logging In", "Please wait");
        loading.setCancelable(true);
        loading.setCanceledOnTouchOutside(false);

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                loading.dismiss();

                if(task.isSuccessful()){
                    Intent chooseIntent = new Intent(Log_in_class.this, Choose.class);
                    startActivity(chooseIntent);
                    finish();
                    Toast.makeText(Log_in_class.this, "Login Successful", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Log_in_class.this, "Error: "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                }





            }
        });

    }
}
