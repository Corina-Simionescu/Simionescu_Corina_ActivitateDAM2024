package com.example.seminar_8;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaCaineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_caine);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener((view) -> {
            EditText editTextNume = findViewById(R.id.editTextNume);
            String nume = editTextNume.getText().toString();

            CheckBox checkBoxEsteAgresiv = findViewById(R.id.checkBoxEsteAgresiv);
            boolean esteAgresiv = checkBoxEsteAgresiv.isChecked();

            RadioGroup radioGroupRasa = findViewById(R.id.radioGroupRasa);
            int idSelectat = radioGroupRasa.getCheckedRadioButtonId();
            RadioButton radioButtonRasa = findViewById(idSelectat);
            String rasa = radioButtonRasa.getText().toString();

            Spinner spinnerDimensiune = findViewById(R.id.spinnerDimensiune);
            String dimensiune = spinnerDimensiune.getSelectedItem().toString();

            RatingBar ratingBarNivelDragutenie = findViewById(R.id.ratingBarNivelDragutenie);
            float nivelDragutenie = ratingBarNivelDragutenie.getRating();

            Switch switchEsteJucaus = findViewById(R.id.switchEsteJucaus);
            boolean esteJucaus = switchEsteJucaus.isChecked();

            Caine caine = new Caine(nume, esteAgresiv, rasa, dimensiune, nivelDragutenie, esteJucaus);
            Intent intent = new Intent();
            intent.putExtra("caine", caine);
            setResult(RESULT_OK, intent);
            finish();
        });

        //seminar 6:
        Intent intent = getIntent();
        if (intent.hasExtra("caine")) {
            Caine caine = intent.getParcelableExtra("caine");

            EditText editTextNume = findViewById(R.id.editTextNume);
            CheckBox checkBoxEsteAgresiv = findViewById(R.id.checkBoxEsteAgresiv);
            RadioGroup radioGroupRasa = findViewById(R.id.radioGroupRasa);
            Spinner spinnerDimensiune = findViewById(R.id.spinnerDimensiune);
            RatingBar ratingBarNivelDragutenie = findViewById(R.id.ratingBarNivelDragutenie);
            Switch switchEsteJucaus = findViewById(R.id.switchEsteJucaus);

            //populate the edit text for name
            editTextNume.setText(caine.getNume());

            //populate the check box for aggressiveness
            checkBoxEsteAgresiv.setChecked(caine.isEsteAgresiv());

            //populate the radio button
            for (int i = 0; i < radioGroupRasa.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) radioGroupRasa.getChildAt(i);

                if (radioButton.getText().toString().equals(caine.getRasa())) {
                    radioButton.setChecked(true);
                    break;
                }
            }

            //populate the spinnner
            for (int i = 0; i < spinnerDimensiune.getCount(); i++) {
                if (spinnerDimensiune.getItemAtPosition(i).toString().equals(caine.getDimensiune())) {
                    spinnerDimensiune.setSelection(i);
                    break;
                }
            }

            //populate the rating bar
            ratingBarNivelDragutenie.setRating(caine.getNivelDeDragutenie());

            //populate the switch
            switchEsteJucaus.setChecked(caine.isEsteJucaus());

        }
    }
}