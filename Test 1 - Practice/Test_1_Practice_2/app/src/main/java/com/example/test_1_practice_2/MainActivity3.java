package com.example.test_1_practice_2;

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

import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intentFromActivity1 = getIntent();
        Bundle bundleFromActivity1 = intentFromActivity1.getExtras();
        List<InventiveClassName> listOfInstances = bundleFromActivity1.getParcelableArrayList("listOfInstances");

        ListView listViewInstances = findViewById(R.id.listViewInstances);
        ArrayAdapter<InventiveClassName> adapterListViewInstances = new ArrayAdapter<>(
                MainActivity3.this, android.R.layout.simple_list_item_1, listOfInstances);
        listViewInstances.setAdapter(adapterListViewInstances);

        listViewInstances.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity3.this, listOfInstances.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}