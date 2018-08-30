package com.caucaragp.worldskills.colorapp.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.caucaragp.worldskills.colorapp.R;

public class Puntuacion extends AppCompatActivity implements View.OnClickListener {

    Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion);
        //Creamos metodos
        inicializar();
        escucharBoton();
    }
    //Metodo que nos ayuda a iniciar los botones
    private void escucharBoton() {
        btnSalir.setOnClickListener(this);

    }
    //Referenciamos los datos
    private void inicializar() {
        btnSalir = findViewById(R.id.btnSalir);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnSalir:
                finish();

                break;

        }
    }
}
