// 211540562 Sapir Bar
package CollisionDetection;
import Geometry.Point;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 06.05.2023
 * The class gives information about collision with collidable object.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;
    /**
     * @ Constructor
     * @param collision point
     * @param collidable object
     */
    public CollisionInfo(Point collision, Collidable collidable) {
        this.collisionPoint = collision;
        this.collisionObject = collidable;
    }
    /**
     * @ getter of collision point
     * @return point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * @ getter of collision object
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
