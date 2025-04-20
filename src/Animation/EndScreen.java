// 211540562 Sapir Bar
package Animation;

import biuoop.DrawSurface;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 15.06.2023
 * This class is in charge of displaying the end screen of the game and providing the user with information about
 * whether they win or lose, as well as the final score. */
public class EndScreen implements Animation {
    private boolean stop;
    private int score;
    private boolean victory;

    /**
     * The constructor - create a new instance of the End-Animation.
     * @param score final score
     * @param victory loss/win
     */
    public EndScreen(int score, boolean victory) {
        this.score = score;
        this.victory = victory;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (victory) {
            d.drawText(200, d.getHeight() / 2, "You win! your score is " + score, 32);
        } else {
            d.drawText(200, d.getHeight() / 2, "Game Over. Your score is " + score, 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
