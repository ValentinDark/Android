package mx.edu.itl.c18131250.u3widgetsapp;

import androidx.appcompat.app.AppCompatActivity;
import mx.edu.itl.c18131250.u3widgetsapp.R;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;

public class CheckedTextViewActivity extends AppCompatActivity {

    private CheckedTextView _chktxtvEjemplo;

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView ( R.layout.activity_checked_text_view );

        _chktxtvEjemplo = findViewById ( R.id._chktxtvEjemplo );

        // Registramos un listener del evento Click sobre el CheckedTextView
        _chktxtvEjemplo.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                if ( _chktxtvEjemplo.isChecked () ) {
                    // Si esta marcado se cambia su estado a desmarcado y se quita la estrellita
                    _chktxtvEjemplo.setChecked ( false );
                    _chktxtvEjemplo.setCheckMarkDrawable ( null );
                } else {
                    // Si esta desmarcado se cambia a marcado y se muestra la estrellita
                    _chktxtvEjemplo.setChecked ( true );
                    _chktxtvEjemplo.setCheckMarkDrawable ( android.R.drawable.btn_star );
                }
            }
        } );
    }
    //----------------------------------------------------------------------------------------------
}