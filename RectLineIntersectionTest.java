import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.Random;

public class RectLineIntersectionTest {

    /**
     * generates a random line (and by that I mean 2 Points which are 2 x and y pairs) in the bounds of the GUI frame.
     * (400by300 [pixels I guess])
     *
     * @return a Line object. a random line.
     */
    public Line generateRandomLine() {
        Random rand = new Random();

        // all x and y values are in the bounds of the GUI window size.
        int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y1 = rand.nextInt(300) + 1; // get integer in range 1-300

        int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y2 = rand.nextInt(300) + 1; // get integer in range 1-300

//        return new Line(140, 51, 150, 300);
        return new Line(x1, y1, x2, y2);
    }


    public Rectangle generateRandomRectangle() {
        Random rand = new Random();
        int x1 = rand.nextInt(300) + 1; // get integer in range 1-400
        int y1 = rand.nextInt(200) + 1; // get integer in range 1-300

//        int x1 = 100;
//        int y1 = 100;

//        int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
//        int y2 = rand.nextInt(300) + 1; // get integer in range 1-300

        return new Rectangle(new Point(x1, y1), 50, 40);

    }

    public void drawRectangle(Rectangle r, DrawSurface d) {
        if (r != null && d != null) {
            d.setColor(Color.blue);
            d.fillRectangle((int) r.getUpperLeft().getX(), (int) r.getUpperLeft().getY(), (int) r.getWidth(),
                    (int) r.getHeight());
        }
    }

    /**
     * draws the input line on the input GUI-DrawSurface object.
     *
     * @param l a line. the line we want to draw.
     * @param d a DrawSurface object. the surface we want to draw on.
     */
    public void drawLine(Line l, DrawSurface d) {
        if (l != null && d != null) {
            d.setColor(Color.BLACK);
            d.drawLine((int) (l.start().getX()), (int) (l.start().getY()), (int) (l.end().getX()),
                    (int) (l.end().getY()));
            drawStartPoint(l, d);
        }
    }


    public void drawIntersectionPoint(Line l1, Rectangle r, DrawSurface d) {
        if (l1 != null && r != null && d != null) {
            Point intersectionPoint = l1.closestIntersectionToStartOfLine(r);
            if (null != intersectionPoint) {
                System.out.println("+1");
                d.setColor(Color.RED);
                d.fillCircle((int) (intersectionPoint.getX()), (int) (intersectionPoint.getY()), 3);
            }
        }
    }

    public void drawStartPoint(Line l, DrawSurface d) {
        if (null != l.start() && d != null) {
            d.setColor(Color.CYAN);
            d.fillCircle((int) (l.start().getX()), (int) (l.start().getY()), 3);
        }
    }


    /**
     * draws 10 random lines, their middle and intersection points with each other.
     * <p>
     * generate an array of random line with the function above. draws each line and it's middle point and the
     * intersection points of lines who intersect.
     * </p>
     */
    public void drawRandomLines() {
        GUI gui = new GUI("Random Circles Example", 400, 300);
        DrawSurface d = gui.getDrawSurface();

        int numOfLines = 3;

        Line[] lineArray = new Line[numOfLines];
        Rectangle[] rectangleArray = new Rectangle[numOfLines];

        for (int i = 0; i < numOfLines; i++) {
            lineArray[i] = generateRandomLine();
            rectangleArray[i] = generateRandomRectangle();
            drawRectangle(rectangleArray[i], d);
            drawLine(lineArray[i], d);
            System.out.print("start:" + lineArray[i].start().getX() + "," + lineArray[i].start().getY());
            System.out.println(" end:" + lineArray[i].end().getX() + "," + lineArray[i].end().getY());
//            drawIntersectionPoint(lineArray[i], rectangleArray[i], d);
        }

//        int i = 0;
        for (int i = 0; i < numOfLines; i++) {
            for (int j = 0; j < numOfLines; j++) {
                drawIntersectionPoint(lineArray[i], rectangleArray[j], d);
            }
        }

        gui.show(d);

    }


    public static void main(String[] args) {
        RectLineIntersectionTest e = new RectLineIntersectionTest();
        e.drawRandomLines();
    }

}
