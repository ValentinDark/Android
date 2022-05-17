package mx.edu.itl.c18131250.u3widgetsapp;

import androidx.appcompat.app.AppCompatActivity;
import mx.edu.itl.c18131250.u3widgetsapp.R;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoCompleteTextViewActivity extends AppCompatActivity {

    private AutoCompleteTextView _autoctxtvApellido;
    private String [] _arrApellidos = { "Vazquez", "Vasquez", "Flores", "Floriuc", "Gutierrez",
                                     "Gomez", "Gonzalez", "Gonzalitoz", "Fernandez", "Fonseca",
                                     "Sanchez", "Sosa", "Salas", "Salmeron", "Solano"   };

    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView ( R.layout.activity_auto_complete_text_view );

        // Se crea el Adaptador usando un diseño predefinido de lista sencilla con el arreglo de apellidos
        ArrayAdapter arrayAdapter = new ArrayAdapter ( this,
                                                         android.R.layout.simple_list_item_1,
                                                         _arrApellidos );

        _autoctxtvApellido = findViewById ( R.id._autoctxtvApellido);
        _autoctxtvApellido.setThreshold ( 1 );   // El menu se desplegara a partir del 1er caracter tecleado
        _autoctxtvApellido.setAdapter  ( arrayAdapter );
    }
    //----------------------------------------------------------------------------------------------
}