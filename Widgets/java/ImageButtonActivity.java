package mx.edu.itl.c18131250.u3widgetsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ImageButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_button);
    }

    public void btnLinuxClick (View v)
    {
        Toast.makeText(this,"Sistema operativo seleccionado: "+ "Linux",Toast.LENGTH_LONG).show();
    }

    public void btnAndroidClick (View v)
    {
        Toast.makeText(this,"Sistema operativo seleccionado: "+ "Android",Toast.LENGTH_LONG).show();
    }
}