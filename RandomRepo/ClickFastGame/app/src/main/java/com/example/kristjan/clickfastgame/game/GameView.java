package com.example.kristjan.clickfastgame.game;

import android.content.Context;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kristjan.clickfastgame.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Kristjan on 10/06/2016.
 */
public class GameView extends RelativeLayout {

    Vibrator vibrator;

    public interface GameViewListener {
        void onGameEnded(int score);
    }

    private GameViewListener listener;

    private int correctColour = 0;

    private int score = 0;

    TextView scoreTextView;

    TextView timerTextView;

    int seconds = 10;

    public void setListener(GameViewListener listener) {
        this.listener = listener;
    }

    public GameView(Context context) {
        super(context);
        initialize(context, null, 0);

    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs, 0);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr);
    }

    List<View> panes = new ArrayList<>();

    private void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.view_game, this);

        timerTextView = (TextView) findViewById(R.id.timer_text_view);

        scoreTextView = (TextView) findViewById(R.id.score_text_view);

        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        panes.add(findViewById(R.id.pane0));
        panes.add(findViewById(R.id.pane1));
        panes.add(findViewById(R.id.pane2));
        panes.add(findViewById(R.id.pane3));
        panes.add(findViewById(R.id.pane4));
        panes.add(findViewById(R.id.pane5));

        setColours();

        for (int i = 0; i <panes.size(); i++) {
            final int j = i;
            panes.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    giveAnswer(j);
                }
            });
        }

        startTimer(seconds);
    }

    Timer timer;

    private void startTimer(int seconds) {

        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (getHandler() != null) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            setTimerText();
                        }
                    });
                }
            }
        }, 1000, 1000);


    }

    private void setTimerText() {
        timerTextView.setText(seconds + "s");
        if (listener != null && seconds <= 0) {
            listener.onGameEnded(score);
            timer.cancel();
        }
        seconds--;
    }

    private void giveAnswer(int i) {

        if (i == correctColour) {
            score++;
            seconds++;
        }
        else {
            vibrator.vibrate(150);
            seconds -= 5;
        }

        scoreTextView.setText("Score: " + score);

        setColours();
    }

    private void setColours() {
        ColourChanger colourChanger = new ColourChanger();
        colourChanger.generateNewColours(getContext());

        for (int i = 0; i < panes.size(); i++) {
            ColourChanger.Colour colour = colourChanger.getColour(i);
            panes.get(i).setBackgroundColor(colour.getColourResource(getContext()));
        }

        Random random = new Random();
        correctColour = random.nextInt(6);
        ColourChanger.Colour colour = colourChanger.getColour(correctColour);

        TextView colourText = (TextView) findViewById(R.id.colour_text);
        colourText.setText(colour.name);
        colourText.setTextColor(colourChanger.getColour(random.nextInt(6)).getColourResource(getContext()));
    }





}
