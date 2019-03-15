package com.alliance.guessword;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class StartGameActivity extends AppCompatActivity {

    TextView teamOne;
    TextView teamTwo;
    TextView teamOneCount;
    TextView teamTwoCount;
    TextView teamStart;
    Button startButton;
    int currentTeam;
    final static int maxScore = 25;
    ArrayList<Team> teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        currentTeam = 0;
        teams = new ArrayList<>();

        Intent intent = getIntent();

        teamOne = findViewById(R.id.teamOne);
        teamTwo = findViewById(R.id.teamTwo);
        teamOneCount = findViewById(R.id.teamOneCount);
        teamTwoCount = findViewById(R.id.teamTwoCount);
        teamStart = findViewById(R.id.teamStartText);
        startButton = findViewById(R.id.startButton);


        if (!intent.getStringExtra("teamOne").equals("")) {
            teamOne.setText(intent.getStringExtra("teamOne"));
        }
        else {
            teamOne.setText("TeamOne");
        }

        if (!intent.getStringExtra("teamTwo").equals("")) {
            teamTwo.setText(intent.getStringExtra("teamTwo"));
        }
        else {
            teamTwo.setText("TeamTwo");
        }

        teams.add(new Team(teamOne.getText().toString(), 0));
        teams.add(new Team(teamTwo.getText().toString(), 0));

        teamOneCount.setText("" + teams.get(0).getScore());
        teamTwoCount.setText("" + teams.get(1).getScore());

        teamStart.setText("Начинает команда '" + teams.get(currentTeam).getName() + "'");
    }

    public void startRound(View view) {
        Intent intent = new Intent(this, GameRoundActivity.class);
        intent.putExtra("teamName", teams.get(currentTeam).getName());
        intent.putExtra("count", teams.get(currentTeam).getScore());
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            int count = data.getIntExtra("count", 0);
            teams.get(currentTeam).setScore(count);
            teamOneCount.setText("" + teams.get(0).getScore());
            teamTwoCount.setText("" + teams.get(1).getScore());
        }

        currentTeam = (currentTeam + 1) % teams.size();
        teamStart.setText("Начинает ход команда '" + teams.get(currentTeam).getName() + "'");
        checkResult();
    }

    public void checkResult() {
        int score1 = teams.get(0).getScore();
        int score2 = teams.get(1).getScore();
        if (currentTeam == 0 && (score1 > 25 || score2 > 25)) {
            if (score1 > score2) {
                teamStart.setText("Победила команда '" + teams.get(0).getName() + "'");
                startButton.setVisibility(View.GONE);
            }
            else if (score2 > score1) {
                teamStart.setText("Победила команда '" + teams.get(1).getName() + "'");
                startButton.setVisibility(View.GONE);
            }
        }
    }


}
