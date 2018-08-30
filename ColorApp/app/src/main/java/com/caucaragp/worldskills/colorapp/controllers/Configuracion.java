package com.caucaragp.worldskills.colorapp.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.caucaragp.worldskills.colorapp.R;

public class Configuracion extends AppCompatActivity implements View.OnClickListener{
    //Declaración de variables
    RadioButton rbtnIntentos, rbtnTiempo;
    Button btnJugarC;
    EditText txtIntentos, txtTiempo, txtTiempoPa;
    SharedPreferences juegoC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        inizialite();
        escucharBotones();
        inputPreferences();

    }

    //Método para inicializar las vistas
    private void inizialite() {
        rbtnIntentos = findViewById(R.id.rbtnIntentos);
        rbtnTiempo = findViewById(R.id.rbtnTiempo);
        btnJugarC = findViewById(R.id.btnJugarC);
        txtIntentos = findViewById(R.id.txtIntentos);
        txtTiempo = findViewById(R.id.txtTiempo);
        txtTiempoPa = findViewById(R.id.txtTiempoPa);
    }

    //Método para cargar las preferencias del usuario para su tipo de juego modificadp
    private void inputPreferences() {
        juegoC = getSharedPreferences("juegoC",MODE_PRIVATE);
        int modo= juegoC.getInt("modo",1);
        int tiempo= juegoC.getInt("tiempo",30000);
        int intentos= juegoC.getInt("intentos",3);
        int tiempopalabra = juegoC.getInt("tiempoPalabra",3000);
        txtTiempo.setText(Integer.toString(tiempo));
        txtIntentos.setText(Integer.toString(intentos));
        txtTiempoPa.setText(Integer.toString(tiempopalabra));
        if (modo==1){
            rbtnIntentos.setChecked(true);
            txtIntentos.setEnabled(true);
            txtTiempo.setEnabled(false);
        }else {
            rbtnTiempo.setChecked(true);
            txtIntentos.setEnabled(false);
            txtTiempo.setEnabled(true);

        }
    }

    //Método para escuchar cuando se hace click a u botón
    private void escucharBotones() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rbtnIntentos:
                txtIntentos.setEnabled(true);
                txtTiempo.setEnabled(false);
                break;
            case R.id.rbtnTiempo:
                txtIntentos.setEnabled(false);
                txtTiempo.setEnabled(true);
                break;
            case R.id.btnJugarC:
                savePreferences();
                Intent intent = new Intent(Configuracion.this,JuegoC.class);
                break;
        }

    }

    private void savePreferences() {
        SharedPreferences.Editor editor = juegoC.edit();
        if (rbtnTiempo.isChecked()){
            editor.putInt("modo",1);
            int intentos = Integer.parseInt(txtIntentos.getText().toString());
            editor.putInt("intentos",intentos);
        }else {
            editor.putInt("modo",2);
            int tiempo = Integer.parseInt(txtIntentos.getText().toString());
            editor.putInt("tiempo",tiempo);
        }
        int tiempoPalabra = Integer.parseInt(txtTiempoPa.getText().toString());
        editor.putInt("tiempoPalabra",tiempoPalabra);

    }
}
