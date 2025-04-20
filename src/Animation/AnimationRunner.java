// 211540562 Sapir Bar
package Animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 15.06.2023
 * This class is responsible for time tracking and running the animation using the given GUI.
 */
public class AnimationRunner {
    private GUI gui;
    private Sleeper sleeper;
    private int framesPerSecond;

    /**
     * The constructor - create a new instance of the Animation-Runner.
     * @param gui a graphical user interface window.
     * @param sleeper helper class for pausing code execution.
     * @param framesPerSecond set the frame rate per second.
     */

    public AnimationRunner(GUI gui, Sleeper sleeper, int framesPerSecond) {
        this.gui = gui;
        this.sleeper = sleeper;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * Run the game - management the animation loop.
     * @param animation animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}