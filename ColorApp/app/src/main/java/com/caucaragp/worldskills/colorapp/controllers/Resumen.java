package com.caucaragp.worldskills.colorapp.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.caucaragp.worldskills.colorapp.R;
import com.caucaragp.worldskills.colorapp.models.GestorDB;
import com.caucaragp.worldskills.colorapp.models.Score;

public class Resumen extends AppCompatActivity {
    //Declaración de variables
    TextView txtCorrectas, txtIncorrectas;
    Button btnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
        inizialite();
        escucharboton();
        inputData();
    }

    //Método para inicializar las vistas
    private void inizialite() {
        txtCorrectas = findViewById(R.id.txtPalabrasCorrectas);
        txtIncorrectas = findViewById(R.id.txtPalabrasIncorrectas);
        btnHome = findViewById(R.id.btnHome);

    }

    //Método para poder escuchar el botón
    private void escucharboton() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //Método para ingresar los datos de la partida y si es juego por defecto guardar la partida
    private void inputData() {
        if (Menu.guardar==1){
            txtCorrectas.setText(Integer.toString(Juego.correctas));
            txtIncorrectas.setText(Integer.toString(Juego.incorrectas));
            Score score = new Score(Juego.correctas);
            GestorDB gestorDB = new GestorDB(this);
            gestorDB.inputData(score);
        }else {
            txtCorrectas.setText(Integer.toString(JuegoC.correctas));
            txtIncorrectas.setText(Integer.toString(JuegoC.incorrectas));
        }
    }
}
