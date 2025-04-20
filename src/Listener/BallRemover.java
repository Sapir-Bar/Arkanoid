// 211540562 Sapir Bar
package Listener;

import Sprite.Ball;
import Sprite.Block;
import Levels.GameLevel;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 28.05.2023
 * the class is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain. */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constructor - creating a new instance of Ball-Remover given a Game and a Counter.
     * @param gameLevel game
     * @param  remainingBalls counter
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decrease(1);
        hitter.removeFromGame(gameLevel);
    }
}
