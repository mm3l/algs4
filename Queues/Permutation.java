/******************************************************************************
 *  Compilation:  javac Permutation.java
 *  Execution:    java Permutation
 *
 *  Author:        Michael Melachridis
 *  Written:       07/01/2017
 *  
 *  Modeling Percolation using an N-by-N grid and Union-Find data
 *  structures to determine the threshold
 *
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String[] args) {

        int k = Integer.valueOf(args[0]);
        System.out.println(k);

        Deque<String> dq = new Deque<String>();

        int i = 0;
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item != null)
                dq.addFirst(item);

            if ((!dq.isEmpty()) && (i++ < k))
                StdOut.println(dq.removeFirst());
        }
    }
}
