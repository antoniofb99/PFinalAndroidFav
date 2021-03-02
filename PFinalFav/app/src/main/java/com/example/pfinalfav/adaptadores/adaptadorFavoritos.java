package com.example.pfinalfav.adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pfinalfav.R;
import com.example.pfinalfav.utils.Equipo;

import java.util.ArrayList;

public class adaptadorFavoritos extends RecyclerView.Adapter<adaptadorFavoritos.MiHolder> {
    private Context context;
    private ArrayList<Equipo> listaFav;
    private adaptadorEquipos.OnEquipoListener listener;
    View v;

    public adaptadorFavoritos(Context context) {
        this.context = context;
        this.listaFav = new ArrayList<>();
        listener = (adaptadorEquipos.OnEquipoListener) context;
    }

    @NonNull
    @Override
    public MiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(context).inflate(R.layout.filas_equipos_fragment, parent, false);
        return new adaptadorFavoritos.MiHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MiHolder holder, int position) {
        final Equipo equipoActual = listaFav.get(position);
        Glide.with(context).load(equipoActual.getEscudo()).into(holder.imagenfav);
        holder.boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEquipoSelected(equipoActual);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaFav.size();

    }

    public void addFav(Equipo equipo) {
        listaFav.add(equipo);
        this.notifyDataSetChanged();
        Log.v("hola", String.valueOf(listaFav.size()));

    }


    class MiHolder extends RecyclerView.ViewHolder {
        ImageView imagenfav;
        Button boton;

        public MiHolder(@NonNull View itemView) {
            super(itemView);
            imagenfav = itemView.findViewById(R.id.imagenEquipo);
            boton = itemView.findViewById(R.id.botonVerDestalles);

        }
    }

}
