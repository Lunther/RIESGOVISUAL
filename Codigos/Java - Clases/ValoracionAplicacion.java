package com.unad.riesgovisual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ValoracionAplicacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layo_valorar_app);

        final EditText comentario = (EditText) findViewById(R.id.textComentario);

        Button btnFinal = findViewById(R.id.btnFinal2);

        btnFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                comentario.getText().clear();

                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Comentario enviado", Toast.LENGTH_SHORT);
                toast1.show();

            }
        });

    }

    public void nuevo (View view)
    {
        Intent nuevo = new Intent(this, Usuario.class);
        startActivity(nuevo);
    }
}
