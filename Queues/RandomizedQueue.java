/******************************************************************************
 *  Compilation:  javac Queue.java
 *  Execution:    java Queue
 *  
 *  Author:        Michael Melachridis
 *  Written:       07/01/2017
 *  
 *  A randomize queue or deque
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int n;

    /**
     * Constructs an empty randomized queue
     */
    public RandomizedQueue() {
        array = (Item[]) new Object[2];
        n = 0;
    }

    /**
     * Is the queue empty?
     * 
     * @return <code>true</code> it empty and <code>false</code> otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of items on the queue
     * 
     * @return the number of items on the queue
     */
    public int size() {
        return n;
    }

    /**
     * Adds an item
     * 
     * @param item
     *            the item to be added
     */
    public void enqueue(Item item) {

        if (item == null)
            throw new java.lang.NullPointerException("Item is null");

        if (n == array.length)
            resize(2 * array.length);

        array[n++] = item;
    }

    /**
     * Removes and returns a random item
     * 
     * @return returns a random item
     */
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
       
        int randomIndex = StdRandom.uniform(n);
        Item[] copy = array;
        
        Item item = array[randomIndex];
        array[randomIndex] = null;                              // to avoid loitering
        
        for (int i = randomIndex; i < n-1; i++) {
            array[i] = copy[i+1];
        }
       
        n--;
        
        // shrink size of array if necessary
        if (n > 0 && n == array.length/4) resize(array.length/2);
        return item;
    }

    /**
     * Returns (but do not remove) a random item
     * 
     * @return a random item
     */
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");

        int index = 0;
        Item item = null;
        while (item == null) {
            index = StdRandom.uniform(n);
            item = array[index];
        }       
        return array[index];
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = array[i];
        }
        array = copy;
    }

    /**
     * Returns an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int i = 0;

        public RandomizedQueueIterator() {
            i = n - 1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {

            if (!hasNext())
                throw new NoSuchElementException();

            return array[i--];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);

        System.out.println("Before: length=" + q.size());
        for (Integer i : q) {
            System.out.println(i);
        }

        System.out.println("pop: " + q.dequeue());

        System.out.println("After: length=" + q.size());
        for (Integer i : q) {
            System.out.println(i);
        }
        
        System.out.println();
        System.out.println(q.sample());
        System.out.println(q.sample());
        System.out.println(q.sample());
        System.out.println(q.sample());
    }
}
