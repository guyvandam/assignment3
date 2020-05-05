import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-03-28.
 */
public class Block implements Collidable, Sprite {
    private Rectangle rect;
    private java.awt.Color color;

    /**
     * @return a Rectangle object. the block's Rectangle value.
     */
    public Rectangle getRect() {
        return rect;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return getRect();
    }

    /**
     * constructor function.
     *
     * @param rect  a Rectangle object.
     * @param color a java.awt.Color object.
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
    }


//    public boolean isPointLeftToVerticalLine(Line l, Point p) {
//        return l != null && p != null && p.getX() < l.start().getX();
//    }

//    public boolean isPointRightToVerticalLine(Line l, Point p) {
//        return l != null && p != null && p.getX() > l.start().getX();
//    }
// gets a point with a velocity right to the line, moves the point one step backwards to see if the point would be left
// to the line
//    public boolean isCrossedVerticalLine(Line l, Point p, Velocity v) {
//        if (l == null || p == null || v == null || !l.isVertical()) {
//            return false;
//        }
//        Velocity inverseVelocity = new Velocity(-v.getDx(), v.getDy());
//        Point oneStepBackwards = inverseVelocity.applyToPoint(p);
//
//        return (isPointLeftToVerticalLine(l, p) && isPointRightToVerticalLine(l, oneStepBackwards)) ||
//                (isPointRightToVerticalLine(l, p) && isPointLeftToVerticalLine(l, oneStepBackwards));
//    }

    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null || currentVelocity == null) {
            return null;
        }
        Line right = this.getCollisionRectangle().getRightEdge();
        Line left = this.getCollisionRectangle().getLeftEdge();
        Line upper = this.getCollisionRectangle().getUpperEdge();
        Line lower = this.getCollisionRectangle().getLowerEdge();

        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        if (left.isInLine(collisionPoint) || right.isInLine(collisionPoint)) {
//            currentVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            dx = -dx;
        }
        if (upper.isInLine(collisionPoint) || lower.isInLine(collisionPoint)) {
//            currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            dy = -dy;
        }

        return new Velocity(dx, dy);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        if (surface != null) {
            surface.setColor(this.color);
            surface.drawRectangle((int) this.getRect().getUpperLeft().getX(), (int)
                            this.getRect().getUpperLeft().getY(),
                    (int) this.getRect().getWidth(), (int) this.getRect().getHeight());
        }
    }

    @Override
    public void timePassed() {
    }

    /**
     * adds the Block to the input Game object.
     *
     * @param g a Game object. the Game we want to add the Block to.
     */
    public void addToGame(Game g) {
        if (g != null) {
            g.addCollidable(this);
            g.addSprite(this);
        }
    }
}
