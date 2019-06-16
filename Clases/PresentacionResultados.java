package com.unad.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.net.Uri;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class PresentacionResultados extends AppCompatActivity {

    private int coidgoEscritura = 1;
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

        Button descargar = (Button) findViewById(R.id.btnDescargar);
        descargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (ContextCompat.checkSelfPermission(PresentacionResultados.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(PresentacionResultados.this, "Creando PDF...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    requerirPermisoEscritura();
                }
                createPDF();
            }

        });
    }

    private void sendMail()
    {
        String comentario = txtResumen.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Nueva Valoración");
        intent.putExtra(Intent.EXTRA_TEXT, comentario);
        intent.setData(Uri.parse("mailto: " + ""));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void requerirPermisoEscritura()
    {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Necesidad del permiso")
                    .setMessage("El permiso es necesario para que la aplicación pueda escribir el PDF en su memoria")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(PresentacionResultados.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},coidgoEscritura);

                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }

        else
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},coidgoEscritura);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(requestCode == coidgoEscritura)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permiso adquirido", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Permiso denegado para escribir", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void createPDF() {
        //Crear el documento
        PdfDocument document = new PdfDocument();

        //Crear descripcion de la página
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(100, 100, 1).create();

        //Iniciamos la página, se espera que el contenido esté en una sola
        PdfDocument.Page page = document.startPage(pageInfo);

        //Definimos el contenido de la página

        //Finalizamos la página
        document.finishPage(page);

        //Escribimos la ruta de finalización
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/mypdf/";
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String targetPdf = directory_path+"Valoracion.pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "PDF Guardado", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e("main", "error "+e.toString());
            Toast.makeText(this, "Error: " + e.toString(),  Toast.LENGTH_LONG).show();
        }

        //Finalizamos el PDF
        document.close();
    }

}