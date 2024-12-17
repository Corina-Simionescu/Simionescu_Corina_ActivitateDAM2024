package com.example.test_2_practice_3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AdaugaOmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_om);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextNume = findViewById(R.id.editTextNume);
                EditText editTextVarsta = findViewById(R.id.editTextVarsta);

                String nume = editTextNume.getText().toString();
                String varstaString = editTextVarsta.getText().toString();
                int varsta = Integer.parseInt(varstaString);

                Om om1 = new Om(nume, varsta);

                //adaugare in baza de date
                Executor executor = Executors.newSingleThreadExecutor();

                executor.execute(() -> {
                    OmDatabase omDatabase = OmDatabase.getInstance(getApplicationContext());
                    long id = omDatabase.omDAO().insert(om1);
                    om1.setId(id);
                });

                finish();
            }
        });
    }
}