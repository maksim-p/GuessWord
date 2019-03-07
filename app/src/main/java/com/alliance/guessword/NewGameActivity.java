package com.alliance.guessword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class NewGameActivity extends AppCompatActivity {

    EditText teamOne;
    EditText teamTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        teamOne = findViewById(R.id.editTeamOne);
        teamTwo = findViewById(R.id.editTeamTwo);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, StartGameActivity.class);

        intent.putExtra("teamOne", teamOne.getText().toString());
        intent.putExtra("teamTwo", teamTwo.getText().toString());
        startActivity(intent);
    }
}
