import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-03-28.
 */
public class Ball {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameEnvironment;


    /**
     * constructor function.
     *
     * @param center a Point. the center of the ball
     * @param r      an int. the radios of the ball.
     * @param color  an java.awt.Color object. the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * constructor function as well, gets x and y of the center point instead of the point itself.
     *
     * @param x     a double. the x field of the center point.
     * @param y     a double. the y field of the center point.
     * @param r     an int. the radios of the ball.
     * @param color an java.awt.Color object. the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }


    /**
     * @return int. the x value of the center point.
     */
    public int getX() {
        return (int) (this.center.getX());
    }

    /**
     * @return int. the y value of the center point.
     */
    public int getY() {
        return (int) (this.center.getY());
    }

    /**
     * @return an int. the radios of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * @return an java.awt.Color object. the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draws the ball on the input surface. in the color of the "color" field.
     *
     * @param surface a DrawSurface object. the GUI surface we want to draw to ball on.
     */
    public void drawOn(DrawSurface surface) {
        if (surface != null) {
            surface.setColor(this.color);
            surface.fillCircle(getX(), getY(), this.r);
        }
    }

    /**
     * sets the velocity of the ball to the input velocity.
     *
     * @param velocity a velocity object. the desired velocity.
     */
    public void setVelocity(Velocity velocity) {
        if (velocity != null) {
            this.v = velocity;
        }
    }

    /**
     * sets the velocity of the ball to the input velocity.
     *
     * @param dx a double. the dx of the desired velocity.
     * @param dy a double. the dx of the desired velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * @return a velocity object. the balls velocity.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * moves one step in the ball's velocity. checks if the velocity isn't a null pointer.
     */
    public void moveOneStep() {
        if (this.v != null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        }

    }

    /**
     * moves one step in the ball's velocity. checks for the ball hitting the corners of the frame. and inverting the
     * velocity.
     * <p>
     * get the bounds of the frame. if one of the edges of the ball hits the corner it flips the sign of the dx or
     * dy, depending on the corner hit. checks for the ball going outside the frame and correct it before it happens.
     * adds the radios to calculation because we want the edges of the ball.
     * </p>
     *
     * @param leftXBound  an int. the left bound of the frame.
     * @param rightXBound an int. the right bound of the frame.
     * @param upperYBound an int. the upper bound of the frame.
     * @param lowerYBound an int. the lower bound of the frame.
     */
    public void moveOneStep(int leftXBound, int rightXBound, int upperYBound, int lowerYBound) {
        moveOneStep();
        if (this.center.getX() - this.r + this.v.getDx() <= leftXBound || this.center.getX() + this.r + this.v.getDx()
                >= rightXBound) {
            this.setVelocity(-this.v.getDx(), this.v.getDy());

        }

        if (this.center.getY() - this.r + this.v.getDy() <= upperYBound || this.center.getY() + this.r + this.v.getDy()
                >= lowerYBound) {
            this.setVelocity(this.v.getDx(), -this.v.getDy());
        }

    }
}