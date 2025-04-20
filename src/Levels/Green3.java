// 211540562 Sapir Bar
package Levels;

import Backgrounds.Green3Background;
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
 * This class represents an information related to the 3rd level of the Arkanoid game.
 */
public class Green3 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return balls().size();
    }
    @Override
    public List<Ball> balls() {
        List<Ball> balls = new ArrayList<>();
        balls.add(new Ball(400, 540, 7, Color.WHITE));
        balls.add(new Ball(400, 540, 7, Color.WHITE));
        return balls;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(-10, 4));
        velocities.add(Velocity.fromAngleAndSpeed(10, 4));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 120;
    }

    @Override
    public String levelName() {
        String levelName = "Green 3";
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return new Green3Background();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int yCoordinate = 100;
        int xCoordinate = 720;
        for (int i = 0; i < 10; i++) {
            blocks.add(new Block(xCoordinate, yCoordinate, 50, 25, Color.GRAY));
            xCoordinate -= 50;
        }
        yCoordinate = 125;
        xCoordinate = 720;

        for (int i = 0; i < 9; i++) {
            blocks.add(new Block(xCoordinate, yCoordinate, 50, 25, Color.RED));
            xCoordinate -= 50;
        }
        yCoordinate = 150;
        xCoordinate = 720;
        for (int i = 0; i < 8; i++) {
            blocks.add(new Block(xCoordinate, yCoordinate, 50, 25, Color.YELLOW));
            xCoordinate -= 50;
        }
        yCoordinate = 175;
        xCoordinate = 720;
        for (int i = 0; i < 7; i++) {
            blocks.add(new Block(xCoordinate, yCoordinate, 50, 25, Color.BLUE));
            xCoordinate -= 50;
        }
        yCoordinate = 200;
        xCoordinate = 720;
        for (int i = 0; i < 6; i++) {
            blocks.add(new Block(xCoordinate, yCoordinate, 50, 25, Color.WHITE));
            xCoordinate -= 50;
        }
        return blocks;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
