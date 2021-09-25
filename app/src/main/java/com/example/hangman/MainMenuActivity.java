package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    Button btnPlay;
    Button btnTimeAttack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btnPlay);
        btnTimeAttack = findViewById(R.id.btnTimeAttack);

        //click to go to Game Activity
        btnPlay.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenuActivity.this, GameActivity.class);
            startActivity(intent);
        });

        //navigates to TimeAttack Activity
        btnTimeAttack.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenuActivity.this, TimeAttackActivity.class);
            startActivity(intent);
        });

    }
}