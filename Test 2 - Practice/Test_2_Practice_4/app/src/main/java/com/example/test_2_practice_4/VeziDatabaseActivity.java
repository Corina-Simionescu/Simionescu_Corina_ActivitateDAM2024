package com.example.test_2_practice_4;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VeziDatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vezi_database);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(() -> {
            TravelDatabase travelDatabase = TravelDatabase.getInstance(getApplicationContext());
            List<Travel> travelList = travelDatabase.travelDAO().getAllTravels();

            handler.post(() -> {
                TextView textView = findViewById(R.id.textView);
                textView.setText(travelList.toString());
            });
        });
    }
}