/******************************************************************************
 * Compilation: javac Solver.java 
 * Execution: java Solver
 * Dependencies: Board.java
 * 
 * Author: Michael Melachridis 
 * Written: 15/01/2017
 * 
 * A board of a n-by-n array of blocks
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {

    /**
     * finds a solution to the initial board (using the A* algorithm)
     * 
     * @param initial
     */
    public Solver(Board initial) {

    }

    /**
     * is the initial board solvable?
     * 
     * @return
     */
    public boolean isSolvable() {
        return false;
    }

    /**
     * min number of moves to solve initial board; -1 if unsolvable
     * 
     * @return
     */
    public int moves() {
        return 0;
    }

    /**
     * sequence of boards in a shortest solution; null if unsolvable
     * 
     * @return
     */
    public Iterable<Board> solution() {
        return null;
    }

    public static void main(String[] args) {
        // solve a slider puzzle (given below)
    }
}
