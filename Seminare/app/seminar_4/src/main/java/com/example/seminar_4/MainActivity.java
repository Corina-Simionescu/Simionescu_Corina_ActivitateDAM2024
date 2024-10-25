package com.example.seminar_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button adaugaCaineButton = findViewById(R.id.adaugaCaineButton);
        adaugaCaineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdaugaCaineActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Caine caine = (Caine) bundle.getSerializable("caine");

                String informatiiCaine = "Nume: " + caine.getNume()
                        + "| Este agresiv: " + caine.isEsteAgresiv()
                        + "| Rasa: " + caine.getRasa()
                        + "| Dimensiune: " + caine.getDimensiune()
                        + "| Nivel de dragutenie: " + caine.getNivelDeDragutenie()
                        + "| Este jucaus: " + caine.isEsteJucaus();

                Toast.makeText(this, informatiiCaine, Toast.LENGTH_LONG).show();
            }
        }
    }
}