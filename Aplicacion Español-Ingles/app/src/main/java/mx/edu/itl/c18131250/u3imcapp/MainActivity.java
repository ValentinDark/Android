/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*                    Pantalla principal de nuestra aplicacion.
:*
:*  Archivo     : MainActivity.java
:*  Autor       : Valentin Herrera Castorena     18131250
:*  Fecha       : 25/Mar/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripci�n : Esta clase nos muestra una pantalla la cual contiene un ImageView, dos TextView
:*                 en los cuales podemos ingresar el peso y la estatura para generar el IMC, y dos Buttons
:*                 los cuales nos permiten ver el IMC en un cuadro de dialogo y el otro nos inicia la activity
:*                 AcercaDe.
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131250.u3imcapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

//--------------------------------------------------------------------------------------------------
public class MainActivity extends AppCompatActivity {

    private EditText edtPeso;
    private EditText edtEstatura;
//--------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar la barra de Acciones (ActionBar)
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        //Obtenemos las referencias a los EditText de la IU
        edtPeso = findViewById(R.id.edtPeso);
        edtEstatura = findViewById(R.id.edtEstatura);
    }
//--------------------------------------------------------------------------------------------------
    public void btnCalcularIMCClick (View v)
    {
        if (edtPeso.getText().toString().isEmpty())
        {
            edtPeso.setError(getString(R.string.error_peso_vacio));
            edtPeso.requestFocus();
            return;
        }
        else if (edtEstatura.getText().toString().isEmpty())
        {
            edtEstatura.setError(getString(R.string.error_estatura_vacio));
            edtEstatura.requestFocus();
            return;
        }
        else
        {
            float peso = Float.parseFloat( edtPeso.getText().toString() );
            float estatura = Float.parseFloat( edtEstatura.getText().toString() );

            //Validar que el peso y la estatura no sean cero
            if (peso <= 0) {
                edtPeso.setError(getString(R.string.error_peso));
                edtPeso.requestFocus();
                return;
            }

            if (estatura <= 0) {
                edtEstatura.setError(getString(R.string.error_estatura));
                edtEstatura.requestFocus();
                return;
            }

            float imc = (float) (peso / Math.pow(estatura, 2));

            String condicion = getString(R.string.condicion);

            if (imc < 15) {
                condicion += " " + getString(R.string.condicion_complemento1);
            } else if (imc >= 15 && imc < 16) {
                condicion += " " + getString(R.string.condicion_complemento2);
            } else if (imc >= 16 && imc < 18.5) {
                condicion += " " + getString(R.string.condicion_complemento3);
            } else if (imc >= 18.5 && imc < 25) {
                condicion += " " + getString(R.string.condicion_complemento4);
            } else if (imc >= 25 && imc < 30) {
                condicion += " " + getString(R.string.condicion_complemento5);
            } else if (imc >= 30 && imc < 35) {
                condicion += " " + getString(R.string.condicion_complemento6);
            } else if (imc >= 35 && imc < 40) {
                condicion += " " + getString(R.string.condicion_complemento7);
            } else if (imc >= 40) {
                condicion += " " + getString(R.string.condicion_complemento8);
            }

            //Desplegamos los resultados en un AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.titulo_builder))
                    .setMessage(getString(R.string.mensaje_builder) + " " + imc + "\n\n" + condicion)
                    .setPositiveButton(getString(R.string.boton_builder), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
        }
    }
//--------------------------------------------------------------------------------------------------
    public void btnAcercaDeClick (View v)
    {
        Intent intent = new Intent(MainActivity.this, AcercaDeActivity.class);
        startActivity(intent);
    }
//--------------------------------------------------------------------------------------------------
}