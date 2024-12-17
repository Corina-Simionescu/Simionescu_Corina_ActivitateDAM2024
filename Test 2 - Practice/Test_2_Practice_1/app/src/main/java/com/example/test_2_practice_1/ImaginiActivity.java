package com.example.test_2_practice_1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Executable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImaginiActivity extends AppCompatActivity {
    List<Bitmap> listaImagini = new ArrayList<>();
    List<ImagineItem> listaImagineItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imagini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //trebuie sa iau o imagine de pe net, adica sa accesez un link catre o imagine
        //si sa fac request catre acel link ca sa imi dea imaginea
        //pasi:
        //1. copy paste link-ul imaginii de pe net ("copy image address")
        //2. fac request la acel link
        //   -> requestul trebuie facut intr un thread secundar
        //3. preiau imaginea (Bitmap)
        //4. din interiorul thread-ului secundar fac niste operatii pentru threadul main
        //   -> ca sa accesez threadul main trb cu handler

        String link1 = "https://cdn.pixabay.com/photo/2023/08/18/15/02/dog-8198719_1280.jpg";
        Bitmap imagine1;

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(link1);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    listaImagini.add(BitmapFactory.decodeStream(inputStream));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listaImagineItems.add(new ImagineItem("imagine 1", listaImagini.get(0), "https://www.royalcanin.com/ro/dogs/breeds?category=x-small"));

                        ListView listViewImagini = findViewById(R.id.listViewImagini);
                        ImagineAdpater imagineAdpater = new ImagineAdpater(ImaginiActivity.this, R.layout.layout_imagine, listaImagineItems);
                        listViewImagini.setAdapter(imagineAdpater);
                    }
                });
            }
        });

            ListView listViewImagini = findViewById(R.id.listViewImagini);
            listViewImagini.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ImagineItem imagineItem = listaImagineItems.get(position);
                    String link = imagineItem.getLink();

                    Intent intent = new Intent(ImaginiActivity.this, WebViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("link", link);
                    intent.putExtras(bundle);

                    startActivity(intent);
                }
            });
    }
}