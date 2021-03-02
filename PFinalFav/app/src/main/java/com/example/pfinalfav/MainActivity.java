package com.example.pfinalfav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.pfinalfav.adaptadores.adaptadorEquipos;
import com.example.pfinalfav.adaptadores.adaptadorLigas;
import com.example.pfinalfav.fragments.FragmenLigas;
import com.example.pfinalfav.fragments.FragmentFav;
import com.example.pfinalfav.fragments.fragmentDetalle;
import com.example.pfinalfav.fragments.fragmentEquipos;
import com.example.pfinalfav.utils.Equipo;
import com.example.pfinalfav.utils.Liga;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements adaptadorLigas.OnLigaListenner  , adaptadorEquipos.OnEquipoListener {


    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentManager fm;
    FragmentTransaction ft;
    String uid;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instancias();
        configurarBarra();
        acciones();
        Bundle  bundle = getIntent().getExtras();
        if (bundle!=null){
            uid = bundle.getString("id");
        }

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.sitio_fragment, new FragmenLigas(getApplicationContext()), "ligas");
        ft.addToBackStack("ligas");
        ft.commit();



    }

    private void acciones() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.menu_ver_todos:
                        fm = getSupportFragmentManager();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.sitio_fragment, new FragmenLigas(getApplicationContext()), "ligas");
                        ft.addToBackStack("ligas");
                        ft.commit();
                        break;

                    case R.id.menu_ver_favoritos:
                        fm = getSupportFragmentManager();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.sitio_fragment,  FragmentFav.newInstance(uid), "ligas");
                        ft.addToBackStack("ligas");
                        ft.commit();
                        break;

                    case R.id.menu_salir:

                        Toast.makeText(getApplicationContext(), "pulsad2121o", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawers();

                        break;


                }
                return true;
            }
        });

    }
    private void configurarBarra() {
        this.setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


    }
    private void instancias() {

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayaout);
        navigationView = findViewById(R.id.menu_navigation);



    }

    @Override
    public void onLigaSelected(Liga liga) {
        Toast.makeText(getApplicationContext(),liga.getNombre(),Toast.LENGTH_SHORT).show();
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.sitio_fragment, fragmentEquipos.newInstance(liga), "ligas");
        ft.addToBackStack("ligas");
        ft.commit();
    }

    @Override
    public void onEquipoSelected(Equipo equipo) {

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.sitio_fragment, fragmentDetalle.newInstance(equipo,uid), "ligas");
        ft.addToBackStack("ligas");
        ft.commit();

    }
}