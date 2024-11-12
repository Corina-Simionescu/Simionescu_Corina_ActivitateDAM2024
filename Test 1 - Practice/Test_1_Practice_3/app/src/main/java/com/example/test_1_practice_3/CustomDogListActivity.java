package com.example.test_1_practice_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class CustomDogListActivity extends AppCompatActivity {
    private List<Dog> dogList = null;
    private int updatedDogIndex;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_custom_dog_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intentFromMainActivity = getIntent();
        Bundle bundleFromMainActivity = intentFromMainActivity.getExtras();

        dogList = new ArrayList<>();
        dogList = bundleFromMainActivity.getParcelableArrayList("dogList");

        ListView listViewCustomDogList = findViewById(R.id.listViewCustomDogList);
        customAdapter = new CustomAdapter(CustomDogListActivity.this, R.layout.custom_item, dogList);
        listViewCustomDogList.setAdapter(customAdapter);

        listViewCustomDogList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("aaaa");
                Dog dogToBeUpdated = dogList.get(position);
                updatedDogIndex = position;

                Intent intentForFormAddDogActivity = new Intent(CustomDogListActivity.this, FormAddDogActivity.class);
                Bundle bundleForFormAddDogActivity = new Bundle();

                bundleForFormAddDogActivity.putParcelable("dogToBeUpdated", dogToBeUpdated);
                intentForFormAddDogActivity.putExtras(bundleForFormAddDogActivity);

                startActivityForResult(intentForFormAddDogActivity, 402);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 402 && resultCode == RESULT_OK && data != null) {
            Bundle bundleFromFormAddDogActivity = data.getExtras();
            Dog updatedDog = bundleFromFormAddDogActivity.getParcelable("newDog");

            dogList.set(updatedDogIndex, updatedDog);
            customAdapter.notifyDataSetChanged();
        }
    }
}