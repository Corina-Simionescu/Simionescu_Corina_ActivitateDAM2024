package com.example.test_1_practice_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sending data via intent
//                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//                intent.putExtra("message", "Message from activity 1");

//                sending data via intent that has a bundle
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                Bundle bundle = new Bundle();

                bundle.putString("message", "Message from activity 1 (using bundle)");
                intent.putExtras(bundle);

//                startActivity(intent);
                startActivityForResult(intent, 999);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 999 && resultCode == RESULT_OK && data != null) {
            Bundle bundle  = data.getExtras();
            String message2 = bundle.getString("message2");

            if(message2 == null){
                Log.d("aaa", "message2 is null");
            }

            Toast.makeText(MainActivity.this, message2, Toast.LENGTH_LONG).show();

            TextView textView = findViewById(R.id.textViewMessageFromActivity2);
            textView.setText(message2);
        }
    }
}