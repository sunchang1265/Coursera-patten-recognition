import java.util.ArrayList;

public class BruteCollinearPoints {
    private Point[] points;
    private int numOfSegments = 0;

    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();
        else {
            for (int i = 0; i < points.length; i++) {
                if (points[i] == null)
                    throw new IllegalArgumentException();
                for (int j = i + 1; j < points.length; j++) {
                    if (points[i].compareTo(points[j]) == 0)
                        throw new IllegalArgumentException();
                }
                this.points[i] = points[i];
            }
        }
    }    // finds all line segments containing 4 points

    public int numberOfSegments() {
        return numOfSegments;
    }        // the number of line segments

    public LineSegment[] segments() {
        ArrayList<LineSegment> al = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                Point p = points[i];
                Point q = points[j];
                for (int k = j + 1; k < points.length; k++) {
                    Point r = points[k];
                    if (p.slopeOrder().compare(q, r) == 0) {
                        for (int l = k + 1; l < points.length; l++) {
                            Point s = points[l];
                            if (p.slopeOrder().compare(q, s) == 0) {
                                //found collinear points
                                Point min_pq = p.compareTo(q) < 0 ? p : q;
                                Point min_rs = r.compareTo(s) < 0 ? r : s;
                                Point min = min_pq.compareTo(min_rs) < 0 ? min_pq : min_rs;
                                Point max_pq = p.compareTo(q) > 0 ? p : q;
                                Point max_rs = r.compareTo(s) > 0 ? r : s;
                                Point max = max_pq.compareTo(max_rs) > 0 ? max_pq : max_rs;
                                al.add(new LineSegment(min, max));
                                numOfSegments++;
                                //lines[numOfSegments++] = new Line(min, max);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return al.toArray(new LineSegment[al.size()]);
    }                // the line segments
}
