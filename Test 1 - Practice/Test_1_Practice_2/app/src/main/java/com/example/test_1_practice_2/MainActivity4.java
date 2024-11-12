package com.example.test_1_practice_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intentFromActivity1 = getIntent();
        Bundle bundleFromActivity1 = intentFromActivity1.getExtras();
        List<InventiveClassName> listOfInstances = bundleFromActivity1.getParcelableArrayList("listOfInstances");

        ListView listViewCustom = findViewById(R.id.listViewCustom);
        CustomAdapter customAdapter = new CustomAdapter(MainActivity4.this, listOfInstances, R.layout.custom_layout);

        listViewCustom.setAdapter(customAdapter);

        listViewCustom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity4.this, listOfInstances.get(position).toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}