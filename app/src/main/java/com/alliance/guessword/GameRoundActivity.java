package com.alliance.guessword;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GameRoundActivity extends AppCompatActivity {

    TextView timer;
    ProgressBar timerBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_round);

        timer = findViewById(R.id.timerView);
        timerBar = findViewById(R.id.timerBar);

        Timer();
    }

    public void Timer() {
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int second = (int) (millisUntilFinished/1000);
                timer.setText(second + "");
                timerBar.setProgress(1/1000);
            }

            @Override
            public void onFinish() {
                timer.setText("-");
            }
        }.start();
    }
}
