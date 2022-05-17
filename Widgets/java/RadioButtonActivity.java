package mx.edu.itl.c18131250.u3widgetsapp;

import androidx.appcompat.app.AppCompatActivity;
import mx.edu.itl.c18131250.u3widgetsapp.R;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class RadioButtonActivity extends AppCompatActivity {

    private RadioButton _rbtnClubFutbol_1, _rbtnClubFutbol_2, _rbtnClubFutbol_3;

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView ( R.layout.activity_radio_button );

        _rbtnClubFutbol_1 = findViewById ( R.id._rbtnClubFutbol_1 );
        _rbtnClubFutbol_2 = findViewById ( R.id._rbtnClubFutbol_2 );
        _rbtnClubFutbol_3 = findViewById ( R.id._rbtnClubFutbol_3 );
    }

    //----------------------------------------------------------------------------------------------
    // Cuando se da clic en Enviar se determina el equipo marcada y se muestra en un SnackBar.

    public void btnEnviarClick ( View v ) {
        String _sClubFavorito = "Ninguno";
        if ( _rbtnClubFutbol_1.isChecked() )
            _sClubFavorito = _rbtnClubFutbol_1.getText().toString();
        else if ( _rbtnClubFutbol_2.isChecked() )
            _sClubFavorito = _rbtnClubFutbol_2.getText().toString();
        else if ( _rbtnClubFutbol_3.isChecked() )
            _sClubFavorito = _rbtnClubFutbol_3.getText().toString();
        Snackbar.make ( v, "Su Club favorito es: " + _sClubFavorito, Snackbar.LENGTH_LONG ).show();
    }

    //----------------------------------------------------------------------------------------------
    // Los 3 RadioButtons tienen el mismo valor del atributo onClick.
    // Cuando se selecciona uno se muestra un Toast con un mensaje alusivo al club seleccionado.

    public void rbtnClubFutbolClick ( View v ) {
        String _sMensaje = "";
        int _iId = v.getId ();
        switch ( _iId ) {
            case R.id._rbtnClubFutbol_1 : _sMensaje = _rbtnClubFutbol_1.getText ().toString() + " es buenisimo"; break;
            case R.id._rbtnClubFutbol_2 : _sMensaje = _rbtnClubFutbol_2.getText ().toString() + " superganador de Champions"; break;
            case R.id._rbtnClubFutbol_3 : _sMensaje = _rbtnClubFutbol_3.getText ().toString() + " la aplanadora alemana"; break;
        }

        Toast.makeText ( this, _sMensaje, Toast.LENGTH_LONG ).show();
    }

    //----------------------------------------------------------------------------------------------
}