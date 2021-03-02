package com.example.pfinalfav;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.pfinalfav.utils.Equipo;


public class dialogoRedes extends DialogFragment {
    View v;
    Equipo equipo;

    TextView textoInsta,textoTwitter,textoFace,textoWeb;


    public static dialogoRedes newInstance(Equipo equipo) {

        Bundle args = new Bundle();
        args.putSerializable("equipo",equipo);
        dialogoRedes fragment = new dialogoRedes();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getArguments() != null){
            equipo = (Equipo) getArguments().getSerializable("equipo");

        }
        v= View.inflate(context,R.layout.layout_dialogo,null);

        textoInsta = v.findViewById(R.id.textoInsta);
        textoTwitter = v.findViewById(R.id.textoTwitter);
        textoFace = v.findViewById(R.id.textoFacebook);
        textoWeb = v.findViewById(R.id.textoWeb);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Diálogo redes");


        textoInsta.setText(equipo.getInsta());
        textoTwitter.setText(equipo.getTwitter());
        textoFace.setText(equipo.getFacebook());
        textoWeb.setText(equipo.getWeb());



        builder.setView(v);




        return builder.create();
    }

}
