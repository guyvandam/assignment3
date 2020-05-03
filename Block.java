import biuoop.DrawSurface;

import java.awt.Color;

public class Block implements Collidable, Sprite {
    private Rectangle rect;
    private java.awt.Color color;


    public Rectangle getRect() {
        return rect;
    }

    public Rectangle getCollisionRectangle() {
        return getRect();
    }


    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
    }

    public boolean isPointLeftToVerticalLine(Line l, Point p) {
        return l != null && p != null && p.getX() < l.start().getX();
    }

    public boolean isPointRightToVerticalLine(Line l, Point p) {
        return l != null && p != null && p.getX() > l.start().getX();
    }

    // gets a point with a velocity right to the line, moves the point one step backwards to see if the point would be left to the line
    public boolean isCrossedVerticalLine(Line l, Point p, Velocity v) {
        if (l == null || p == null || v == null || !l.isVertical()) {
            return false;
        }
        Velocity inverseVelocity = new Velocity(-v.getDx(), v.getDy());
        Point oneStepBackwards = inverseVelocity.applyToPoint(p);

        return (isPointLeftToVerticalLine(l, p) && isPointRightToVerticalLine(l, oneStepBackwards)) ||
                (isPointRightToVerticalLine(l, p) && isPointLeftToVerticalLine(l, oneStepBackwards));
    }

    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null || currentVelocity == null) {
            return null;
        }
        Line right = this.getCollisionRectangle().getRightEdge();
        Line left = this.getCollisionRectangle().getLeftEdge();
        Line upper = this.getCollisionRectangle().getUpperEdge();
        Line lower = this.getCollisionRectangle().getLowerEdge();

//        double collisionPointX = collisionPoint.getX();
//        double collisionPointY = collisionPoint.getY();

//        if (collisionPointX <= right.start().getX() && collisionPointX >= left.start().getX()) {
//        if (isCrossedVerticalLine(right, collisionPoint, currentVelocity) || isCrossedVerticalLine(left, collisionPoint, currentVelocity)) {
        if (left.isInLine(collisionPoint) || right.isInLine(collisionPoint)) {
            currentVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (upper.isInLine(collisionPoint) || lower.isInLine(collisionPoint)) {
//        if (collisionPointY >= upper.start().getY() && collisionPointY <= lower.start().getY()) {
            currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }

        return currentVelocity;
    }

    public void drawOn(DrawSurface surface) {
        if (surface != null) {
            surface.setColor(this.color);
            surface.fillRectangle((int) this.getRect().getUpperLeft().getX(), (int) this.getRect().getUpperLeft().getY(),
                    (int) this.getRect().getWidth(), (int) this.getRect().getHeight());
        }
    }

    public void timePassed() {
    }

    public void addToGame(Game g) {
        if (g != null) {
            g.addCollidable(this);
            g.addSprite(this);
        }
    }
}
