package com.example.test_1_practice_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class DogListActivity extends AppCompatActivity {
    private List<Dog> dogList = null;
    private int updatedDogIndex;
    ArrayAdapter<Dog> arrayAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dog_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intentFromMainActivity = getIntent();
        Bundle bundleFromMainActivity = intentFromMainActivity.getExtras();

        dogList = new ArrayList<>();
        dogList = bundleFromMainActivity.getParcelableArrayList("dogList");

        ListView listViewDogList = findViewById(R.id.listViewDogList);
        arrayAdapter = new ArrayAdapter<>(DogListActivity.this, android.R.layout.simple_list_item_1, dogList);
        listViewDogList.setAdapter(arrayAdapter);

        listViewDogList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updatedDogIndex = position;

                Intent intentForFormAddDoActivity = new Intent(DogListActivity.this, FormAddDogActivity.class);
                Bundle bundleForFormAddDoActivity = new Bundle();

                Dog dogToBeUpdated = dogList.get(position);
                bundleForFormAddDoActivity.putParcelable("dogToBeUpdated", dogToBeUpdated);
                intentForFormAddDoActivity.putExtras(bundleForFormAddDoActivity);

                startActivityForResult(intentForFormAddDoActivity, 302);
            }
        });

        listViewDogList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Dog dogToBeRemoved = dogList.get(position);
                String message = dogToBeRemoved.getName() + " was removed";

                dogList.remove(position);
                arrayAdapter.notifyDataSetChanged();

                Toast.makeText(DogListActivity.this, message, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 302 && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            Dog dogUpdated = bundle.getParcelable("newDog");

            dogList.set(updatedDogIndex, dogUpdated);
            arrayAdapter.notifyDataSetChanged();
        }
    }
}