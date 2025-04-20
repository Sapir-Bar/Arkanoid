// 211540562 Sapir Bar
package Backgrounds;

import Levels.GameLevel;
import biuoop.DrawSurface;
import Sprite.Sprite;

import java.awt.Color;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 15.06.2023
 * This class is in charge of creating the background for the 3rd level of the game. */
public class Green3Background implements Sprite {

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(0, 100, 0));
        d.fillRectangle(0, 0, 800, 600);

        d.setColor(new Color(211, 211, 211));
        d.fillRectangle(0, 0, 800, 20);
    }

    @Override
    public void timePassed() {
    }
}
