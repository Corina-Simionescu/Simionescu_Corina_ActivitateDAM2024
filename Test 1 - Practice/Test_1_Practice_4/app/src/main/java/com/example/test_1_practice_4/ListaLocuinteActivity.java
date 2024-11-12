package com.example.test_1_practice_4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ListaLocuinteActivity extends AppCompatActivity {
    List<Locuinta> locuintaList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_locuinte);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intentDeLaMainActivity = getIntent();
        Bundle bundleDeLaMainActivity = intentDeLaMainActivity.getExtras();

        locuintaList = new ArrayList<>();
        locuintaList = bundleDeLaMainActivity.getParcelableArrayList("locuinte");

        ListView listView = findViewById(R.id.listViewLocuinte);
        ArrayAdapter<Locuinta> arrayAdapter = new ArrayAdapter<>(ListaLocuinteActivity.this, android.R.layout.simple_list_item_1, locuintaList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Locuinta locuintaSelectata = locuintaList.get(position);

                Toast.makeText(ListaLocuinteActivity.this, locuintaSelectata.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Locuinta locuintaSelectata = locuintaList.get(position);
                locuintaList.remove(locuintaSelectata);

                arrayAdapter.notifyDataSetChanged();

                return false;
            }
        });
    }
}