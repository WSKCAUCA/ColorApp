package com.caucaragp.worldskills.colorapp.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.caucaragp.worldskills.colorapp.R;
import com.caucaragp.worldskills.colorapp.models.GestorDB;
import com.caucaragp.worldskills.colorapp.models.Score;

import java.util.List;

public class Puntuacion extends AppCompatActivity implements View.OnClickListener {
    //Definición de variables
    Button btnSalir;
    TextView txtPrimero, txtSegundo, txtTercero, txtCuarto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion);
        //Creamos metodos
        inicializar();
        escucharBoton();
        ingresarPunutacion();
    }



    //Referenciamos los datos
    private void inicializar() {
        btnSalir = findViewById(R.id.btnSalir);
        txtPrimero = findViewById(R.id.txtPrimero);
        txtSegundo = findViewById(R.id.txtSegundo);
        txtTercero = findViewById(R.id.txtTercero);
        txtCuarto = findViewById(R.id.txtCuarto);
    }

    //Metodo que nos ayuda a iniciar los botones
    private void escucharBoton() {
        btnSalir.setOnClickListener(this);

    }

    //Método para ingresar la puntuación de juegos por defecto
    private void ingresarPunutacion() {
        GestorDB gestorDB = new GestorDB(this);
        List<Score> scores = gestorDB.scoreList();
        if (scores.size()>0){
            Score score = scores.get(0);
            txtPrimero.setText(Integer.toString(score.getPuntaje()));
        }else {
            Toast.makeText(this, "No hay punutaciones disponibles", Toast.LENGTH_LONG).show();
        }

        if (scores.size()>1){
            Score score = scores.get(1);
            txtSegundo.setText(Integer.toString(score.getPuntaje()));
        }

        if (scores.size()>2){
            Score score = scores.get(2);
            txtTercero.setText(Integer.toString(score.getPuntaje()));
        }

        if (scores.size()>3){
            Score score = scores.get(3);
            txtCuarto.setText(Integer.toString(score.getPuntaje()));
        }
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
