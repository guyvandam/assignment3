import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private java.awt.Color color;
    private GameEnvironment gameEnvironment;
    private int numOfRegions;
    private int partOfWidth;

    public Paddle(KeyboardSensor keyboard, Rectangle rect, Color color, GameEnvironment gameEnvironment) {
        this.keyboard = keyboard;
        this.rect = rect;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
        this.numOfRegions = 5;
        this.partOfWidth = 6;
    }

    public int getNumOfRegions() {
        return numOfRegions;
    }

    public int getPartOfWidth() {
        return this.partOfWidth;
    }

    public Rectangle getRect() {
        return rect;
    }

    public KeyboardSensor getKeyboard() {
        return keyboard;
    }

    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }


    public void moveLeft() {
        Point upperLeft = this.getRect().getUpperLeft(),
                oneStep = new Point(upperLeft.getX() - this.getRect().getWidth() / this.getPartOfWidth(), upperLeft.getY());

        Line trajectory = new Line(upperLeft, oneStep);
        CollisionInfo closestCollision = this.getGameEnvironment().getClosestCollision(trajectory, this);
        if (closestCollision == null) {
            this.getRect().setUpperLeft(trajectory.end());
        } else {
            this.getRect().setUpperLeft(closestCollision.collisionPoint());
        }
    }

    public void moveRight() {
        Point upperRight = this.getRect().getUpperRight(), upperLeft = this.getRect().getUpperLeft(),
                oneStepR = new Point(upperRight.getX() + this.getRect().getWidth() / this.getPartOfWidth(), upperRight.getY()),
                oneStep = new Point(upperLeft.getX() + this.getRect().getWidth() / this.getPartOfWidth(), upperLeft.getY());

        Line trajectory = new Line(upperRight, oneStepR);

        CollisionInfo closestCollision = this.getGameEnvironment().getClosestCollision(trajectory, this);
        if (closestCollision == null) {
            this.getRect().setUpperLeft(oneStep);
        } else {
            this.getRect().setUpperLeft(new Point(closestCollision.collisionPoint().getX() -
                    this.getRect().getWidth(), closestCollision.collisionPoint().getY()));
        }
    }

    // Sprite
    @Override
    public void timePassed() {
        if (this.getKeyboard().isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (this.getKeyboard().isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        } else {
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
        if (collisionPoint == null || currentVelocity == null) {
            return null;
        }
        Line right = this.getCollisionRectangle().getRightEdge();
        Line left = this.getCollisionRectangle().getLeftEdge();
        Line upper = this.getCollisionRectangle().getUpperEdge();
        Line lower = this.getCollisionRectangle().getLowerEdge();

        if (left.isInLine(collisionPoint) || right.isInLine(collisionPoint)) {

            currentVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }

        if (upper.isInLine(collisionPoint) || lower.isInLine(collisionPoint)) {
            Line edge = upper.isInLine(collisionPoint) ? upper : lower;
            int region = this.getRegion(edge, collisionPoint);
            double speed = Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx() +
                    currentVelocity.getDy() * currentVelocity.getDy());

            switch (region) {
                case 1:
                    currentVelocity = Velocity.fromAngleAndSpeed(300, speed);
                    break;
                case 2:
                    currentVelocity = Velocity.fromAngleAndSpeed(330, speed);
                    break;
                case 3:
                    currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                    break;
                case 4:
                    currentVelocity = Velocity.fromAngleAndSpeed(30, speed);
                    break;
                case 5:
                    currentVelocity = Velocity.fromAngleAndSpeed(60, speed);
                    break;
                default:
                    System.out.println("ERROR");
            }
        }
        return currentVelocity;
    }

    public int getRegion(Line edge, Point point) {
        if (edge == null || point == null) {
            return 0;
        }
        double dist = edge.start().distance(point);
        int i = 1;
        double lengthOfRegion = this.getRect().getWidth() / this.getNumOfRegions();
//        while (i < numOfRegions && dist >= lengthOfRegion * i) {
        while (i <= this.getNumOfRegions() && dist > lengthOfRegion * i) {
            i++;
        }
        System.out.println(i);
        return i;
    }

    // Add this paddle to the game.
    public void addToGame(Game g) {
        if (g != null) {
            g.addSprite(this);
            g.addCollidable(this);
        }
    }
}