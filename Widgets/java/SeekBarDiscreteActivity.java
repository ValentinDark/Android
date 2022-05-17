/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: AGO-DIC/2021    HORA: 10-11 HRS
:*
:*
:*
:*  Archivo     : U3WidgetsApp.java
:*  Autor       : María Guadalupe Reza Casas 17130829
:*                Juan Daniel Garcia Torres  17130789
:*  Fecha       : 17/10/2021
:*  Compilador  : Android Studio Artic Fox 2020.3.1 + JDK 11
:*  Descripci�n :
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*  22/09/2021  María                 Terminar app.
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c18131250.u3widgetsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarDiscreteActivity extends AppCompatActivity {
    SeekBar seekbar;
    TextView textview, textviewtamaño;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar_discrete);
        seekbar = (SeekBar) findViewById(R.id.seekBar3);
        textview = (TextView ) findViewById(R.id.textView11);
        textviewtamaño = (TextView) findViewById(R.id.textView8);

        cambiarTamaño();
    }

    public void cambiarTamaño () {
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i == 0){
                    textviewtamaño.setText("XXS");
                    textviewtamaño.setTextSize(10);
                    textview.setTextSize(10);
                }
                if(i == 1){
                    textviewtamaño.setText("XS");
                    textviewtamaño.setTextSize(11);
                    textview.setTextSize(11);
                }
                if(i == 2){
                    textviewtamaño.setText("S");
                    textviewtamaño.setTextSize(12);
                    textview.setTextSize(12);
                }
                if(i == 3){
                    textviewtamaño.setText("M");
                    textviewtamaño.setTextSize(14);
                    textview.setTextSize(14);
                }
                if(i == 4){
                    textviewtamaño.setText("L");
                    textviewtamaño.setTextSize(16);
                    textview.setTextSize(16);
                }
                if(i == 5){
                    textviewtamaño.setText("XL");
                    textviewtamaño.setTextSize(18);
                    textview.setTextSize(18);
                }
                if(i == 6){
                    textviewtamaño.setText("XXL");
                    textviewtamaño.setTextSize(20);
                    textview.setTextSize(20);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}