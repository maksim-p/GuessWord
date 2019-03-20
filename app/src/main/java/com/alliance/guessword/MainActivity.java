package com.alliance.guessword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button newGame;
    Button continueGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGame = findViewById(R.id.newGame);
        continueGame = findViewById(R.id.continueGame);

        newGame.setOnClickListener(this);
        continueGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newGame:
                Intent intent = new Intent(this, NewGameActivity.class);
                startActivity(intent);
                break;
            case R.id.continueGame:
                break;
            case R.id.rules:
                break;
        }
    }
}
