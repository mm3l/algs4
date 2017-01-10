/******************************************************************************
 *  Compilation:  javac FastCollinearPoints.java
 *  Execution:    java FastCollinearPoints
 *  Dependencies: none
 *  
 *  Author:        Michael Melachridis
 *  Written:       10/01/2017
 *  
 *  Finds all line segments containing 4 or more points
 *
 ******************************************************************************/

import java.util.Arrays;
import java.util.LinkedList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

    private LinkedList<LineSegment> lineSegments = null;
    private int numOfSegments = 0;
    
    /**
     * Finds all line segments containing 4 or more points
     * @param points
     */
    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new java.lang.NullPointerException();
        
        for (Point p : points) {
            if (p == null)
                throw new java.lang.NullPointerException();
        }
        
        // check for duplicates
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++)
                if (points[i].compareTo(points[j]) == 0)
                    throw new java.lang.IllegalArgumentException();
        }
        
        findSegements(points);
    }
    
    private void findSegements(Point[] oldPoints) {
        // sort in natural order by y coordinate and breaking ties by x
        // coordinate
        Point[] points = oldPoints.clone();
        Arrays.sort(points);
        // temporary list to hold segments
        lineSegments = new LinkedList<LineSegment>();
        for (int i = 0; i < points.length; i++) {
            // always preserve the previous natural order
            Point[] slopeOrder = points.clone();
            // sort points on the slope with respect to the chosen point which
            // is now treated as
            // origin, considered in
            // natural order, sort has to be stable as we require natural order
            Arrays.sort(slopeOrder, slopeOrder[i].slopeOrder());
            // will hold the slopes with respect to the chosen point, which now
            // serves as origin
            double[] slopes = new double[slopeOrder.length];
            // calculate slopes with respect to the point chosen
            for (int j = 0; j < slopeOrder.length; j++) {
                slopes[j] = points[i].slopeTo(slopeOrder[j]);
            }
            int start = 0;
            int end = start;
            while (end < slopes.length) {
                // calculate the interval until the slope is equal
                while (end < slopes.length && Double.compare(slopes[start], slopes[end]) == 0)
                    end++;
                // if we have found a set of 4 or more collinear points
                if (end - start > 2) {
                    // if the current origin is the lowest by natural order,
                    // then
                    // only it should be considered for segment, otherwise
                    // always the
                    // second in list is the lowest in natural order, as the
                    // sort is stable
                    // this prevent sub-segments to go into the list
                    if (points[i].compareTo(slopeOrder[start]) < 0) {
                        lineSegments.add(new LineSegment(points[i], slopeOrder[end - 1]));
                        numOfSegments++;
                    }
                }
                start = end;
            }
        }
    }
    
    /**
     * Returns the number of line segments.
     * @return the number of line segments
     */
    public int numberOfSegments() {
        return numOfSegments;
    }
    
    /**
     * Returns the the line segments
     * @return the line segments
     */
    public LineSegment[] segments() {
        LineSegment[] s = new LineSegment[lineSegments.size()];
        return lineSegments.toArray(s);
    }
    
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
