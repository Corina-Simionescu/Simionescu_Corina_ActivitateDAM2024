package com.example.seminar_11;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListaCainiActivity extends AppCompatActivity {
    private List<Caine> caini = null;
    private int idModificat = 0;
    private CaineAdapter caineAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_caini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listViewCaini = findViewById(R.id.listViewCaini);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            CaineDatabase db = CaineDatabase.getInstance(getApplicationContext());
            caini = db.caineDAO().getAllCaini();

            runOnUiThread(() -> {
                caineAdapter = new CaineAdapter(caini, ListaCainiActivity.this, R.layout.row_item);
                listViewCaini.setAdapter(caineAdapter);
            });
        });

        listViewCaini.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentModifica = new Intent(ListaCainiActivity.this, AdaugaCaineActivity.class);
                Caine selectedCaine = caini.get(position);
                long selctedCaineDbId = selectedCaine.getId();

                intentModifica.putExtra("caineId", selctedCaineDbId);
                startActivityForResult(intentModifica, 777);
            }
        });

        listViewCaini.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sp = getSharedPreferences("cainiFavoriti", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(String.valueOf(caini.get(position).getId()), caini.get(position).toString());
                editor.apply();

                Toast.makeText(ListaCainiActivity.this, "Caine adaugat la favorite", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 777) {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                CaineDatabase db = CaineDatabase.getInstance(getApplicationContext());
                caini.clear();
                caini.addAll(db.caineDAO().getAllCaini());

                runOnUiThread(() -> {
                    caineAdapter.notifyDataSetChanged();
                });
            });
        }
    }
}