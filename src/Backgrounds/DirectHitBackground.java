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
 * This class is in charge of creating the background for the 1st level of the game. */
public class DirectHitBackground implements Sprite {

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(211, 211, 211));
        d.fillRectangle(0, 0, 800, 20);

        d.setColor(Color.BLUE);
        d.drawCircle(400, 200, 120);
        d.drawCircle(400, 200, 90);
        d.drawCircle(400, 200, 60);

        d.drawLine(430, 200, 540, 200);
        d.drawLine(400, 230, 400, 340);
        d.drawLine(370, 200, 260, 200);
        d.drawLine(400, 170, 400, 60);
    }

    @Override
    public void timePassed() {
    }
}

