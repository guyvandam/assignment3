import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;

    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    public GUI getGui() {
        return gui;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public SpriteCollection getSprites() {
        return sprites;
    }

    public GameEnvironment getEnvironment() {
        return environment;
    }

    public void addCollidable(Collidable c) {
        this.getEnvironment().addCollidable(c);
    }

    public void addSprite(Sprite s) {
        this.getSprites().addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        int GUIWidth = 800, GUIHeight = 600;
        this.setGui(new GUI("title", GUIWidth, GUIHeight));

        int blockWidth = 40, blockHeight = 30;
        java.awt.Color blockColor = Color.orange;

        int ballSize = 3, startX = 52, startY = 40, dx = 3, dy = 3;
        java.awt.Color ballColor = Color.BLACK;

        int paddleWidth = 60, paddleHeight = 10, paddleX = 50, paddleY = 330;
        java.awt.Color paddleColor = Color.gray;

        Paddle paddle = new Paddle(this.getGui().getKeyboardSensor(), new Rectangle(new Point(paddleX, paddleY), paddleWidth, paddleHeight), paddleColor, this.getEnvironment());
        paddle.addToGame(this);


        Ball ball = new Ball(new Point(startX, startY), ballSize, ballColor);
        ball.setGameEnvironment(this.getEnvironment());
        ball.setVelocity(dx, dy);
        ball.addToGame(this);

        // adds the blocks.
        for (int i = 0; i < GUIWidth; i += blockWidth) {

            for (int j = 0; j < GUIHeight; j += blockHeight) {
                if (i == 0 || i == GUIWidth - blockWidth || j == 0 || j == GUIHeight - blockHeight) {
                    Block temp = new Block(new Rectangle(new Point(i, j), blockWidth, blockHeight), blockColor);
                    temp.addToGame(this);
                }
            }
        }

    }

    // Run the game -- start the animation loop.
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;


        Sleeper sleeper = new Sleeper();

        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = this.getGui().getDrawSurface();
            this.sprites.drawAllOn(d);
            this.getGui().show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
