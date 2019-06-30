package com.unad.riesgovisual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Usuario extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;

    Button btnIngresar;
    EditText txt_Nombre, txt_Email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layo_usuario);

        txt_Nombre = findViewById(R.id.txt_Nombre);
        txt_Email = findViewById(R.id.txt_Email);
        btnIngresar = findViewById(R.id.btnIngresar);

    }


    public void comprobar (View view)
    {
        String nombre = txt_Nombre.getText().toString();
        String mail = txt_Email.getText().toString();

        if (nombre.isEmpty())
        {
            txt_Nombre.setError("Ingrese un nombre");
            txt_Nombre.requestFocus();
        }
        else if (mail.isEmpty())
        {
            txt_Email.setError("Ingrese un mail");
            txt_Email.requestFocus();
        } else
            {
                Intent siguiente = new Intent(this, Empresa.class);
                siguiente.putExtra("nombreUsuario",nombre);
                siguiente.putExtra("emailUsuario",mail);
                startActivity(siguiente);
            }
    }
}
