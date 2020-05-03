import biuoop.DrawSurface;

public interface Sprite {
    // draw the sprite to the screen

    /**
     * yeet
     * @param d kr
     */
    void drawOn(DrawSurface d);
    // notify the sprite that time has passed
    void timePassed();
}