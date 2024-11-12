package com.example.test_1_practice_3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Dog> dogList = new ArrayList<>();

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

        Button buttonGoToFormAddDogActivity = findViewById(R.id.buttonGoToFormAddDogActivity);
        buttonGoToFormAddDogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentForFormAddDogActivity = new Intent(MainActivity.this, FormAddDogActivity.class);

                startActivityForResult(intentForFormAddDogActivity, 102);
            }
        });

        Button buttonGoToDogListActivity = findViewById(R.id.buttonGoToDogListActivity);
        buttonGoToDogListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentForDogListActivity = new Intent(MainActivity.this, DogListActivity.class);
                Bundle bundleForDogListActivity = new Bundle();

                bundleForDogListActivity.putParcelableArrayList("dogList", (ArrayList<? extends Parcelable>) dogList);
                intentForDogListActivity.putExtras(bundleForDogListActivity);

                startActivity(intentForDogListActivity);
            }
        });

        Button buttonGoToCustomDogListActivity = findViewById(R.id.buttonGoToCustomDogListActivity);
        buttonGoToCustomDogListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentForCustomDogListActivity = new Intent(MainActivity.this, CustomDogListActivity.class);
                Bundle bundleForCustomDogListActivity = new Bundle();

                bundleForCustomDogListActivity.putParcelableArrayList("dogList", (ArrayList<? extends Parcelable>) dogList);
                intentForCustomDogListActivity.putExtras(bundleForCustomDogListActivity);

                startActivity(intentForCustomDogListActivity);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 102 && resultCode == RESULT_OK && data != null) {
            Bundle bundleFromFormAddDogActivity = data.getExtras();
            Dog newDog = bundleFromFormAddDogActivity.getParcelable("newDog");

            dogList.add(newDog);

            Log.d("newDog", newDog.toString());
            Toast.makeText(MainActivity.this, newDog.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}