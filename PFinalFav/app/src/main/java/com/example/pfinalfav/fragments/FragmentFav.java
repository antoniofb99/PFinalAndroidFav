package com.example.pfinalfav.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pfinalfav.R;
import com.example.pfinalfav.adaptadores.adaptadorFavoritos;
import com.example.pfinalfav.utils.Equipo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Iterator;

public class FragmentFav extends Fragment {
    private View view;
    private adaptadorFavoritos adaptadorFav;
    private RecyclerView recyclerFav;
    private String id;
    private FirebaseDatabase firebaseDatabase;




    public static FragmentFav newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("id", id);
        FragmentFav fragmentFav = new FragmentFav();
        fragmentFav.setArguments(args);
        return fragmentFav;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getArguments() != null) {
            id = getArguments().getString("id");
        }

        adaptadorFav = new adaptadorFavoritos(context);
        obtenerFavs();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragment_ligas, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        instancias();

        rellenarRecycler();

    }
    private void rellenarRecycler() {
        recyclerFav.setAdapter(adaptadorFav);
        recyclerFav.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    private void obtenerFavs() {
        firebaseDatabase = FirebaseDatabase.getInstance("https://pfinalfav-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference databaseReference = firebaseDatabase.getReference("usuarios").child(id).child("equiposFavoritos");
        databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot = task.getResult();
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    Log.v("id",id);
                    Equipo equipoFav = iterator.next().getValue(Equipo.class);
                    adaptadorFav.addFav(equipoFav);
                }
            }
        });
    }

    private void instancias() {
        recyclerFav = view.findViewById(R.id.recy_ligas);

    }
}
