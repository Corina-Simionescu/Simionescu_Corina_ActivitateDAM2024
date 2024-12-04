package com.example.seminar_10;

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

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AdaugaCaineActivity extends AppCompatActivity {
    private boolean toBeUpdated = false;

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

        Intent intent = getIntent();
        if (intent.hasExtra("caineId")) {
            toBeUpdated = true;
            long caineId = intent.getLongExtra("caineId", -1);

            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                CaineDatabase db = CaineDatabase.getInstance(getApplicationContext());
                Caine caine = db.caineDAO().getCaineById(caineId);

                // Update UI on the main thread
                runOnUiThread(() -> {
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
                });
            });
        }

        //===============================================

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

            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                CaineDatabase db = CaineDatabase.getInstance(getApplicationContext());

                if (toBeUpdated == false) {
                    long id = db.caineDAO().insert(caine);
                    caine.setId(id);
                } else {
                    long caineId = getIntent().getLongExtra("caineId", -1);
                    caine.setId(caineId);
                    db.caineDAO().update(caine);
                }

                setResult(RESULT_OK);
                finish();
            });
        });
    }
}