package com.unad.riesgovisual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ValoracionRiesgos extends AppCompatActivity {

    public static float tHurto, tHomicidio, tIncendio, tSabotaje;
    public static String nombreUsuario, emailUsuario, longitud, latitud, nombreEmpresa, nitEmpresa, nombreSede;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layo_valoracion_riesgos);

        final Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnEnviar.setEnabled(false);

        Button btnSalir = (Button) findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        Button valorar = (Button) findViewById(R.id.btnValorar);
        valorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Riesgo 1 escenario 1
                final Spinner sp1 = (Spinner) findViewById(R.id.spr1e1pro);
                float n1 = Float.parseFloat(sp1.getSelectedItem().toString());

                final Spinner sp2 = (Spinner) findViewById(R.id.spr1e1con);
                float n2 = Float.parseFloat(sp2.getSelectedItem().toString());

                float R1 = n1 * n2;

                TextView T1 = findViewById(R.id.Val1);
                T1.setText(Float.toString(R1));

                //Riesgo 2 escenario 1
                final Spinner sp3 = (Spinner) findViewById(R.id.spr2e1pro);
                float n3 = Float.parseFloat(sp3.getSelectedItem().toString());

                final Spinner sp4 = (Spinner) findViewById(R.id.spr2e1con);
                float n4 = Float.parseFloat(sp4.getSelectedItem().toString());

                float R2 = n3 * n4;

                TextView T2 = findViewById(R.id.Val2);
                T2.setText(Float.toString(R2));

                //Riesgo 3 escenario 1
                final Spinner sp5 = (Spinner) findViewById(R.id.spr3e1pro);
                float n5 = Float.parseFloat(sp5.getSelectedItem().toString());

                final Spinner sp6 = (Spinner) findViewById(R.id.spr3e1con);
                float n6 = Float.parseFloat(sp6.getSelectedItem().toString());

                float R3 = n5 * n6;

                TextView T3 = findViewById(R.id.Val3);
                T3.setText(Float.toString(R3));

                //Riesgo 4 escenario 1
                final Spinner sp7 = (Spinner) findViewById(R.id.spr4e1pro);
                float n7 = Float.parseFloat(sp7.getSelectedItem().toString());

                final Spinner sp8 = (Spinner) findViewById(R.id.spr4e1con);
                float n8 = Float.parseFloat(sp8.getSelectedItem().toString());

                float R4 = n7 * n8;

                TextView T4 = findViewById(R.id.Val4);
                T4.setText(Float.toString(R4));

                //Riesgo 1 escenario 2
                final Spinner sp9 = (Spinner) findViewById(R.id.spr1e2pro);
                float n9 = Float.parseFloat(sp9.getSelectedItem().toString());

                final Spinner sp10 = (Spinner) findViewById(R.id.spr1e2con);
                float n10 = Float.parseFloat(sp10.getSelectedItem().toString());

                float R5 = n9 * n10;

                TextView T5 = findViewById(R.id.Val5);
                T5.setText(Float.toString(R5));

                //Riesgo 2 escenario 2
                final Spinner sp11 = (Spinner) findViewById(R.id.spr2e2pro);
                float n11 = Float.parseFloat(sp11.getSelectedItem().toString());

                final Spinner sp12 = (Spinner) findViewById(R.id.spr2e2con);
                float n12 = Float.parseFloat(sp12.getSelectedItem().toString());

                float R6 = n11 * n12;

                TextView T6 = findViewById(R.id.Val6);
                T6.setText(Float.toString(R6));

                //Riesgo 3 escenario 2
                final Spinner sp13 = (Spinner) findViewById(R.id.spr3e2pro);
                float n13 = Float.parseFloat(sp13.getSelectedItem().toString());

                final Spinner sp14 = (Spinner) findViewById(R.id.spr3e2con);
                float n14 = Float.parseFloat(sp14.getSelectedItem().toString());

                float R7 = n13 * n14;

                TextView T7 = findViewById(R.id.Val7);
                T7.setText(Float.toString(R7));

                //Riesgo 4 escenario 2
                final Spinner sp15 = (Spinner) findViewById(R.id.spr4e2pro);
                float n15 = Float.parseFloat(sp15.getSelectedItem().toString());

                final Spinner sp16 = (Spinner) findViewById(R.id.spr4e2con);
                float n16 = Float.parseFloat(sp16.getSelectedItem().toString());

                float R8 = n15 * n16;

                TextView T8 = findViewById(R.id.Val8);
                T8.setText(Float.toString(R8));

                //Riesgo 1 escenario 3
                final Spinner sp17 = (Spinner) findViewById(R.id.spr1e3pro);
                float n17 = Float.parseFloat(sp17.getSelectedItem().toString());

                final Spinner sp18 = (Spinner) findViewById(R.id.spr1e3con);
                float n18 = Float.parseFloat(sp18.getSelectedItem().toString());

                float R9 = n17 * n18;

                TextView T9 = findViewById(R.id.Val9);
                T9.setText(Float.toString(R9));

                //Riesgo 2 escenario 3
                final Spinner sp19 = (Spinner) findViewById(R.id.spr2e3pro);
                float n19 = Float.parseFloat(sp19.getSelectedItem().toString());

                final Spinner sp20 = (Spinner) findViewById(R.id.spr2e3con);
                float n20 = Float.parseFloat(sp20.getSelectedItem().toString());

                float R10 = n19 * n20;

                TextView T10 = findViewById(R.id.Val10);
                T10.setText(Float.toString(R10));

                //Riesgo 3 escenario 3
                final Spinner sp21 = (Spinner) findViewById(R.id.spr3e3pro);
                float n21 = Float.parseFloat(sp21.getSelectedItem().toString());

                final Spinner sp22 = (Spinner) findViewById(R.id.spr3e3con);
                float n22 = Float.parseFloat(sp22.getSelectedItem().toString());

                float R11 = n21 * n22;

                TextView T11 = findViewById(R.id.Val11);
                T11.setText(Float.toString(R11));

                //Riesgo 4 escenario 3
                final Spinner sp23 = (Spinner) findViewById(R.id.spr4e3pro);
                float n23 = Float.parseFloat(sp23.getSelectedItem().toString());

                final Spinner sp24 = (Spinner) findViewById(R.id.spr4e3con);
                float n24 = Float.parseFloat(sp24.getSelectedItem().toString());

                float R12 = n23 * n24;

                TextView T12 = findViewById(R.id.Val12);
                T12.setText(Float.toString(R12));

                tHurto = (R1+R5+R9)/3;
                tHomicidio = (R2+R6+R10)/3;
                tIncendio = (R3+R7+R11)/3;
                tSabotaje = (R4+R8+R12)/3;

                btnEnviar.setEnabled(true);

            }
        });
   }

    public void enviar (View view)

    {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                nombreUsuario = extras.getString("nombreUsuario");
                emailUsuario = extras.getString("emailUsuario");
                latitud = extras.getString("latitud");
                longitud = extras.getString("longitud");
                nombreEmpresa = extras.getString("nombreEmpresa");
                nombreSede = extras.getString("nombreSede");
                nitEmpresa = extras.getString("nitEmpresa");
            }
            Intent siguiente = new Intent(this, Resultados.class);
            //Nuevas variables a mover
            siguiente.putExtra("totalHurto", tHurto);
            siguiente.putExtra("totalHomicidio", tHomicidio);
            siguiente.putExtra("totalIncendio", tIncendio);
            siguiente.putExtra("totalSabotaje", tSabotaje);
            //Viejas variables
            siguiente.putExtra("latitud", latitud);
            siguiente.putExtra("longitud", longitud);
            siguiente.putExtra("nombreEmpresa", nombreEmpresa);
            siguiente.putExtra("nitEmpresa", nitEmpresa);
            siguiente.putExtra("nombreSede", nombreSede);
            siguiente.putExtra("nombreUsuario", nombreUsuario);
            siguiente.putExtra("emailUsuario", emailUsuario);
            startActivity(siguiente);
        }
}

