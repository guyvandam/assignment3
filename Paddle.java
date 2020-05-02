import biuoop.DrawSurface;

public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;

    public void moveLeft() {

    }

    public void moveRight() {
    }

    // Sprite
    public void timePassed() {
    }

    public void drawOn(DrawSurface d) {
    }

    // Collidable
    public Rectangle getCollisionRectangle() {
        return null;
    }

    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        return null;
    }

    // Add this paddle to the game.
    public void addToGame(Game g) {
    }
}