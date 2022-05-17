/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: AGO-DIC/2021    HORA: 10-11 HRS
:*
:*         Esta clase contiene un progressBar para mostrar un proceso de carga
:*
:*  Archivo     : ProgressBarHorizActivity.java
:*  Autor       : Luis Javier Domínguez Reyes    17130021
:*                Edgar Abraham Pedrueza Duran   17130056
:*  Fecha       : 08/11/2021
:*  Compilador  : Android Studio Artic Fox 2020.3.1 + JDK 11
:*  Descripci�n : por medio del ingreso de los dtaos de usuario y contraseña se comprueba si estos
:*                para posteriormente cargar el proceso del inicio de sesion en caso de que sean
:*                correctos sino solo mostrara que son incorrectas las credenciales
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*
:*------------------------------------------------------------------------------------------*/
package mx.edu.itl.c18131250.u3widgetsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProgressBarHorizActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView edtUsu, edtCargado;
    private EditText edtCon;
    Handler handler = new Handler();
    int progreso = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_horiz);

        progressBar = findViewById(R.id.progressBar);
        edtUsu = findViewById(R.id.edtUsuario);
        edtCon = findViewById(R.id.edtContrasena);
        edtCargado = findViewById(R.id.edtProgreso);


    }

    public void btnAceptarClick(View v){
        String usuario = edtUsu.getText().toString();
        String contrasena = edtCon.getText().toString();

        if(usuario.equals("ejemplo")) {
            if (contrasena.equals("ejemplo")) {
                //Iniciamos el hilo con la accion del progress bar
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progreso < 100) {
                            progreso = progreso + 3;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(progreso);
                                    if (progreso == 100) {
                                        Toast.makeText(ProgressBarHorizActivity.this, "Inicio de sesion correcta", Toast.LENGTH_LONG).show();
                                    }
                                    edtCargado.setText(progreso + " / " + progressBar.getMax());
                                }
                            });

                            try {
                                //Duerme el hilo por 200 milisegundos
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();
            }else{
                Toast.makeText(ProgressBarHorizActivity.this, "Credenciales Incorrectas", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(ProgressBarHorizActivity.this, "Credenciales Incorrectas", Toast.LENGTH_LONG).show();
        }
    }
}