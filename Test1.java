import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;

public class Test1 {

    public static void drawAnimation() {
        GUI gui = new GUI("title", 400, 400);
        Sleeper sleeper = new Sleeper();


        Ball ball = new Ball(140, 140, 3, java.awt.Color.BLACK);
        ball.setVelocity(6, 7);
        GameEnvironment GE = new GameEnvironment();
        Block b1 = new Block(new Rectangle(new Point(0, 0), 20, 400), Color.red);
        Block b2 = new Block(new Rectangle(new Point(0, 0), 400, 20), Color.blue);
        Block b3 = new Block(new Rectangle(new Point(380, 0), 20, 400), Color.yellow);
        Block b4 = new Block(new Rectangle(new Point(0, 380), 400, 20), Color.CYAN);
        Block[] blocks = {b1,b2,b3,b4};
        for (Block b:blocks) {
            GE.addCollidable(b);
        }
        ball.setGameEnvironment(GE);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            for (Block b:blocks) {
                b.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    public static void main(String[] args) {
        drawAnimation();
    }
}
