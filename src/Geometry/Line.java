// 211540562 Sapir Bar
package Geometry;
import java.util.List;

/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 04.04.2023
 * The class describes a line. Geometry.Line has a start point and an end point. Lines have lengths, and may
 * intersect with other lines. It can also tell if it is the same as another line segment.
 */
public class Line {
    private final double threshold = 0.0001;
    private Point startPoint;
    private Point endPoint;

    /**
     * @param start point
     * @param end   point
     * @ constructor - create a new line. The arguments are startPoint and endPoint
     */
    public Line(Point start, Point end) {
        if (start.getX() == end.getX()) { // vertical line
            if (start.getY() <= end.getY()) { // down up direction
                this.startPoint = start;
                this.endPoint = end;
            } else {
                this.startPoint = end;
                this.endPoint = start;
            }
        } else if (start.getX() < end.getX()) { // non-vertical, left to right direction
            this.startPoint = start;
            this.endPoint = end;
        } else { //end.getX() < start.getX()
            this.startPoint = end;
            this.endPoint = start;
        }
    }

    /**
     * @param x1 start point, x coordinate
     * @param y1 start point, y coordinate
     * @param x2 end point, x coordinate
     * @param y2 end point, y coordinate
     * @ constructor - create a new line. The arguments are (x1, y1) for startPoint and (x2, y2) for endPoint
     */
    public Line(double x1, double y1, double x2, double y2) {
        if (x1 == x2) { // vertical line
            if (y1 <= y2) { // down up direction
                this.startPoint = new Point(x1, y1);
                this.endPoint = new Point(x2, y2);
            } else {
                this.startPoint = new Point(x2, y2);
                this.endPoint = new Point(x1, y1);
            }
        } else if (x1 < x2) { // non-vertical, left to right direction
            this.startPoint = new Point(x1, y1);
            this.endPoint = new Point(x2, y2);
        } else { // x1 > x2
            this.startPoint = new Point(x2, y2);
            this.endPoint = new Point(x1, y1);
        }
    }

    /**
     * @return the length of the line using the method "distance" of the class "Geometry.Point"
     */
    public double length() {
        return startPoint.distance(endPoint);
    }

    /**
     * @return the middle point of the line
     */
    public Point middle() {
        double midX = (startPoint.getX() + endPoint.getX()) / 2;
        double midY = (startPoint.getY() + endPoint.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return this.startPoint;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return this.endPoint;
    }

    /**
     * @param p     Geometry.Point
     * @param start (startPoint of the line)
     * @param end   (endPoint of the line)
     * @return true if the point is on the line, otherwise return false
     * @ check whether is point located on line fragment.
     */
    public boolean isPointOnLine(Point p, Point start, Point end) {
        if (start.getX() <= end.getX()) {
            return (Math.abs(start.getX() - p.getX()) < threshold || start.getX() <= p.getX())
                    && (Math.abs(p.getX() - end.getX()) < threshold || p.getX() <= end.getX());
    } else {
        return (Math.abs(end.getX() - p.getX()) < threshold || end.getX() <= p.getX())
                && (Math.abs(p.getX() - start.getX()) < threshold || p.getX() <= start.getX());
    }
}

    /**
     * @param p     Geometry.Point
     * @param start (startPoint of the line)
     * @param end   (endPoint of the line)
     * @return true if the point is on the line, otherwise return false
     * @ check whether is point located on line fragment
     */
    public boolean isPointOnLineVer(Point p, Point start, Point end) {
        if (start.getY() <= end.getY()) {
            return (Math.abs(start.getY() - p.getY()) < threshold || start.getY() <= p.getY())
                    && (Math.abs(p.getY() - end.getY()) < threshold || p.getY() <= end.getY());
        } else {
            return (Math.abs(end.getY() - p.getY()) < threshold || end.getY() <= p.getY())
                    && (Math.abs(p.getY() - start.getY()) < threshold || p.getY() <= start.getY());
        }
    }
    /**
     * @param start point
     * @param end   point
     * @return slope
     * @ find slope of a line following the equation: (y1-y2)\(x1-x2)
     */
    public double findSlope(Point start, Point end) {
        Double slope = null;
        if ((start.equals(end))) { //Geometry.Line is a point
            slope = Double.POSITIVE_INFINITY;
        } else if (Math.abs(start.getY() - end.getY()) < threshold) { //Horizontal
            slope = 0.0;
        } else if (Math.abs(start.getX() - end.getX()) < threshold) { //Vertical
            slope = Double.NEGATIVE_INFINITY;
        } else {
            slope = (start.getY() - end.getY()) / (start.getX() - end.getX());
        }
        return slope;
    }

    /**
     * @param p     point on the line
     * @param slope of a line
     * @return intercept
     * @ find the intercept of a line (where the line crosses the y-axis).
     * Equation of a Geometry.Line is y = ax + b --> b = y - ax.
     */
    public double findIntercept(Point p, double slope) {
        return p.getY() - slope * p.getX();
    }

    /**
     * @param other line
     * @return intersection point if the lines intersect, and null otherwise
     * @ Calculate an intersection of 2 lines considering different cases
     */
    public Point intersectionWith(Line other) {
// case 1: the lines are equal, so there is no intersection
        if (equals(other)) {
            return null;
        }
// case 2: none of the lines are vertical
        if (startPoint.getX() != endPoint.getX() && other.startPoint.getX() != other.endPoint.getX()) {
            double slopeA = findSlope(startPoint, endPoint);
            double slopeB = findSlope(other.startPoint, other.endPoint);
            double interceptA = findIntercept(startPoint, slopeA);
            double interceptB = findIntercept(other.startPoint, slopeB);
            if ((slopeA == slopeB) && (interceptA != interceptB)) { //parallel lines
                return null;
            } else if ((slopeA == slopeB) && (interceptA == interceptB)) { //lie on the same straight line
                if (inclusion(other)) {
                    return null;
                } else if (other.endPoint.equals(startPoint)) {
                    return startPoint;
                } else if (other.startPoint.equals(endPoint)) {
                    return endPoint;
                }
            }
            double xIntersection = (interceptB - interceptA) / (slopeA - slopeB);
            double y1 = (slopeA * xIntersection) + interceptA;
            Point intersection = new Point(xIntersection, y1);
            if ((isPointOnLine(intersection, startPoint, endPoint))
                    && (isPointOnLine(intersection, other.startPoint, other.endPoint))) {
                return intersection;
            }
//case 3: line A vertical and line B isn't vertical
        } else if ((startPoint.getX() == endPoint.getX()) && (other.startPoint.getX() != other.endPoint.getX())) {
            double slopeB = findSlope(other.startPoint, other.endPoint);
            double interceptB = findIntercept(other.startPoint, slopeB);
            // y = ax + b - x value at lineA is constant, so we set this x value at the lineB equation
            double yIntersection = slopeB * startPoint.getX() + interceptB;
            Point intersection = new Point(startPoint.getX(), yIntersection);
            if ((isPointOnLineVer(intersection, startPoint, endPoint))
                    && isPointOnLine(intersection, other.startPoint, other.endPoint)) {
                return intersection;
            }
//case 4: line B vertical and line A isn't vertical
        } else if (startPoint.getX() != endPoint.getX() && other.startPoint.getX() == other.endPoint.getX()) {
            double slopeA = findSlope(startPoint, endPoint);
            double interceptA = findIntercept(startPoint, slopeA);
            double yIntersection = slopeA * other.startPoint.getX() + interceptA;
            Point intersection = new Point(other.startPoint.getX(), yIntersection);
            if ((isPointOnLine(intersection, startPoint, endPoint))
                    && isPointOnLineVer(intersection, other.startPoint, other.endPoint)) {
                return intersection;
            }
            return null;
//case 5: both of the lines are verticals
        } else if (startPoint.getX() == endPoint.getX() && other.startPoint.getX() == other.endPoint.getX()) {
            if (startPoint.getX() == other.startPoint.getX()) { // lie on the same straight line
                if (inclusionVer(other)) {
                    return null;
                } else if (startPoint.getY() == other.endPoint.getY()) {
                    return new Point(startPoint.getX(), startPoint.getY());
                } else if (other.startPoint.getY() == endPoint.getY()) {
                    return new Point(endPoint.getX(), endPoint.getY());
                }
            }
        }
        return null;

    }

    /**
     * @param other line
     * @return true if the lines intersect, false otherwise
     */

    public boolean isIntersecting(Line other) {
        if (this.equals(other)) {
            return true;
        }
        if (startPoint.getX() == endPoint.getX() && other.startPoint.getX() == other.endPoint.getX()) { //vertical lines
            if (startPoint.getX() == other.startPoint.getX()) { //vertical lines with the same x-coordinate
                if (inclusionVer(other)) { //inclusion
                    return true;
                }
                return this.intersectionWith(other) != null; //disjoint or intersect
            } else { //parallel lines
                return false;
            }
        }
        double slopeA = findSlope(startPoint, endPoint);
        double slopeB = findSlope(other.startPoint, other.endPoint);
        double interceptA = findIntercept(startPoint, slopeA);
        double interceptB = findIntercept(other.startPoint, slopeB);
        if ((slopeA == slopeB) && (interceptA == interceptB)
                && (inclusion(other))) { // non-vertical, lie on the same straight line
            return true;
        }
        return this.intersectionWith(other) != null;
    }

    /**
     * @param other line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (startPoint.equals(other.startPoint)) && (endPoint.equals(other.endPoint));
    }

    /**
     * @param other Geometry.Line
     * @return true: full/partial inclusion, false: 2 different lines
     * @ given 2 fragments lines (non-vertical) with the same line equation, checking for inclusion
     * by examining the x-rate range of the lines.
     */
    public boolean inclusion(Line other) {
        Point bottom, top;
        if (startPoint.getX() == other.startPoint.getX() || endPoint.getX() == other.endPoint.getX()) {
            return true;
        }
        if (startPoint.getX() < other.startPoint.getX()) {
            bottom = startPoint;
            top = other.endPoint;
        } else {
            bottom = other.startPoint;
            top = endPoint;
        }
        double distance = bottom.distance(top);
        double length = this.length() + other.length();
        return ((length - distance) > threshold);
    }

    /**
     * @param other Geometry.Line
     * @return true: full/partial inclusion, false: 2 different lines
     * @ given 2 vertical fragments lines with the same line equation, checking for inclusion by examining the
     * y-rate range of the lines.
     */
    public boolean inclusionVer(Line other) {
        if (startPoint.getY() == other.startPoint.getY() || endPoint.getY() == other.endPoint.getY()) {
            return true;
        }
        double length = this.length() + other.length();
        double topMax = Math.max(endPoint.getY(), other.endPoint.getY());
        double bottomMin = Math.min(startPoint.getY(), other.startPoint.getY());
        double distance = topMax - bottomMin;
        return ((length - distance) > threshold);
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect rectangle
     * @return closet intersection point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> list = rect.intersectionPoints(this);
        if (list.isEmpty()) { //Inclusion or disjoint
            return null;
        }
        Point closet = new Point(list.get(0).getX(), list.get(0).getY());
        if (startPoint.getX() == endPoint.getX()) { //Vertical line
            for (Point p : list) {
                if (Math.abs(this.start().getY() - p.getY()) < Math.abs(this.start().getY() - closet.getY())) {
                    closet = p;
                }
            }
        } else { //Non-vertical lines
            for (Point p : list) {
                if (Math.abs(this.start().getX() - p.getX()) < Math.abs(this.start().getX() - closet.getX())) {
                    closet = p;
                }
            }
        }
        return closet;
    }
}











