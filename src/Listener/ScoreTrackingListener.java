// 211540562 Sapir Bar
package Listener;

import Sprite.Ball;
import Sprite.Block;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 28.05.2023
 * the class is in charge of listening to hit events and updating the score accordingly. */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * Constructor - creating a new instance of Score Tracking Listener given a Counter.
     * @param scoreCounter counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(5);
       beingHit.removeHitListener(this);
    }
}