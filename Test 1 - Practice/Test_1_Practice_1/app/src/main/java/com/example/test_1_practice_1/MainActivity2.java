package com.example.test_1_practice_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

//        receiving intent that just has data inside, no bundle
//        Intent intent = getIntent();
//        String message = intent.getStringExtra("message");

//        receiving intent that has a bundle inside
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String message = bundle.getString("message");

        Toast.makeText(MainActivity2.this, message, Toast.LENGTH_LONG).show();

//      putting the message inside a text view
        TextView textViewMessage = findViewById(R.id.textViewMessage);
        textViewMessage.setText(message);

        Button buttonGoBack = findViewById(R.id.buttonGoBack);
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message2 = "Message from activity 2";
                Bundle bundle1 = new Bundle();
                Intent resultIntent = new Intent();

                bundle1.putString("message2", message2);
                resultIntent.putExtras(bundle1);

                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}