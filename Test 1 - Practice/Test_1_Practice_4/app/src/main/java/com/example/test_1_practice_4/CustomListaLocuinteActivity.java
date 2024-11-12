package com.example.test_1_practice_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class CustomListaLocuinteActivity extends AppCompatActivity {
    private List<Locuinta> locuintaList = new ArrayList<>();
    private int locuintaSelectataIndex;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_custom_lista_locuinte);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intentFromMainActivity = getIntent();
        Bundle bundleFromMainActivity = intentFromMainActivity.getExtras();

        locuintaList = bundleFromMainActivity.getParcelableArrayList("locuintaList");

        ListView listViewCustom = findViewById(R.id.listViewCustom);
        customAdapter = new CustomAdapter(CustomListaLocuinteActivity.this, R.layout.custom_list_item, locuintaList);
        listViewCustom.setAdapter(customAdapter);

        listViewCustom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentPentruAdaugaLocuintaActivity = new Intent(CustomListaLocuinteActivity.this, AdaugaLocuintaActivity.class);
                Bundle bundlePentruAdaugaLocuintaActivity = new Bundle();

                Locuinta locuintaSelectata = locuintaList.get(position);
                locuintaSelectataIndex = position;

                bundlePentruAdaugaLocuintaActivity.putParcelable("locuintaSelectata", locuintaSelectata);
                intentPentruAdaugaLocuintaActivity.putExtras(bundlePentruAdaugaLocuintaActivity);

                startActivityForResult(intentPentruAdaugaLocuintaActivity, 402);
            }
        });

        listViewCustom.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                locuintaList.remove(locuintaSelectataIndex);
                customAdapter.notifyDataSetChanged();

                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 402 && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            Locuinta locuinta = bundle.getParcelable("locuinta");
            locuintaList.set(locuintaSelectataIndex, locuinta);
            customAdapter.notifyDataSetChanged();
        }
    }
}