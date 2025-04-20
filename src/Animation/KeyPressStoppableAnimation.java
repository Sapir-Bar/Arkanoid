// 211540562 Sapir Bar2
package Animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 15.06.2023
 * This class is implements the decorator pattern. it's wraps an existing animation and add
 * a "waiting-for-key" behavior to it. */
public class KeyPressStoppableAnimation implements Animation {

    private Animation decorated;
    private KeyboardSensor keyboard;
    private String key;
    private boolean stop;
    private boolean isAlreadyPressed;
    /**
     * The constructor - create a new instance of the KeyPressStoppableAnimation.
     * @param keyboardSensor received from the gui.
     * @param key name of keyboard key.
     * @param animation decorated object.
   */
    public KeyPressStoppableAnimation(KeyboardSensor keyboardSensor, String key, Animation animation) {
        this.decorated = animation;
        this.keyboard = keyboardSensor;
        this.key = key;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
   public boolean shouldStop() {
        return this.stop;
    }

   @Override
   public void doOneFrame(DrawSurface d) {
       this.decorated.doOneFrame(d);
       if (this.keyboard.isPressed(this.key) && !isAlreadyPressed) {
           this.stop = true;
       } else if (!this.keyboard.isPressed(this.key)) {
           this.isAlreadyPressed = false;
       }
   }
}
