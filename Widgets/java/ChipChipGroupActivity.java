package mx.edu.itl.c18131250.u3widgetsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import mx.edu.itl.c18131250.u3widgetsapp.R;

public class ChipChipGroupActivity extends AppCompatActivity {
    Chip chip;
    MaterialButton btnAgregarEtiqueta, btnSeleccionados;
    ChipGroup chipGroup;
    TextInputEditText textEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chip_chip_group);


        //-------------- Codigo para el chip --------------------
        chip = (Chip)findViewById(R.id.PruebaChip);

        chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(ChipChipGroupActivity.this,"Chip "+isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChipChipGroupActivity.this,"Chip ", Toast.LENGTH_SHORT).show();
            }
        });

        //-------------------- Codgio para el chipGroup --------------------------------
        btnAgregarEtiqueta = (MaterialButton)findViewById(R.id.btnAgregarEtiqueta);
        chipGroup = (ChipGroup)findViewById(R.id.chip_group);
        textEdit = (TextInputEditText) findViewById(R.id.input);

        //Cuando damos click en agregar, vamos a partir el texto en tags
        btnAgregarEtiqueta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] tags = textEdit.getText().toString().split(" ");
                LayoutInflater inflater = LayoutInflater.from(ChipChipGroupActivity.this);
                for(String text : tags)
                {
                    Chip chip = (Chip)inflater.inflate(R.layout.chip_item, null,false);
                    chip.setText(text);
                    chip.setOnCloseIconClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chipGroup.removeView(v);
                        }

                    });

                    chipGroup.addView(chip);
                }
            }
        });
    }
}