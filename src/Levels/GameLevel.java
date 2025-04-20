// 211540562 Sapir Bar
package Levels;

import Animation.AnimationRunner;
import Animation.Animation;
import Animation.PauseScreen;
import Animation.CountdownAnimation;
import Animation.KeyPressStoppableAnimation;

import CollisionDetection.GameEnvironment;
import CollisionDetection.Collidable;

import Listener.BallRemover;
import Listener.BlockRemover;
import Listener.Counter;

import Listener.ScoreTrackingListener;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

import Sprite.Ball;
import Sprite.Block;
import Sprite.Paddle;
import Sprite.ScoreIndicator;
import Sprite.Sprite;
import Sprite.SpriteCollection;
import Sprite.LevelName;

/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 06.05.2023
 * This class receives a level-information and creates an appropriate animation.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTracking;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter scoreCounter;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboardSensor;
    private LevelInformation levelInfo;

    /**
     * The constructor - creating a new instance of Game-Level.
     * @param levelInfo game layout.
     * @param keyboardSensor received from the gui.
     * @param runner animation runner.
     * @param scoreCounter kept across levels. */

    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor,
                     AnimationRunner runner, Counter scoreCounter) {
        this.levelInfo = levelInfo;
        this.keyboardSensor = keyboardSensor;
        this.runner = runner;
        this.scoreCounter = scoreCounter;
    }

    /**
     * Add the collidable object to the game using the method "addCollidable" defined at the class "gameEnvironment".
     *
     * @param c collidable object
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Remove the collidable object from the game using the method "removeCollidable"
     * defined at the class "gameEnvironment".
     *
     * @param c collidable object
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Add the sprite object to the game using the method "addSprite" defined at the class "Sprite".
     *
     * @param s sprite object
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Remove the sprite object from the game using the method "removeSprite" defined at the class "Sprite".
     *
     * @param s sprite object
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create blocks, balls and paddle and add them to the game.
     */
    public void initialize() {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        blocksCounter = new Counter(levelInfo.numberOfBlocksToRemove());
        blockRemover = new BlockRemover(this, blocksCounter);
        ballsCounter = new Counter(levelInfo.numberOfBalls());
        ballRemover = new BallRemover(this, ballsCounter);
        scoreTracking = new ScoreTrackingListener(scoreCounter);
        this.running = true;
        addBackground();
        addBlocks();
        addPaddle();
        addBalls();
        addScoreIndicator();
        addLevelName();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboardSensor.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space", new PauseScreen()));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (blocksCounter.getValue() == 0) {
            scoreCounter.increase(100);
        }
        if (blocksCounter.getValue() == 0 || ballsCounter.getValue() == 0) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Run the game - start the animation loop.
     */
    public void run() {
        // use our runner to run a countdown animation.
        this.runner.run(new CountdownAnimation(2, 3, 60, this.sprites));
        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
    }

    /**
     * Create blocks and add them to the game.
     */
    public void addBlocks() {
        // (1) Initialize the frame blocks
        new Block(0, 20, 30, 580, Color.GRAY).addToGame(this); //vertical
        new Block(770, 20, 30, 580, Color.GRAY).addToGame(this); //vertical
        new Block(0, 20, 800, 30, Color.GRAY).addToGame(this); //horizontal
        // (2) Initialize the death block with ball remover hitListener.
        Block deathRegion = new Block(0, 600, 800, 30, Color.GRAY);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);
        // (3) Initialize the rest of the blocks
        List<Block> blockList = levelInfo.blocks();
        for (Block b : blockList) {
            b.addToGame(this);
            b.addHitListener(scoreTracking);
            b.addHitListener(blockRemover);
        }
    }

    /**
     * Create balls and add them to the game.
     */
    public void addBalls() {
        List<Ball> balls = levelInfo.balls();

        for (int i = 0; i < levelInfo.numberOfBalls(); i++) {
            balls.get(i).addToGame(this);
            balls.get(i).setGameEnvironment(environment);
            balls.get(i).checkCenter();
            balls.get(i).setVelocity(levelInfo.initialBallVelocities().get(i));
        }
    }

    /**
     * Create a paddle and add it to the game.
     * The width and speed of the paddle determined accordingly to the received level-information.
     */
    public void addPaddle() {
        Paddle paddle = new Paddle(this.keyboardSensor, 30, 30,
                levelInfo.paddleWidth(), 20, levelInfo.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * Create the sprite 'Score-Indicator' and add it to the game.
     */
    public void addScoreIndicator() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreCounter);
        scoreIndicator.addToGame(this);
    }

    /**
     * Create the sprite 'Level-Name' and add it to the game.
     */
    public void addLevelName() {
        LevelName levelName = new LevelName(levelInfo.levelName());
        levelName.addToGame(this);
    }
    /**
     * Create an appropriate background and add it to the game.
     */
    public void addBackground() {
        Sprite background = levelInfo.getBackground();
        background.addToGame(this);
    }

    /**
     * getter of the current amount of balls.
     * @return integer value
     */
    public int getBallsCount() {
        return this.ballsCounter.getValue();
    }

}

