package com.caucaragp.worldskills.colorapp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.caucaragp.worldskills.colorapp.R;

public class Menu extends AppCompatActivity implements View.OnClickListener{
    //Declaraciónde variables
    Button btnJugar, btnPuntuacion, btnConfiguracion;
    int guardar=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        inizialite();
        escucharBotones();
    }

    //Método para inicializar las vistas
    private void inizialite() {
        btnJugar = findViewById(R.id.btnJugar);
        btnPuntuacion = findViewById(R.id.btnPuntuacion);
        btnConfiguracion = findViewById(R.id.btnConfiguracion);
    }

    //Método para escuchar los botones
    private void escucharBotones() {
        btnJugar.setOnClickListener(this);
        btnPuntuacion.setOnClickListener(this);
        btnConfiguracion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){

            case R.id.btnJugar:
                intent = new Intent(Menu.this,Juego.class);
                startActivity(intent);
                guardar=1;
                break;

            case R.id.btnPuntuacion:
                intent = new Intent(Menu.this,Puntuacion.class);
                startActivity(intent);
                guardar=0;
                break;

            case R.id.btnConfiguracion:
                intent = new Intent(Menu.this,Configuracion.class);
                startActivity(intent);
                guardar=0;
                break;
        }
    }
}
