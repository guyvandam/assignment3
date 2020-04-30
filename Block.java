import java.awt.Color;

public class Block implements Collidable {
    private Rectangle rect;
    private java.awt.Color color;


    public Rectangle getRect() {
        return rect;
    }

    public Rectangle getCollisionRectangle() {
        return getRect();
    }


    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Line right = this.getCollisionRectangle().getRightEdge();
        Line left = this.getCollisionRectangle().getLeftEdge();
        Line upper = this.getCollisionRectangle().getUpperEdge();
        Line lower = this.getCollisionRectangle().getLowerEdge();


        if (right.isInLine(collisionPoint) || left.isInLine(collisionPoint)) {
            currentVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (upper.isInLine(collisionPoint) || lower.isInLine(collisionPoint)) {
            currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }

        return currentVelocity;
    }
}
