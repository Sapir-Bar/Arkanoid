// 211540562 Sapir Bar
package Sprite;
import Levels.GameLevel;
import biuoop.DrawSurface;
import Listener.Counter;
import java.awt.Color;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 28.05.2023
 * The class representing a score indicator. it's getting a score-counter on its constructor
 * and representing it during the game. */
public class ScoreIndicator implements Sprite {
    private Counter currentScore;
    /**
     * Constructor - creating a new instance of score indicator given a counter.
     * @param currentScore counter
     */
    public ScoreIndicator(Counter currentScore) {
        this.currentScore = currentScore;
    }
    /**
     * Add the score indicator to the sprite collection.
     * @param gameLevel game0
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(0, 17, "Score: " + currentScore.toString(), 20);
    }
    @Override
    public void timePassed() {
    }
}
