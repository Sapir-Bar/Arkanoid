package Sprite;

import Levels.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

public class LevelName implements Sprite {
    private String levelName;

    public LevelName(String levelName) {
        this.levelName = levelName;
    }
    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(150, 17, "Level Name: " + levelName, 20);
    }

    @Override
    public void timePassed() {
    }
}
