// 211540562 Sapir Bar
package CollisionDetection;
import Geometry.Point;
import Geometry.Rectangle;
import Sprite.Ball;
import Sprite.Velocity;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 06.05.2023
 * The Interface describes the characteristics of collidable object.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return collision rectangle
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param hitter Ball
     * @param collisionPoint point
     * @param currentVelocity velocity
     * @return new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}