package com.example.seminar_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it = getIntent();
        String text = it.getStringExtra("text");
        int nr1 = it.getIntExtra("nr1", 0);
        int nr2 = it.getIntExtra("nr2", 0);

        Toast.makeText(getApplicationContext(), text + ", suma: " + (nr1 + nr2), Toast.LENGTH_LONG).show();

        //Ce se va face la seminarul 4: datele completate in MainActivity2, le punem intr un obiect
        //si le trimitem in MainActivity1

        EditText editTextNume = findViewById(R.id.editTextNume);
        EditText editTextGrupa = findViewById(R.id.editTextGrupa);
        EditText editTextFacultate = findViewById(R.id.editTextFacultate);
        CheckBox checkBoxIntegralist = findViewById(R.id.checkBoxIntegralist);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nume = editTextNume.getText().toString();
                int grupa = Integer.parseInt(editTextGrupa.getText().toString());
                String facultate = editTextFacultate.getText().toString();
                boolean esteIntegralist = checkBoxIntegralist.isChecked();

                Student student = new Student(nume, grupa, facultate, esteIntegralist);

                Intent intent = new Intent();
                intent.putExtra("student", student);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}