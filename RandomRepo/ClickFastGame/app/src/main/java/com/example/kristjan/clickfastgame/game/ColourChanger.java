package com.example.kristjan.clickfastgame.game;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.example.kristjan.clickfastgame.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Kristjan on 11/06/2016.
 */
public class ColourChanger {

    public enum Colour {
        RED("Red"),
        GREEN("Green"),
        BLUE("Blue"),
        YELLOW("Yellow"),
        PURPLE("Purple"),
        ORANGE("Orange");

        String name;

        Colour(String name) {
            this.name = name;
        }

        public static Colour getColour(int i) {
            switch (i) {
                case 0:
                    return Colour.RED;
                case 1:
                    return Colour.GREEN;
                case 2:
                    return Colour.BLUE;
                case 3:
                    return Colour.YELLOW;
                case 4:
                    return Colour.PURPLE;
                case 5:
                    return Colour.ORANGE;
                default:
                    return Colour.RED;
            }
        }

        public int getColourResource(Context context) {
            switch (this) {
                case RED:
                    return ContextCompat.getColor(context, R.color.red);
                case GREEN:
                    return ContextCompat.getColor(context, R.color.green);
                case BLUE:
                    return ContextCompat.getColor(context, R.color.blue);
                case YELLOW:
                    return ContextCompat.getColor(context, R.color.yellow);
                case PURPLE:
                    return ContextCompat.getColor(context, R.color.purple);
                case ORANGE:
                    return ContextCompat.getColor(context, R.color.orange);
                default:
                    return ContextCompat.getColor(context, R.color.red);
            }
        }
    }

    private List<Colour> colours;

    public ColourChanger() {

    }

    public void generateNewColours(Context context) {
        colours = new ArrayList<>();
        Random randomGenerator = new Random();
        for (int i = 0; i < 6; i++) {
            while(true) {
                int randomInt = randomGenerator.nextInt(6);
                if (!colours.contains(Colour.getColour(randomInt))) {
                    colours.add(Colour.getColour(randomInt));
                    break;
                }
            }
        }
    }

    public Colour getColour(int i) {
        return colours.get(i);
    }

    public Colour getRandomColour() {
        Random randomGenerator = new Random();

        return Colour.getColour(randomGenerator.nextInt(6));
    }

}
