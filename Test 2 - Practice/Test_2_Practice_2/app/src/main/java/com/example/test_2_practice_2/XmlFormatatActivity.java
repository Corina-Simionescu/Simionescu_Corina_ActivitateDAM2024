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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlFormatatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xml_formatat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textViewXmlFormatat = findViewById(R.id.textViewXmlFormatat);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(() -> {
            String textDeAfisat;

            try {
                URL url = new URL("https://www.bnr.ro/nbrfxrates10days.xml");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();

                //parsare xml
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(inputStream);

                StringBuilder stringBuilder = new StringBuilder();

                // Luăm toate elementele Cube (pentru fiecare zi)
                NodeList cubeList = document.getElementsByTagName("Cube");
                for (int i = 0; i < cubeList.getLength(); i++) {
                    Node cubeNode = cubeList.item(i);
                    if (cubeNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element cube = (Element) cubeNode;
                        String date = cube.getAttribute("date");
                        stringBuilder.append("\nData: ").append(date).append("\n");

                        // Luăm toate ratele pentru ziua curentă
                        NodeList rateList = cube.getElementsByTagName("Rate");
                        for (int j = 0; j < rateList.getLength(); j++) {
                            Node rateNode = rateList.item(j);
                            if (rateNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element rate = (Element) rateNode;
                                String currency = rate.getAttribute("currency");
                                String multiplier = rate.getAttribute("multiplier");
                                String value = rate.getTextContent();

                                if (multiplier != null && !multiplier.isEmpty()) {
                                    stringBuilder.append(currency)
                                            .append(" (x")
                                            .append(multiplier)
                                            .append("): ")
                                            .append(value)
                                            .append("\n");
                                } else {
                                    stringBuilder.append(currency)
                                            .append(": ")
                                            .append(value)
                                            .append("\n");
                                }
                            } //inchidere if 2
                        } //inchidere for 2
                    } //inchidere if 1
                } //inchidere for

                textDeAfisat = stringBuilder.toString();
                inputStream.close();
                httpURLConnection.disconnect();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            }

            handler.post(()->{
                textViewXmlFormatat.setText(textDeAfisat);
            });
        });
    }
}