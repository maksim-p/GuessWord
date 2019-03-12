package com.alliance.guessword;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GameRoundActivity extends AppCompatActivity {

    TextView timer;
    ProgressBar timerBar;
    TextView easyWord, hardWord, celebrity, proverb;
    TextView teamName, count;

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

        Intent intent = getIntent();

        teamName = findViewById(R.id.teamNameText);
        count = findViewById(R.id.countText);
        timer = findViewById(R.id.timerView);
        timerBar = findViewById(R.id.timerBar);
        easyWord = findViewById(R.id.easyWordText);
        hardWord = findViewById(R.id.hardWordText);
        celebrity = findViewById(R.id.celebrityText);
        proverb = findViewById(R.id.proverbText);

        teamName.setText(intent.getStringExtra("teamName"));
        count.setText("" + intent.getIntExtra("count", 0));

        dbHelper = new DBHelper(this);

        easyWord.setText(getTheWord("easyWords", "easyWord"));
        hardWord.setText(getTheWord("easyWords", "easyWord"));
        celebrity.setText(getTheWord("easyWords", "easyWord"));
        proverb.setText(getTheWord("easyWords", "easyWord"));

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
                Intent intent = new Intent();
                intent.putExtra("count", 50);
                setResult(RESULT_OK, intent);
                finish();
            }
        }.start();
    }
}
