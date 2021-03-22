package com.miguel_lm.appmygarden.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.miguel_lm.appmygarden.R;
import com.miguel_lm.appmygarden.core.Planta;
import com.miguel_lm.appmygarden.core.RepositorioPlantas;

import java.util.ArrayList;
import java.util.List;

import static com.miguel_lm.appmygarden.ui.ActivityDetalle.CLAVE_PLANTA;


public class Fragment_Mi_Jardin extends Fragment implements SeleccionarPlanta {

    private AdapterPlantas adapterPlantas;
    private List<Planta> listaPlantasEscogidas;
    private TextView textViewNoPlantas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment__mi__jardin, container, false);
        textViewNoPlantas = root.findViewById(R.id.textViewNoPlantas);

        RecyclerView recyclerViewPlantas = root.findViewById(R.id.recyclerViewPlantas);
        recyclerViewPlantas.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapterPlantas = new AdapterPlantas(getContext(), this);
        recyclerViewPlantas.setAdapter(adapterPlantas);

        listaPlantasEscogidas = RepositorioPlantas.getInstance(getContext()).obtenerPlantasSeleccionadas();

        adapterPlantas.actualizarListado(listaPlantasEscogidas);

        comprobarElementos();

        return root;
    }

    @Override
    public void eliminarPlanta(Planta planta) {

        planta.setSeleccionada(0);
        RepositorioPlantas.getInstance(getContext()).actualizarPlanta(planta);

        listaPlantasEscogidas.remove(planta);
        adapterPlantas.actualizarListado(listaPlantasEscogidas);
        comprobarElementos();
    }

    @Override
    public void plantaInfoPulsado(Planta planta) {

        //TODO:Hacer acceso a una webView para mostrar los datos de las plantas a través de la web de wikipedia preferentemente.
        //TODO:Además, se debe controlar los enlaces que no son de wikipedia para que estas se abran en el navegador y no en la porpia web.

        Intent intent = new Intent(getContext(), ActivityDetalle.class);
        intent.putExtra(CLAVE_PLANTA, planta);
        startActivity(intent);
    }



    public void insertarPlantasEnRecycler() {

        final List<Planta> listaPlantasBD = RepositorioPlantas.getInstance(getContext()).obtenerPlantas();
        final List<Planta> listaPlantasParaDialogo = new ArrayList<>();
        for (Planta planta : listaPlantasBD) {

            boolean plantaEncontrada = false;
            for (Planta plantaBD : listaPlantasEscogidas) {

                if (plantaBD.getKey() == planta.getKey()) {
                    plantaEncontrada = true;
                    break;
                }
            }

            if (!plantaEncontrada) {
                listaPlantasParaDialogo.add(planta);
            }
        }

        if (listaPlantasParaDialogo.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setIcon(R.drawable.list);
            builder.setTitle("Añadir a lista");
            builder.setMessage("Lista vacia, no hay plantas seleccionables.");
            builder.setPositiveButton("Entendido", null);
            builder.show();

            return;
        }
    }

    private void comprobarElementos() {

        textViewNoPlantas.setVisibility(listaPlantasEscogidas.isEmpty() ? View.VISIBLE : View.GONE);
    }

    private void accionEliminar() {

        AlertDialog.Builder builderElimina = new AlertDialog.Builder(getContext());
        builderElimina.setIcon(R.drawable.remove_symbol);
        builderElimina.setTitle("Eliminar");

        String[] arrayPlantas = new String[listaPlantasEscogidas.size()];
        final boolean[] plantasSeleccionados = new boolean[listaPlantasEscogidas.size()];
        for (int i = 0; i < listaPlantasEscogidas.size(); i++)
            arrayPlantas[i] = listaPlantasEscogidas.get(i).getNombre();
        builderElimina.setMultiChoiceItems(arrayPlantas, plantasSeleccionados, (dialog, i, isChecked) -> plantasSeleccionados[i] = isChecked);

        builderElimina.setPositiveButton("Borrar", (dialog, which) -> {

            AlertDialog.Builder builderEliminar_Confirmar = new AlertDialog.Builder(getContext());
            builderEliminar_Confirmar.setIcon(R.drawable.exclamation);
            builderEliminar_Confirmar.setMessage("¿Eliminar los elementos?");
            builderEliminar_Confirmar.setNegativeButton("Cancelar", null);
            builderEliminar_Confirmar.setPositiveButton("Borrar", (dialogInterface, which1) -> {

                for (int i = listaPlantasEscogidas.size() - 1; i >= 0; i--) {
                    if (plantasSeleccionados[i]) {

                        Planta plantaEscogida = listaPlantasEscogidas.get(i);

                        plantaEscogida.setSeleccionada(0);
                        RepositorioPlantas.getInstance(getContext()).actualizarPlanta(plantaEscogida);

                        listaPlantasEscogidas.remove(i);
                    }
                }
                adapterPlantas.actualizarListado(listaPlantasEscogidas);
                comprobarElementos();

                Toast.makeText(getContext(), "Plantas eliminadas", Toast.LENGTH_SHORT).show();
            });
            builderEliminar_Confirmar.create().show();
        });
        builderElimina.setNegativeButton("Cancelar", null);
        builderElimina.create().show();
    }


    // AVISOS DESDE EL FRAGMENTPLANTAS de plantas que hay que quitar y poner
    public void actualizarListado() {

        listaPlantasEscogidas = RepositorioPlantas.getInstance(getContext()).obtenerPlantasSeleccionadas();

        adapterPlantas.actualizarListado(listaPlantasEscogidas);


    }

}