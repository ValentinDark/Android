package mx.edu.itl.c18131250.u3widgetsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import mx.edu.itl.c18131250.u3widgetsapp.R;

public class CheckBoxActivity extends AppCompatActivity {

    private CheckBox _chkClubFutbol_1, _chkClubFutbol_2, _chkClubFutbol_3;

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView ( R.layout.activity_checkbox );

        _chkClubFutbol_1 = findViewById ( R.id._chkClubFutbol_1 );
        _chkClubFutbol_2 = findViewById ( R.id._chkClubFutbol_2 );
        _chkClubFutbol_3 = findViewById ( R.id._chkClubFutbol_3 );
    }

    //----------------------------------------------------------------------------------------------
    // Cuando se da clic en Enviar se determina el equipo marcada y se muestra en un SnackBar.

    public void btnEnviarClick ( View v ) {
        ArrayList<String> arrayList = new ArrayList<> ();

        if ( _chkClubFutbol_1.isChecked() )
            arrayList.add ( _chkClubFutbol_1.getText().toString() );
        if ( _chkClubFutbol_2.isChecked() )
            arrayList.add ( _chkClubFutbol_2.getText().toString() );
        if ( _chkClubFutbol_3.isChecked() )
            arrayList.add ( _chkClubFutbol_3.getText().toString() );

        Snackbar.make ( v, "Sus favoritos : " + arrayList.toString(), Snackbar.LENGTH_LONG ).show();
    }

    //----------------------------------------------------------------------------------------------
    // Los 3 CheckBox tienen el mismo valor del atributo onClick.
    // Cuando se selecciona uno se muestra un Toast con un mensaje alusivo al club seleccionado.

    public void chkClubFutbolClick ( View v ) {
        String _sMensaje = "";
        int _iId = v.getId ();
        switch ( _iId ) {
            case R.id._chkClubFutbol_1 :
                if ( _chkClubFutbol_1.isChecked() )
                    _sMensaje = _chkClubFutbol_1.getText ().toString() + " es buenisimo";
                break;
            case R.id._chkClubFutbol_2 :
                if ( _chkClubFutbol_2.isChecked() )
                    _sMensaje = _chkClubFutbol_2.getText ().toString() + " superganador de Champions";
                break;
            case R.id._chkClubFutbol_3 :
                if ( _chkClubFutbol_3.isChecked() )
                    _sMensaje = _chkClubFutbol_3.getText ().toString() + " la aplanadora alemana";
                break;
        }

        if ( ! _sMensaje.isEmpty() )
            Toast.makeText ( this, _sMensaje, Toast.LENGTH_LONG ).show();
    }

    //----------------------------------------------------------------------------------------------
}