// 211540562 Sapir Bar
package Animation;

import Sprite.SpriteCollection;
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 15.06.2023
 * This class will display the given gameScreen,for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before it is replaced with the next one. */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private int framesPerSeconds;
    private int numOfFrames;
    private int framesCounter;
    private int currentDigit;
    private SpriteCollection gameScreen;
    private boolean stop;

    /**
     * The constructor - create a new instance of the Countdown-Animation.
     * @param numOfSeconds of the animation.
     * @param countFrom natural number.
     * @param framesPerSeconds rate of animation.
     * @param gameScreen collection of sprites.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, int framesPerSeconds, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.framesPerSeconds = framesPerSeconds;
        this.numOfFrames = (int) numOfSeconds * framesPerSeconds;
        this.framesCounter = numOfFrames;
        this.currentDigit = countFrom;
        this.stop = false;
        this.gameScreen = gameScreen;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(Color.WHITE);
        d.drawText(400, 300, Integer.toString(currentDigit), 60);
        framesCounter--;
        if (framesCounter % (numOfFrames / countFrom) == 0) {
            currentDigit--;
        }
        if (framesCounter == 0) {
            stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}