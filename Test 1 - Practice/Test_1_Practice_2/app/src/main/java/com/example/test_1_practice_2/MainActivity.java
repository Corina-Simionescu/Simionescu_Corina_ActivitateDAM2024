package com.example.test_1_practice_2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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

        InventiveClassName instance1 = new InventiveClassName(1, 1.5f, 2.5, "instance1");

        Button buttonGoToActivity2 = findViewById(R.id.buttonGoToActivity2);
        buttonGoToActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToSendAtActivity2 = new Intent(MainActivity.this, MainActivity2.class);
                Bundle bundleToSendAtActivity2 = new Bundle();

                bundleToSendAtActivity2.putParcelable("instance1", instance1);
                intentToSendAtActivity2.putExtras(bundleToSendAtActivity2);

                startActivity(intentToSendAtActivity2);
            }
        });

        InventiveClassName instance2 = new InventiveClassName(2, 2.5f, 20.5, "instance2");
        InventiveClassName instance3 = new InventiveClassName(3, 3.5f, 30.5, "instance3");
        InventiveClassName instance4 = new InventiveClassName(4, 4.5f, 40.5, "instance4");

        List<InventiveClassName> listOfInstances = new ArrayList<>();
        listOfInstances.add(instance2);
        listOfInstances.add(instance3);
        listOfInstances.add(instance4);

        Button buttonGoToActivity3 = findViewById(R.id.buttonGoToActivity3);
        buttonGoToActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToSendAtActivity3 = new Intent(MainActivity.this, MainActivity3.class);
                Bundle bundleToSendAtActivity3 = new Bundle();

                bundleToSendAtActivity3.putParcelableArrayList("listOfInstances", (ArrayList<? extends Parcelable>) listOfInstances);
                intentToSendAtActivity3.putExtras(bundleToSendAtActivity3);

                startActivity(intentToSendAtActivity3);
            }
        });

        Button buttonGoToActivity4 = findViewById(R.id.buttonGoToActivity4);
        buttonGoToActivity4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToSendToActivity4 = new Intent(MainActivity.this, MainActivity4.class);
                Bundle bundleToSendToActivity4 = new Bundle();

                bundleToSendToActivity4.putParcelableArrayList("listOfInstances", (ArrayList<? extends Parcelable>) listOfInstances);
                intentToSendToActivity4.putExtras(bundleToSendToActivity4);

                startActivity(intentToSendToActivity4);
            }
        });
    }
}