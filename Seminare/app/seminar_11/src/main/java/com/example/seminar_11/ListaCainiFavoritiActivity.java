package com.example.seminar_11;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListaCainiFavoritiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_caini_favoriti);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences sharedPreferences = getSharedPreferences("cainiFavoriti", MODE_PRIVATE);
        Map<String, String> cheiCainiFavoriti = (Map<String, String>) sharedPreferences.getAll();
        List<String> cainiFavoriti = new ArrayList<>();

        for (Map.Entry<String, String> cheieCaineFavorit : cheiCainiFavoriti.entrySet()) {
            cainiFavoriti.add(cheieCaineFavorit.getValue());
        }

        ListView listViewFavorite = findViewById(R.id.listViewFavorite);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ListaCainiFavoritiActivity.this, android.R.layout.simple_list_item_1, cainiFavoriti);
        listViewFavorite.setAdapter(arrayAdapter);
    }
}