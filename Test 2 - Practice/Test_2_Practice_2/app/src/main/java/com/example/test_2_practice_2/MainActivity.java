package com.example.test_2_practice_2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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

        Button buttonDateJson = findViewById(R.id.buttonDateJson);
        buttonDateJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JsonActivity.class);
                startActivity(intent);
            }
        });

        Button buttonDateXML = findViewById(R.id.buttonDateXML);
        buttonDateXML.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, XmlActivity.class);
            startActivity(intent);
        });

        Button buttonDateJsonFormatate = findViewById(R.id.buttonDateJsonFormatate);
        buttonDateJsonFormatate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JsonFormatatActivity.class);
                startActivity(intent);
            }
        });

        Button buttonDateXmlFormatate = findViewById(R.id.buttonDateXmlFormatate);
        buttonDateXmlFormatate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, XmlFormatatActivity.class);
                startActivity(intent);
            }
        });
    }
}