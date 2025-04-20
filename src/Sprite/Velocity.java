// 211540562 Sapir Bar
package Sprite;
import Geometry.Point;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 04.04.2023
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;
    private double angle;
    /**
     * @ constructor
     * @param dx change in x coordinate per one step
     * @param dy change in y coordinate per one step
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
        this.angle = Math.atan2(dy, dx);
    }
    /**
     * @ Sprite.Velocity can be defined as a vector with magnitude and direction (speed and angle accordingly).
     * @param angle (degrees)
     * @param speed (size of the vector)
     * @return velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radAngle = (angle * Math.PI) / 180;
        double dx = speed * Math.sin(radAngle);
        double dy = -1 * speed * Math.cos(radAngle);
        return new Velocity(dx, dy);
    }
    /**
     * @ getter of dx - change in x coordinate per one step
     * @return dx
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * @ getter of dy - change in y coordinate per one step
     * @return dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * @ getter of angle (degrees)
     * @return angle
     */
    public double getAngle() {
        return Math.atan2(dy, dx);
    }
    /**
     * @ getter of speed (size of velocity)
     * @return speed
     */
    public double getSize() {
        return Math.sqrt(getDx() * getDx() + getDy() * getDy());
    }
    /**
     * @ take a point with position (x,y) and return a new point with position (x+dx, y+dy)
     * @param p Geometry.Point
     * @return modified Geometry.Point
     */
    public Point applyToPoint(Point p) {
        double x = p.getX() + dx;
        double y = p.getY() + dy;
        return new Point(x, y);
    }
}