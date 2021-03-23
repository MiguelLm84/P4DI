package com.miguel_lm.appmygarden.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.android.material.textview.MaterialTextView;
import com.miguel_lm.appmygarden.R;
import com.miguel_lm.appmygarden.core.Planta;

public class ActivityDetalle extends AppCompatActivity {

    private Planta planta;
    public static final String CLAVE_PLANTA = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        ImageView btn_volver = findViewById(R.id.btn_volver);

        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (recibiendoDatosTema()) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        planta = (Planta) getIntent().getSerializableExtra(CLAVE_PLANTA);
        seleccionPlantaInfo();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    private void seleccionPlantaInfo() {

        ImageView imagenPlanta = findViewById(R.id.imageViewPlanta);
        MaterialTextView tvTitulo = findViewById(R.id.tvNombre_InfoPlanta);
        MaterialTextView tvNomCientificoPlanta = findViewById(R.id.tvNomCientificoPlanta);
        MaterialTextView tvNomTemporadaPlanta = findViewById(R.id.tvTemporadaPlanta);
        WebView webViewPlanta = findViewById(R.id.webViewPlanta);

        int imagenId = getResources().getIdentifier(planta.getImagen(), "drawable", getPackageName());

        imagenPlanta.setImageResource(imagenId);
        tvTitulo.setText(planta.getNombre());
        tvNomCientificoPlanta.setText(planta.getNombreCientifico());
        tvNomTemporadaPlanta.setText(planta.getTemporada());

        webViewPlanta.getSettings().setJavaScriptEnabled(true);
        webViewPlanta.getSettings().setBuiltInZoomControls(true);
        webViewPlanta.getSettings().setDisplayZoomControls(false);

        webViewPlanta.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (request.getUrl().toString().contains("wikipedia")) return false;

                Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
                startActivity(intent);
                return true;
            }
        });
        webViewPlanta.loadUrl(planta.getDescripcion());
    }

    public boolean recibiendoDatosTema() {

        SharedPreferences sharedPreferences = this.getSharedPreferences("preferenciasTema", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("modoDark", false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}