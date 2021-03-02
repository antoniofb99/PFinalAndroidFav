package com.example.pfinalfav.fragments;
;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pfinalfav.R;
import com.example.pfinalfav.adaptadores.adaptadorEquipos;
import com.example.pfinalfav.utils.Equipo;
import com.example.pfinalfav.utils.Liga;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class fragmentEquipos extends Fragment {
    Liga liga;
    private RecyclerView recyclerEquipo;
    private adaptadorEquipos adaptadorEquipo;
    private View view;

    public static fragmentEquipos newInstance(Liga liga) {

        Bundle args = new Bundle();
        args.putSerializable("liga",liga);
        fragmentEquipos fragment = new fragmentEquipos();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getArguments() != null){
            liga = (Liga) getArguments().getSerializable("liga");
            adaptadorEquipo = new adaptadorEquipos(getContext());
            rellenarLista();
        }
    }

    private void rellenarLista() {

        String url = String.format("https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=%s",liga.getId());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                procesarPeticion(response);
                Log.v("prueba","realizar peticion");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("prueba", error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }
    private void procesarPeticion(JSONObject response) {
        try {

            Log.v("prueba","procesar peticion");
            JSONArray arrayEquipos = response.getJSONArray("teams");
            for (int i = 0; i < arrayEquipos.length(); i++) {
                JSONObject equipo = arrayEquipos.getJSONObject(i);
                String nombre = equipo.getString("strTeam");
                String idTeam = equipo.getString("idTeam");
                String escudo = equipo.getString("strTeamBadge");
                String banner = equipo.getString("strTeamBanner");
                String facebook = equipo.getString("strFacebook");
                String twitter = equipo.getString("strTwitter");
                String descripcion= equipo.getString("strDescriptionEN");
                String web = equipo.getString("strWebsite");
                String insta = equipo.getString("strInstagram");

                adaptadorEquipo.rellenarLista(new Equipo( nombre, escudo,  descripcion,  banner,  idTeam, facebook, twitter,  insta, web));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragment_ligas,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        instancias();
        rellenarRecycler();
    }

    private void rellenarRecycler() {
        recyclerEquipo.setAdapter(adaptadorEquipo);
        recyclerEquipo.setLayoutManager(new GridLayoutManager(getContext(),2));
    }

    private void instancias() {
        recyclerEquipo = view.findViewById(R.id.recy_ligas);

    }

}


