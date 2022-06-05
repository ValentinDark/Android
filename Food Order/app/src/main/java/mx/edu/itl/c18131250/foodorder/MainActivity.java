/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*            Actividad que muestra en pantalla el menu principal
:*
:*  Archivo     : MainActivity.java
:*  Autor       : Valentin Herrera Castorena     18131250
:*  Fecha       : 01/Junio/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripci�n : Esta clase nos muestra una pantalla la cual contiene lo inicial de nuestra aplicacion
                    con un GridView el cual almacena las opciones para:
                    -Almuerzo
                    -Comidas
                    -Cena
                    -Bebidas
                    y continuar con la compra de nuestro pedido
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131250.foodorder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    final Integer [] opciones = {   R.drawable.almuerzo,    R.drawable.comida,
                                    R.drawable.cena,        R.drawable.bebida };

    private GridView        grdvOpciones;
    private GridAdaptador   adaptador;

    FloatingActionButton fabMain, fabOkey, fabCarrito;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    boolean isOpen = false;

    ConstraintLayout    layoutPrincipal;
    LinearLayout        layoutGrdv, layoutTotales;

    TextView titulo;

    int fondo = 0;

    //Variables que se mandaran entre actividades para almacenar los detalles del pedido
    String almuerzoSeleccionadoFinal    = "";
    String comidaSeleccionadaFinal      = "";
    String cenaSeleccionadaFinal        = "";
    String bebidasSeleccionadasFinal    = "";

    int costoTotal      = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        fabMain = (FloatingActionButton) findViewById(R.id.fabMain);
        fabCarrito = (FloatingActionButton) findViewById(R.id.fabCarrito);
        fabOkey = (FloatingActionButton) findViewById(R.id.fabOkey);

        fondo = getIntent().getIntExtra("fondo", 0);

        //Recuperar lo seleccionado
        almuerzoSeleccionadoFinal   =   getIntent().getStringExtra("almSelec");
        comidaSeleccionadaFinal     =   getIntent().getStringExtra("comSelec");
        cenaSeleccionadaFinal       =   getIntent().getStringExtra("cenSelec");
        bebidasSeleccionadasFinal   =   getIntent().getStringExtra("bebSelec");

        costoTotal   = getIntent().getIntExtra("costo", 0);

        //Animaciones
        fabOpen = AnimationUtils.loadAnimation(this,R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this,R.anim.fab_close);

        rotateForward = AnimationUtils.loadAnimation(this,R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this,R.anim.rotate_backward);

        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animatefab();
            }
        });
        fabCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animatefab();
                Intent intent = new Intent(MainActivity.this, CarritoActivity.class);

                intent.putExtra("almSelec", almuerzoSeleccionadoFinal);
                intent.putExtra("comSelec", comidaSeleccionadaFinal);
                intent.putExtra("cenSelec",  cenaSeleccionadaFinal);
                intent.putExtra("bebSelec", bebidasSeleccionadasFinal);

                intent.putExtra("costo", costoTotal);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        fabOkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animatefab();
                if(costoTotal != 0)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("¿Continuar?")
                            .setMessage("Se solicita una informacion adicional para confirmar su pedido.")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Intent intent = new Intent (MainActivity.this, PagoActivity.class);

                                    intent.putExtra("costo", costoTotal);
                                    intent.putExtra("fondo",    fondo);

                                    intent.putExtra("almSelec", almuerzoSeleccionadoFinal);
                                    intent.putExtra("comSelec", comidaSeleccionadaFinal);
                                    intent.putExtra("cenSelec",  cenaSeleccionadaFinal);
                                    intent.putExtra("bebSelec", bebidasSeleccionadasFinal);

                                    intent.putExtra("costo", costoTotal);

                                    startActivity(intent);
                                    MainActivity.this.finish();
                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setCancelable( false ).create().show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "No ha seleccionado nada para su pedido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        grdvOpciones    = findViewById(R.id.grdvOpciones);
        adaptador       = new GridAdaptador(this, R.layout.gridview_item_imagen, opciones);
        grdvOpciones.setAdapter(adaptador);

        grdvOpciones.setOnItemClickListener(this);

        layoutPrincipal = (ConstraintLayout)    findViewById ( R.id.layAlmPrincipal);
        layoutGrdv      = (LinearLayout)        findViewById( R.id.layGrdv );
        layoutTotales   = (LinearLayout)        findViewById( R.id.layTotales );

        titulo  = (TextView) findViewById(R.id.txtvTitulo);

        if ( fondo == 1)
        {
            layoutPrincipal.setBackgroundColor(Color.GRAY);
            layoutGrdv.setBackgroundColor(Color.GRAY);
            layoutTotales.setBackgroundColor(Color.GRAY);
            titulo.setTextColor(Color.WHITE);
        }
        else
        {
            //Nada
        }
    }

    private void animatefab()
    {
        if (isOpen)
        {
            fabMain.startAnimation(rotateBackward);
            fabOkey.startAnimation(fabClose);
            fabCarrito.startAnimation(fabClose);
            fabOkey.setClickable(false);
            fabCarrito.setClickable(false);
            isOpen=false;
        }
        else
        {
            fabMain.startAnimation(rotateForward);
            fabOkey.startAnimation(fabOpen);
            fabCarrito.startAnimation(fabOpen);
            fabOkey.setClickable(true);
            fabCarrito.setClickable(true);
            isOpen=true;
        }
    }

    @Override
    public void onBackPressed ()
    {
        // Hacer nada cuando se pulse el boton ATRAS del dispositivo
        Toast.makeText ( this, "Presione la flecha del menu para salir de la aplicacion.", Toast.LENGTH_SHORT ).show ();
    }

    @Override
    public void onItemClick (AdapterView<?> adapterView, View view, int i, long l )
    {
        Intent intent = null;

        if(i == 0)
        {
            intent = new Intent( this, AlmuerzoActivity.class );
        }
        else if(i == 1)
        {
            intent = new Intent( this, ComidaActivity.class );
        }
        else if(i == 2)
        {
            intent = new Intent( this, CenaActivity.class );
        }
        else if(i == 3)
        {
            intent = new Intent( this, BebidasActivity.class );
        }

        intent.putExtra("fondo",    fondo);

        intent.putExtra("almSelec", almuerzoSeleccionadoFinal);
        intent.putExtra("comSelec", comidaSeleccionadaFinal);
        intent.putExtra("cenSelec",  cenaSeleccionadaFinal);
        intent.putExtra("bebSelec", bebidasSeleccionadasFinal);

        intent.putExtra("costo", costoTotal);

        startActivity ( intent );
        this.finish();
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate ( R.menu.menu_main_activity_sin_tamano, menu );
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch ( id ) {
            case R.id.mniFondoOscuro    :
                                            layoutPrincipal.setBackgroundColor(Color.GRAY);
                                            layoutGrdv.setBackgroundColor(Color.GRAY);
                                            layoutTotales.setBackgroundColor(Color.GRAY);
                                            titulo.setTextColor(Color.WHITE);
                                            fondo = 1;
                                            break;
            case R.id.mniAcercaDe       :
                                            Intent intent = new Intent (this, AcercaDeActivity.class);
                                            startActivity(intent);
            break;
            case R.id.mniReturn         :   finish ();
            default                     :   return super.onOptionsItemSelected(item);
        }
        return true;
    }

    //------------------------------------------------------------------------------------------

    public class GridAdaptador extends ArrayAdapter
    {
        private Integer [] opciones;
        private int        layoutResId;
        private LayoutInflater inflater;

        //------------------------------------------------------------------------------------------
        // Constructor

        public GridAdaptador(@NonNull Context context, int resource, @NonNull Integer [] opciones )
        {
            // Llamar al constructor de la clase padre y guardar los argumentos en variables de la clase
            super (context, resource, opciones);
            this.opciones = opciones;
            layoutResId = resource;
            inflater = LayoutInflater.from(context);
        }
        //------------------------------------------------------------------------------------------

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // Implementar la personalizacion de convertView inflando primero el layout que va a utilizar
            if(convertView == null) {
                convertView = inflater.inflate(layoutResId, parent, false);
            }

            ImageView imgOpcion = convertView.findViewById(R.id.imgvOpcion);
            imgOpcion.setScaleType(ImageView.ScaleType.FIT_XY);

            imgOpcion.setImageResource(opciones[position]);

            return convertView;
        }
        //------------------------------------------------------------------------------------------
    }
}