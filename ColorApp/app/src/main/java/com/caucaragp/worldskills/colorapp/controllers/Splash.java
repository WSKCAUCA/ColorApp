package com.caucaragp.worldskills.colorapp.controllers;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.caucaragp.worldskills.colorapp.R;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    ImageView ImgSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        inputTimer();
        inicializar();

    }
    //Referenciamos los elementos que vamos a utilizar
    private void inicializar() {

        ImgSplash = findViewById(R.id.ImgSplash);
    }

    //Método para implementar el splash a la aplicación
    private void inputTimer() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this,Menu.class);
                startActivity(intent);
                finish();

            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,2000);
    }
}
