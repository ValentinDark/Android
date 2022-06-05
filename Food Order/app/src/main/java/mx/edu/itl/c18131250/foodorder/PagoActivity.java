/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*            Actividad que muestra en pantalla la informacion de Acerca De
:*
:*  Archivo     : PagoActivity.java
:*  Autor       : Valentin Herrera Castorena     18131250
:*  Fecha       : 01/Junio/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripci�n : Esta clase nos muestra una pantalla en la cual procedemos a realizar el pago
                    de nuestro pedido, dando direccion y datos sobre una posible tarjeta.
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131250.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PagoActivity extends AppCompatActivity {

    private SQLiteDatabase db;

    public int idPed;

    Spinner spnMetodo;
    String opcion = "";
    int costoTotal = 0;
    int fondo = 0;

    LinearLayout layoutCredito, layoutPrincipal, layoutDireccion, layoutMetodo, layoutCvv, layoutNumero, layoutFinalizar;
    TextView titulo, direccion, metodo, cvv, numero, fecha;
    EditText multDireccion, multCvv, multNumero, multFecha;
    Button btnFinalizar;

    String almuerzoSeleccionadoFinal    = "";
    String comidaSeleccionadaFinal      = "";
    String cenaSeleccionadaFinal        = "";
    String bebidasSeleccionadasFinal    = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        spnMetodo = findViewById(R.id.spnMetodo);

        ArrayAdapter adaptador = ArrayAdapter.createFromResource(this, R.array.metodos_de_pago, android.R.layout.simple_spinner_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMetodo.setAdapter(adaptador);

        layoutCredito = findViewById(R.id.layCredito);
        layoutPrincipal = findViewById(R.id.layPrincipalP);
        layoutDireccion = findViewById(R.id.layDir);
        layoutMetodo = findViewById(R.id.layMetodo);
        layoutCvv = findViewById(R.id.layCVV);
        layoutNumero = findViewById(R.id.layNum);
        layoutFinalizar = findViewById(R.id.layFinalizar);

        titulo = findViewById(R.id.txtTitulo2);
        direccion = findViewById(R.id.txtDireccion);
        metodo = findViewById(R.id.txtMetodo);
        cvv = findViewById(R.id.txtCvv);
        numero = findViewById(R.id.txtNum);
        fecha = findViewById(R.id.txtFecha);

        multDireccion = findViewById(R.id.etxDir);
        multCvv = findViewById(R.id.edtCvv);
        multNumero = findViewById(R.id.edtNum);
        multFecha = findViewById(R.id.edtFecha);

        btnFinalizar = findViewById(R.id.buttonFinalizar);

        costoTotal   = getIntent().getIntExtra("costo", 0);
        fondo   = getIntent().getIntExtra("fondo", 0);

        almuerzoSeleccionadoFinal   =   getIntent().getStringExtra("almSelec");
        comidaSeleccionadaFinal     =   getIntent().getStringExtra("comSelec");
        cenaSeleccionadaFinal       =   getIntent().getStringExtra("cenSelec");
        bebidasSeleccionadasFinal   =   getIntent().getStringExtra("bebSelec");

        if (fondo == 1)
        {
            layoutCredito.setBackgroundColor(Color.GRAY);
            layoutPrincipal.setBackgroundColor(Color.GRAY);
            layoutDireccion.setBackgroundColor(Color.GRAY);
            layoutMetodo.setBackgroundColor(Color.GRAY);
            layoutCvv.setBackgroundColor(Color.GRAY);
            layoutNumero.setBackgroundColor(Color.GRAY);
            layoutFinalizar.setBackgroundColor(Color.GRAY);

            titulo.setTextColor(Color.WHITE);
            direccion.setTextColor(Color.WHITE);
            metodo.setTextColor(Color.WHITE);
            cvv.setTextColor(Color.WHITE);
            numero.setTextColor(Color.WHITE);
            fecha.setTextColor(Color.WHITE);

            multDireccion.setTextColor(Color.WHITE);
            multCvv.setTextColor(Color.WHITE);
            multNumero.setTextColor(Color.WHITE);
            multFecha.setTextColor(Color.WHITE);

            btnFinalizar.setBackgroundColor(Color.WHITE);
            btnFinalizar.setTextColor(Color.BLACK);

            spnMetodo.setBackgroundColor(Color.GRAY);

        }

        layoutCredito.setVisibility(View.INVISIBLE);

        spnMetodo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                   opcion = spnMetodo.getItemAtPosition(position).toString();

                   if(opcion.equals("Tarjeta de credito"))
                   {
                       layoutCredito.setVisibility(View.VISIBLE);
                   }
                   else
                   {
                       layoutCredito.setVisibility(View.INVISIBLE);
                   }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onBackPressed ()
    {
        Intent intent = new Intent( this, MainActivity.class );
        intent.putExtra ( "fondo", fondo );
        intent.putExtra("almSelec", almuerzoSeleccionadoFinal);
        intent.putExtra("comSelec", comidaSeleccionadaFinal);
        intent.putExtra("cenSelec", cenaSeleccionadaFinal);
        intent.putExtra("bebSelec", bebidasSeleccionadasFinal);

        intent.putExtra("costo",    costoTotal);

        startActivity ( intent );
        this.finish();
    }

    public void btnFinalizarClick (View v)
    {
        if(!multDireccion.getText().toString().equals(""))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirmar su pedido")
                    .setMessage("Su total a pagar sera: $" + costoTotal)
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            BDSQLite bd = new BDSQLite(getApplication(), "Food Order", null, 1);
                            db = bd.getWritableDatabase();

                            Random random = new Random();
                            String pedido = "";

                            if(almuerzoSeleccionadoFinal != "" && almuerzoSeleccionadoFinal != null)
                            {
                                pedido += almuerzoSeleccionadoFinal;
                            }
                            if(comidaSeleccionadaFinal != "" && comidaSeleccionadaFinal != null)
                            {
                                pedido += comidaSeleccionadaFinal;
                            }
                            if(cenaSeleccionadaFinal != "" && cenaSeleccionadaFinal != null)
                            {
                                pedido += cenaSeleccionadaFinal;
                            }
                            if(bebidasSeleccionadasFinal != "" && bebidasSeleccionadasFinal != null)
                            {
                                pedido += bebidasSeleccionadasFinal;
                            }

                            String query = "insert into pedido (idPed, descripcionPed, costoPed) values ("
                                    + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + ", " +
                                    "'"+ pedido +"', " +
                                    costoTotal + ")";

                            try
                            {
                                //Ejecutamos la consulta
                                db.execSQL(query);

                                Toast.makeText(PagoActivity.this, "Su pedido llegara pronto.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(PagoActivity.this, MainActivity.class);
                                startActivity(intent);
                                PagoActivity.this.finish();
                            }
                            catch (Exception e)
                            {
                                Toast.makeText(getApplication(), "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setCancelable(false).create().show();
        }
        else
        {
            Toast.makeText(PagoActivity.this, "Favor de ingresar su direccion.", Toast.LENGTH_SHORT).show();
        }
    }
}