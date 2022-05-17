package mx.edu.itl.c18131250.u3widgetsapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: AGO-DIC/2021    HORA: 10-11 HRS
:*
:*                  Clase que realiza el metodo de MultilineText
:*
:*  Archivo     : MainActivity.java
:*  Autor       : Johan Ismael Lopez Flores        18130568
:*                Wolfhang Karim Avila Favela      17131462
:*  Fecha       : 08/11/2021
:*  Compilador  : Android Studio Artic Fox 2020.3.1 + JDK 11
:*  Descripci�n : Esta clase es la que se encarga de realizar el metodo para poder tener mulilineas
:*                 en el EditText
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/
public class MultilineTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiline_text);

        final TextView textView = (TextView) findViewById(R.id.test1);
        Button but=(Button) findViewById(R.id.send);
        final EditText edit=(EditText) findViewById(R.id.edit);

        textView.setMovementMethod(new ScrollingMovementMethod()); //Un método de movimiento que interpreta las teclas de movimiento desplazando el búfer de texto.
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=edit.getText().toString();
                textView.append("\n"+text);
            }
        });
    }
}