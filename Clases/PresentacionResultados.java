package com.unad.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.net.Uri;


public class PresentacionResultados extends AppCompatActivity {

    private Button agentes;
    private EditText txtResumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentacionresultados);

        agentes = (Button) findViewById(R.id.btnMapa);
        txtResumen = (EditText) findViewById(R.id.txtResumen);

        Button btnEnviar = findViewById(R.id.btnEmail);
        btnEnviar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendMail();
                finish();
            }
        });

        agentes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                Intent inten = new Intent(PresentacionResultados.this,MapaAgentes.class);
                startActivity(inten);
            }
        });

        Button btnSalir = (Button) findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void sendMail()
    {
        String comentario = txtResumen.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Nuevo Comentario");
        intent.putExtra(Intent.EXTRA_TEXT, comentario);
        intent.setData(Uri.parse("mailto: " + ""));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}