// 211540562 Sapir Bar
package Sprite;
import Levels.GameLevel;
import biuoop.DrawSurface;
/**
 * @ The interface represents a sprite object that can be drawn to the screen.
 */
public interface Sprite {
    /**
     * @ Draw the sprite to the screen
     * @param d draw surface
     */
    void drawOn(DrawSurface d);
    /**
     * @ notify the sprite that time has passed
     */
    void timePassed();
    void addToGame(GameLevel gameLevel);
}
