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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class JsonFormatatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_json_formatat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(() -> {
            String textDeAfisatString;

            try {
                URL url = new URL("https://pdm.ase.ro/SITUATII.JSON");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();

                //citire json din input stream
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                String jsonResult = stringBuilder.toString();

                bufferedReader.close();
                inputStreamReader.close();

                //parsare json
                StringBuilder textDeAfisat = new StringBuilder();
                JSONObject jsonObject = new JSONObject(jsonResult);
                JSONArray jsonArraySituatii = jsonObject.getJSONArray("situatii");

                for (int i = 0; i < jsonArraySituatii.length(); i++) {
                    JSONObject jsonObjectSituatie = jsonArraySituatii.getJSONObject(i);
                    textDeAfisat
                            .append("Disciplina: ")
                            .append(jsonObjectSituatie.getString("disciplina"))
                            .append("\nActivitate: ")
                            .append(jsonObjectSituatie.getString("activitate"))
                            .append("\nValoare: ")
                            .append(jsonObjectSituatie.getInt("valoare"))
                            .append("\nPondere: ")
                            .append(jsonObjectSituatie.getDouble("pondere"))
                            .append("\nData: ")
                            .append(jsonObjectSituatie.getString("data"))
                            .append("\nDescriere: ")
                            .append(jsonObjectSituatie.getString("descriere"))
                            .append("\n\n");
                }

                textDeAfisatString = textDeAfisat.toString();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            handler.post(() -> {
                TextView textViewJsonFormatat = findViewById(R.id.textViewJsonFormatat);
                textViewJsonFormatat.setText(textDeAfisatString);
            });
        });
    }
}