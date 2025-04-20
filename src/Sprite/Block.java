//211540562 Sapir Bar
package Sprite;
import CollisionDetection.Collidable;
import Geometry.Point;
import Geometry.Rectangle;
import Levels.GameLevel;
import Listener.HitListener;
import Listener.HitNotifier;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 06.05.2023
 * The class describes a block.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private final double threshold = 0.0001;
    private Rectangle rect;
    private Color color;
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Constructor - create a new instance of block given a rectangle and a color.
     * @param rect rectangle
     * @param color color
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
    }
    /**
     * Constructor - create a new instance of block given (x,y) coordinates of the upper-left point of the rectangle,
     * width, height and a color.
     * @param x coordinate of upper-left point
     * @param y coordinate of upper-left point
     * @param width of the rectangle
     * @param height of the rectangle
     * @param color color
     */
    public Block(int x, int y, int width, int height, Color color) {
        this.rect = new Rectangle(new Point(x, y), width, height);
        this.color = color;
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        if (collisionPoint.equals(this.rect.getUpperLeft())
                || collisionPoint.equals(this.rect.getUpperRight())
                || collisionPoint.equals(this.rect.getBottomLeft())
                || collisionPoint.equals(this.rect.getBottomRight())) {
            return new Velocity(-1 * currentVelocity.getDx(), -1 * currentVelocity.getDy());
        } else  if (collisionPoint.isOnLIne(this.rect.getUpperLine())
                || collisionPoint.isOnLIne(this.rect.getBottomLine())) {
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        } else if (collisionPoint.isOnLIne(this.rect.getRightLine())
                || collisionPoint.isOnLIne(this.rect.getLeftLine())) {
            return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
        }
        return currentVelocity;
    }
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        Point p = rect.getUpperLeft();
        surface.fillRectangle((int) p.getX(), (int) p.getY(), (int) rect.getWidth(), (int) rect.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) p.getX(), (int) p.getY(), (int) rect.getWidth(), (int) rect.getHeight());
    }
    @Override
    public void timePassed() {
    }
    /**
     * Add the block to the: sprite collection, collidable collection.
     * @param gameLevel game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }
    /**
     * Remove the block from the: sprite collection, collidable collection.
     * @param gameLevel game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
