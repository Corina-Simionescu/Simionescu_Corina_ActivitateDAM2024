package com.example.seminar_8;

import android.content.Intent;
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

        Intent intent = getIntent();
        caini = intent.getParcelableArrayListExtra("caini");

        ListView listViewCaini = findViewById(R.id.listViewCaini);
        caineAdapter = new CaineAdapter(caini, ListaCainiActivity.this, R.layout.row_item);
        listViewCaini.setAdapter(caineAdapter);
        Log.d("ListaCainiActivity", "Number of items in adapter: " + caineAdapter.getCount());

        listViewCaini.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ListaCainiActivity", "Item clicked at position: " + position);
                Caine selectedCaine = caini.get(position);
                Log.d("ListaCainiActivity", "Selected caine: " + selectedCaine);

                Intent intentModifica = new Intent(ListaCainiActivity.this, AdaugaCaineActivity.class);
                intentModifica.putExtra("caine", caini.get(position));
                idModificat = position;
                startActivityForResult(intentModifica, 777);
                Toast.makeText(getApplicationContext(), caini.get(position).toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 777) {
                caini.set(idModificat, data.getParcelableExtra("caine"));
                caineAdapter.notifyDataSetChanged();
            }
        }

    }
}