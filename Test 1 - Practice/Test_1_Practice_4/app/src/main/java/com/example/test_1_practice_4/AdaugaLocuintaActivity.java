package com.example.test_1_practice_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
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

import java.util.Date;

public class AdaugaLocuintaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_locuinta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //        gestionare intent de la custom lista locuinte activity (daca exista) - start
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null && bundle.containsKey("locuintaSelectata")) {
            Locuinta locuinta = bundle.getParcelable("locuintaSelectata");

//            populare viewuri
            EditText et = findViewById(R.id.editTextAdresa);
            et.setText(locuinta.getAdresa().toString());

            CheckBox cb = findViewById(R.id.checkboxDeLux);
            cb.setChecked(locuinta.isDeLux());

            RadioGroup rg = findViewById(R.id.radioGroupTip);
            for (int i = 0; i < rg.getChildCount(); i++) {
                RadioButton rb = (RadioButton) rg.getChildAt(i);
                if (rb.getText().toString().equals(locuinta.getTip())) {
                    rb.setChecked(true);
                    System.out.println("BBBBB");
                    break;
                }
            }

            Spinner sp = findViewById(R.id.spinnerConfort);
            for (int i = 0; i < sp.getCount(); i++) {
                if (sp.getItemAtPosition(i).toString().equals(locuinta.getConfort())) {
                    sp.setSelection(i);
                }
            }

            RatingBar rb = findViewById(R.id.ratingBarRating);
            rb.setRating(locuinta.getRating());

            Switch s = findViewById(R.id.switchFrumoasa);
            s.setChecked(locuinta.isFrumoasa());

            DatePicker dp = findViewById(R.id.datePickerData);
            dp.init(locuinta.getData().getYear(), locuinta.getData().getMonth(), locuinta.getData().getDay(), null);
        }

//        gestionare intent de la custom lista locuinte activity (daca exista) - end

        Button buttonAdauga = findViewById(R.id.buttonAdauga);
        buttonAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextAdresa = findViewById(R.id.editTextAdresa);
                String adresa = editTextAdresa.getText().toString();

                CheckBox checkboxDeLux = findViewById(R.id.checkboxDeLux);
                boolean deLux = checkboxDeLux.isChecked();

                RadioGroup radioGroupTip = findViewById(R.id.radioGroupTip);
                int radioButtonId = radioGroupTip.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(radioButtonId);
                String tip = radioButton.getText().toString();

                Spinner spinnerConfort = findViewById(R.id.spinnerConfort);
                String confort = spinnerConfort.getSelectedItem().toString();

                RatingBar ratingBarRating = findViewById(R.id.ratingBarRating);
                float rating = ratingBarRating.getRating();

                Switch switchFrumoasa = findViewById(R.id.switchFrumoasa);
                boolean frumoasa = switchFrumoasa.isChecked();

                DatePicker datePickerData = findViewById(R.id.datePickerData);
                Date data = new Date(datePickerData.getYear(), datePickerData.getMonth(), datePickerData.getDayOfMonth());

                Locuinta locuinta = new Locuinta(adresa, deLux, tip, confort, rating, frumoasa, data);

                Intent intentPentruMainActivity = new Intent();
                Bundle bundlePentruMainActivity = new Bundle();

                bundlePentruMainActivity.putParcelable("locuinta", locuinta);
                intentPentruMainActivity.putExtras(bundlePentruMainActivity);

                setResult(RESULT_OK, intentPentruMainActivity);
                finish();
            }
        });
    }
}