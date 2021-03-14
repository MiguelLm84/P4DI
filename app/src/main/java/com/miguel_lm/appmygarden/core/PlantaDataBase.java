package com.miguel_lm.appmygarden.core;

import androidx.room.*;

@Database(entities = {com.miguel_lm.appmygarden.core.Planta.class}, version = 1, exportSchema = false)
public abstract class PlantaDataBase extends RoomDatabase {
    public abstract com.miguel_lm.appmygarden.core.DAOPlanta getDAOPlanta();
}
