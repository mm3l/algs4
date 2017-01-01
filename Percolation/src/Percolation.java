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
 ******************************************************************************/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n = 0;                        // grid size 
    private boolean[][] sites;                 // false = BLOCKED or true = OPEN
    private WeightedQuickUnionUF ufp;
    private int vTop = 0;
    private int vBottom;
    private int numOfOpenSites = 0;
    
    /**
     * Creates n-by-n grid, with all sites blocked
     * @param n the number of sites
     */
    public Percolation(int n) {
        
        if (n <= 0) 
            throw new java.lang.IllegalArgumentException("Invalid grid size");
        
        this.n = n;

        sites = new boolean[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                sites[row][col] = false;
            }
        }
        
        ufp = new WeightedQuickUnionUF((n*n) + 2);
        vBottom = (n * n) + 1;
        numOfOpenSites = 0;
    }
    
    /**
     * Opens site (row, col) if it is not open already
     * 
     * @param row the row coordinate
     * @param col the column coordinate
     */
    public void open(int row, int col) {
        validateIndex(row);
        validateIndex(col);
        
        sites[row-1][col-1] = true;
        numOfOpenSites++;
        
        if (row == 1) {
            ufp.union(xyTo1D(row, col), vTop);
        }
        
        if (row == n) {
            ufp.union(xyTo1D(row, col), vBottom);
        }

        if (col > 1 && isOpen(row, col - 1)) {
            ufp.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }
        if (col < n && isOpen(row, col + 1)) {
            ufp.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }
        if (row > 1 && isOpen(row - 1, col)) {
            ufp.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }
        if (row < n && isOpen(row + 1, col)) {
            ufp.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
    }
    
    /**
     * Is site (row, col) open?
     * 
     * @param row the row coordinate
     * @param col the column coordinate
     * @return <code>true</code> if it does, and <code>false</code> otherwise
     */
    public boolean isOpen(int row, int col) {
        validateIndex(row);
        validateIndex(col);
        
        return sites[row-1][col-1];
    }
    
    /**
     * Is site (row, col) full?
     * 
     * @param row the row coordinate
     * @param col the column coordinate
     * @return <code>true</code> if it is full, and <code>false</code> otherwise
     */
    public boolean isFull(int row, int col) {
        validateIndex(row);
        validateIndex(col);
        
        return ufp.connected(xyTo1D(row, col), vTop);
    }
    
    /**
     * Returns the number of open sites.
     * 
     * @return the number of open sites
     */
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }
    
    /**
     * Does the system percolate?
     * @return <code>true</code> if it does, and <code>false</code> otherwise
     */
    public boolean percolates() {
        return ufp.connected(vTop, vBottom);
    }
       
    private void validateIndex(int i) {
        if (i <= 0 || i > n) 
            throw new IndexOutOfBoundsException("i index i=" + i + " out of bounds");    
    }
    
    private int xyTo1D(int row, int col) {    
        return ((n * (row - 1) + col));  
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Percolation");
    }
}
