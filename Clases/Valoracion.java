package com.unad.valoracionriesgos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.math.BigDecimal;

public class Valoracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            public void onClick(View v) {

                //Riesgo 1 escenario 1
                final Spinner sp1 = (Spinner) findViewById(R.id.spr1e1pro);
                int n1 = Integer.parseInt(sp1.getSelectedItem().toString());

                final Spinner sp2 = (Spinner) findViewById(R.id.spr1e1con);
                int n2 = Integer.parseInt(sp2.getSelectedItem().toString());

                int R1 = n1 * n2;

                TextView T1 = findViewById(R.id.Val1);
                T1.setText(Integer.toString(R1));

                //Riesgo 2 escenario 1
                final Spinner sp3 = (Spinner) findViewById(R.id.spr2e1pro);
                int n3 = Integer.parseInt(sp3.getSelectedItem().toString());

                final Spinner sp4 = (Spinner) findViewById(R.id.spr2e1con);
                int n4 = Integer.parseInt(sp4.getSelectedItem().toString());

                int R2 = n3 * n4;

                TextView T2 = findViewById(R.id.Val2);
                T2.setText(Integer.toString(R2));

                //Riesgo 3 escenario 1
                final Spinner sp5 = (Spinner) findViewById(R.id.spr3e1pro);
                int n5 = Integer.parseInt(sp5.getSelectedItem().toString());

                final Spinner sp6 = (Spinner) findViewById(R.id.spr2e1con);
                int n6 = Integer.parseInt(sp6.getSelectedItem().toString());

                int R3 = n5 * n6;

                TextView T3 = findViewById(R.id.Val3);
                T3.setText(Integer.toString(R3));

                //Riesgo 4 escenario 1
                final Spinner sp7 = (Spinner) findViewById(R.id.spr4e1pro);
                int n7 = Integer.parseInt(sp7.getSelectedItem().toString());

                final Spinner sp8 = (Spinner) findViewById(R.id.spr4e1con);
                int n8 = Integer.parseInt(sp8.getSelectedItem().toString());

                int R4 = n7 * n8;

                TextView T4 = findViewById(R.id.Val4);
                T4.setText(Integer.toString(R4));

                //Riesgo 1 escenario 2
                final Spinner sp9 = (Spinner) findViewById(R.id.spr1e2pro);
                int n9 = Integer.parseInt(sp9.getSelectedItem().toString());

                final Spinner sp10 = (Spinner) findViewById(R.id.spr1e2con);
                int n10 = Integer.parseInt(sp10.getSelectedItem().toString());

                int R5 = n9 * n10;

                TextView T5 = findViewById(R.id.Val5);
                T5.setText(Integer.toString(R5));

                //Riesgo 2 escenario 2
                final Spinner sp11 = (Spinner) findViewById(R.id.spr2e2pro);
                int n11 = Integer.parseInt(sp11.getSelectedItem().toString());

                final Spinner sp12 = (Spinner) findViewById(R.id.spr2e2con);
                int n12 = Integer.parseInt(sp12.getSelectedItem().toString());

                int R6 = n11 * n12;

                TextView T6 = findViewById(R.id.Val6);
                T6.setText(Integer.toString(R6));

                //Riesgo 3 escenario 2
                final Spinner sp13 = (Spinner) findViewById(R.id.spr3e2pro);
                int n13 = Integer.parseInt(sp13.getSelectedItem().toString());

                final Spinner sp14 = (Spinner) findViewById(R.id.spr3e2con);
                int n14 = Integer.parseInt(sp14.getSelectedItem().toString());

                int R7 = n13 * n14;

                TextView T7 = findViewById(R.id.Val7);
                T7.setText(Integer.toString(R7));

                //Riesgo 4 escenario 2
                final Spinner sp15 = (Spinner) findViewById(R.id.spr4e2pro);
                int n15 = Integer.parseInt(sp15.getSelectedItem().toString());

                final Spinner sp16 = (Spinner) findViewById(R.id.spr4e2con);
                int n16 = Integer.parseInt(sp16.getSelectedItem().toString());

                int R8 = n15 * n16;

                TextView T8 = findViewById(R.id.Val8);
                T8.setText(Integer.toString(R8));

                //Riesgo 1 escenario 3
                final Spinner sp17 = (Spinner) findViewById(R.id.spr1e3pro);
                int n17 = Integer.parseInt(sp17.getSelectedItem().toString());

                final Spinner sp18 = (Spinner) findViewById(R.id.spr1e3con);
                int n18 = Integer.parseInt(sp18.getSelectedItem().toString());

                int R9 = n17 * n18;

                TextView T9 = findViewById(R.id.Val9);
                T9.setText(Integer.toString(R9));

                //Riesgo 2 escenario 3
                final Spinner sp19 = (Spinner) findViewById(R.id.spr2e3pro);
                int n19 = Integer.parseInt(sp19.getSelectedItem().toString());

                final Spinner sp20 = (Spinner) findViewById(R.id.spr2e3con);
                int n20 = Integer.parseInt(sp20.getSelectedItem().toString());

                int R10 = n19 * n20;

                TextView T10 = findViewById(R.id.Val10);
                T10.setText(Integer.toString(R10));

                //Riesgo 3 escenario 3
                final Spinner sp21 = (Spinner) findViewById(R.id.spr3e3pro);
                int n21 = Integer.parseInt(sp21.getSelectedItem().toString());

                final Spinner sp22 = (Spinner) findViewById(R.id.spr3e3con);
                int n22 = Integer.parseInt(sp22.getSelectedItem().toString());

                int R11 = n21 * n22;

                TextView T11 = findViewById(R.id.Val11);
                T11.setText(Integer.toString(R11));

                //Riesgo 4 escenario 3
                final Spinner sp23 = (Spinner) findViewById(R.id.spr4e3pro);
                int n23 = Integer.parseInt(sp23.getSelectedItem().toString());

                final Spinner sp24 = (Spinner) findViewById(R.id.spr4e3con);
                int n24 = Integer.parseInt(sp24.getSelectedItem().toString());

                int R12 = n23 * n24;

                TextView T12 = findViewById(R.id.Val12);
                T12.setText(Integer.toString(R12));

            }
        });
    }
}
