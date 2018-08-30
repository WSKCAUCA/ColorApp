package com.caucaragp.worldskills.colorapp.controllers;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.caucaragp.worldskills.colorapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JuegoC extends AppCompatActivity implements View.OnClickListener{
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
    int ab=0, valorcito, colorR, palabraR, pausar, modo,tiempo,tiempoPalabra;
    public static int correctas, incorrectas;
    int cantidad, intentos;
    int [] milisegundos = {0,30000};
    SharedPreferences juegoC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_c);
        inizialite();
        escucharBotones();
        inputList();
        randomizar();
        inputValues();
        inputData();
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

    //Método para ingresar el escuchador a los botones
    private void escucharBotones() {
        btnColor1.setOnClickListener(this);
        btnColor2.setOnClickListener(this);
        btnColor3.setOnClickListener(this);
        btnColor4.setOnClickListener(this);
        btnPause.setOnClickListener(this);
    }

    //Método para ingresar las listas que se van a necesitar
    private void inputList() {
        listaPalabras = new ArrayList<>();
        listaColores = new ArrayList<>();
        listaPalabras.add("AMARILLO");
        listaColores.add(getColor(R.color.colorAmarillo));
        listaPalabras.add("AZUL");
        listaColores.add(getColor(R.color.colorAzul));
        listaPalabras.add("ROJO");
        listaColores.add(getColor(R.color.colorRojo));
        listaPalabras.add("VERDE");
        listaColores.add(getColor(R.color.colorVerde));
    }

    //Método para hacer aleatorio la palabra, colores y botones
    private void randomizar() {
        listaColoresTmp = listaColores;
        Collections.shuffle(listaColoresTmp);
        palabraR = (int) (Math.random() * 4);
        colorR = (int) (Math.random() * 4);
        txtPalabra.setText(listaPalabras.get(palabraR));
        txtPalabra.setTextColor(listaColores.get(colorR));

        btnColor1.setColorFilter(listaColoresTmp.get(0));
        btnColor2.setColorFilter(listaColoresTmp.get(1));
        btnColor3.setColorFilter(listaColoresTmp.get(2));
        btnColor4.setColorFilter(listaColoresTmp.get(3));

    }

    //Método para ingresar valores predeterminados
    private void inputValues() {
        juegoC = getSharedPreferences("juegoC",MODE_PRIVATE);
        modo=juegoC.getInt("modo",1);
        tiempoPalabra = juegoC.getInt("tiempoPalabra",3000);
        if (modo==1) {
            intentos = juegoC.getInt("intentos",3);
        }else {
            intentos=0;
        }

        correctas =0;
        incorrectas=0;
        bandera=true;
        bandera1=true;
        ab=0;
        if (modo==1) {
            pTiempo.setMax(30000);
            pTiempo.setProgress(30000);
        }else {
            tiempo=juegoC.getInt("tiempo",30000);
            pTiempo.setMax(tiempo);
            pTiempo.setProgress(tiempo);
        }

    }

    //Método para ingresar a los TextView la información cantidad de palabras, palabras correctas, intentos restantes
    private void inputData() {
        txtCantidad.setText(Integer.toString(cantidad));
        txtCorrectas.setText(Integer.toString(correctas));
        if (modo==1){
            txtRestante.setText(Integer.toString(intentos));
        }else {
            TextView txtCambia = findViewById(R.id.txtCambia);
            txtCambia.setText(getString(R.string.tiempofaltante));
            txtRestante.setText(Integer.toString(tiempo));
        }
    }

    //Método para iniciar el juego
    private void goGame() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (bandera){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (bandera1) {
                                if (milisegundos[0] == tiempoPalabra) {
                                    milisegundos[0] = 0;
                                    cantidad++;
                                    intentos--;
                                    incorrectas++;
                                    randomizar();
                                    inputData();
                                    endGame();
                                }
                                milisegundos[0]++;
                                milisegundos[1]--;
                                pTiempo.setProgress(milisegundos[1]);
                                if (modo==1) {

                                    if (milisegundos[1] == 0) {
                                        milisegundos[1] = 30000;
                                    }
                                }else {
                                    txtRestante.setText(Integer.toString(milisegundos[1]));
                                }

                                endGame();
                            }
                        }
                    });
                }
            }
        });
        thread.start();
    }

    //Método para finalizar el juego
    private void endGame() {
        if (ab==0 && ((modo==1 && intentos==0) || (modo==2 && tiempo==0))){
            btnColor1.setEnabled(false);
            btnColor2.setEnabled(false);
            btnColor3.setEnabled(false);
            btnColor4.setEnabled(false);
            ab=1;
            bandera=false;
            bandera1=false;
            Intent intent = new Intent(JuegoC.this,Resumen.class);
            startActivity(intent);
            finish();

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnColor1:
                valorcito=1;
                validar();
                break;

            case R.id.btnColor2:
                valorcito=2;
                validar();
                break;

            case R.id.btnColor3:
                valorcito=3;
                validar();
                break;

            case R.id.btnColor4:
                valorcito=4;
                validar();
                break;

            case R.id.btnPause:
                pauseGame();
                break;
        }
    }



    //Método para validar la jugada
    private void validar() {
        switch (valorcito){
            case 1:
                if (colorR==0){
                    correctas++;
                }else {
                    incorrectas++;
                    intentos--;
                }
                break;

            case 2:
                if (colorR==1){
                    correctas++;
                }else {
                    incorrectas++;
                    intentos--;
                }
                break;


            case 3:
                if (colorR==2){
                    correctas++;
                }else {
                    incorrectas++;
                    intentos--;
                }
                break;

            case 4:
                if (colorR==3){
                    correctas++;
                }else {
                    incorrectas++;
                    intentos--;
                }
                break;
        }
        cantidad++;
        endGame();
        randomizar();
        inputData();
        milisegundos[0]=0;

    }


    //Método para pausar el juego
    private void pauseGame() {
        pausar++;
        if (pausar<=2) {
            bandera1=false;
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.item_pause);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            Button btnContinuar = dialog.findViewById(R.id.btnContinuar);
            btnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    milisegundos[0]=0;
                    bandera1=true;
                    randomizar();
                    dialog.cancel();
                }
            });
            dialog.show();
        }else {
            Toast.makeText(this, "No puedes utilizar más pausas, el límite son dos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera=false;
        bandera1=false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        bandera1=false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        bandera1=false;
    }

}
