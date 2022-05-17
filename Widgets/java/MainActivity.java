package mx.edu.itl.c18131250.u3widgetsapp;

import androidx.appcompat.app.AppCompatActivity;
import mx.edu.itl.c18131250.u3widgetsapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

public class MainActivity extends AppCompatActivity {

    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //----------------------------------------------------------------------------------------------
    public void btnTextInputEditTextClick ( View v ) {
        Intent intent = new Intent ( this, TextInputEditActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void btnCheckedTextViewClick  ( View v ) {
        Intent intent = new Intent ( this, CheckedTextViewActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void btnAutoCompleteTextViewClick ( View v ) {
        Intent intent = new Intent ( this, AutoCompleteTextViewActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void btnRadioButtonClick ( View v ) {
        Intent intent = new Intent ( this, RadioButtonActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void btnCheckBoxClick ( View v ) {
        Intent intent = new Intent ( this, CheckBoxActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void btnMultiLineTextClick ( View v ) {
        Intent intent = new Intent ( this, MultilineTextActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void  btnMultiAutoCompleteTextViewClick ( View v ) {
        Intent intent = new Intent ( this, MultiAutoCompleteTextViewActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void  btnImageButtonClick ( View v ) {
        Intent intent = new Intent ( this, ImageButtonActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void  btnChipClick ( View v ) {
        Intent intent = new Intent ( this, ChipChipGroupActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void  btnToggleButtonClick ( View v ) {
        Intent intent = new Intent ( this, ToggleButtonActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void  btnSwitchClick ( View v ) {
        Intent intent = new Intent ( this, SwitchActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void  btnFloatingActionButtonClick ( View v ) {
        Intent intent = new Intent ( this, FloatingActionButtonActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void btnWebViewClick  ( View v ) {
        Intent intent = new Intent ( this, WebViewActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void  btnCalendarViewClick ( View v ) {
        Intent intent = new Intent ( this, CalendarViewActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void  btnProgressBarHorizClick ( View v ) {
        Intent intent = new Intent ( this, ProgressBarHorizActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void btnSeekBarClick  ( View v ) {
        Intent intent = new Intent ( this, SeekBarActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void  btnSeekBarDiscreteClick ( View v ) {
        Intent intent = new Intent ( this, SeekBarDiscreteActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void  btnRatingBarClick ( View v ) {
        Intent intent = new Intent ( this, RatingBarActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void  btnSearchViewClick ( View v ) {
        Intent intent = new Intent ( this, SearchViewActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void  btnDatePickerClick ( View v ) {
        Intent intent = new Intent ( this, DatePickerActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void btnTimePickerClick  ( View v ) {
        Intent intent = new Intent ( this, TimePickerActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
    public void btnImageViewClick  ( View v ) {
        Intent intent = new Intent ( this, ImageViewActivity.class );
        startActivity ( intent );
    }
    //----------------------------------------------------------------------------------------------
}