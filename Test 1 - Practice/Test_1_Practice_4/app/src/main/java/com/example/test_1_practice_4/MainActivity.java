package com.example.test_1_practice_4;

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
    List<Locuinta> locuintaList = new ArrayList<>();

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

        Button buttonGoToAdaugaLocuinta = findViewById(R.id.buttonGoToAdaugaLocuinta);
        buttonGoToAdaugaLocuinta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPentruAdaugaLocuinta = new Intent(MainActivity.this, AdaugaLocuintaActivity.class);
                startActivityForResult(intentPentruAdaugaLocuinta, 102);
            }
        });

        Button buttonGoToListaLocuinte = findViewById(R.id.buttonGoToListaLocuinte);
        buttonGoToListaLocuinte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPentruListaLocuinteActivity = new Intent(MainActivity.this, ListaLocuinteActivity.class);
                Bundle bundlePentruListaLocuinteActivity = new Bundle();

                bundlePentruListaLocuinteActivity.putParcelableArrayList("locuinte", (ArrayList<? extends Parcelable>) locuintaList);
                intentPentruListaLocuinteActivity.putExtras(bundlePentruListaLocuinteActivity);

                startActivity(intentPentruListaLocuinteActivity);
            }
        });

        Button buttonGoToCustomListaLocuinte = findViewById(R.id.buttonGoToCustomListaLocuinte);
        buttonGoToCustomListaLocuinte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentForCustomActivity = new Intent(MainActivity.this, CustomListaLocuinteActivity.class);
                Bundle bundleForCustomActivity = new Bundle();

                bundleForCustomActivity.putParcelableArrayList("locuintaList", (ArrayList<? extends Parcelable>) locuintaList);
                intentForCustomActivity.putExtras(bundleForCustomActivity);

                startActivity(intentForCustomActivity);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 102 && resultCode == RESULT_OK && data != null) {
            Bundle bundleDeLaAdaugaLocuinta = data.getExtras();
            Locuinta locuinta = bundleDeLaAdaugaLocuinta.getParcelable("locuinta");

            locuintaList.add(locuinta);

            Toast.makeText(MainActivity.this, locuinta.toString(), Toast.LENGTH_SHORT).show();
            System.out.println(locuinta);
        }
    }
}