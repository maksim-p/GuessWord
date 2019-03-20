package com.alliance.guessword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NewGameActivity extends AppCompatActivity implements View.OnClickListener {

    TextView teamOne;
    TextView teamTwo;
    ImageView imageView4;
    ImageView imageView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        teamOne = findViewById(R.id.teamOneStart);
        teamTwo = findViewById(R.id.teamTwoStart);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);

        imageView4.setOnClickListener(this);
        imageView5.setOnClickListener(this);
    }

    public void onClick(View v){
        EditNameDialog dialogFragment = new EditNameDialog();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.imageView4:
                bundle.putInt("ID",R.id.teamOneStart);
                dialogFragment.setArguments(bundle);
                dialogFragment.show(getSupportFragmentManager(), "edit_name_dialog");
                break;
            case R.id.imageView5:
                bundle.putInt("ID",R.id.teamTwoStart);
                dialogFragment.setArguments(bundle);
                dialogFragment.show(getSupportFragmentManager(), "edit_name_dialog");
                break;
        }
    }
    public void startGame(View view) {
        Intent intent = new Intent(this, StartGameActivity.class);

        intent.putExtra("teamOne", teamOne.getText().toString());
        intent.putExtra("teamTwo", teamTwo.getText().toString());
        startActivity(intent);
    }
}
