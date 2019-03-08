package com.alliance.guessword;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GameRoundActivity extends AppCompatActivity {

    TextView timer;
    ProgressBar timerBar;
    TextView easyWordText;

    DBHelper dbHelper;

    public void getTheWord(String query, String level){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToNext()) {
            int wordIndex = cursor.getColumnIndex(level);
            easyWordText.setText(cursor.getString(wordIndex));
            Log.d("mLog", "word: " + cursor.getString(wordIndex));
        } else
            easyWordText.setText("no nouns");
            Log.d("mLog", "no nouns");
        cursor.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_round);

        timer = findViewById(R.id.timerView);
        timerBar = findViewById(R.id.timerBar);

        easyWordText = findViewById(R.id.easyWordText);

        dbHelper = new DBHelper(this);

        getTheWord("SELECT noun FROM nouns ORDER BY RANDOM() LIMIT 1", "noun");
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
