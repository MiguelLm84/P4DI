package com.miguel_lm.appmygarden.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miguel_lm.appmygarden.R;
import com.miguel_lm.appmygarden.core.Planta;

import java.util.List;

public class AdapterPlantas extends RecyclerView.Adapter<ViewHolderPlanta> {

    private List<Planta> listPlanta;
    private final Context context;
    private final SeleccionarPlanta selecPlanta;

    public AdapterPlantas(final Context context, SeleccionarPlanta selecPlanta){
        this.context = context;
        this.selecPlanta = selecPlanta;
    }

    public void actualizarListado(List<Planta> listaPlantasSeleccionadas) {
        this.listPlanta = listaPlantasSeleccionadas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderPlanta onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.planta_card_view, parent, false);
        return new ViewHolderPlanta(v, selecPlanta);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPlanta holder, int position) {

        Planta plantaAPintar = listPlanta.get(position);
        holder.mostrarPlanta(plantaAPintar,context);
    }

    @Override
    public int getItemCount() {
        return listPlanta == null ? 0 : listPlanta.size();
    }
}
