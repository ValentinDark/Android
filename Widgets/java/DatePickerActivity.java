package mx.edu.itl.c18131250.u3widgetsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerActivity extends AppCompatActivity {

    public DatePickerDialog dpd;
    public Button btnDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        initDatePicker();
        btnDate = findViewById(R.id.btnDatePicker);
        btnDate.setText(getTodaysDate());
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        mes = mes + 1;
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(dia, mes, ano);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia)
            {
                mes = mes + 1;
                String fecha = makeDateString(dia, mes, ano);
                btnDate.setText(fecha);
            }
        };

        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        dpd = new DatePickerDialog(this, style, dateSetListener, ano, mes, dia);

        //Descomentar en caso de que se desee que
        //no se seleccione una fecha posterior
        //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
        //dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int dia, int mes, int ano)
    {
        return getMesFormat(mes) + " " + dia + " " + ano;

    }

    private String getMesFormat(int mes) {

        if(mes == 1)
            return "ENE";
        if(mes == 2)
            return "FEB";
        if(mes == 3)
            return "MAR";
        if(mes == 4)
            return "ABR";
        if(mes == 5)
            return "MAY";
        if(mes == 6)
            return "JUN";
        if(mes == 7)
            return "JUL";
        if(mes == 8)
            return "AGO";
        if(mes == 9)
            return "SEP";
        if(mes == 10)
            return "OCT";
        if(mes == 11)
            return "NOV";
        if(mes == 12)
            return "DIC";
        return "ENE";

    }

    public void AbrirDatePicker(View view)
    {
        dpd.show();
    }
}