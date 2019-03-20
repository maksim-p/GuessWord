package com.alliance.guessword;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class GameRoundActivity extends AppCompatActivity implements View.OnClickListener {

    TextView timer;
    ProgressBar timerBar;
    TextView easyWord, hardWord, celebrity, proverb;
    TextView teamName, count;
    ImageView plus1, plus2, plus3, plus4;
    ImageView minus1, minus2, minus3, minus4;

    DBHelper dbHelper;
    String word = "";
    int rnd;
    int score;
    int limiter1, limiter2, limiter3, limiter4;

    CountDownTimer countDownTimer;

    private static final long START_TIME_IN_MILLIS = 60000;
    private long currentTimeInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_round);

        Intent intent = getIntent();
        initializeViews();

        limiter1 = 0;
        limiter2 = 0;
        limiter3 = 0;
        limiter4 = 0;

        teamName.setText(intent.getStringExtra("teamName"));
        score = intent.getIntExtra("count", 0);
        count.setText("" + score);

        dbHelper = new DBHelper(this);

        if(rand(1,10) > 5){
            easyWord.setText(getTheWord("adjectives", "adjective"));
        }else {
            easyWord.setText(getTheWord("nouns", "noun"));
        }
        if(rand(1,10) > 5){
            hardWord.setText(getTheWord("verbs", "verb"));
        }else {
            hardWord.setText(getTheWord("adverbs", "adverb"));
        }

        celebrity.setText(getTheWord("celebrities", "celebrity"));
        proverb.setText(getTheWord("proverbs", "proverb"));

        startTimer();

        ObjectAnimator anim = ObjectAnimator.ofInt(timerBar, "progress", 60, 0);
        anim.setDuration(59000);
        anim.setInterpolator(new LinearInterpolator());
        anim.start();
    }

    private void initializeViews() {
        teamName = findViewById(R.id.teamNameText);
        count = findViewById(R.id.countText);
        timer = findViewById(R.id.timerView);
        timerBar = findViewById(R.id.timerBar);
        easyWord = findViewById(R.id.easyWordText);
        hardWord = findViewById(R.id.hardWordText);
        celebrity = findViewById(R.id.celebrityText);
        proverb = findViewById(R.id.proverbText);

        minus1 = findViewById(R.id.minus1);
        minus1.setOnClickListener(this);
        minus2 = findViewById(R.id.minus2);
        minus2.setOnClickListener(this);
        minus3 = findViewById(R.id.minus3);
        minus3.setOnClickListener(this);
        minus4 = findViewById(R.id.minus4);
        minus4.setOnClickListener(this);

        plus1 = findViewById(R.id.plus1);
        plus1.setOnClickListener(this);
        plus2 = findViewById(R.id.plus2);
        plus2.setOnClickListener(this);
        plus3 = findViewById(R.id.plus3);
        plus3.setOnClickListener(this);
        plus4 = findViewById(R.id.plus4);
        plus4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.minus1:
                score = score - 1;
                count.setText("" + score);
                if(rand(1,10) > 5){
                    easyWord.setText(getTheWord("adjectives", "adjective"));
                }else {
                    easyWord.setText(getTheWord("nouns", "noun"));
                }
                limiter1++;
                if (limiter1 > 2) hideWord(minus1, plus1, easyWord);
                break;
            case R.id.plus1:
                score = score + 1;
                count.setText("" + score);
                if(rand(1,10) > 5){
                    easyWord.setText(getTheWord("adjectives", "adjective"));
                }else {
                    easyWord.setText(getTheWord("nouns", "noun"));
                }
                limiter1++;
                if (limiter1 > 2) hideWord(minus1, plus1, easyWord);
                break;
            case R.id.minus2:
                score = score - 2;
                count.setText("" + score);
                if(rand(1,10) > 5){
                    hardWord.setText(getTheWord("verbs", "verb"));
                }else {
                    hardWord.setText(getTheWord("adverbs", "adverb"));
                }
                limiter2++;
                if (limiter2 > 2) hideWord(minus2, plus2, hardWord);
                break;
            case R.id.plus2:
                score = score + 2;
                count.setText("" + score);
                if(rand(1,10) > 5){
                    hardWord.setText(getTheWord("verbs", "verb"));
                }else {
                    hardWord.setText(getTheWord("adverbs", "adverb"));
                }
                limiter2++;
                if (limiter2 > 2) hideWord(minus2, plus2, hardWord);
                break;
            case R.id.minus3:
                score = score - 3;
                count.setText("" + score);
                celebrity.setText(getTheWord("celebrities", "celebrity"));
                limiter3++;
                if (limiter3 > 2) hideWord(minus3, plus3, celebrity);
                break;
            case R.id.plus3:
                score = score + 3;
                count.setText("" + score);
                celebrity.setText(getTheWord("celebrities", "celebrity"));
                limiter3++;
                if (limiter3 > 2) hideWord(minus3, plus3, celebrity);
                break;
            case R.id.minus4:
                score = score - 4;
                count.setText("" + score);
                proverb.setText(getTheWord("proverbs", "proverb"));
                limiter4++;
                if (limiter4 > 2) hideWord(minus4, plus4, proverb);
                break;
            case R.id.plus4:
                score = score + 4;
                count.setText("" + score);
                proverb.setText(getTheWord("proverbs", "proverb"));
                limiter4++;
                if (limiter4 > 2) hideWord(minus4, plus4, proverb);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        startTimer();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(currentTimeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int second = (int) (millisUntilFinished / 1000);
                timer.setText(second + "");
                timerBar.setProgress(1 / 1000);
                if (easyWord.getVisibility() == View.INVISIBLE &&
                        hardWord.getVisibility() == View.INVISIBLE &&
                        celebrity.getVisibility() == View.INVISIBLE && proverb.getVisibility() == View.INVISIBLE)
                    onFinish();
                currentTimeInMillis = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                timer.setText("0");
                Intent intent = new Intent();
                intent.putExtra("count", score);
                setResult(RESULT_OK, intent);
                finish();
            }
        }.start();
    }



    private String getTheWord(String table, String column) {
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

    private int rand(int min, int max){
        Random random = new Random();
        rnd = random.nextInt(max - min + 1) + min ;
        return rnd;
    }

    private void hideWord(View minus, View plus, View text) {
        minus.setVisibility(View.INVISIBLE);
        plus.setVisibility(View.INVISIBLE);
        text.setVisibility(View.INVISIBLE);
    }
}
