package com.example.kristjan.clickfastgame.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.kristjan.clickfastgame.R;
import com.example.kristjan.clickfastgame.main.MainActivity;

/**
 * Created by Kristjan on 10/06/2016.
 */
public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game);

        GameView gameView = (GameView) findViewById(R.id.game_view);

        gameView.setListener(new GameView.GameViewListener() {
            @Override
            public void onGameEnded(int score) {
                openMainActivity(score);
            }
        });
    }

    private void openMainActivity(int score) {
        Bundle bundle = new Bundle();
        bundle.putInt(MainActivity.SCORE , score);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();



    }
}
