package com.example.pfinalfav.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.bumptech.glide.Glide;
import com.example.pfinalfav.R;
import com.example.pfinalfav.dialogoRedes;
import com.example.pfinalfav.utils.Equipo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class fragmentDetalle extends Fragment implements View.OnClickListener {
    View v;
    Equipo equipo;
    private ImageView imagen;
    private TextView descripcionEquipo, nombreEquipo;
    private Button botonRedes, botonFavoritos;
    FirebaseDatabase firebaseDatabase;
    String uid;

    public static fragmentDetalle newInstance(Equipo equipo, String id ) {

        Bundle args = new Bundle();
        args.putSerializable("equipo", equipo);
        args.putString("id",id);
        fragmentDetalle fragment = new fragmentDetalle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getArguments() != null) {
            equipo = (Equipo) getArguments().getSerializable("equipo");
            uid = getArguments().getString("id");

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.layout_detalle, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        instancias();
        insertarDatos();
        acciones();
    }

    private void acciones() {
        botonRedes.setOnClickListener(this);
        botonFavoritos.setOnClickListener(this);
    }

    private void insertarDatos() {
        Glide.with(getContext()).load(equipo.getBanner()).into(imagen);
        nombreEquipo.setText(equipo.getNombre());
        descripcionEquipo.setText(equipo.getDescripcion());

    }

    private void instancias() {

        imagen = v.findViewById(R.id.imagen_detalle);
        nombreEquipo = v.findViewById(R.id.texto_detalle);
        descripcionEquipo = v.findViewById(R.id.descripcion);
        botonFavoritos = v.findViewById(R.id.boton_fav);
        botonRedes = v.findViewById(R.id.boton_redes);
        firebaseDatabase = FirebaseDatabase.getInstance("https://pfinalfav-default-rtdb.europe-west1.firebasedatabase.app/");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.boton_redes:
                dialogoRedes dialogo = dialogoRedes.newInstance(equipo);
                dialogo.show(getFragmentManager(), "dialogo");
                break;
            case R.id.boton_fav:

                DatabaseReference nodoReferencia = firebaseDatabase.getReference("usuarios").child(uid).child("equiposFavoritos").child(equipo.getNombre());
                nodoReferencia.setValue(equipo);
                Toast.makeText(getContext(), "Añadido Correctamente", Toast.LENGTH_LONG).show();

                break;

        }


    }
}
