package com.miguel_lm.appmygarden.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.miguel_lm.appmygarden.R;

public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.splash_planta);

        new Handler().postDelayed(() -> startActivity(new Intent(ActivitySplash.this, ActivityPrincipal.class)), 1500);

        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setExitTransition(explode);
    }
}