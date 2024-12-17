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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class JsonActivity extends AppCompatActivity {
    List<Travel> travelList = new ArrayList<>();

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

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(() -> {
            String textDeAfisatString;
            try {
                URL url = new URL("https://api.jsonbin.io/v3/qs/67615f75ad19ca34f8dc9394");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();

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
                JSONObject jsonObjectRecord = jsonObject.getJSONObject("record");
                JSONArray jsonArrayTravels = jsonObjectRecord.getJSONArray("travels");

                //afisarea datelor
                for (int i = 0; i < jsonArrayTravels.length(); i++) {
                    JSONObject jsonObjectTravel = jsonArrayTravels.getJSONObject(i);
                    JSONObject jsonObjectFlight = jsonArrayTravels.getJSONObject(i).getJSONObject("flight");

                    textDeAfisat
                            .append("departureCountry: ")
                            .append(jsonObjectTravel.getString("departureCountry"))
                            .append("\ntravelDocument: ")
                            .append(jsonObjectTravel.getString("travelDocument"))
                            .append("\nflight!: ")
                            .append("\n  arrivalCountry: ")
                            .append(jsonObjectFlight.getString("arrivalCountry"))
                            .append("\n   price: ")
                            .append(jsonObjectFlight.getString("price"))
                            .append("\n====================================\n");
                }

                textDeAfisatString = textDeAfisat.toString();

                //crearea obiectelor
                for (int i = 0; i < jsonArrayTravels.length(); i++) {
                    JSONObject jsonObjectTravel = jsonArrayTravels.getJSONObject(i);
                    String departureCountry = jsonObjectTravel.getString("departureCountry");
                    String travelDocument = jsonObjectTravel.getString("travelDocument");
                    JSONObject jsonObjectFlight = jsonArrayTravels.getJSONObject(i).getJSONObject("flight");
                    String arrivalCountry = jsonObjectFlight.getString("arrivalCountry");
                    String price = jsonObjectFlight.getString("price");

                    Travel travel = new Travel(departureCountry, travelDocument, arrivalCountry, price);
                    travelList.add(travel);
                }

                //punerea obiectelor in baza de date
                for (int i = 0; i < travelList.size(); i++) {
                    TravelDatabase travelDatabase = TravelDatabase.getInstance(getApplicationContext());
                    long id = travelDatabase.travelDAO().insert(travelList.get(i));
                    travelList.get(i).setId(id);
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            handler.post(() -> {
                TextView textView = findViewById(R.id.textView);
                textView.setText(textDeAfisatString);
            });
        });
    }
}