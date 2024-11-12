package com.example.test_1_practice_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intentFromActivity1 = getIntent();
        Bundle bundleFromActivity1 = intentFromActivity1.getExtras();
        InventiveClassName instance1 = bundleFromActivity1.getParcelable("instance1");

        Toast.makeText(MainActivity2.this, instance1.toString(), Toast.LENGTH_LONG).show();

        TextView textViewInstaceFromActivity1 = findViewById(R.id.textViewInstanceFromActivity1);
        textViewInstaceFromActivity1.setText(instance1.toString());
    }
}