/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-03-28.
 */

public interface Collidable {
    /**
     * Returns the "collision shape" of the object.
     *
     * @return a Rectangle object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit based of the hit location on the object.
     *
     * @param collisionPoint  a Point object. the collision point.
     * @param currentVelocity a Velocity object. the velocity of the object hitting our Collidable.
     * @return a Velocity object. the expected Velocity after the impact.
     */

    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}