package com.example.kristjan.clickfastgame.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.kristjan.clickfastgame.game.GameActivity;
import com.example.kristjan.clickfastgame.R;

public class MainActivity extends Activity {

    public static final String SCORE = "score";

    private int score = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (getIntent() != null && getIntent().getExtras() != null) {
            score = getIntent().getExtras().getInt(SCORE, -1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        setContentView(R.layout.activity_main);

        MainView mainView = (MainView) findViewById(R.id.main_view);
        mainView.setListener(new MainView.MainViewListener() {
            @Override
            public void onStartButtonClicked() {
                openGameActivity();
            }
        });

        if (score >= 0) {
            mainView.setScore(score);
        }
    }

    private void openGameActivity() {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
        finish();
    }
}
