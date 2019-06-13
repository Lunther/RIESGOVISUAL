package com.unad.valoraraplicacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.net.Uri;

public class ValorarAplicacion extends AppCompatActivity {

    private EditText textComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textComentario = findViewById(R.id.textComentario);

        Button btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendMail();
                finish();
            }
        });

        Button btnSalir = (Button) findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();

            }
        });
    }

    private void sendMail()
    {
        String comentario = textComentario.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Nuevo Comentario");
        intent.putExtra(Intent.EXTRA_TEXT, comentario);
        intent.setData(Uri.parse("mailto: " + "lunther_1@hotmail.com"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}