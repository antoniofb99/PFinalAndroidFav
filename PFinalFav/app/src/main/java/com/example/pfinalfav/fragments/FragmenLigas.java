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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pfinalfav.R;
import com.example.pfinalfav.adaptadores.adaptadorLigas;
import com.example.pfinalfav.utils.Liga;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmenLigas extends Fragment {
    RecyclerView recyLigas;
    private ArrayList<Liga> listaLigasFr;
    private com.example.pfinalfav.adaptadores.adaptadorLigas adaptadorLigas;
    View v;
    Context context;

    public FragmenLigas(Context context) {
        this.context = context;
        listaLigasFr = new ArrayList<>();
    }

    private void realizarPeticion() {

        String url = "https://www.thesportsdb.com/api/v1/json/1/all_leagues.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                procesarPeticion(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        //PILA
        RequestQueue requestQueue = Volley.newRequestQueue(this.context);
        requestQueue.add(jsonObjectRequest);


    }

    private void procesarPeticion(JSONObject response) {
        try {
            JSONArray ligasarray = response.getJSONArray("leagues");
            //Log.v("prueba", ligasarray.toString());

            for (int i = 0; i < ligasarray.length(); i++) {
                JSONObject liga = (JSONObject) ligasarray.get(i);
                String ligaSacada = liga.getString("strLeague");
                String idLiga = liga.getString("idLeague");
                String deporte = liga.getString("strSport");
                Log.v("prueba", ligaSacada);
                Log.v("prueba", idLiga);
                Log.v("prueba", deporte);


                if (deporte.toString().equals("Soccer")) {
                    Liga ligaPasar = new Liga(ligaSacada, idLiga);
                    adaptadorLigas.rellenarLista(ligaPasar);
                    Log.v("tamaño", String.valueOf(listaLigasFr.size()));
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        adaptadorLigas = new adaptadorLigas(getContext());
        realizarPeticion();


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.layout_fragment_ligas, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        instancias();
        rellenarRecy();

    }

    private void rellenarRecy() {
        recyLigas.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyLigas.setAdapter(adaptadorLigas);
        adaptadorLigas.notifyDataSetChanged();
    }

    private void instancias() {
        recyLigas = v.findViewById(R.id.recy_ligas);
    }
}
