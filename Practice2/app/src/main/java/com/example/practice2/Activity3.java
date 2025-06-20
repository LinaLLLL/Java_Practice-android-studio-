package com.example.practice2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

public class Activity3 extends AppCompatActivity {

    private Fragment1 fragment1 = new Fragment1();// создание переменной типа Fragment1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn_fragment1 = findViewById(R.id.button_fragment1);
        Button btn_fragment2 = findViewById(R.id.button_fragment2);

        setNewFragmant(fragment1);

        btn_fragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewFragmant(fragment1);
            }
        });

        btn_fragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment2 fragment2 = new Fragment2();
                setNewFragmant(fragment2);
            }
        });

    }
    private void setNewFragmant(androidx.fragment.app.Fragment fragment) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction(); // переменная типа FragmentTransaction для передачи фрагмента
        ft.replace(R.id.frame_layout, fragment);
        ft.commit(); //сохранение
    }
}