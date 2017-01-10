/******************************************************************************
 *  Compilation:  javac BruteCollinearPoints.java
 *  Execution:    java BruteCollinearPoints
 *  Dependencies: none
 *  
 *  Author:        Michael Melachridis
 *  Written:       10/01/2017
 *  
 *  Examines 4 points at a time and checks whether they all lie on the same 
 *  line segment, returning all such line segments
 *
 ******************************************************************************/
import java.util.Arrays;
import java.util.LinkedList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
public class BruteCollinearPoints {

    private LinkedList<LineSegment> lineSegments = null;
    private int numOfSegments = 0;
    
    /**
     * Finds all line segments containing 4 points
     * 
     * @param points The points to be examined
     */
    public BruteCollinearPoints(Point[] points) {
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
        
        lineSegments = new LinkedList<LineSegment>();
        findSegments(points);
    }
    
    private void findSegments(Point[] oldArray) {
        Point[] points = oldArray.clone();
        Arrays.sort(points);
        
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        if (points[i].slopeTo(points[j]) == points[j]
                                .slopeTo(points[k])
                                && points[j].slopeTo(points[k]) == points[k]
                                        .slopeTo(points[l])) {
                            lineSegments.add(new LineSegment(points[i], points[l]));
                            numOfSegments++;
                        }
                    }
                }
            }
        }
    }
        
    /**
     * Returns the number of line segments
     * 
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
