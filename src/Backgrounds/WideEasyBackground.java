// 211540562 Sapir Bar
package Backgrounds;

import Levels.GameLevel;
import Sprite.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 15.06.2023
 * This class is in charge of creating the background for the 2nd level of the game. */
public class WideEasyBackground implements Sprite {
    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(new Color(211, 211, 211));
        d.fillRectangle(0, 0, 800, 20);

        d.setColor(new Color(255, 165, 0));
        d.fillRectangle(0, 50, 800, 62);

        d.setColor(new Color(255, 140, 0));
        d.fillRectangle(0, 112, 800, 62);

        d.setColor(new Color(255, 100, 0));
        d.fillRectangle(0, 174, 800, 63);

        d.setColor(new Color(204, 51, 0));
        d.fillRectangle(0, 237, 800, 63);

        d.setColor(new Color(255, 190, 0));
        d.fillCircle(400, 300, 110);
        d.setColor(new Color(255, 210, 0));
        d.fillCircle(400, 300, 90);
        d.setColor(new Color(255, 230, 128));
        d.fillCircle(400, 300, 70);

        d.setColor(Color.BLUE);
        d.fillRectangle(0, 300, 800, 300);
    }
    @Override
    public void timePassed() {
    }
}
