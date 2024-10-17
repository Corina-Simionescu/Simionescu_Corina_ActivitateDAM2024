package com.example.seminar_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

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
    }

    public void metoda1(View view) {
        Intent it = new Intent(this, MainActivity2.class);
        it.putExtra("text", "text trimis din MainActivity1");
        it.putExtra("nr1", 1);
        it.putExtra("nr2", 2);
        startActivityForResult(it, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Student student = (Student) data.getSerializableExtra("student");
            if (student != null) {
                String informatiiStudent = "Nume: " + student.getNume()
                        + "; Grupa: " + student.getGrupa()
                        + "; Facultate: " + student.getFacultate()
                        + "; Este integralist: " + student.isEsteIntegralist();

                Toast.makeText(this, informatiiStudent, Toast.LENGTH_LONG).show();
            }
        }
    }
}




















