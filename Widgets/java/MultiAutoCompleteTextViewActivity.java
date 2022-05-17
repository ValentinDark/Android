/*------------------------------------------------------------------------------------------
:* INSTITUTO TECNOLOGICO DE LA LAGUNA
:* INGENIERIA EN SISTEMAS COMPUTACIONALES
:* TOPICOS AVANZADOS DE PROGRAMACION "B"
:*
:* SEMESTRE: AGO-DIC/2021 HORA: 10-11 HRS
:*
:* Clase main activity
:*
:* Archivo : U3ElBolaApp.java
:* Autor : Kevin Avila Ibañez 17130761
           Luis Mario Lopez Moreno 17130797
:* Fecha : 8/11/21
:* Compilador : Android Studio Artic Fox 2020.3.1 + JDK 11
:* Descripción : Esta clase es donde ulitizamos la clase MultiAutoCompleteTextView y hacemos un
                pequeño ejemplo
:* Ultima modif:
:* Fecha 8/11/2021 Modificacion Motivo
:*==========================================================================================
:* 18/10/2021 Kevin
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131250.u3widgetsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import mx.edu.itl.c18131250.u3widgetsapp.R;

public class MultiAutoCompleteTextViewActivity extends AppCompatActivity {

    private static final String[] lista = new String[]{"Mexico","Francia","España","Inglaterra",
            "Italia","Estados Unidos","Canada","Alemania","Rusia","Brasil","Argentina","Uruguay",
            "Colombia"};

    private MultiAutoCompleteTextView edtText;
    //---------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_auto_complete_text_view);

        edtText = findViewById(R.id.mactv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, lista);
        edtText.setAdapter(adapter);
        edtText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
    //---------------------------------------------------------------------------------------------
    public void mostrarEntradaOnClick(View v){

        String entrada = edtText.getText().toString();
        Toast.makeText(this, entrada, Toast.LENGTH_SHORT).show();

    }
}