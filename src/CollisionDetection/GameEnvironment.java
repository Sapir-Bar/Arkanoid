//211540562 Sapir Bar
package CollisionDetection;

import Geometry.Line;
import Geometry.Point;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 06.05.2023
 * The class holds a list of collidable objects.
 * Given a trajectory, it's returns a collision info about the closet collision relative the start of the line.
 */
public class GameEnvironment {
    private List<Collidable> collidableList = new ArrayList<>();
    /**
     * getter of collidable list.
     * @return collidable list.
     */
    public List<Collidable> getCollidableList() {
        return this.collidableList;
    }
    /**
     * Add the given collidable to the environment.
     * @param c collidable object
     */
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }

    /**
     * Remove the given collidable from the environment.
     * @param c collidable object
     */
    public void removeCollidable(Collidable c) {
        collidableList.remove(c);
    }
    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory line
     * @return collision info
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // Make a copy of the collidables before iterating over them.
        List<Collidable> collidables = new ArrayList<Collidable>(this.collidableList);
        List<Collidable> list = new ArrayList<>();
        for (Collidable object : collidables) {
            if (trajectory.closestIntersectionToStartOfLine(object.getCollisionRectangle()) != null) {
                list.add(object);
            }
        }
        if (list.isEmpty()) {
            return null;
        }
        Point collisionPoint = trajectory.closestIntersectionToStartOfLine(list.get(0).getCollisionRectangle());
        CollisionInfo info = new CollisionInfo(collisionPoint, list.get(0));

        if (trajectory.start().getX() == trajectory.end().getX()) { //Vertical trajectory
            for (Collidable item : list) {
                Point p = trajectory.closestIntersectionToStartOfLine(item.getCollisionRectangle());
                if (Math.abs(trajectory.start().getY() - p.getY())
                        < Math.abs(trajectory.start().getY() - collisionPoint.getY())) {
                    info = new CollisionInfo(p, item);
                }
            }
        } else if (trajectory.start().getX() != trajectory.end().getX()) {  //Non-Vertical trajectory
            for (Collidable item : list) {
                Point p = trajectory.closestIntersectionToStartOfLine(item.getCollisionRectangle());
                if (Math.abs(trajectory.start().getX() - p.getX())
                        < Math.abs(trajectory.start().getX() - collisionPoint.getX())) {
                    info = new CollisionInfo(p, item);
                }
            }
        }
        return info;
    }
}
