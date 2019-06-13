package com.unad.splashrv;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String carpetaFuente = "fonts/Papyrus.ttf";
        TextView vistaFuente = (TextView) findViewById(R.id.cambiaFuente);
        Typeface fuente = Typeface.createFromAsset(getAssets(), carpetaFuente);
        if (vistaFuente != null) {
            vistaFuente.setTypeface(fuente);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
            }
        }, 6000);
    }
}