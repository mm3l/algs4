/******************************************************************************
 * Compilation: javac Board.java 
 * Execution: java Board
 * Dependencies: None
 * 
 * Author: Michael Melachridis 
 * Written: 15/01/2017
 * 
 * A board of a n-by-n array of blocks
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    
    private int n = 0;
    private int[][] tiles = null;
    
    /**
     * constructs a board from an n-by-n array of blocks
     * (where blocks[i][j] = block in row i, column j)
     * 
     * @param blocks
     */
    public Board(int[][] blocks) {
        this.n = blocks.length;
        tiles = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = blocks[i][j];
            }
        }
    }

    /**
     * board dimension n
     * @return
     */
    public int dimension() {
        return n;
    }

    /**
     * the number of blocks out of place
     * @return the  number of blocks out of place
     */
    public int hamming() {
        int fixed = 0;
        int idx = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                
                if (tiles[i][j] == ++idx) {
                    fixed++;
                    System.out.println(tiles[i][j] + "|" + idx + "*");
                } 
                else {
                    System.out.println(tiles[i][j] + "|" + idx);
                }
            }
        }
        
        return ((n*n) - fixed) -1;
    }

    /**
     * Returns the sum of Manhattan distances between blocks and goal
     * @return the sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        int sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                int value = tiles[i][j];

                if (value != 0) {
                    int targetX = (value - 1) / n; // expected x-coordinate
                                                   // (row)
                    int targetY = (value - 1) % n; // expected y-coordinate
                                                   // (col)
                    int dx = i - targetX; // x-distance to expected coordinate
                    int dy = j - targetY; // y-distance to expected coordinate
                    sum += Math.abs(dx) + Math.abs(dy);
                }
            }
        }
        return sum;
    }

    /**
     * is this board the goal board?
     * @return
     */
    public boolean isGoal() {
        return false;
    }

    /**
     * Returns a board that is obtained by exchanging any pair of blocks
     * @return
     */
    public Board twin() {
        return null;
    }

    /**
     * does this board equal y?
     */
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != y.getClass()) return false;
        Board that = (Board) y;
        
        if (this.n != that.n) return false;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != that.tiles[i][j])
                    return false;
            }
        }
        
        return true;
    }

    /**
     * all neighboring boards
     * @return
     */
    public Iterable<Board> neighbors() {
        return null;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        
        StdOut.println(initial.toString());
        
        StdOut.println("Hamming  : " + initial.hamming());
        StdOut.println("Manhattan: " + initial.manhattan());
        
        // solve the puzzle
        /*Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }*/
    }
}
