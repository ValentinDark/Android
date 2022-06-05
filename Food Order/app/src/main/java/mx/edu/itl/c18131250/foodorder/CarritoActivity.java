/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*            Actividad que muestra en pantalla la informacion acerca del pedido y los pedidos anteriores.
:*
:*  Archivo     : CarritoActivity.java
:*  Autor       : Valentin Herrera Castorena     18131250
:*  Fecha       : 01/Junio/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripci�n : Esta clase nos muestra en pantalla la informacion sobre el pedido que esta en
                    proceso y el precio, ademas de una lista con pedidos anteriores que se hayan
                    realizado en la aplicacion.
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
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CarritoActivity extends AppCompatActivity {

    private ListView lstvPedidos;
    private SQLiteDatabase db;

    public int costoPed, idPed;
    public String descripcionPed;

    TextView txtPA, txtPedido, txtCosto;

    //Variables que se mandaran entre actividades para almacenar los detalles del pedido
    String almuerzoSeleccionadoFinal    = "";
    String comidaSeleccionadaFinal      = "";
    String cenaSeleccionadaFinal        = "";
    String bebidasSeleccionadasFinal    = "";
    int fondo = 0;

    //Variables que se mandaran entre actividades para hacer el total del costo
    int costoTotal   = 0;

    @Override
    public void onBackPressed ()
    {
        Intent intent = new Intent( this, MainActivity.class );
        intent.putExtra("almSelec", almuerzoSeleccionadoFinal);
        intent.putExtra("comSelec", comidaSeleccionadaFinal);
        intent.putExtra("cenSelec", cenaSeleccionadaFinal);
        intent.putExtra("bebSelec", bebidasSeleccionadasFinal);

        intent.putExtra("costo", costoTotal);

        startActivity ( intent );
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        lstvPedidos = findViewById(R.id.lstvPedidos);
        txtPA = (TextView) findViewById(R.id.txtPA);
        txtPedido = (TextView) findViewById(R.id.txtPedidoC);
        txtCosto = (TextView) findViewById(R.id.txtCostoC);

        almuerzoSeleccionadoFinal   =   getIntent().getStringExtra("almSelec");
        comidaSeleccionadaFinal     =   getIntent().getStringExtra("comSelec");
        cenaSeleccionadaFinal       =   getIntent().getStringExtra("cenSelec");
        bebidasSeleccionadasFinal   =   getIntent().getStringExtra("bebSelec");

        costoTotal   = getIntent().getIntExtra("costo", 0);

        String ped = "";

        if(almuerzoSeleccionadoFinal != "" && almuerzoSeleccionadoFinal != null)
        {
            ped += almuerzoSeleccionadoFinal;
        }
        if(comidaSeleccionadaFinal != "" && comidaSeleccionadaFinal != null)
        {
            ped += comidaSeleccionadaFinal;
        }
        if(cenaSeleccionadaFinal != "" && cenaSeleccionadaFinal != null)
        {
            ped += cenaSeleccionadaFinal;
        }
        if(bebidasSeleccionadasFinal != "" && bebidasSeleccionadasFinal != null)
        {
            ped += bebidasSeleccionadasFinal;
        }

        if(ped != null && ped != "")
        {
            txtPedido.setText(ped);
            txtCosto.setText("$" + costoTotal);
        }

        ArrayAdapter adaptador = new ArrayAdapter(this, R.layout.list_fila_sencilla);
        actualizarLista(adaptador);

        lstvPedidos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(CarritoActivity.this);
                builder.setTitle("¿Desea eliminar este pedido?")
                        .setMessage("No habra manera de recuperar la informacion del pedido.")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                String ped = ((TextView) view).getText().toString();
                                ped = ped.substring(8);
                                ped = ped.trim();

                                //Query eliminar
                                BDSQLite bd = new BDSQLite(getApplication(), "Food Order", null, 1);
                                db = bd.getReadableDatabase();

                                String query = "select idPed from pedido where descripcionPed = '" + ped +"'";
                                Cursor c;

                                try
                                {
                                    //Ejecutamos la consulta
                                    c = db.rawQuery(query, null);

                                    if(c.moveToFirst())
                                    {
                                        do
                                        {
                                            idPed = c.getInt(0);
                                        }
                                        //Mientras exista registro en la tabla, asignaremos al arrayadapter el nombre del usuario
                                        while(c.moveToNext());

                                        String delete = "delete from pedido where idPed = " + idPed;
                                        db.execSQL(delete);
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplication(), "Error: No se encontro el id", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e) {
                                    Toast.makeText(getApplication(), "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                //Actualizamos la lista
                                ArrayAdapter adaptador = new ArrayAdapter(CarritoActivity.this, R.layout.list_fila_sencilla);
                                actualizarLista(adaptador);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setCancelable( false ).create().show();
                return false;
            }
        });
    }

    public void actualizarLista(ArrayAdapter adaptador)
    {
        BDSQLite bd = new BDSQLite(getApplication(), "Food Order", null, 1);
        db = bd.getReadableDatabase();

        String query = "select * from pedido";
        String pedido = "";
        Cursor c;

        try
        {
            c = db.rawQuery(query, null);

            if(c.moveToFirst())
            {
                do
                {
                    descripcionPed = c.getString(1);
                    costoPed = c.getInt(2);

                    pedido =  "Pedido: " + descripcionPed + "\n" +
                            "$" + costoPed;

                    adaptador.add(pedido);
                }
                //Mientras exista registro en la tabla, asignaremos al arrayadapter el nombre del usuario
                while(c.moveToNext());

                //Una vez que termina el while, añade el array en el listView
                lstvPedidos.setAdapter(adaptador);
            }
            else
            {
                Toast.makeText(getApplication(), "No hay pedidos registrados", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            //this.finish();
        }
    }
}