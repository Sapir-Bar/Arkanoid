// 211540562 Sapir Bar
package Geometry;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 06.05.2023
 * The class describes a rectangle.
 * Rectangle can be intersected with a given line and consist a given point. */
public class Rectangle {
    private double width;
    private double height;
    private Point upperLeftPoint;
    private Point upperRightPoint;
    private Point bottomLeftPoint;
    private Point bottomRightPoint;
    private Line upperLine;
    private Line bottomLine;
    private Line rightLine;
    private Line leftLine;
    private Line[] sides;

    /**
     * @ Constructor - create a new rectangle with location and width/height.
     * @param upperLeft point
     * @param width width of rectangle
     * @param height height of rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeftPoint = upperLeft;
        this.upperRightPoint = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.bottomLeftPoint = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.bottomRightPoint = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.upperLine = new Line(upperLeftPoint, upperRightPoint);
        this.bottomLine = new Line(bottomLeftPoint, bottomRightPoint);
        this.rightLine = new Line(bottomRightPoint, upperRightPoint);
        this.leftLine = new Line(bottomLeftPoint, upperLeftPoint);
        this.sides = new Line[]{upperLine, bottomLine, rightLine, leftLine};
    }
    /**
     * @ getter of upper line
     * @return upper line
     */
    public Line getUpperLine() {
        return this.upperLine;
    }
    /**
     * @ getter of bottom line
     * @return bottom line
     */
    public Line getBottomLine() {
        return this.bottomLine;
    }
    /**
     * @ getter of right line
     * @return right line
     */
    public Line getRightLine() {
        return this.rightLine;
    }
    /**
     * @ getter of left line
     * @return left line
     */
    public Line getLeftLine() {
        return this.leftLine;
    }
    /**
     * @ getter of the width of the rectangle
     * @return width of rectangle
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * @ getter of the height of the rectangle
     * @return height of rectangle
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * @ getter of the upper-left point of the rectangle
     * @return upper-left point
     */
    public Point getUpperLeft() {
        return this.upperLeftPoint;
    }
    /**
     * @ getter of the upper-right point of the rectangle
     * @return upper-right point
     */
    public Point getUpperRight() {
        return this.upperRightPoint;
    }
    /**
     * @ getter of the bottom-left point of the rectangle
     * @return bottom-left point
     */
    public Point getBottomLeft() {
        return this.bottomLeftPoint;
    }
    /**
     * @ getter of the bottom-right point of the rectangle
     * @return bottom-right point
     */
    public Point getBottomRight() {
        return this.bottomRightPoint;
    }
    /**
     * @ find all intersection points with the specified line.
     * @param line line
     * @return (possibly empty) List of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<>();
        for (Line side : sides) {
            if ((side.intersectionWith(line) == null) && side.isIntersecting(line)) { //Inclusion of lines
                list.add(side.start());
                list.add(side.end());
            }
        }
        for (Line side : sides) {
            if ((side.intersectionWith(line) != null) && side.isIntersecting(line)) {
                list.add(side.intersectionWith(line));
            }
        }
        return list;
    }
    /**
     * @ Check whether a given point located inside of this rectangle.
     * @param p point
     * @return true if the point is within the bounds of the rectangle, otherwise return false.
     */
    public boolean isPointInsideRectangle(Point p) {
        if (p.getX() >= this.getUpperLeft().getX() && p.getX() <= this.getUpperRight().getX()) {
            if (p.getY() >= this.getUpperLeft().getY() && p.getY() <= this.getBottomLeft().getY()) {
                return true;
            }
        }
        return false;
    }
}