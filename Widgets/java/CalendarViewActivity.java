/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: AGO-DIC/2021    HORA: 10-11 HRS
:*
:*                       descripcion breve de la clase (centrado)
:*
:*  Archivo     : U3WidgetsApps.java
:*  Autor       : Andre Salvador Ochoa        17130813
:*                Jesus Alberto Diaz de Leon  17130778
:*  Fecha       : 07/11/2021
:*  Compilador  : Android Studio Artic Fox 2020.3.1 + JDK 11
:*  Descripci�n : Uso de Widgets en una App
:*  Ultima modif: 07/11/2021
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*  06/11/2021 Andre      Creacion del CalendarViewActivity y su programacion
:*  07/11/2021 Jesus      Pequenos ajustes
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c18131250.u3widgetsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarViewActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView myDate;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        calendarView = findViewById(R.id.calendarView);
        myDate  = (TextView) findViewById(R.id.txtDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                myDate.setText("Fecha seleccionada:" + date);
                date = (i2) + "/" + (i1 + 1) + "/" + i;
                myDate.setText("Fecha seleccionada:" + date);
            }
        });

    }

    public void regresarClick( View v ) {
        Log.d("Hola","fecha seleccionada: " + date);
        Toast.makeText( calendarView.getContext(),"Ultima fecha seleccionada: " + date,Toast.LENGTH_LONG).show();
        finish();
    }

}