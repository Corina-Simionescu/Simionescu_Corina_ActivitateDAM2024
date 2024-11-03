package com.example.seminar_5;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Caine> caini = null;
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

        caini =  new ArrayList<>();

        Button adaugaCaineButton = findViewById(R.id.adaugaCaineButton);
        adaugaCaineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdaugaCaineActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        Button buttonVizualizareCaini = findViewById(R.id.buttonVizualizareCaini);
        buttonVizualizareCaini.setOnClickListener((View view)->{
            Intent intent = new Intent(getApplicationContext(), ListaCainiActivity.class);
            intent.putParcelableArrayListExtra("caini", (ArrayList<? extends Parcelable>) caini);
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Caine caine = data.getParcelableExtra("caine");
            caini.add(caine);
            Toast.makeText(getApplicationContext(), caine.toString(), Toast.LENGTH_LONG).show();
        }
    }
}