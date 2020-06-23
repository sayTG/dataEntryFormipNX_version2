package com.example.dataentryformipnx_2.login_package;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

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

        ImageView btn_signout = (ImageView) view.findViewById(R.id.btn_signout);
        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               signOut();
            }
        });
        return view;
    }

    private void signOut(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        alertDialog.setTitle("Sign Out")
                .setMessage("Do you want to sign out already?")
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setPositiveButton("Sign out", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseAuth.signOut();
                        SharedPreferences preferences = getContext().getSharedPreferences("MyPref", 0);
                        preferences.edit().remove("keyName").apply();
                        Intent signIntent = new Intent(getContext(), Log_in_class.class);
                        startActivity(signIntent);
                        getActivity().finish();


                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                })
                .show();


    }
}
