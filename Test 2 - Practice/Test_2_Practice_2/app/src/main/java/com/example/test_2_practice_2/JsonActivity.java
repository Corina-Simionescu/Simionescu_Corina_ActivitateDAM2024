package com.example.test_2_practice_2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class JsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_json);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textViewJson = findViewById(R.id.textViewJson);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(() -> {
            String jsonResult;

            try {
                URL url = new URL("https://pdm.ase.ro/SITUATII.JSON");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();

                //citire JSON din input stream
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                jsonResult = stringBuilder.toString();

                bufferedReader.close();
                inputStreamReader.close();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //Nu e nevoie sa fac: <String finalJsonResult = jsonResult;>
            //pt ca folosind lambda expression, jsonResult e privita ca o variabila final,
            //deoarece lui jsonResult nu i se schimba valoarea niciodata dupa initializare.
            //Daca i s ar fi schimbat, atunci trb sa fac chestia cu final
            handler.post(() -> {
                textViewJson.setText(jsonResult);
            });
        });
    }
}