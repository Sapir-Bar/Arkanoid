//211540562 Sapir Bar
package Sprite;
import CollisionDetection.Collidable;
import Geometry.Point;
import Geometry.Rectangle;
import Levels.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 06.05.2023
 * The class represented a paddle controlled by the player.
 */
public class Paddle implements Collidable, Sprite {
    private final double speed;
    private final int widthOfFrameBlocksVertical;
    private final int heightOfFrameBlocksHorizontal;
    private final int guiWidth = 800;
    private final int guiHeight = 600;
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private final Color color = Color.YELLOW;
    /**
     * @ Constructor - create a new instance of paddle.
     * @param keyboard keyboard
     * @param widthOfFrameBlocksVertical width
     * @param heightOfFrameBlocksHorizontal height
     * @param width of rectangle
     * @param height of rectangle
     * @param speed of paddle
     */
    public Paddle(biuoop.KeyboardSensor keyboard, int widthOfFrameBlocksVertical, int heightOfFrameBlocksHorizontal,
                  int width, int height, double speed) {
        this.keyboard = keyboard;
        this.widthOfFrameBlocksVertical = widthOfFrameBlocksVertical;
        this.heightOfFrameBlocksHorizontal = heightOfFrameBlocksHorizontal;
        int y = guiHeight - height - heightOfFrameBlocksHorizontal;
        int x = (guiWidth / 2) - (width / 2);
        this.rect = new Rectangle(new Point(x, y), width, height);
        this.speed = speed;
    }
    /**
     * @ Setter of this rectangle by define a new upper-left point (fixed width and height).
     * @param upperLeft point
     */
    public void setRectangle(Point upperLeft) {
        this.rect = new Rectangle(upperLeft, this.rect.getWidth(), this.rect.getHeight());
    }
    /**
     * Move the paddle to the left by defined a new upper-left point.
     */
    public void moveLeft() {
        if (this.rect.getUpperLeft().getX() - speed >= widthOfFrameBlocksVertical) {
            setRectangle(new Point(this.rect.getUpperLeft().getX() - speed,
                    this.rect.getUpperLeft().getY()));
        }
    }
    /**
     * Move the paddle to the right by defined a new upper-left point.
     */
    public void moveRight() {
        if (this.rect.getUpperRight().getX() + speed <= guiWidth - widthOfFrameBlocksVertical) {
            setRectangle(new Point(this.rect.getUpperLeft().getX() + speed,
                    this.rect.getUpperLeft().getY()));
        }
    }
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
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
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double size = currentVelocity.getSize();
        double angle;
        if (findRegion(collisionPoint) == 1) {
            angle = -60;
        } else if (findRegion(collisionPoint) == 2) {
            angle = -30;
        } else if (findRegion(collisionPoint) == 3) {
            angle = 0;
        } else if (findRegion(collisionPoint) == 4) {
            angle = 30;
        } else if (findRegion(collisionPoint) == 5) {
            angle = 60;
        } else {
            return null;
        }
        if (currentVelocity.getDy() < 0) {
            return Velocity.fromAngleAndSpeed(180 - angle, size);
        } else {
            return Velocity.fromAngleAndSpeed(angle, size);
        }
    }
    /**
     * Determining the region of the collision point by dividing the rectangle into 5 equally-spaced regions.
     * @param collisionPoint point
     * @return the index of the collided region
     */
    public int findRegion(Point collisionPoint) {
        int width = (int) this.rect.getWidth();
        int xCoordinateOfPaddle = (int) this.rect.getUpperLeft().getX();
        int xCoordinateOfCollision = (int) collisionPoint.getX();
        if (xCoordinateOfCollision >= xCoordinateOfPaddle
                && xCoordinateOfCollision < xCoordinateOfPaddle + ((width) / 5)) {
            return 1;
        } else if (xCoordinateOfCollision >= xCoordinateOfPaddle + ((width) / 5)
                && xCoordinateOfCollision < xCoordinateOfPaddle + ((width * 2) / 5)) {
            return 2;
        } else if (xCoordinateOfCollision >= xCoordinateOfPaddle + ((width * 2) / 5)
                && xCoordinateOfCollision < xCoordinateOfPaddle + ((width * 3) / 5)) {
            return 3;
        } else if (xCoordinateOfCollision >= xCoordinateOfPaddle + ((width * 3) / 5)
                && xCoordinateOfCollision < xCoordinateOfPaddle + ((width * 4) / 5)) {
            return 4;
        } else if (xCoordinateOfCollision >= xCoordinateOfPaddle + ((width * 4) / 5)
                && xCoordinateOfCollision <= this.rect.getUpperRight().getX()) {
            return 5;
        } else {
            return 0;
        }
    }
    /**
     * Add the block to the: sprite collection, collidable collection.
     * @param gameLevel game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }
}
