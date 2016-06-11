package com.example.kristjan.clickfastgame.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kristjan.clickfastgame.R;

/**
 * Created by Kristjan on 10/06/2016.
 */
public class MainView extends RelativeLayout {

    public interface MainViewListener {
        void onStartButtonClicked();
    }

    private TextView scoreTextView;

    private MainViewListener listener;

    public void setScore(int score) {
        this.score = score;

        scoreTextView.setText("Score: " + score);
    }

    private int score = 0;

    public MainView(Context context) {
        super(context);
        initialize(context, null, 0);
    }

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs, 0);
    }

    public MainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr);
    }

    private void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.view_main, this);

        scoreTextView = (TextView) findViewById(R.id.score_text_view);

        final Button startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.animate().translationY(getHeight()).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if (listener != null) listener.onStartButtonClicked();
                    }
                });
            }
        });
    }



    public void setListener(MainViewListener listener) {
        this.listener = listener;
    }
}
