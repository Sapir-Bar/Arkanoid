// 211540562 Sapir Bar
package Animation;

import biuoop.DrawSurface;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 15.06.2023
 * This class is in charge of displaying the pause screen of the game. */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * The constructor - create a new instance of the Pause-Screen.
     */
    public PauseScreen() {
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(150, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    @Override
    public boolean shouldStop() {
    return this.stop;
    }
}