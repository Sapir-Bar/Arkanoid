// 211540562 Sapir Bar
package Sprite;
import CollisionDetection.Collidable;
import CollisionDetection.GameEnvironment;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Levels.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 04.04.2023
 * The class describes a ball with the following characteristics: radius, center, color, constant velocity.
 * The ball changes his direction once it collide at collidable object (block, paddle).
 */
public class Ball implements Sprite {
    private int radius;
    private Point point;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private Line trajectory;

    /**
     * @param center Geometry.Point
     * @param r      radius
     * @param color  RGB
     * @ constructor
     */
    public Ball(Point center, int r, Color color) {
        //default values
        if (r <= 0) {
            r = 30;
        }
        if (center == null) {
            center = new Point(30, 30);
        }
        if (color == null) {
            color = Color.BLACK;
        }
        //input
        this.point = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * @param x     coordinate
     * @param y     coordinate
     * @param r     radius
     * @param color RGB
     * @ constructor
     */
    public Ball(int x, int y, int r, Color color) {
        //default values
        if (r <= 0) {
            r = 30;
        }
        if (x < 0) {
            x = 30;
        }
        if (y < 0) {
            y = 30;
        }
        if (color == null) {
            color = Color.BLACK;
        }
        //input
        this.point = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * @return x coordinate
     * @ getter of x coordinate of the center of the circle
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * @return y coordinate
     * @ getter of y coordinate of the center of the circle
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * @return radius
     * @ getter of the radius of the circle
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return color
     * @ getter of the color of the circle
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @return velocity
     * @ getter of the velocity of the circle defined by dx, dy
     */
    public Velocity getVelocity() {
        return new Velocity(velocity.getDx(), velocity.getDy());
    }

    /**
     * @param v velocity
     * @ setter of the velocity of the circle
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
        this.setTrajectory();
    }

    /**
     * @param dx change in x coordinate per one step
     * @param dy change in y coordinate per one step
     * @ setter of the velocity of the circle defined by dx, dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
        this.setTrajectory();
    }
    /**
     * Setter of trajectory according the ball's velocity.
     */
    public void setTrajectory() {
        this.trajectory = new Line(this.point.getX(), this.point.getY(),
                this.velocity.getDx() + this.point.getX(), this.velocity.getDy() + this.point.getY());
        if (this.velocity.getDx() < 0) {
            switchPoints(trajectory.start(), trajectory.end());
        }
        if (this.velocity.getDx() == 0 && this.velocity.getDy() < 0) {
            switchPoints(trajectory.start(), trajectory.end());
        }
    }
    /**
     * Switch between the start, end points of a given line in a case of movement up/to the left.
     * Important in order to find the correct closest collision point.
     * @param start point of trajectory.
     * @param end point of trajectory.
     */
    private void switchPoints(Point start, Point end) {
        Point temp = new Point(trajectory.start().getX(), trajectory.start().getY());
        this.trajectory.start().setX(trajectory.end().getX());
        this.trajectory.start().setY(trajectory.end().getY());
        this.trajectory.end().setX(temp.getX());
        this.trajectory.end().setY(temp.getY());
    }
    /**
     * @ Setter of this game environment
     * @param gameEnvironment game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * @ Moving the ball one step forward according to the velocity (changes the point of the ball).
     * If no collision is detected, it moves the ball to the end of the trajectory.
     * else it moves the ball to the "almost" collision point.
     */
    public void moveOneStep() {
        Line trajectory = this.trajectory;
        if (this.gameEnvironment.getClosestCollision(trajectory) == null) {
            this.point = getVelocity().applyToPoint(this.point);
            this.setTrajectory();
        } else {
            Point collisionPoint = this.gameEnvironment.getClosestCollision(trajectory).collisionPoint();
            Collidable collisionObject = this.gameEnvironment.getClosestCollision(trajectory).collisionObject();
            Rectangle r = collisionObject.getCollisionRectangle();
            // Place the ball at "almost" the collision point
            if (collisionPoint.isOnLIne(r.getUpperLine())) {
                point.setX(collisionPoint.getX());
                point.setY(collisionPoint.getY() - 1.5 * this.radius);
            } else if (collisionPoint.isOnLIne(r.getBottomLine())) {
                point.setX(collisionPoint.getX());
                point.setY(collisionPoint.getY() + 1.5 * this.radius);
            } else if (collisionPoint.isOnLIne(r.getRightLine())) {
                point.setX(collisionPoint.getX() + 1.5 * this.radius);
                point.setY(collisionPoint.getY());
            } else if (collisionPoint.isOnLIne(r.getLeftLine())) {
                point.setX(collisionPoint.getX() - 1.5 * this.radius);
                point.setY(collisionPoint.getY());
            }
            setVelocity(collisionObject.hit(this, collisionPoint, this.velocity));
        }
    }
    /**
     * Check whether the center of the ball is located at one of the collidable objects during the initialization.
     * if it does, the method places it at another point on the screen.
     */
    public void checkCenter() {
        List<Collidable> collidableList = this.gameEnvironment.getCollidableList();
        for (Collidable c : collidableList) {
            if (c.getCollisionRectangle().isPointInsideRectangle(this.point)) {
                point.setX(100); // No collidable object found at the point (100, 500)
                point.setY(500);
            }
        }
    }
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(getX(), getY(), getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(getX(), getY(), getSize());
    }
    @Override
    public void timePassed() {
        moveOneStep();
    }
    /**
     * Add the ball to the sprite collection.
     * @param gameLevel game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
    /**
     * remove the ball from the sprite collection.
     * @param gameLevel game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}

