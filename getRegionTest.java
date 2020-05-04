public class getRegionTest {
    public static int getRegion(Line edge, Point point) {
        // a<=x<b

        if (edge == null || point == null) {
            return 0;
        }
        double dist = edge.start().distance(point);
        int i = 1;
        double lengthOfRegion = 20 / 5;
//        while (i < 5 && dist >= lengthOfRegion * i) {
        while (i <= 5 && dist > lengthOfRegion * i) {
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        Line e = new Line(new Point(0, 0), new Point(20, 0));
        Point p = new Point(2, 0);

        System.out.println(getRegion(e, p));
    }
}
