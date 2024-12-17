package com.example.test_2_practice_3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.TextureView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DBActivity extends AppCompatActivity {
    List<Om> oameniDB = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dbactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textViewDB = findViewById(R.id.textViewDB);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(() -> {
            OmDatabase omDatabase = OmDatabase.getInstance(getApplicationContext());
            oameniDB = omDatabase.omDAO().getAllOameni();

            handler.post(() -> {
                textViewDB.setText(oameniDB.toString());
            });
        });
    }
}