package com.alliance.guessword;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class StartGameActivity extends AppCompatActivity {

    TextView teamOne;
    TextView teamTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        Intent intent = getIntent();

        teamOne = findViewById(R.id.teamOne);
        teamTwo = findViewById(R.id.teamTwo);

        if (!intent.getStringExtra("teamOne").equals(""))
            teamOne.setText(intent.getStringExtra("teamOne"));
        else
            teamOne.setText("TeamOne");
        if (!intent.getStringExtra("teamTwo").equals(""))
            teamTwo.setText(intent.getStringExtra("teamTwo"));
        else
            teamTwo.setText("TeamTwo");
    }

    public void startRound(View view) {
        Intent intent = new Intent(this, GameRoundActivity.class);
        startActivity(intent);
    }
}