package com.example.seminar_8;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListaImaginiActivity extends AppCompatActivity {
    private List<Bitmap> downloadedImagini = new ArrayList<>();
    private List<ImaginiDomeniu> objectsImagini = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_imagini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<String> linkuriImagini = new ArrayList<>();
        //caini
        linkuriImagini.add("https://img.freepik.com/free-photo/ai-generated-labrador-retriever-dog-picture_23-2150645006.jpg?t=st=1732616431~exp=1732620031~hmac=7ecfb5dc423088bb6deec43a43712f9567ec2999b7409dfb1601706589a1d284&w=1800");
        linkuriImagini.add("https://as1.ftcdn.net/v2/jpg/02/31/62/58/1000_F_231625827_2XdTflBYi2ep7fzene8RV5DNNQTEc42V.jpg");
        linkuriImagini.add("https://media.istockphoto.com/id/1364860635/ro/fotografie/c%C3%A2ine-catelus-dachshund-st%C3%A2nd-%C3%AEn-cad%C4%83-cu-ra%C8%9B%C4%83-de-plastic-galben-pe-cap-%C8%99i-se-uit%C4%83-%C3%AEn-sus.jpg?s=612x612&w=0&k=20&c=dWj8HV3XfVe_7g9S9SxklRlAqmIm8_kMN5_hSl5EL6k=");
        linkuriImagini.add("https://wallpapers.com/images/featured/funny-dog-picture-vznfz9yqlvhukg0f.jpg");
        linkuriImagini.add("https://images.unsplash.com/photo-1669891189952-25eb6fd9cf13?w=700&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NjB8fGZ1bm55JTIwZG9nfGVufDB8fDB8fHww");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (String link : linkuriImagini) {
                    HttpURLConnection connection = null;
                    try {
                        URL url = new URL(link);
                        connection = (HttpURLConnection) url.openConnection();

                        InputStream is = connection.getInputStream();
                        downloadedImagini.add(BitmapFactory.decodeStream(is));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        objectsImagini.add(new ImaginiDomeniu("caine dragut", downloadedImagini.get(0), "https://en.wikipedia.org/wiki/Dog"));
                        objectsImagini.add(new ImaginiDomeniu("caine dragut", downloadedImagini.get(1), "https://en.wikipedia.org/wiki/Dog"));
                        objectsImagini.add(new ImaginiDomeniu("caine dragut", downloadedImagini.get(2), "https://en.wikipedia.org/wiki/Dog"));
                        objectsImagini.add(new ImaginiDomeniu("caine dragut", downloadedImagini.get(3), "https://en.wikipedia.org/wiki/Dog"));
                        objectsImagini.add(new ImaginiDomeniu("caine dragut", downloadedImagini.get(4), "https://en.wikipedia.org/wiki/Dog"));

                        ListView listViewImagini = findViewById(R.id.listViewImagini);
                        ImagineAdapter imagineAdapter = new ImagineAdapter(ListaImaginiActivity.this, R.layout.imagine_layout, objectsImagini);
                        listViewImagini.setAdapter(imagineAdapter);
                    }
                });
            }
        });

        ListView listViewImagini = findViewById(R.id.listViewImagini);
        listViewImagini.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                intent.putExtra("link", objectsImagini.get(position).getLink());
                startActivity(intent);
            }
        });
    }
}