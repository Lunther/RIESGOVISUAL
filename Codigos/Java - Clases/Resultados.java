package com.unad.riesgovisual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Resultados extends AppCompatActivity {

    private int coidgoEscritura = 1;
    private Button agentes;
    private EditText txtResumen;

    private Font tituloFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLUE);
    private Font valoracionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.ITALIC);
    private Font textoFont = new Font(Font.FontFamily.HELVETICA, 12);

    public static float tHurto, tHomicidio, tIncendio, tSabotaje;
    public static String nombreUsuario, emailUsuario, longitud, latitud, nombreEmpresa, nitEmpresa, nombreSede;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layo_resultados);


        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            tHurto = extras.getFloat("totalHurto");
            tHomicidio = extras.getFloat("totalHomicidio");
            tIncendio = extras.getFloat("totalIncendio");
            tSabotaje = extras.getFloat("totalSabotaje");
            nombreUsuario = extras.getString("nombreUsuario");
            emailUsuario = extras.getString("emailUsuario");
            latitud = extras.getString("latitud");
            longitud = extras.getString("longitud");
            nombreEmpresa = extras.getString("nombreEmpresa");
            nombreSede = extras.getString("nombreSede");
            nitEmpresa = extras.getString("nitEmpresa");
        }

        //Pintar el mapa de calor para Hurto
        if (tHurto < 6)
        {
            TextView HurtoBajo = (TextView) findViewById(R.id.Val1x2);
            HurtoBajo.setText("R1");
        }
        else if (tHurto > 6 && tHurto < 12)
        {
            TextView HurtoMedio = (TextView) findViewById(R.id.Val2x4);
            HurtoMedio.setText("R1");
        }
        else if (tHurto > 12 && tHurto < 18)
        {
            TextView HurtoFuerte = (TextView) findViewById(R.id.Val5x2);
            HurtoFuerte.setText("R1");
        }
        else
        {
            TextView HurtoAlto = (TextView) findViewById(R.id.Val5x5);
            HurtoAlto.setText("R1");
        }

        //Pintar el mapa de calor para Homicidio
        if (tHomicidio < 6)
        {
            TextView HomiBajo = (TextView) findViewById(R.id.Val2x1);
            HomiBajo.setText("R2");
        }
        else if (tHomicidio > 6 && tHomicidio < 12)
        {
            TextView HomiMedio = (TextView) findViewById(R.id.Val3x3);
            HomiMedio.setText("R2");
        }
        else if (tHomicidio > 12 && tHomicidio < 18)
        {
            TextView HomiFuerte = (TextView) findViewById(R.id.Val4x3);
            HomiFuerte.setText("R2");
        }
        else
        {
            TextView HomiAlto = (TextView) findViewById(R.id.Val4x5);
            HomiAlto.setText("R2");
        }

        //Pintar el mapa de calor para Incendio

        if (tIncendio < 6)
        {
            TextView IncenBajo = (TextView) findViewById(R.id.Val2x2);
            IncenBajo.setText("R3");
        }
        else if (tIncendio > 6 && tIncendio < 12)
        {
            TextView IncenMedio = (TextView) findViewById(R.id.Val3x1);
            IncenMedio.setText("R3");
        }
        else if (tIncendio > 12 && tIncendio < 18)
        {
            TextView IncenFuerte = (TextView) findViewById(R.id.Val2x5);
            IncenFuerte.setText("R3");
        }
        else
        {
            TextView IncenAlto = (TextView) findViewById(R.id.Val5x4);
            IncenAlto.setText("R3");
        }

        //Pintar el mapa de calor para sabotaje

        if (tSabotaje < 6)
        {
            TextView SaboBajo = (TextView) findViewById(R.id.Val1x3);
            SaboBajo.setText("R4");
        }
        else if (tSabotaje > 6 && tSabotaje < 12)
        {
            TextView SaboMedio = (TextView) findViewById(R.id.Val4x2);
            SaboMedio.setText("R4");
        }
        else if (tSabotaje > 12 && tSabotaje < 18)
        {
            TextView SaboFuerte = (TextView) findViewById(R.id.Val4x4);
            SaboFuerte.setText("R4");
        }
        else
        {
            TextView SaboAlto = (TextView) findViewById(R.id.Val5x3);
            SaboAlto.setText("R4");
        }

        agentes = (Button) findViewById(R.id.btnMapa);
        txtResumen = (EditText) findViewById(R.id.txtResumen);

        Button btnEnviar = findViewById(R.id.btnEmail);
        btnEnviar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendMail();
            }
        });


        agentes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                Intent inten = new Intent(Resultados.this,Agentes.class);
                inten.putExtra("latitud",latitud);
                inten.putExtra("longitud",longitud);
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
                if (ContextCompat.checkSelfPermission(Resultados.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(Resultados.this, "PDF Guardado - Documentos - RiesgoVisual ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    requerirPermisoEscritura();
                }
                createPDF();
            }

        });
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
                            ActivityCompat.requestPermissions(Resultados.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},coidgoEscritura);

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
                Toast.makeText(this, "Permiso adquirido, pulse de nuevo para descargar", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Permiso denegado para escribir", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void createPDF()
    {
        Document doc = new Document();
        String recomendaciones = txtResumen.getText().toString();

        try {
            String directory_path = Environment.getExternalStorageDirectory().getPath() + "/RiesgoVisual/";
            File dir = new File(directory_path);
            if (!dir.exists())
                dir.mkdirs();
            File file = new File(directory_path, "Valoracion.pdf");
            FileOutputStream fOut = new FileOutputStream(file);
            PdfWriter.getInstance(doc, fOut);
            doc.open();

            //Titulo PDF
            Paragraph titulo = new Paragraph("RIESGO VISUAL");
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            titulo.setFont(tituloFont);
            titulo.setSpacingAfter(20);
            doc.add(titulo);

            //Titulo EMPRESA
            Paragraph idenEmpresa = new Paragraph("EMPRESA Y SEDE VALORADA");
            idenEmpresa.setAlignment(Paragraph.ALIGN_CENTER);
            idenEmpresa.setFont(tituloFont);
            idenEmpresa.setSpacingAfter(20);
            doc.add(idenEmpresa);

            //Identificacion empresa
            Paragraph nomEmpresa = new Paragraph ("Nombre Empresa: " + nombreEmpresa);
            nomEmpresa.setFont(valoracionFont);
            nomEmpresa.setAlignment(Paragraph.ALIGN_JUSTIFIED);
            doc.add(nomEmpresa);

            //Identificacion nit empresa
            Paragraph empresaNIT = new Paragraph ("Nit Empresa: " + nitEmpresa);
            empresaNIT.setFont(valoracionFont);
            empresaNIT.setAlignment(Paragraph.ALIGN_JUSTIFIED);
            doc.add(empresaNIT);

            //Identificacion nombre sede
            Paragraph sedeNombre = new Paragraph ("Nombre Sede: " + nombreSede);
            sedeNombre.setFont(valoracionFont);
            sedeNombre.setAlignment(Paragraph.ALIGN_JUSTIFIED);
            doc.add(sedeNombre);

            //Coordenadas sede
            Paragraph coordenadasSede = new Paragraph ("Longitud: " + longitud + " Latitud: " + latitud);
            coordenadasSede.setFont(valoracionFont);
            coordenadasSede.setAlignment(Paragraph.ALIGN_JUSTIFIED);
            coordenadasSede.setSpacingAfter(20);
            doc.add(coordenadasSede);

            //Titulo PDF VALORACION
            Paragraph valoracion = new Paragraph("VALORACION DE RIESGOS");
            valoracion.setAlignment(Paragraph.ALIGN_CENTER);
            valoracion.setFont(tituloFont);
            valoracion.setSpacingAfter(20);
            doc.add(valoracion);

            //Zona Valoracion
            Paragraph Hurto = new Paragraph("Total Hurto: " + tHurto);
            Hurto.setAlignment(Paragraph.ALIGN_JUSTIFIED);
            Hurto.setFont(valoracionFont);
            doc.add(Hurto);

            //Zona Valoracion
            Paragraph Homicidio = new Paragraph("Total Homicidio: " + tHomicidio);
            Homicidio.setAlignment(Paragraph.ALIGN_JUSTIFIED);
            Homicidio.setFont(valoracionFont);
            doc.add(Homicidio);

            //Zona Valoracion
            Paragraph Incendio = new Paragraph("Total Incendio: " + tIncendio);
            Incendio.setAlignment(Paragraph.ALIGN_JUSTIFIED);
            Incendio.setFont(valoracionFont);
            doc.add(Incendio);

            //Zona Valoracion
            Paragraph Sabotaje = new Paragraph("Total Sabotaje: " + tSabotaje);
            Sabotaje.setAlignment(Paragraph.ALIGN_JUSTIFIED);
            Sabotaje.setFont(valoracionFont);
            Sabotaje.setSpacingAfter(30);
            doc.add(Sabotaje);

            //Titulo PDF RECOMENDACIONES
            Paragraph Recomendaicones = new Paragraph("RECOMENDACIONES DE SEGURIDAD");
            Recomendaicones.setFont(tituloFont);
            Recomendaicones.setAlignment(Paragraph.ALIGN_CENTER);
            Recomendaicones.setSpacingAfter(20);
            doc.add(Recomendaicones);

            //Recomendaciones de seguridad
            Paragraph TextoRecomienda = new Paragraph(recomendaciones);
            TextoRecomienda.setAlignment(Paragraph.ALIGN_JUSTIFIED);
            TextoRecomienda.setFont(textoFont);
            doc.add(TextoRecomienda);

        }
        catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        }
        finally
        {
            doc.close();
        }
    }

    private void sendMail()
    {
        String comentario = txtResumen.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Nueva Valoración");
        intent.putExtra(Intent.EXTRA_TEXT, "El riesgo de hurto obtuvo un valor de: " + tHurto + " .El riesgo de homicidio obtuvo un valor de: " + tHomicidio + " .El riesgo de Incendio obtuvo un valor de: " + tIncendio + " .El riesgo de sabotaje obtuvo un valor de " + tSabotaje + " .Por ende se recomienda: " + comentario);
        intent.setData(Uri.parse("mailto: " + emailUsuario));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void pasar (View view)
    {
        Intent siguiente = new Intent(Resultados.this, ValoracionAplicacion.class);
        startActivity(siguiente);
    }
}
