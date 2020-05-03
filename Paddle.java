import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private java.awt.Color color;
    private GameEnvironment gameEnvironment;

    public Paddle(KeyboardSensor keyboard, Rectangle rect, Color color) {
        this.keyboard = keyboard;
        this.rect = rect;
        this.color = color;
    }

    public Rectangle getRect() {
        return rect;
    }

    public KeyboardSensor getKeyboard() {
        return keyboard;
    }

    public void moveLeft() {

//        for(Collidable c:)
    }

    public void moveRight() {
    }

    // Sprite
    @Override
    public void timePassed() {
        if(this.getKeyboard().isPressed(KeyboardSensor.LEFT_KEY)){
            moveLeft();
        }else if(this.getKeyboard().isPressed(KeyboardSensor.RIGHT_KEY)){
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (d != null) {
            d.setColor(this.color);
            d.fillRectangle((int) this.getRect().getUpperLeft().getX(), (int) this.getRect().getUpperLeft().getY(),
                    (int) this.getRect().getWidth(), (int) this.getRect().getHeight());
        }
    }


    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        return null;
    }

    // Add this paddle to the game.
    public void addToGame(Game g) {
        if (g != null) {
            g.addSprite(this);
            g.addCollidable(this);
        }
    }
}