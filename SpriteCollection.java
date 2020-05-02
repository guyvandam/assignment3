import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

public class SpriteCollection {
    private List<Sprite> sprites;

    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        for (Sprite s : this.getSprites()) {
            s.timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.getSprites()) {
            s.drawOn(d);
        }
    }
}