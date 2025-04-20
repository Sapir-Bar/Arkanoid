// 211540562 Sapir Bar
package Geometry;

/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 04.04.2023
 * The class describes a point.  A point has an x and a y value, and can measure the distance to other points,
 * and if it is equal to another point. */
public class Point {
    private double x;
    private double y;

    private final double threshold = 0.00001;
    /**
     * @ constructor - create a new point
     * @param x coordinate
     * @param y coordinate */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * @return x value of this point */
    public double getX() {
        return this.x;
    }
    /**
     * @return y value of this point */
    public double getY() {
        return this.y;
    }
    /**
     * @param x value of this point
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * @param y value of this point
     */
    public void setY(double y) {
        this.y = y;
    }
    /**
     * @ calculate the distance between 2 points (x1,y1) (x2,y2) following the equation:
     * square root of :((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2))
     * @param other Geometry.Point
     * @return distance between this point to other */

    public double distance(Point other) {
        return Math.sqrt((x - other.getX()) * (x - other.getX()) + (y - other.getY()) * (y - other.getY()));
    }
    /**
     * @param other point
     * @return true if the points are equal, false otherwise
     * @ check whether the points are equal (x1 = x2) and (y1 = y2) */
    public boolean equals(Point other) {
        if (other != null) {
            return (Math.abs(x - other.getX()) < threshold) && (Math.abs(y - other.getY()) < threshold);
        }
        return false;
    }

    /**
     * @ Check whether this point found on a given line
     * @param line line
     * @return true if the point is on the line, otherwise return false
     */
    public boolean isOnLIne(Line line) {
        double slope = line.findSlope(line.start(), line.end());
        if (slope == Double.NEGATIVE_INFINITY) { //Vertical line
            if (this.x == line.start().getX() && this.y <= line.end().getY() && this.y >= line.start().getY()) {
                return true;
            }
        } else if (slope == Double.POSITIVE_INFINITY) { //Geometry.Line is a point
            if (this.equals(line.start())) {
                return true;
            }
        } else { //By equation
            double yIntercect = line.findIntercept(line.start(), slope);
            double yIntersection = slope * this.x + yIntercect;
            if (this.y == yIntersection && this.x <= line.end().getX() && this.x >= line.start().getX()) {
                return true;
            }
        }
        return false;
    }
}







