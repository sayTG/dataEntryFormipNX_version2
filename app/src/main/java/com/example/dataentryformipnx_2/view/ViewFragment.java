package com.example.dataentryformipnx_2.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dataentryformipnx_2.R;
import com.example.dataentryformipnx_2.ui.remediation.Remediation;

import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment extends Fragment {
    Button btn_preventive, btn_corrective, btn_remediation, btn_critical;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view, container, false);

        btn_preventive = (Button)view.findViewById(R.id.btn_preventive);
        btn_corrective = (Button)view.findViewById(R.id.btn_corrective);
        btn_critical = (Button)view.findViewById(R.id.btn_critical);
        btn_remediation =(Button)view.findViewById(R.id.btn_remediation);

        btn_preventive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent preventiveIntent = new Intent(getContext(), ViewPreventive.class);
                startActivity(preventiveIntent);

            }
        });

        btn_corrective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent correctiveIntent = new Intent(getContext(), ViewCorrective.class);
                startActivity(correctiveIntent);

            }
        });

        btn_remediation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent remediationIntent = new Intent(getContext(), ViewRemediation.class);
                startActivity(remediationIntent);

            }
        });

        btn_critical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent criticalIntent = new Intent(getContext(), ViewCritical.class);
                startActivity(criticalIntent);

            }
        });

        return view;
    }
}
