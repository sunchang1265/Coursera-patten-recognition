import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Main {

    public static void main(String[] args) {
        // read the n points from a file
//        In in = new In(args[0]);
//        int n = in.readInt();
//        Point[] points = new Point[n];
//        for (int i = 0; i < n; i++) {
//            int x = in.readInt();
//            int y = in.readInt();
//            points[i] = new Point(x, y);
//        }

//        Point[] points = new Point[]{
//                new Point(19000, 10000),
//                new Point(18000, 10000),
//                new Point(32000, 10000),
//                new Point(21000, 10000),
//                new Point(1234, 5678),
//                new Point(14000, 1000),
//        };

        Point[] points = new Point[]{
                new Point(10000, 0),
                new Point(0, 10000),
                new Point(3000, 7000),
                new Point(7000, 3000),
                new Point(20000, 21000),
                new Point(3000, 4000),
                new Point(14000, 15000),
                new Point(6000, 7000),
        };

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        //BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        StdOut.println(collinear.segments().length);
        for (Line segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
