package com.caucaragp.worldskills.colorapp.controllers;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.caucaragp.worldskills.colorapp.R;

import java.util.ArrayList;
import java.util.List;

public class Juego extends AppCompatActivity implements View.OnClickListener{
    //Declaración de variables
    List<String> listaPalabras = new ArrayList<>();
    List<Integer> listaColores = new ArrayList<>();
    List<Integer> listaColoresTmp = new ArrayList<>();

    TextView txtCantidad, txtCorrectas, txtRestante, txtPalabra;
    ProgressBar pTiempo;
    FloatingActionButton btnPause;
    ImageButton btnColor1, btnColor2, btnColor3, btnColor4;
    boolean bandera = true;
    boolean bandera1 = true;
    int ab=0, valorcito, colorR, palabraR;
    public static int correctas, incorrectas;
    int cantidad, intentos;
    int [] milisegundos = {0,0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        inizialite();
        inputList();
        randomizar();
        inputValues();
        goGame();
    }

    //Método para iniciallizar variables
    private void inizialite() {
        txtCantidad = findViewById(R.id.txtCantidad);
        txtCorrectas = findViewById(R.id.txtCorrectas);
        txtRestante = findViewById(R.id.txtRestante);
        txtPalabra = findViewById(R.id.txtPalabra);
        pTiempo= findViewById(R.id.pTiempo);
        btnColor1 = findViewById(R.id.btnColor1);
        btnColor2 = findViewById(R.id.btnColor2);
        btnColor3 = findViewById(R.id.btnColor3);
        btnColor4 = findViewById(R.id.btnColor4);
        btnPause = findViewById(R.id.btnPause);

    }

    //Método para ingresar las listas que se van a necesitar
    private void inputList() {
        listaPalabras.add("AMARILLO");
    }

    //Método para hacer aleatorio la palabra, colores y botones
    private void randomizar() {
    }

    //Método para ingresar valores predeterminados
    private void inputValues() {
    }

    //Método para iniciar el juego
    private void goGame() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
