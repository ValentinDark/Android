/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*         Clase que despliega distintos cuadros de mensaje y dialogo basicos.
:*
:*  Autor       : Valentin Herrera Castorena    18131250
:*  Archivo     : MainActivity.java
:*  Fecha       : 22/Feb/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripción : Los dialogos de alerta que se despliegan son los siguientes:
                    Toast de duracion corta
                    Toast de duracion larga
                    Snackbar
                    Cuadro de dialogo basico
                    Dialogo basico con boton OK
                    Dialogo basico con boton OK y CANCEL
                    Dialogo con botones de radio y seleccion unica
                    Dialogo con botones casillas de verificacion seleccion multiple
                    Dialogo con layout de login incrustado
                    Acerca de
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131250.u2cuadrosdialogoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //----------------------------------------------------------------------------------------------
    // Mensaje Toast de duracion corta
    public void btnToastCortoClick (View v)
    {
        Toast.makeText(this, "Toast corto", Toast.LENGTH_SHORT).show();
    }

    //----------------------------------------------------------------------------------------------
    // Toast de duracion larga
    public void btnToastLargoClick (View v)
    {
        Toast.makeText(this, "Toast largo", Toast.LENGTH_LONG).show();
    }

    //----------------------------------------------------------------------------------------------
    // SnackBar
    public void btnSnackBarClick (View v)
    {
        LinearLayout layoutPrincipal = findViewById ( R.id.layoutPrincipal );
        Snackbar.make( layoutPrincipal, "Esto es un Snackbar", Snackbar.LENGTH_SHORT).show();
    }

    //----------------------------------------------------------------------------------------------
    // Cuadro de dialogo basico
    public void btnDialogoBasicoClick (View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setMessage( "Cuadro de Dialogo basico" ).create().show();
    }

    //----------------------------------------------------------------------------------------------
    // Cuadro de dialogo con boton OK
    public void btnDialogoBotonOkClick (View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setMessage( "Dialogo con boton ACEPTAR" )
                .setTitle( "Android" )
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick ( DialogInterface dialogInterface, int i )
                    {
                        mostrarToast("Aceptar");
                    }
                } ).create().show();
    }

    //----------------------------------------------------------------------------------------------
    //
    public void mostrarToast ( String mensaje )
    {
        Toast.makeText( MainActivity.this, mensaje, Toast.LENGTH_SHORT ).show();
    }

    //----------------------------------------------------------------------------------------------
    // Dialogo con botones OK y CANCEL
    public void btnDialogoBotonOkCancelClick (View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Android")
                .setMessage("Mensaje con botones Aceptar y Cancelar")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mostrarToast("Aceptar");
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

    //----------------------------------------------------------------------------------------------
    // Cuadro de dialogo con lista de opciones basicas
    private CharSequence colores [] = { "Verde", "Blanco", "Rojo" };

    public void btnDialogoListaClick (View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Escoja un color bonito:")
                .setItems(colores, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mostrarToast("Color seleccionado: " + colores [which]);
                    }
                }).create().show();
    }

    //----------------------------------------------------------------------------------------------
    // Cuadro de dialogo con lista de opciones con botones de radio y seleccion unica
    private int iColorFavorito = 2; // Por default el Rojo

    public void btnDialogoRadioClick (View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Escoge tu color favorito: ")
                .setSingleChoiceItems(colores, iColorFavorito, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        iColorFavorito = which;
                        mostrarToast( "Escogio: " + colores[which]);
                    }
                }).create().show();
    }

    //----------------------------------------------------------------------------------------------
    // Cuadro de dialogo con lista de opciones con casillas de verificacion para seleccion multiple
    private boolean coloresSeleccionados [] = {false, false, false}; //Ninguna aparece seleccionada
    private ArrayList<String> coloresFavoritos = new ArrayList<>();

    public void btnDialogoCasillasVerificacionClick (View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciones sus colores favoritos: ")
                .setMultiChoiceItems(colores, coloresSeleccionados, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked)
                        {
                            mostrarToast("Color seleccionado: " + colores [which]);
                            coloresFavoritos.add(colores [which].toString());
                        }
                        else
                        {
                            mostrarToast("Color deseleccionado: " + colores [which]);
                            coloresFavoritos.remove(colores[which].toString());
                        }
                    }
                })
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mostrarToast("Colores favoritos: " + coloresFavoritos);
                    }
                }).create().show();
    }

    //----------------------------------------------------------------------------------------------
    // Cuadro de dialogo con layout de accesso (login) incrustado.
    private EditText edtUsuario;
    private EditText edtContrasena;
    private View login_layout;

    public void btnDialogoLayoutIncrustadoClick (View v)
    {
        // Obtener la instancia del layout de login y de sus campos Usuario y Contraseña
        login_layout = getLayoutInflater().inflate(R.layout.login_layout, null);
        edtUsuario = login_layout.findViewById(R.id.edtUsuario);
        edtContrasena = login_layout.findViewById(R.id.edtContrasena);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Acceso")
                .setIcon(R.drawable.loguito_itl)
                .setView(login_layout)
                .setPositiveButton("Entrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mostrarToast("Bienvenido: " +
                                        edtUsuario.getText().toString() + " (" +
                                        edtContrasena.getText().toString() + ")"
                                );
                    }
                } )
                .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false).create().show();
    }

    //----------------------------------------------------------------------------------------------
    // Boton Acerca De
    public void btnAcercaDeClick (View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Acerca de")
                .setIcon(R.drawable.loguito_itl)
                .setMessage("TecNM campus la Laguna\n" +
                            "Valentin Herrera Castorena (18131250)\n" +
                            "Derechos reservados 2022. Mexico")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }
}