package com.example.test_1_practice_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

public class FormAddDogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_add_dog);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        handle intent from DogListActivity (if exists) - start
        Intent intentFromDogListActivity = getIntent();
        Bundle bundleFromDogListActivity = intentFromDogListActivity.getExtras();
        if (bundleFromDogListActivity != null && bundleFromDogListActivity.containsKey("dogToBeUpdated")) {
            Dog dogToBeUpdated = bundleFromDogListActivity.getParcelable("dogToBeUpdated");

//            populate the views with the values of dogToBeUpdated
            EditText editTextName = findViewById(R.id.editTextName);
            editTextName.setText(dogToBeUpdated.getName());

            CheckBox checkBoxIsAggressive = findViewById(R.id.checkBoxIsAggressive);
            checkBoxIsAggressive.setChecked(dogToBeUpdated.isAggressive());

            RadioGroup radioGroupBreed = findViewById(R.id.radioGroupBreed);
            for (int i = 0; i < radioGroupBreed.getChildCount(); i++) {
                RadioButton radioButtonBreed = (RadioButton) radioGroupBreed.getChildAt(i);
                if (radioButtonBreed.getText().toString().equals(dogToBeUpdated.getBreed())) {
                    radioButtonBreed.setChecked(true);
                    break;
                }
            }

            Spinner spinnerSize = findViewById(R.id.spinnerSize);
            for (int i = 0; i < spinnerSize.getCount(); i++) {
                if (spinnerSize.getItemAtPosition(i).toString().equals(dogToBeUpdated.getSize())) {
                    spinnerSize.setSelection(i);
                    break;
                }
            }

            RatingBar ratingBarCutenessLevel = findViewById(R.id.ratingBarCutenessLevel);
            ratingBarCutenessLevel.setRating((float) dogToBeUpdated.getCutenessLevel());

            Switch switchIsPlayful = findViewById(R.id.switchIsPlayful);
            switchIsPlayful.setChecked(dogToBeUpdated.isPlayful());

            DatePicker datePickerDateOfBirth = findViewById(R.id.datePickerDateOfBirth);
            datePickerDateOfBirth.init(dogToBeUpdated.getDateOfBirth().getYear(),
                    dogToBeUpdated.getDateOfBirth().getMonth(),
                    dogToBeUpdated.getDateOfBirth().getDay(),
                    null);
        }
//        handle intent from DogListActivity (if exists) - end

        Button buttonAddDog = findViewById(R.id.buttonAddDog);
        buttonAddDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                extracting the submited values from the form
                EditText editTextName = findViewById(R.id.editTextName);
                String name = editTextName.getText().toString();

                CheckBox checkBoxIsAggressive = findViewById(R.id.checkBoxIsAggressive);
                boolean isAggressive = checkBoxIsAggressive.isChecked();

                RadioGroup radioGroupBreed = findViewById(R.id.radioGroupBreed);
                int selectetRadioButtonId = radioGroupBreed.getCheckedRadioButtonId();
                RadioButton radioButtonBreed = findViewById(selectetRadioButtonId);
                String breed = radioButtonBreed.getText().toString();

                Spinner spinnerSize = findViewById(R.id.spinnerSize);
                String size = spinnerSize.getSelectedItem().toString();

                RatingBar ratingBarCutenessLevel = findViewById(R.id.ratingBarCutenessLevel);
                double cutenessLevel = ratingBarCutenessLevel.getRating();

                Switch switchIsPlayful = findViewById(R.id.switchIsPlayful);
                boolean isPlayful = switchIsPlayful.isChecked();

                DatePicker datePickerDateOfBirth = findViewById(R.id.datePickerDateOfBirth);
                Date dateOfBirth = new Date(datePickerDateOfBirth.getYear(), datePickerDateOfBirth.getMonth(), datePickerDateOfBirth.getDayOfMonth());

                Dog newDog = new Dog(name, isAggressive, breed, size, cutenessLevel, isPlayful, dateOfBirth);

                Intent intentForMainActivity = new Intent();
                Bundle bundleForMainActivity = new Bundle();

                bundleForMainActivity.putParcelable("newDog", newDog);
                intentForMainActivity.putExtras(bundleForMainActivity);

                setResult(RESULT_OK, intentForMainActivity);
                finish();
            }
        });
    }
}