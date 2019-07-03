package com.unad.riesgovisual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
import android.widget.TextView;
import android.widget.Toast;

public class Empresa extends AppCompatActivity implements LocationListener {

    TextView latitud, longitud;
    LocationManager locationManager;
    public static String tvLongi;
    public static String tvLati;
    Button btnBuscar, btnSalir, btnEnviar;
    EditText txt_Nit, txt_Nombre_Empresa, txt_Sede;

    public static String nombreUsuario, emailUsuario;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layo_empresa);
        CheckPermission();

        latitud = (TextView) findViewById(R.id.txt_latitudValueTextGPS);
        longitud = (TextView) findViewById(R.id.txt_longitudeValueTextGPS);

        txt_Nit = findViewById(R.id.txt_NIT);
        txt_Nombre_Empresa = findViewById(R.id.txt_Nombre_Empresa);
        txt_Sede = findViewById(R.id.txt_Sede);

        final Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnEnviar.setEnabled(false);

        Button geo = (Button) findViewById(R.id.btnObtenerGeo);
        geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            btnEnviar.setEnabled(true);
            }

        });

        Button btnSalir = (Button) findViewById(R.id.btnSalir2);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }

    public void CheckPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
    }

    public void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        tvLongi = String.valueOf(location.getLongitude());
        tvLati = String.valueOf(location.getLatitude());

        // Setting Current Longitude
        latitud.setText(tvLati);
        // Setting Current Latitude
        longitud.setText(tvLongi);
    }
    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(Empresa.this, "Encienda la alta precisión en la configuración de su teléfono", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider!" + provider,
                Toast.LENGTH_SHORT).show();
    }


    public void comprobar (View view)
    {
        String nit = txt_Nit.getText().toString();
        String nombre = txt_Nombre_Empresa.getText().toString();
        String sede = txt_Sede.getText().toString();

        if(nit.isEmpty())
        {
            txt_Nit.setError("Ingrese un NIT");
            txt_Nit.requestFocus();
        }
        else if (nombre.isEmpty())
        {
            txt_Nombre_Empresa.setError("Ingrese el nombre de la empresa");
            txt_Nombre_Empresa.requestFocus();
        }
        else if (sede.isEmpty())
        {
            txt_Sede.setError("Ingrese el nombre de la sede");
            txt_Sede.requestFocus();
        }
        else
        {
            Bundle extras = getIntent().getExtras();
            if (extras != null)
            {
            nombreUsuario = extras.getString("nombreUsuario");
            emailUsuario = extras.getString("emailUsuario");
            }

            Intent siguiente = new Intent(this, ValoracionRiesgos.class);
            //Datos Nuevos
            siguiente.putExtra("latitud",latitud.getText().toString());
            siguiente.putExtra("longitud",longitud.getText().toString());
            siguiente.putExtra("nombreEmpresa",nombre);
            siguiente.putExtra("nitEmpresa",nit);
            siguiente.putExtra("nombreSede",sede);
            //Datos de la actividad anterior
            siguiente.putExtra("nombreUsuario",nombreUsuario);
            siguiente.putExtra("emailUsuario",emailUsuario);
            startActivity(siguiente);
        }
    }
}
