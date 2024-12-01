package com.example.seminar_9;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.SystemBarStyle;
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
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VremeActivity extends AppCompatActivity {
    private String apiKey = "1XpwJefokhTfGoVAjGabyXu8YZLENuv0";
    private String cheieOras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vreme);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonCautaVreme = findViewById(R.id.buttonCautaVreme);
        buttonCautaVreme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextOras = findViewById(R.id.editTextOras);
                String oras = editTextOras.getText().toString();

                afiseazaCheieOras(oras, new Runnable() {
                    @Override
                    public void run() {
                        afiseazaVremeOZi();
                    }
                });
            }
        });
    }

    private void afiseazaCheieOras(String oras, Runnable onComplete) {
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    String encodedOras = URLEncoder.encode(oras, StandardCharsets.UTF_8.toString());
                    URL url = new URL("https://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + apiKey + "&q=" + oras);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream is = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;

                        while ((line = reader.readLine()) != null) {
                            stringBuilder.append(line);
                        }

                        final String response = stringBuilder.toString();

                        JSONArray jsonArray = new JSONArray(response);
                        if (jsonArray.length() > 0) {
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            cheieOras = jsonObject.getString("Key");

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    TextView textViewCheieOras = findViewById(R.id.textViewCheieOras);
                                    textViewCheieOras.setText(cheieOras);

                                    if (onComplete != null) {
                                        onComplete.run();
                                    }
                                }
                            });
                        } else {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(VremeActivity.this, "Orasul nu a fost gasit", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        reader.close();
                        is.close();
                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(VremeActivity.this, "Error: " + responseCode, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (Exception e) {
                    final String errorMessage = e.getMessage();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(VremeActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        });
    }

    private void afiseazaVremeOZi() {
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("https://dataservice.accuweather.com/forecasts/v1/daily/1day/" + cheieOras + "?apikey=" + apiKey);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream is = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;

                        while ((line = reader.readLine()) != null) {
                            stringBuilder.append(line);
                        }

                        final String response = stringBuilder.toString();
                        JSONObject jsonRepsonse = new JSONObject(response);
                        JSONArray dailyForecast = jsonRepsonse.getJSONArray("DailyForecasts");
                        JSONObject firstDay = dailyForecast.getJSONObject(0);
                        JSONObject temperature = firstDay.getJSONObject("Temperature");

                        double minTemp = temperature.getJSONObject("Minimum").getDouble("Value");
                        double maxTemp = temperature.getJSONObject("Maximum").getDouble("Value");

                        int minTempC = (int) (minTemp - 32) * 5 / 9;
                        int maxTempC = (int) (maxTemp - 32) * 5 / 9;

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                TextView textViewVreme = findViewById(R.id.textViewVreme);
                                textViewVreme.setText("min: " + minTempC + ", max: " + maxTempC);
                            }
                        });

                        reader.close();
                        is.close();
                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(VremeActivity.this, "Error: " + responseCode, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (ProtocolException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        });
    }
}