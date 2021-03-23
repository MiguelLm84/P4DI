package com.miguel_lm.appmygarden.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.miguel_lm.appmygarden.R;
import com.miguel_lm.appmygarden.core.Planta;
import com.miguel_lm.appmygarden.core.RepositorioPlantas;

public class ActivityPrincipal extends AppCompatActivity implements ListenerCambioPlanta {

    private long tiempoParaSalir = 0;
    private Fragment_Mi_Jardin fragment_mi_jardin;
    private FragmentPlantas fragmentPlantas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        if (recibiendoDatosTema()) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        fragment_mi_jardin = new Fragment_Mi_Jardin();
        mostrarFragment(fragment_mi_jardin);

        guardarPlantasEnBD();

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment frag = null;
                switch (tab.getPosition()) {
                    case 0: {
                        if (fragment_mi_jardin == null)
                            fragment_mi_jardin = new Fragment_Mi_Jardin();
                        frag = fragment_mi_jardin;
                        break;
                    }
                    case 1: {
                        if (fragmentPlantas == null)
                            fragmentPlantas = FragmentPlantas.newInstance(ActivityPrincipal.this);
                        frag = fragmentPlantas;
                        break;
                    }
                }

                mostrarFragment(frag);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal_planta, menu);
         int FlagsModoOscuro = this.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        switch (FlagsModoOscuro) {

            case Configuration.UI_MODE_NIGHT_YES:
                menu.getItem(0).setIcon(R.drawable.moon);
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                menu.getItem(0).setIcon(R.drawable.sun);
                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                break;
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        int FlagsModoOscuro = this.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        if (id == R.id.light_dark_theme) {

            if (FlagsModoOscuro == Configuration.UI_MODE_NIGHT_YES) {
                item.setIcon(R.drawable.sun);
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                guardandoDatosTema(false);

            } else if (FlagsModoOscuro == Configuration.UI_MODE_NIGHT_NO) {

                item.setIcon(R.drawable.moon);
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                guardandoDatosTema(true);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void guardandoDatosTema(boolean modoDark) {

        SharedPreferences sharedPreferences = this.getSharedPreferences("preferenciasTema", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("modoDark", modoDark);
        editor.apply();
    }

    public boolean recibiendoDatosTema() {

        SharedPreferences sharedPreferences = this.getSharedPreferences("preferenciasTema", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("modoDark", false);
    }

    private void guardarPlantasEnBD() {

        Planta girasol = new Planta("girasol", getString(R.string.nomComunGirasol), getString(R.string.nomCientificoGirasol), getString(R.string.temporadaGirasol), getString(R.string.descripcionGirasol), 0);
        Planta rosa = new Planta("rosa", getString(R.string.nomComunRosa), getString(R.string.nomCientificoRosa), getString(R.string.temporadaRosa), getString(R.string.descripcionRosa), 0);
        Planta aloe = new Planta("aloe", getString(R.string.nomComunAloe), getString(R.string.nomCientificoAloe), getString(R.string.temporadaAloe), getString(R.string.descripcionAloe), 0);
        Planta clavel = new Planta("clavel", getString(R.string.nomComunClavel), getString(R.string.nomCientificoClavel), getString(R.string.temporadaClavel), getString(R.string.descripcionClavel), 0);
        Planta manzanilla = new Planta("manzanilla", getString(R.string.nomComunManzanilla), getString(R.string.nomCientificoManzanilla), getString(R.string.temporadaManzanilla), getString(R.string.descripcionManzanilla), 0);
        Planta diente_de_leon = new Planta("diente_de_leon", getString(R.string.nomComunDienteLeon), getString(R.string.nomCientificoDienteLeon), getString(R.string.temporadaDienteLeon), getString(R.string.descripcionDienteLeon), 0);
        Planta cactus = new Planta("cactus", getString(R.string.nomComunCactus), getString(R.string.nomCientificoCactus), getString(R.string.temporadaCactus), getString(R.string.descripcionCactus), 0);
        Planta crisantemo = new Planta("crisantemo", getString(R.string.nomComunCrisantemo), getString(R.string.nomCientificoCrisantemo), getString(R.string.temporadaCrisantemo), getString(R.string.descripcionCrisantemo), 0);

        RepositorioPlantas repositorioPlantas = RepositorioPlantas.getInstance(this);
        repositorioPlantas.insertar(girasol);
        repositorioPlantas.insertar(rosa);
        repositorioPlantas.insertar(aloe);
        repositorioPlantas.insertar(clavel);
        repositorioPlantas.insertar(manzanilla);
        repositorioPlantas.insertar(diente_de_leon);
        repositorioPlantas.insertar(cactus);
        repositorioPlantas.insertar(crisantemo);
    }

    private void mostrarFragment(Fragment fragment) {
        FragmentTransaction fragmentTransition = getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutFragments, fragment);
        fragmentTransition.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransition.commit();
    }

    @Override
    public void onBackPressed() {

        long tiempo = System.currentTimeMillis();
        if (tiempo - tiempoParaSalir > 3000) {
            tiempoParaSalir = tiempo;
            Toast.makeText(this, "Presione de nuevo 'Atrás' si desea salir",
                    Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void actualizarListado() {
        if (fragment_mi_jardin != null)
            fragment_mi_jardin.actualizarListado();
    }
}