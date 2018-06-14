import java.util.ArrayList;

public class FastCollinearPoints {
    private Point[] points;
    private int numOfSegments = 0;

    public FastCollinearPoints(Point[] points) {
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
    }    // finds all line segments containing 4 or more points

    public int numberOfSegments() {
        return numOfSegments;
    }        // the number of line segments

    public LineSegment[] segments() {
        ArrayList<LineSegment> lines = new ArrayList<>();
        ArrayList<Double> addedSlopes = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            Point[] sortP = new Point[points.length - 1];
            double[] slopes = new double[points.length - 1];
            int index = 0;
            for (int j = 0; j < points.length; j++) {
                Point q = points[j];
                if (p.compareTo(q) != 0) {
                    slopes[index] = p.slopeTo(q);
                    sortP[index] = q;
                    index++;
                }
            }

//            StdOut.println("P: " + p.toString());
//            for(int k=0; k<sortP.length; k++) {
//                StdOut.print(sortP[k].toString() + ", ");
//            }
//            StdOut.println();
//            for(int k=0; k<slopes.length; k++) {
//                StdOut.print(slopes[k] + ", ");
//            }
//            StdOut.println();
//            StdOut.println("-----------------------------------------");

            double[] aux = new double[slopes.length];
            Point[] aux_p = new Point[sortP.length];
            sort(slopes, aux, sortP, aux_p, 0, slopes.length - 1);

//            for(int k=0; k<sortP.length; k++) {
//                StdOut.print(sortP[k].toString() + ", ");
//            }
//            StdOut.println();
//            for(int k=0; k<slopes.length; k++) {
//                StdOut.print(slopes[k] + ", ");
//            }
//            StdOut.println();

            for (int k = 0; k < slopes.length - 2; k++) {
                int count = 1;
                Point q = sortP[k];
                Point max = p;
                Point min = q;
                if (p.compareTo(q) < 0) {
                    max = q;
                    min = p;
                }
                while (k + count < slopes.length && Double.compare(slopes[k], slopes[k + count]) == 0) {
                    if (max.compareTo(sortP[k + count]) < 0) max = sortP[k + count];
                    else if (min.compareTo(sortP[k + count]) > 0) min = sortP[k + count];
                    count++;
                }
                if (count >= 3) {
                    if (lines.isEmpty()) {
                        lines.add(new LineSegment(min, max));
                        addedSlopes.add(slopes[k]);
                    } else {
                        boolean found = false;
                        for (int j = 0; j < addedSlopes.size(); j++) {
                            if (addedSlopes.contains(slopes[k])) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            lines.add(new LineSegment(min, max));
                            addedSlopes.add(slopes[k]);
                        }
                    }
                    k += count;
                }

            }
        }
        return lines.toArray(new LineSegment[numOfSegments]);
    }

    private void sort(double[] a, double[] aux, Point[] sp, Point[] aux_p, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, sp, aux_p, lo, mid);
        sort(a, aux, sp, aux_p, mid + 1, hi);
        Merge(a, aux, sp, aux_p, lo, mid, hi);
    }

    private void Merge(double[] a, double[] aux, Point[] sp, Point[] aux_p, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
            aux_p[k] = sp[k];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j];
                sp[k] = aux_p[j];
                j++;
            } else if (j > hi) {
                a[k] = aux[i];
                sp[k] = aux_p[i];
                i++;
            } else if (aux[i] <= aux[j]) {
                a[k] = aux[i];
                sp[k] = aux_p[i];
                i++;
            } else {
                a[k] = aux[j];
                sp[k] = aux_p[j];
                j++;
            }
        }
    }


}
