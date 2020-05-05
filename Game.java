import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-03-28.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private int guiWidth;
    private int guiHeight;

    /**
     * constractur function.
     *
     * @param guiWidth  the GUI window width
     * @param guiHeight the GUI window height.
     */
    public Game(int guiWidth, int guiHeight) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.guiWidth = guiWidth;
        this.guiHeight = guiHeight;
    }

    /**
     * main method. creates a new Game object, initializes everything and runs the game.
     *
     * @param args the command line arguments, we have no use for them in this main method.
     */
    public static void main(String[] args) {
        Game game = new Game(800, 600);
        game.initialize();
        game.run();
    }

    /**
     * @return an integer. the GUI width.
     */
    public int getGuiWidth() {
        return guiWidth;
    }

    /**
     * @return an integer. the GUI height.
     */
    public int getGuiHeight() {
        return guiHeight;
    }

    /**
     * @return a GUI object.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * @param newGui a GUI object. the future GUI to be
     */
    public void setGui(GUI newGui) {
        this.gui = newGui;
    }

    /**
     * @return a spriteCollection object.
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * @return a GameEnvironment object.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * adds the input Collidable to the Collidable list of the GameEnvironment object.
     *
     * @param c a Collidable object.
     */
    public void addCollidable(Collidable c) {
        this.getEnvironment().addCollidable(c);
    }

    /**
     * adds the input Sprite to the Sprite list of the SpriteCollection object.
     *
     * @param s a Sprite object.
     */
    public void addSprite(Sprite s) {
        this.getSprites().addSprite(s);
    }

    /**
     * Initialize a new game: creates the Blocks, Balls (and Paddle) and adds them to the game.
     */
    public void initialize() {
        this.setGui(new GUI("title", getGuiWidth(), getGuiHeight()));

//        int blockWidth = 40, blockHeight = 60;
//        java.awt.Color blockColor = Color.orange;
//
//        int ballSize = 3, startX = 70, startY = 70, dx = 4, dy = 4;
//        java.awt.Color ballColor = Color.BLACK;

//        int paddleWidth = 60, paddleHeight = 10, paddleX = 50, paddleY = 530;
//        java.awt.Color paddleColor = Color.gray;
//
//        Paddle paddle = new Paddle(this.getGui().getKeyboardSensor(), new Rectangle(new Point(paddleX, paddleY),
//        paddleWidth, paddleHeight), paddleColor, this.getEnvironment());
//        paddle.addToGame(this);


//        Ball ball = new Ball(new Point(startX, startY), ballSize, ballColor);
//        ball.setGameEnvironment(this.getEnvironment());
//        ball.setVelocity(dx, dy);
//        ball.addToGame(this);

//        // adds the blocks.
//        for (int i = 0; i < GUIWidth; i += blockWidth) {
//
//            for (int j = 0; j < GUIHeight; j += blockHeight) {
//                if (i == 0 || i == GUIWidth - blockWidth || j == 0 || j == GUIHeight - blockHeight) {
//                    Block temp = new Block(new Rectangle(new Point(i, j), blockWidth, blockHeight), blockColor);
//                    temp.addToGame(this);
//                }
//            }
//        }

        int widthORHeight = this.addBorderBlocks();
        this.addBalls(widthORHeight);
        this.addPaddle(widthORHeight);
//        this.addBlocks();
    }

    public void addBlocks() {
        java.awt.Color[] colors = {Color.gray, Color.red, Color.yellow, Color.cyan, Color.pink, Color.green};

        int blockWidth = 30, blockHeight = 20;
        int startX = 100, startY = 200, c = 0;
        for (int j = startY; j < startY + colors.length * blockHeight; j += blockHeight) {
            for (int i = startX; i < this.getGuiWidth() - blockWidth; i += blockWidth) {
                Block temp = new Block(new Rectangle(new Point(i, j), blockWidth, blockHeight), colors[c]);
                temp.addToGame(this);
            }
            c++;
        }

    }

    /**
     * adds the paddle to the game.
     *
     * @param widthORHeight an integer. the width/height of the corner blocks.
     */
    public void addPaddle(int widthORHeight) {
        int paddleWidth = 60, paddleHeight = 10, paddleX = 50, paddleY = this.getGuiHeight() - widthORHeight
                - paddleHeight;
        java.awt.Color paddleColor = Color.ORANGE;

        Paddle paddle = new Paddle(this.getGui().getKeyboardSensor(), new Rectangle(new Point(paddleX, paddleY),
                paddleWidth, paddleHeight), paddleColor, this.getEnvironment());
        paddle.addToGame(this);
    }

    /**
     * adds 2 balls to the game.
     *
     * @param widthORHeight an integer. the width/height of the corner blocks.
     */
    public void addBalls(int widthORHeight) {
        int ballSize = 3, dx = 4, dy = 4;
        java.awt.Color ballColor = Color.BLACK;

        // random start points so they wont look uniform.
        Ball b1 = new Ball(new Point(widthORHeight * 2, widthORHeight * 2), ballSize, ballColor,
                new Velocity(dx, dy), this.getEnvironment());
        Ball b2 = new Ball(new Point(widthORHeight + this.getGuiWidth() / 3, widthORHeight * 3), ballSize,
                ballColor, new Velocity(dx, dy), this.getEnvironment());

        b1.addToGame(this);
        b2.addToGame(this);
    }

    /**
     * adds the border blocks to the game.
     *
     * @return an integer. the width/height of the corner blocks.
     */
    public int addBorderBlocks() {
        java.awt.Color blockColor = Color.gray;
        int widthORHeight = 20;
        Block upper = new Block(new Rectangle(new Point(0, 0), this.getGuiWidth(), widthORHeight), blockColor);
        Block lower = new Block(new Rectangle(new Point(widthORHeight, this.getGuiHeight() - widthORHeight),
                this.getGuiWidth() - 2 * widthORHeight, widthORHeight), blockColor);
        Block left = new Block(new Rectangle(new Point(0, widthORHeight), widthORHeight, this.getGuiWidth()
                - 2 * widthORHeight), blockColor);
        Block right = new Block(new Rectangle(new Point(this.getGuiWidth() - widthORHeight, widthORHeight),
                widthORHeight, this.getGuiWidth() - 2 * widthORHeight), blockColor);

        Block[] blocks = {upper, lower, right, left};
        for (Block b : blocks) {
            b.addToGame(this);
        }
        return widthORHeight;
    }

    /**
     * Run the game -- start the animation loop.
     */
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
}
