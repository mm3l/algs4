/******************************************************************************
 *  Compilation:  javac Percolation.java
 *  Execution:    java Percolation
 *
 *  Author:        Michael Melachridis
 *  Written:       1/12/2017
 *  
 *  Modeling Percolation using an N-by-N grid and Union-Find data
 *  structures to determine the threshold
 *
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private Percolation percolation;
    private double[] thres;
    private int trials;
    
    /**
     * Perform trials independent experiments on an n-by-n grid
     * 
     * @param n the size of the grid
     * @param trials number of trials
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Given n ≤ 0 or trials ≤ 0");
        
        this.trials = trials;
        thres = new double[trials];
        
        for (int i = 0; i < thres.length; i++) {
            thres[i] = calculatedTreshold(n);
        }
    }
    
    private double calculatedTreshold(int n) {
        double counter = 0;
        int i, j;
        percolation = new Percolation(n);
        while (!percolation.percolates()) {
            i = StdRandom.uniform(n) + 1;
            j = StdRandom.uniform(n) + 1;
            if (!percolation.isOpen(i, j)) {
                counter++;
                percolation.open(i, j);
            }
        }
        return counter / (n * n);
    }
    
    /**
     * Sample mean of percolation threshold
     * @return
     */
    public double mean() {
        return StdStats.mean(thres);
    }
       
    /**
     * Sample standard deviation of percolation threshold
     * @return
     */
    public double stddev() {
        return StdStats.stddev(thres);
    }
    
    /**
     * low endpoint of 95% confidence interval
     * @return
     */
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
    }
    
    /**
     * high endpoint of 95% confidence interval
     * @return
     */
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
    }
       
    /**
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 2)
            throw new java.lang.IllegalArgumentException("Provide n=grid length T=num of trials");
        
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        
        PercolationStats percolationStats = new PercolationStats(n, t);

        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println("stddev                  = " + percolationStats.stddev());
        System.out.println("95% confidence interval = " + 
                percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());
    }
}
