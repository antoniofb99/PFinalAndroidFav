package com.example.pfinalfav.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pfinalfav.R;
import com.example.pfinalfav.utils.Equipo;


import java.util.ArrayList;

public class adaptadorEquipos extends RecyclerView.Adapter<adaptadorEquipos.MiHolder> {

    private Context context;
    private ArrayList<Equipo> listaEquipo;
    private OnEquipoListener listener;

    public adaptadorEquipos(Context context) {
        this.context = context;
        this.listaEquipo = new ArrayList<>();
        this.listener = (OnEquipoListener) context;
    }

    @NonNull
    @Override
    public MiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.filas_equipos_fragment, parent, false);

        return new MiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiHolder holder, int position) {
        final Equipo equipoActual = listaEquipo.get(position);
        Glide.with(context).load(equipoActual.getEscudo()).into(holder.imagen);
        holder.boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEquipoSelected(equipoActual);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listaEquipo.size();
    }

    public void rellenarLista(Equipo equipo) {
        listaEquipo.add(equipo);
        notifyDataSetChanged();
    }

    public interface OnEquipoListener {
        void onEquipoSelected(Equipo equipo);
    }


    class MiHolder extends RecyclerView.ViewHolder {

        private ImageView imagen;
        private Button boton;

        public MiHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imagenEquipo);
            boton = itemView.findViewById(R.id.botonVerDestalles);
        }

        public ImageView getImagen() {
            return imagen;
        }

        public Button getBoton() {
            return boton;
        }
    }

}
