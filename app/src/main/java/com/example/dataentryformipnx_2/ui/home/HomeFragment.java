package com.example.dataentryformipnx_2.ui.home;

import androidx.appcompat.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.dataentryformipnx_2.R;

import org.w3c.dom.Text;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);


        CardView card_manager = (CardView) view.findViewById(R.id.card_manager);
        card_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone("+2348159694026");
            }

        });

        CardView card_osp = (CardView) view.findViewById(R.id.card_osp);
        card_osp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callOSP();

            }
        });

        CardView card_interns = (CardView) view.findViewById(R.id.card_intern);
        card_interns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callIntern();
            }
        });

        CardView card_backend = (CardView) view.findViewById(R.id.card_backend);
        card_backend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackend();

            }
        });




        return view;

    }

    private void callBackend() {
        final AlertDialog.Builder alertBackend = new AlertDialog.Builder(getActivity());
        View viewBackend = getLayoutInflater().inflate(R.layout.layout_backend, null);
        TextView text_tobi = (TextView) viewBackend.findViewById(R.id.text_tobi);
        TextView text_segun = (TextView) viewBackend.findViewById(R.id.text_segun);
        TextView text_sheriff = (TextView) viewBackend.findViewById(R.id.text_sheriff);

        text_tobi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Phone Call")
                        .setMessage("Do you want to call Samuel Oladosu Tobi ?")
                        .setIcon(R.drawable.ic_phone_black_24dp)
                        .setPositiveButton("CALL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "+2347017770093", null)));


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
        });

        text_segun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Phone Call")
                        .setMessage("Do you want to call Ayorinde Adesegun ?")
                        .setIcon(R.drawable.ic_phone_black_24dp)
                        .setPositiveButton("CALL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "+2347017761020", null)));


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
        });

        text_sheriff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Phone Call")
                        .setMessage("Do you want to call Adelaja Adegoke Sheriff ?")
                        .setIcon(R.drawable.ic_phone_black_24dp)
                        .setPositiveButton("CALL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "+2347086459882", null)));


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
        });

        alertBackend.setView(viewBackend);
        AlertDialog dialog = alertBackend.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    private void callIntern() {
        final AlertDialog.Builder alertIntern = new AlertDialog.Builder(getActivity());
        View viewIntern = getLayoutInflater().inflate(R.layout.layout_intern, null);
        TextView text_tg = viewIntern.findViewById(R.id.text_tg);
        TextView text_mololuwa = viewIntern.findViewById(R.id.text_mololuwa);

        text_tg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Phone Call")
                        .setMessage("Do you want to call Busari ThankGod ?")
                        .setIcon(R.drawable.ic_phone_black_24dp)
                        .setPositiveButton("CALL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "+2348168134287", null)));


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
        });

        text_mololuwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Phone Call")
                        .setMessage("Do you want to call Arogbodo Mololuwa ?")
                        .setIcon(R.drawable.ic_phone_black_24dp)
                        .setPositiveButton("CALL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "+2349020576156", null)));


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
        });

        alertIntern.setView(viewIntern);
        AlertDialog dialog = alertIntern.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();



    }

    private void callOSP() {
        final AlertDialog.Builder alertOSP = new AlertDialog.Builder(getActivity());
        View viewOSP = getLayoutInflater().inflate(R.layout.layout_osp, null);
        TextView text_obinna = (TextView) viewOSP.findViewById(R.id.text_obinna);
        TextView text_monday = (TextView) viewOSP.findViewById(R.id.text_monday);
        TextView text_ifeanyi = (TextView) viewOSP.findViewById(R.id.text_ifeanyi);
        TextView text_paul = (TextView) viewOSP.findViewById(R.id.text_paul);
        TextView text_samuel = (TextView) viewOSP.findViewById(R.id.text_samuel);
        TextView text_afeez = (TextView) viewOSP.findViewById(R.id.text_afeez);

        text_obinna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Phone Call")
                        .setMessage("Do you want to call Obinna Oforka ?")
                        .setIcon(R.drawable.ic_phone_black_24dp)
                        .setPositiveButton("CALL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "+2347017770034", null)));


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
        });

        text_monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Phone Call")
                        .setMessage("Do you want to call Monday Uyana ?")
                        .setIcon(R.drawable.ic_phone_black_24dp)
                        .setPositiveButton("CALL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "+2347017770070", null)));


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
        });

        text_ifeanyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Phone Call")
                        .setMessage("Do you want to call Ifeanyi Ikemenaogu?")
                        .setIcon(R.drawable.ic_phone_black_24dp)
                        .setPositiveButton("CALL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "+2347017770028", null)));


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
        });

        text_paul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Phone Call")
                        .setMessage("Do you want to call Paul Osisiogu  ?")
                        .setIcon(R.drawable.ic_phone_black_24dp)
                        .setPositiveButton("CALL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "+2347017770048", null)));


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
        });

        text_samuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Phone Call")
                        .setMessage("Do you want to call Samuel Nnaocha ?")
                        .setIcon(R.drawable.ic_phone_black_24dp)
                        .setPositiveButton("CALL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "+2348075094452", null)));


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
        });

        text_afeez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Phone Call")
                        .setMessage("Do you want to call Afeez Obiende ?")
                        .setIcon(R.drawable.ic_phone_black_24dp)
                        .setPositiveButton("CALL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "+2347017770043", null)));


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
        });

        alertOSP.setView(viewOSP);
        AlertDialog dialog = alertOSP.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();



    }

    private void dialContactPhone(final String phoneNumber){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        alertDialog.setTitle("Phone Call")
                .setMessage("Do you want to call the manager?")
                .setIcon(R.drawable.ic_phone_black_24dp)
                .setPositiveButton("CALL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));


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



