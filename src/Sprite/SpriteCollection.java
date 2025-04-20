// 211540562 Sapir Bar
package Sprite;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
/**
 * The class holds a list of sprite objects.
 */
public class SpriteCollection {
    private List<Sprite> spriteList = new ArrayList<>();
    /**
     * @ add a sprite object to the collection.
     * @param s Sprite object
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }
    /**
     * @ remove a sprite object from the collection.
     * @param s Sprite object
     */
    public void removeSprite(Sprite s) {
        spriteList.remove(s);
    }

    /**
     * @ call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        // Make a copy of the sprites before iterating over them.
        List<Sprite> sprites = new ArrayList<Sprite>(spriteList);
        for (Sprite s : sprites) {
            s.timePassed();
        }
    }
    /**
     * call drawOn(d) on all sprites.
     * @param d draw surface
     */
    public void drawAllOn(DrawSurface d) {
        // Make a copy of the sprites before iterating over them.
        List<Sprite> sprites = new ArrayList<Sprite>(spriteList);
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
