// 211540562 Sapir Bar
package Levels;

import Backgrounds.DirectHitBackground;
import Sprite.Sprite;
import Sprite.Velocity;
import Sprite.Block;
import Sprite.Ball;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 15.06.2023
 * This class represents an information related to the 1st level of the Arkanoid game.
 * It's representing a single ball flying directly to a block located at the top of the screen.
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return balls().size();
    }

    @Override
    public List<Ball> balls() {
        List<Ball> balls = new ArrayList<>();
        balls.add(new Ball(400, 540, 7, Color.RED));
        return balls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(0, 4));
        return velocities;
    }
    @Override
    public int paddleSpeed() {
        return 5;
    }
    @Override
    public int paddleWidth() {
        return 150;
    }
    @Override
    public String levelName() {
        String levelName = "Direct Hit";
        return levelName;
    }
   @Override
    public Sprite getBackground() {
        return new DirectHitBackground();
    }

   @Override
    public List<Block> blocks() {
       List<Block> blocks = new ArrayList<>();
       blocks.add(new Block(385, 185, 30, 30, Color.RED));
       return blocks;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

}
