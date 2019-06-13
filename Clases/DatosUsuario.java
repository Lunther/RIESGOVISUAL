package com.unad.datosusuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DatosUsuario extends AppCompatActivity {

    Button btnIngresar;
    EditText txt_Nombre, txt_Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario);

        txt_Nombre = findViewById(R.id.txt_Nombre);
        txt_Email = findViewById(R.id.txt_Email);
        btnIngresar = findViewById(R.id.btnIngresar);
    }

    //Comprobación de vacíos y envíar pantalla

    public void comprobar (View view)
    {
        String nombre = txt_Nombre.getText().toString();
        String mail = txt_Email.getText().toString();

        if(nombre.isEmpty())
        {
            txt_Nombre.setError("Ingrese un nombre");
            txt_Nombre.requestFocus();
        }
        else if (mail.isEmpty())
        {
            txt_Email.setError("Ingrese un mail");
            txt_Email.requestFocus();
        }
        else
        {
            Intent siguiente = new Intent(this, MainActivity.class);
            startActivity(siguiente);
        }
    }
}
