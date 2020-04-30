import java.util.ArrayList;
import java.util.List;


public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        if (line == null) {
            return null;
        }

        List<Point> returnPoints = new ArrayList<Point>();
        Line[] lineList = {line, new Line(line.end(), line.start())};
        for (Line l : lineList) {
            Point temp = l.closestIntersectionToStartOfLine(new Rectangle(this.upperLeft, this.width, this.height));
            if (temp != null) {
                returnPoints.add(temp);
            }
        }

        if (returnPoints.isEmpty()) {
            return null;
        } else {
            return returnPoints;
        }


    }

    // Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    public Line getUpperEdge() {
        return new Line(this.getUpperLeft(), new Point(this.getUpperLeft().getX() + getWidth(), this.getUpperLeft().getY()));
    }

    public Line getLowerEdge() {
        return new Line(new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() - getHeight()),
                new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY() - this.getHeight()));
    }

    public Line getLeftEdge() {
        return new Line(this.getUpperLeft(), new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() - this.getHeight()));
    }

    public Line getRightEdge() {
        return new Line(new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY()),
                new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY() - this.getHeight()));
    }
}