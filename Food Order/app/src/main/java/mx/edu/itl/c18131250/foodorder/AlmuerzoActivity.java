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
:*  Archivo     : AlmuerzoActivity.java
:*  Autor       : Valentin Herrera Castorena     18131250
:*  Fecha       : 01/Junio/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripci�n : Esta clase nos muestra en pantalla las opciones de almuerzo para
                    hacer pedido
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131250.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AlmuerzoActivity extends AppCompatActivity {

    private ListView lstvAlmuerzos;
    private ArrayList<String> seleccionado;
    private SQLiteDatabase db;

    public int precioAlm;
    public String nomAlm;

    LinearLayout layoutPrincipal, layoutBotones, layoutLista;
    Button btnContinuar, btnCancelar;

    int fondo = 0;

    //Variables que se mandaran entre actividades para almacenar los detalles del pedido
    String almuerzoSeleccionadoFinal    = "";
    String comidaSeleccionadaFinal      = "";
    String cenaSeleccionadaFinal        = "";
    String bebidasSeleccionadasFinal    = "";

    //Variables que se mandaran entre actividades para hacer el total del costo
    int costoTotal   = 0;

    @Override
    public void onBackPressed ()
    {
        Intent intent = new Intent( this, MainActivity.class );
        intent.putExtra ( "fondo", fondo );
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
        setContentView(R.layout.activity_almuerzo);

        lstvAlmuerzos = findViewById ( R.id.lstvAlmuerzos );

        BDSQLite bd = new BDSQLite(getApplication(), "Food Order", null, 1);
        db = bd.getReadableDatabase();

        String query = "select * from almuerzo";
        String almuerzo = "";
        Cursor c;

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice);

        try
        {
            c = db.rawQuery(query, null);

            if(c.moveToFirst())
            {
                do
                {
                    nomAlm = c.getString(1);
                    precioAlm = c.getInt(2);

                    almuerzo = "$" + precioAlm + ": " + nomAlm;

                    adaptador.add(almuerzo);
                }
                //Mientras exista registro en la tabla, asignaremos al arrayadapter el nombre del usuario
                while(c.moveToNext());

                //Una vez que termina el while, añade el array en el listView
                lstvAlmuerzos.setAdapter(adaptador);
            }
            else
            {
                Toast.makeText(getApplication(), "Error: No hay almuerzos registrados", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            //this.finish();
        }

        layoutPrincipal = (LinearLayout) findViewById(R.id.layAlmPrincipal);
        layoutBotones = (LinearLayout) findViewById(R.id.layBotones);
        layoutLista = (LinearLayout) findViewById(R.id.layLista);
        btnContinuar = (Button) findViewById(R.id.btnConfirm);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        fondo = getIntent().getIntExtra("fondo", 0);

        almuerzoSeleccionadoFinal   =   getIntent().getStringExtra("almSelec");
        comidaSeleccionadaFinal     =   getIntent().getStringExtra("comSelec");
        cenaSeleccionadaFinal       =   getIntent().getStringExtra("cenSelec");
        bebidasSeleccionadasFinal   =   getIntent().getStringExtra("bebSelec");

        costoTotal   = getIntent().getIntExtra("costo", 0);

        if(fondo == 1)
        {
            layoutPrincipal.setBackgroundColor(Color.GRAY);
            layoutBotones.setBackgroundColor(Color.GRAY);
            layoutLista.setBackgroundColor(Color.GRAY);

            lstvAlmuerzos.setBackgroundColor(Color.GRAY);

            btnContinuar.setBackgroundColor(Color.WHITE);
            btnContinuar.setTextColor(Color.BLACK);

            btnCancelar.setBackgroundColor(Color.WHITE);
            btnCancelar.setTextColor(Color.BLACK);
        }
        else
        {
            //Nada
        }
    }

    public void btnCancelarClick ( View v )
    {
        Intent intent = new Intent( this, MainActivity.class );
        intent.putExtra ( "fondo", fondo );
        intent.putExtra("almSelec", almuerzoSeleccionadoFinal);
        intent.putExtra("comSelec", comidaSeleccionadaFinal);
        intent.putExtra("cenSelec", cenaSeleccionadaFinal);
        intent.putExtra("bebSelec", bebidasSeleccionadasFinal);

        intent.putExtra("costo", costoTotal);

        startActivity ( intent );
        this.finish();
    }

    public void btnElementosSeleccionadosClick ( View v )
    {
        seleccionado = new ArrayList<>();
        int suma = 0;
        SparseBooleanArray elementosMarcados = lstvAlmuerzos.getCheckedItemPositions();

        int total [] = new int [elementosMarcados.size()];

        for(int i = 0; i < elementosMarcados.size(); i++)
        {
            int llave = elementosMarcados.keyAt(i);
            boolean valor = elementosMarcados.get(llave);

            if (valor)
            {
                seleccionado.add (lstvAlmuerzos.getItemAtPosition(llave).toString().substring(5));

                if(llave == 0)
                    total[i] = 49;
                else if(llave == 1)
                    total[i] = 29;
                else if(llave == 2)
                    total[i] = 59;
                else if(llave == 3)
                    total[i] = 39;
                else if(llave == 4)
                    total[i] = 50;
                else if(llave == 5)
                    total[i] = 40;
                else if(llave == 6)
                    total[i] = 29;
                else if(llave == 7)
                    total[i] = 39;
                else if(llave == 8)
                    total[i] = 49;
                else if(llave == 9)
                    total[i] = 59;
                else if(llave == 10)
                    total[i] = 69;
            }

        }

        for(int j = 0; j < elementosMarcados.size(); j++)
        {
            suma += total[j];
        }

        Toast.makeText(this, "Su seleccion de almuerzo fue: " + seleccionado + ", su cantidad a pagar es: $" + suma, Toast.LENGTH_LONG).show();

        String almuerzo = seleccionado.toString();
        costoTotal += suma;

        Intent intent = new Intent( this, MainActivity.class );

        intent.putExtra ( "costo", costoTotal);

        intent.putExtra ( "fondo", fondo );

        intent.putExtra("almSelec", almuerzo);
        intent.putExtra("comSelec", comidaSeleccionadaFinal);
        intent.putExtra("cenSelec", cenaSeleccionadaFinal);
        intent.putExtra("bebSelec", bebidasSeleccionadasFinal);

        intent.putExtra("costo", costoTotal);

        startActivity ( intent );
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate ( R.menu.menu_main_activity_sin_tamano, menu );
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch ( id ) {
            case R.id.mniFondoOscuro    :   layoutPrincipal.setBackgroundColor(Color.GRAY);
                                            layoutBotones.setBackgroundColor(Color.GRAY);
                                            layoutLista.setBackgroundColor(Color.GRAY);
                                            btnContinuar.setBackgroundColor(Color.WHITE);
                                            btnContinuar.setTextColor(Color.BLACK);

                                            btnCancelar.setBackgroundColor(Color.WHITE);
                                            btnCancelar.setTextColor(Color.BLACK);
                                            fondo = 1;
                                            break;
            case R.id.mniAcercaDe       :
                                            Intent intent2 = new Intent (this, AcercaDeActivity.class);
                                            startActivity(intent2);
                                            break;
            case R.id.mniReturn         :
                                            Intent intent = new Intent( this, MainActivity.class );
                                            intent.putExtra ( "fondo", fondo );
                                            intent.putExtra("almSelec", almuerzoSeleccionadoFinal);
                                            intent.putExtra("comSelec", comidaSeleccionadaFinal);
                                            intent.putExtra("cenSelec", cenaSeleccionadaFinal);
                                            intent.putExtra("bebSelec", bebidasSeleccionadasFinal);

                                            intent.putExtra("costo",    costoTotal);

                                            startActivity ( intent );
                                            this.finish();
            default                     :
                                            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}