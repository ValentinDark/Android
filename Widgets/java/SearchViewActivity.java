/*------------------------------------------------------------------------------------------
:*                          TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: AGO-DIC/2021    HORA: 10-11 HRS
:*
:*                       Aplicacion para fiestas
:*
:*  Archivo     : SearchViewActivity.java
:*  Autores     : Jesus Andree Gonzalez Avalos  18130560
:*                Johan Arturo Rodriguez Moreno 17130834
:*  Fecha       : 08/Nov/2021
:*  Compilador  : Android Studio Artic Fox 2020.3.1 + JDK 11
:*  Descripcion : Desarrollar un search view y mostrar un ejemplo
:*  Ultima modif:
:*  Fecha       Modifico             Motivo
:*
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c18131250.u3widgetsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchViewActivity extends AppCompatActivity {

    ListView lista;
    String[] listaElementos = {"Facebook", "Instagram", "WhatsApp", "TikTok", "YouTube", "Twitter", "Telegram", "Line"};
    List<String> listaString;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView ( R.layout.activity_search_view );

        lista = findViewById(R.id.lista);
        listaString =new ArrayList<>(Arrays.asList(listaElementos));
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listaElementos);
        lista.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_view_menu, menu);
        MenuItem buscador = menu.findItem(R.id.accion_buscar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(buscador);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                SearchViewActivity.this.arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}