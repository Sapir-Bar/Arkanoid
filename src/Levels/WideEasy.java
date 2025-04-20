// 211540562 Sapir Bar
package Levels;

import Backgrounds.WideEasyBackground;
import Sprite.Ball;
import Sprite.Sprite;
import Sprite.Velocity;
import Sprite.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 15.06.2023
 * This class represents an information related to the 2nd level of the Arkanoid game.
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return balls().size();
    }

    @Override
    public List<Ball> balls() {
        List<Ball> balls = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            balls.add(new Ball(400, 540, 7, Color.WHITE));
        }
        return balls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        double angle = 20;
        for (int i = 0; i < balls().size() / 2; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(angle, 4));
            angle += 10;
        }
        angle = -20;
        for (int j = balls().size() / 2; j < balls().size(); j++) {
            velocities.add(Velocity.fromAngleAndSpeed(angle, 4));
            angle -= 10;
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        String levelName = "Wide Easy";
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return new WideEasyBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(30, 300, 49, 20, Color.RED));
        blocks.add(new Block(79, 300, 49, 20, Color.RED));
        blocks.add(new Block(128, 300, 50, 20, Color.ORANGE));
        blocks.add(new Block(178, 300, 50, 20, Color.ORANGE));
        blocks.add(new Block(228, 300, 49, 20, Color.YELLOW));
        blocks.add(new Block(277, 300, 49, 20, Color.YELLOW));
        blocks.add(new Block(326, 300, 50, 20, Color.GREEN));
        blocks.add(new Block(376, 300, 50, 20, Color.GREEN));
        blocks.add(new Block(426, 300, 49, 20, Color.GREEN));
        blocks.add(new Block(475, 300, 49, 20, Color.BLUE));
        blocks.add(new Block(524, 300, 50, 20, Color.BLUE));
        blocks.add(new Block(574, 300, 50, 20, Color.PINK));
        blocks.add(new Block(624, 300, 49, 20, Color.PINK));
        blocks.add(new Block(673, 300, 49, 20, Color.CYAN));
        blocks.add(new Block(722, 300, 48, 20, Color.CYAN));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
