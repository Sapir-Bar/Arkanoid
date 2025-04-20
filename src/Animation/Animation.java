// 211540562 Sapir Bar
package Animation;

import biuoop.DrawSurface;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 15.06.2023
 * This interface representing an animation. once it called, it executes 'do one frame' method
 * until a positive value is received by 'should stop' method.
 */
public interface Animation {
    /**
     * performs a single frame of the animation.
     * @param d draw-surface
     */
    void doOneFrame(DrawSurface d);
    /**
     * gives the animation runner an indication to stop the animation.
     * @return boolean value
     */
    boolean shouldStop();
}
