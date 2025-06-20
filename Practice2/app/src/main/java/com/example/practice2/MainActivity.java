package com.example.practice2;

import static android.app.ProgressDialog.show;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //всплывающие подсказки
    private Button button2;

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
        TextView text = findViewById(R.id.text);
        button2 = findViewById(R.id.button2);

        ImageButton button_image_car = findViewById(R.id.button_image_car);
        ImageButton button_image_police = findViewById(R.id.button_image_police);

        MediaPlayer carSound = MediaPlayer.create(this, R.raw.car);
        MediaPlayer policeSound = MediaPlayer.create(this, R.raw.police);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(text.getText().toString(), button2);
                ShowInfoAllert("Вы хотите закрыть приложения?");
            }
        });

        button_image_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPlayButton(carSound, policeSound);
            }
        });

        button_image_police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPlayButton(policeSound, carSound);
            }
        });
    }

    private void soundPlayButton(MediaPlayer sound1, MediaPlayer sound2) {
        if(sound1.isPlaying()){ //проверка играет ли мелодия
            sound1.pause(); //пауза
            sound1.seekTo(0); //установка на начало мелодии(из-за паузы, что бы не начиналось с того места где остановаилась)
            sound1.setLooping(false); //зацикленность false
        }
        if (sound2.isPlaying()){
            sound2.pause();
            sound2.seekTo(0);
            sound2.setLooping(false);
        }
        sound1.start();
        sound1.setLooping(true);

    }

    public void btnClick(View v){
        showInfo(((Button)v).getText().toString(), ((Button)v));
    }

    private void ShowInfoAllert(String text){
        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Большая подсказка")
                .setMessage(text)
                .setCancelable(false)
                .setPositiveButton("Конечно", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void showInfo(String text, Button btn){
        btn.setText("Уже нажали");
        btn.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();

    }
    //метод для перехода на вторую страницу
    public void startNewActivity(View v){
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    public void trasitionThirdActivity(View v){
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }


}