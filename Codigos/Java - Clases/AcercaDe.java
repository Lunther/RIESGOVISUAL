package com.unad.riesgovisual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AcercaDe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layo_acerca);
    }

    public void regresar (View view)
    {
        Intent volver = new Intent(this, Usuario.class);
        startActivity(volver);
    }


}
