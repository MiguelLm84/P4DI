package com.miguel_lm.appmygarden.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.miguel_lm.appmygarden.R;
import com.miguel_lm.appmygarden.core.Planta;
import com.miguel_lm.appmygarden.core.RepositorioPlantas;


public class FragmentPlantas extends Fragment {

    private ListenerCambioPlanta listenerCambioPlanta;


    public static FragmentPlantas newInstance(ListenerCambioPlanta listenerCambioPlanta) {
        FragmentPlantas fragmentPlantas = new FragmentPlantas();
        fragmentPlantas.listenerCambioPlanta = listenerCambioPlanta;
        return fragmentPlantas;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_plantas, container, false);

        RepositorioPlantas repositorioPlantas = RepositorioPlantas.getInstance(getContext());

        // GIRASOL ///////////////////////////////////////////////////////////////////////////////////////////////
        SwitchMaterial switchPlantaGirasol = root.findViewById(R.id.switchPlantaGirasol);

        if(switchPlantaGirasol != null){
            // Cambiar el estado de la planta
            switchPlantaGirasol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    Planta planta = repositorioPlantas.buscarPlanta(getString(R.string.nomComunGirasol));
                    planta.setSeleccionada(isChecked ? 1 : 0);
                    repositorioPlantas.actualizarPlanta(planta);

                    if(!isChecked){
                        listenerCambioPlanta.actualizarListado();
                    }
                }
            });

            // mostrar selección actual en el switch
            switchPlantaGirasol.setChecked(repositorioPlantas.buscarPlanta(getString(R.string.nomComunGirasol)).getSeleccionada() == 1);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////
        SwitchMaterial switchPlantaRosa = root.findViewById(R.id.switchPlantaRosa);

        if(switchPlantaRosa != null){
            // Cambiar el estado de la planta
            switchPlantaRosa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    Planta planta = repositorioPlantas.buscarPlanta(getString(R.string.nomComunRosa));
                    planta.setSeleccionada(isChecked ? 1 : 0);
                    repositorioPlantas.actualizarPlanta(planta);

                    if(!isChecked){
                        listenerCambioPlanta.actualizarListado();
                    }
                }
            });

            // mostrar selección actual en el switch
            switchPlantaRosa.setChecked(repositorioPlantas.buscarPlanta(getString(R.string.nomComunRosa)).getSeleccionada() == 1);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////
        SwitchMaterial switchPlantaAloe = root.findViewById(R.id.switchPlantaAloe);

        if(switchPlantaAloe != null){
            // Cambiar el estado de la planta
            switchPlantaAloe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    Planta planta = repositorioPlantas.buscarPlanta(getString(R.string.nomComunAloe));
                    planta.setSeleccionada(isChecked ? 1 : 0);
                    repositorioPlantas.actualizarPlanta(planta);

                    if(!isChecked){
                        listenerCambioPlanta.actualizarListado();
                    }
                }
            });

            // mostrar selección actual en el switch
            switchPlantaAloe.setChecked(repositorioPlantas.buscarPlanta(getString(R.string.nomComunAloe)).getSeleccionada() == 1);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////
        SwitchMaterial switchPlantaClavel = root.findViewById(R.id.switchPlantaClavel);

        if(switchPlantaClavel != null){
            // Cambiar el estado de la planta
            switchPlantaClavel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    Planta planta = repositorioPlantas.buscarPlanta(getString(R.string.nomComunClavel));
                    planta.setSeleccionada(isChecked ? 1 : 0);
                    repositorioPlantas.actualizarPlanta(planta);

                    if(!isChecked){
                        listenerCambioPlanta.actualizarListado();
                    }
                }
            });

            // mostrar selección actual en el switch
            switchPlantaClavel.setChecked(repositorioPlantas.buscarPlanta(getString(R.string.nomComunClavel)).getSeleccionada() == 1);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////
        SwitchMaterial switchPlantaManzanilla = root.findViewById(R.id.switchPlantaManzanilla);

        if(switchPlantaManzanilla != null){
            // Cambiar el estado de la planta
            switchPlantaManzanilla.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    Planta planta = repositorioPlantas.buscarPlanta(getString(R.string.nomComunManzanilla));
                    planta.setSeleccionada(isChecked ? 1 : 0);
                    repositorioPlantas.actualizarPlanta(planta);

                    if(!isChecked){
                        listenerCambioPlanta.actualizarListado();
                    }
                }
            });

            // mostrar selección actual en el switch
            switchPlantaManzanilla.setChecked(repositorioPlantas.buscarPlanta(getString(R.string.nomComunManzanilla)).getSeleccionada() == 1);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////
        SwitchMaterial switchPlantaDienteDeLeon = root.findViewById(R.id.switchPlantaDienteDeLeon);

        if(switchPlantaDienteDeLeon != null){
            // Cambiar el estado de la planta
            switchPlantaDienteDeLeon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    Planta planta = repositorioPlantas.buscarPlanta(getString(R.string.nomComunDienteLeon));
                    planta.setSeleccionada(isChecked ? 1 : 0);
                    repositorioPlantas.actualizarPlanta(planta);

                    if(!isChecked){
                        listenerCambioPlanta.actualizarListado();
                    }
                }
            });

            // mostrar selección actual en el switch
            switchPlantaDienteDeLeon.setChecked(repositorioPlantas.buscarPlanta(getString(R.string.nomComunDienteLeon)).getSeleccionada() == 1);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////
        SwitchMaterial switchPlantaCactus = root.findViewById(R.id.switchPlantaCactus);

        if(switchPlantaCactus != null){
            // Cambiar el estado de la planta
            switchPlantaCactus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    Planta planta = repositorioPlantas.buscarPlanta(getString(R.string.nomComunCactus));
                    planta.setSeleccionada(isChecked ? 1 : 0);
                    repositorioPlantas.actualizarPlanta(planta);

                    if(!isChecked){
                        listenerCambioPlanta.actualizarListado();
                    }
                }
            });

            // mostrar selección actual en el switch
            switchPlantaCactus.setChecked(repositorioPlantas.buscarPlanta(getString(R.string.nomComunCactus)).getSeleccionada() == 1);
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////
        SwitchMaterial switchPlantaCrisantemo = root.findViewById(R.id.switchPlantaCrisantemo);

        if(switchPlantaCrisantemo != null){
            // Cambiar el estado de la planta
            switchPlantaCrisantemo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    Planta planta = repositorioPlantas.buscarPlanta(getString(R.string.nomComunCrisantemo));
                    planta.setSeleccionada(isChecked ? 1 : 0);
                    repositorioPlantas.actualizarPlanta(planta);

                    if(!isChecked){
                        listenerCambioPlanta.actualizarListado();
                    }
                }
            });

            // mostrar selección actual en el switch
            switchPlantaCrisantemo.setChecked(repositorioPlantas.buscarPlanta(getString(R.string.nomComunCrisantemo)).getSeleccionada() == 1);
        }

        return root;
    }
}