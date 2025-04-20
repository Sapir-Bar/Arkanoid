// 211540562 Sapir Bar
package Listener;

import Sprite.Ball;
import Sprite.Block;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 28.05.2023
 * the interface performs an action when the beingHit object is hit. */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit Block
     * @param hitter Ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}
