package mx.edu.itl.c18131250.u3widgetsapp;

import androidx.appcompat.app.AppCompatActivity;
import mx.edu.itl.c18131250.u3widgetsapp.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class TextInputEditActivity extends AppCompatActivity
                          implements View.OnClickListener
{
    private TextInputEditText _txtinptHimno;
    private Button            _btnEnviar, _btnLimpiar, _btnAgregar;
    private EditText          _edtLineaTexto;

    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textinputedit);

        _edtLineaTexto= findViewById ( R.id._edtLineaTexto );
        _txtinptHimno = findViewById ( R.id._txtinptHimno);
        _txtinptHimno.append ( "\nPara ti las guirnaldas de Oliva." );

        _btnEnviar    = findViewById ( R.id._btnEnviar );
        _btnEnviar.setOnClickListener ( this );
        _btnAgregar   = findViewById ( R.id._btnAgregar );
        _btnAgregar.setOnClickListener ( this );
        _btnLimpiar   = findViewById ( R.id._btnLimpiar );
        _btnLimpiar.setOnClickListener ( this );
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onClick ( View view ) {
        int _id = view.getId();
        if ( _id == R.id._btnAgregar ) {
            // Se agrega la linea de texto al widget del Himno
            _txtinptHimno.append ( _edtLineaTexto.getText().toString() + "\n" );
            _edtLineaTexto.setText ( "" );
        } else if ( _id == R.id._btnLimpiar ) {
            _txtinptHimno.setText  ( "" );
            _edtLineaTexto.setText ( "" );
        } else if ( _id == R.id._btnEnviar ) {
            // Se lee el el texto del himno y se muestra
            String _himno = _txtinptHimno.getText ().toString();
            Toast.makeText ( this, _himno, Toast.LENGTH_LONG ).show ();
        }
    }

    //----------------------------------------------------------------------------------------------
}