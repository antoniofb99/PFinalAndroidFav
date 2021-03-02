package com.example.pfinalfav.adaptadores;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pfinalfav.R;
import com.example.pfinalfav.utils.Liga;


import java.util.ArrayList;

public class adaptadorLigas extends RecyclerView.Adapter<adaptadorLigas.Miholder> {
    ArrayList<Liga> listaLigas;
    private Context context;
    private OnLigaListenner listenner;

    public adaptadorLigas( Context context) {
        this.listaLigas = new ArrayList<>();
        this.context = context;
        listenner = (OnLigaListenner) context;
    }

    @NonNull
    @Override
    public Miholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fila_ligas_fragment, parent, false);
        return new Miholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Miholder holder, int position) {
        final Liga ligaActual = listaLigas.get(position);
        holder.getNombreLiga().setText(ligaActual.getNombre());

        holder.getNombreLiga().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenner.onLigaSelected(ligaActual);
            }
        });

    }



    public interface OnLigaListenner {
        void onLigaSelected(Liga liga);


    }

    @Override
    public int getItemCount() {
        return listaLigas.size();
    }


    public void  rellenarLista(Liga liga){
        listaLigas.add(liga);
        notifyDataSetChanged();
    }

    class Miholder extends RecyclerView.ViewHolder {
        TextView nombreLiga;

        public Miholder(@NonNull View itemView) {
            super(itemView);
            nombreLiga = itemView.findViewById(R.id.textoLigas);

        }

        public TextView getNombreLiga() {
            return nombreLiga;
        }

        public void setNombreLiga(TextView nombreLiga) {
            this.nombreLiga = nombreLiga;
        }
    }
}
