/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: AGO-DIC/2021    HORA: 10-11 HRS
:*
:*                       Clase que define los metodos para la utilzaccion de los switch
:*
:*  Archivo     : SwitchActivity.java
:*  Autor       : Jorge Arturo Moreno Salazar (18130580)
:*                Francisco Eduardo Castellanos Barrón (17130015)
:*  Fecha       : 08/11/2021
:*  Compilador  : Android Studio Artic Fox 2020.3.1 + JDK 11
:*  Descripcion : Implementamos tres switches con las siguientes funciones
:*               .setBackgroundColor---> cambiar color de fondo
:*               .setTextColor----> cambiar color de texto
:*               .setTextSize------> cambiar tamaño de texto
:*                cada switch actua con el textView con el texto ANDROID
:*  Ultima modif:
:*  Fecha       Modifico             Motivo
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c18131250.u3widgetsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SwitchActivity extends AppCompatActivity {

    private Switch tipo;
    private Switch verde;
    private Switch tamaño;
    private TextView AndroidTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        tipo = findViewById ( R.id.tipo );
        verde = findViewById ( R.id.verde );
        tamaño = findViewById ( R.id.tamaño );
        AndroidTexto = findViewById ( R.id.AndroidTexto );
    }
    public void btnActivarClick ( View v ) {

        if (tipo.isChecked())
            AndroidTexto.setBackgroundColor(Color.CYAN);
        else
            AndroidTexto.setBackgroundColor(Color.WHITE);

        if (verde.isChecked())
            AndroidTexto.setTextColor(Color.GREEN);
        else
            AndroidTexto.setTextColor(Color.BLACK);

        if (tamaño.isChecked())
            AndroidTexto.setTextSize(16);
        else
            AndroidTexto.setTextSize(20);

    }

    public void btnEnviar ( View v) {

        String statusSwitch1, statusSwitch2, statusSwitch3;
        if (tipo.isChecked())
            statusSwitch1 = tipo.getTextOn().toString();
        else
            statusSwitch1 = tipo.getTextOff().toString();
        if (verde.isChecked())
            statusSwitch2 = verde.getTextOn().toString();
        else
            statusSwitch2 = verde.getTextOff().toString();
        if (tamaño.isChecked())
            statusSwitch3 = tamaño.getTextOn().toString();
        else
            statusSwitch3 = tamaño.getTextOff().toString();

        Toast.makeText(getApplicationContext(), "Estado de los switch" + "\n" + "Switch1 :" + statusSwitch1 + "\n" + "Switch2 :" + statusSwitch2 +
                "\n" + "Switch3 :" + statusSwitch3, Toast.LENGTH_LONG).show();
    }

}