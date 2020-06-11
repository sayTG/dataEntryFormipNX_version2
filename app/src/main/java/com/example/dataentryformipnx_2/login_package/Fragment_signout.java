package com.example.dataentryformipnx_2.login_package;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dataentryformipnx_2.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_signout extends Fragment {
    FirebaseAuth firebaseAuth;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signout, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        Button btn_signout = (Button)view.findViewById(R.id.btn_signout);
        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                SharedPreferences preferences = getContext().getSharedPreferences("MyPref", 0);
                preferences.edit().remove("keyName").apply();
                Intent signIntent = new Intent(getContext(), Log_in_class.class);
                startActivity(signIntent);
                getActivity().finish();
            }
        });
        return view;
    }
}
