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
    TextView easyWordText, hardWordText, celebrityText, proverbText;

    DBHelper dbHelper;

    String word = "";

    public String getTheWord(String table, String column){
        String query = "SELECT " + column + " FROM " + table + " ORDER BY RANDOM() LIMIT 1";
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToNext()) {
            int wordIndex = cursor.getColumnIndex(column);
            word = cursor.getString(wordIndex);
        } else
            word = "no words";
        cursor.close();
        return word;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_round);

        timer = findViewById(R.id.timerView);
        timerBar = findViewById(R.id.timerBar);

        easyWordText = findViewById(R.id.easyWordText);
        hardWordText = findViewById(R.id.hardWordText);
        celebrityText = findViewById(R.id.celebrityText);
        proverbText = findViewById(R.id.proverbText);

        dbHelper = new DBHelper(this);


        easyWordText.setText(getTheWord("easyWords", "easyWord"));
        hardWordText.setText(getTheWord("easyWords", "easyWord"));
        celebrityText.setText(getTheWord("easyWords", "easyWord"));
        proverbText.setText(getTheWord("easyWords", "easyWord"));

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
