import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<Collidable>();


    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (null == trajectory) {
            return null;
        }
        Point startOfTrajectory = trajectory.start();
        Collidable returnCollidable = collidables.get(0);
        Rectangle returnRect = returnCollidable.getCollisionRectangle();
        Point returnPoint = trajectory.closestIntersectionToStartOfLine(returnRect);

        for (Collidable c : collidables) {
            Rectangle tempRect = c.getCollisionRectangle();
            Point tempPoint = trajectory.closestIntersectionToStartOfLine(tempRect);

            if (startOfTrajectory.distance(tempPoint) < startOfTrajectory.distance(returnPoint)) {
                returnCollidable = c;
                returnPoint = tempPoint;
            }
        }

        return new CollisionInfo(returnPoint, returnCollidable);
    }

}