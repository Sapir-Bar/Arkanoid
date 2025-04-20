// 211540562 Sapir Bar
package Levels;

import Sprite.Block;
import Sprite.Velocity;
import Sprite.Sprite;
import Sprite.Ball;

import java.util.List;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 15.06.2023
 * This interface supply necessary information about a game level.
 */
public interface LevelInformation {
    /**
     * Amount of balls included at this game level.
     * @return Integer value.
     */
    int numberOfBalls();

    /**
     * The balls included at this game level.
     * Each ball contains its point of center, size, speed and color.
     * @return list of balls.
     */
    List<Ball> balls();

    /**
     * The initial velocity of each ball.
     * @return list of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * The paddle speed.
     * @return Integer value.
     */
    int paddleSpeed();

    /**
     * The paddle width.
     * @return Integer value.
     */
    int paddleWidth();

    /**
     * The level name, which will be displayed at the top of the screen.
     * @return string.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return sprite object.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     * @return list of blocks.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * @return Integer value.
     */
    int numberOfBlocksToRemove();
}
