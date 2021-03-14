package com.miguel_lm.appmygarden.core;

import androidx.room.*;

import java.util.List;

@Dao
public interface DAOPlanta {

    @Delete
    void eliminarPlanta(Planta planta);

    @Insert
    void insertar(Planta planta);

    @Query("SELECT * FROM Plantas")
    List<Planta> obtenerPlantas();

    @Query("SELECT * FROM Plantas where seleccionada == 1")
    List<Planta> obtenerPlantasSeleccionadas();

    @Query("SELECT * FROM Plantas WHERE nombre LIKE :nombre")
    Planta buscarPlanta(String nombre);

    @Update
    void actualizarPlanta(Planta planta);

    @Query("DELETE FROM Plantas")
    void borrarDatos();

}
