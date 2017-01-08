/******************************************************************************
 *  Compilation:  javac Deque.java
 *  Execution:    java Deque
 *  
 *  Author:        Michael Melachridis
 *  Written:       07/01/2017
 *  
 *  A double-ended queue or deque implemented as a Double Linked-List
 *
 ******************************************************************************/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node head = null;
    private Node last = null;
    private int length = 0;

    /**
     * Constructs an empty queue
     */
    public Deque() {
    }

    /**
     * Is the deque empty?
     * @return <code>true</code> if it is empty and <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of items on the deque
     * @return the number of items on the deque
     */
    public int size() {
        return length;
    }

    /**
     * Adds the item to the front
     * @param item The item to be added to
     */
    public void addFirst(Item item) {
        
        if (item == null)
            throw new java.lang.NullPointerException("Item is null");
        
        Node link = new Node();
        link.item = item;
        
        if (isEmpty()) 
            last = link;
        else 
            head.prev = link;
        
        link.next = head;
        head = link;
        
        length++;
    }

    /**
     * Adds the item to the end
     * @param item The item to be added to
     */
    public void addLast(Item item) {
        
        if (item == null)
            throw new java.lang.NullPointerException("Item is null");
        
        Node link = new Node();
        link.item = item;

        if (isEmpty()) {
            last = link; // make it the last link
        } 
        else {
            last.next = link; // make link a new last link
            link.prev = last; // mark old last node as prev of new link
        }
        
        length++;

        last = link;                // point last to new last node
    }

    /**
     * Removes and returns the item from the front
     * 
     * @return the item from the front
     */
    public Item removeFirst() {
        
        if (isEmpty()) 
            throw new NoSuchElementException("Queue underflow");
            
        Item item = head.item;

        if (head.next == null) {
            last = null;
        } 
        else {
            head.next.prev = null;
        }
         
        head = head.next;
        
        length--;
        
        return item;
    }

    /**
     * Removes and returns the item from the end
     * 
     * @return the item from the end
     */
    public Item removeLast() {

        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");

        Item item = last.item;

        // if only one link
        if (head.next == null) {
            head = null;
        } 
        else {
            last.prev.next = null;
        }

        last = last.prev;

        length--;

        return item;
    }

    /**
     * Return an iterator over items in order from front to end
     * 
     * @return iterator over items
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    /**
     * It represents a single node of a Linked-List
     */
    private class Node {
        
        private Item item;
        private Node next;
        private Node prev;
    }
        
    private class ListIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            /* not supported */ 
            throw new UnsupportedOperationException();
        }

        public Item next() {
            
            if (!hasNext()) 
                throw new NoSuchElementException();
            
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<Integer>();
        
        dq.addFirst(1);
        dq.addFirst(2);
        dq.addFirst(3);
        dq.addLast(4);
        dq.addLast(5);
        
        System.out.println("Before: length=" + dq.size());
        for (Integer i: dq) {
            System.out.println(i);
        }
        
        dq.removeFirst();
        dq.removeFirst();
        dq.removeLast();
        
        System.out.println("After: length=" + dq.size());
        for (Integer i: dq) {
            System.out.println(i);
        }
    }
}
