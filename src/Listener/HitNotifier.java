// 211540562 Sapir Bar
package Listener;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 28.05.2023
 * the interface announce all the registered listeners about a hit event. */

public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl Hit-Listener
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl Hit-Listener
     */
    void removeHitListener(HitListener hl);
}
