package com.unad.infoempresa;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DatosEmpresa extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;

    Button btnBuscar, btnSalir, btnEnviar;
    EditText txt_Nit, txt_Nombre_Empresa, txt_Sede;
    TextView latitud;
    TextView longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_Nit = findViewById(R.id.txt_NIT);
        txt_Nombre_Empresa = findViewById(R.id.txt_Nombre_Empresa);
        txt_Sede = findViewById(R.id.txt_Sede);

        Button btnSalir = (Button) findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                updateLocationInfo(location);
            }
            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }
            @Override
            public void onProviderEnabled(String s) {
            }
            @Override
            public void onProviderDisabled(String s) {
            }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation != null) {
                updateLocationInfo(lastKnownLocation);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startListening();
        }
    }
    public void startListening() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }
    public void updateLocationInfo(Location location) {
        latitud = (TextView) findViewById(R.id.txt_latitudValueTextGPS);
        longitud = (TextView) findViewById(R.id.txt_longitudeValueTextGPS);
        latitud.setText(Double.toString(location.getLatitude()));
        longitud.setText(Double.toString(location.getLongitude()));
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
            Intent siguiente = new Intent(this, Exito.class);
            startActivity(siguiente);
        }
    }
}

