// 211540562 Sapir Bar
package Listener;

import Sprite.Ball;
import Sprite.Block;
import Levels.GameLevel;

/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 28.05.2023
 * the class is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain. */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor - creating a new instance of Block-Remover given a game and a counter.
     * @param gameLevel game
     * @param remainingBlocks counter
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBlocks.decrease(1);
        beingHit.removeFromGame(gameLevel);
        beingHit.removeHitListener(this);
    }
}