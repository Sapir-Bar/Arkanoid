// 211540562 Sapir Bar
package Levels;

import Animation.AnimationRunner;
import Animation.EndScreen;
import Animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import Listener.Counter;

import java.util.List;

/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 15.06.2023
 * given a list consisting of level-information objects,
 * this class is in charge of creating the different levels, and moving from one level to the next.
 */
public class GameFlow {
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private Counter scoreCounter;
    private boolean victory;
    /**
     * The constructor - creating a new instance of Game-Flow.
     * @param runner animation-runner
     * @param keyboardSensor received from the gui.
     */
    public GameFlow(AnimationRunner runner, KeyboardSensor keyboardSensor) {
        this.runner = runner;
        this.keyboardSensor = keyboardSensor;
        this.scoreCounter = new Counter(0);
        this.victory = true;
    }

    /**
     * This method initializes and runs all the given levels one after the other.
     * At the end of the game it's represents the end screen.
     * @param levels list consisting of level-information objects.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.runner, this.scoreCounter);
            level.initialize();

            while (!level.shouldStop()) { // There is more balls and blocks
                level.run();
            }

            if (level.getBallsCount() == 0) { // There is no balls so the game is over
                victory = false;
                break;
            }
        }
            this.runner.run(new KeyPressStoppableAnimation(
                    this.keyboardSensor, "space", new EndScreen(scoreCounter.getValue(), victory)));
    }
}
