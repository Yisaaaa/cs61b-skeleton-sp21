package deque;

import jh61b.junit.In;

public class LinkedListDeque<T> {
    // sentinel is always 69
    IntNode sentinel;
    private int size = 0;

    /* IntNode class */
    public class IntNode {
        T item;
        IntNode prev;
        IntNode next;

        public IntNode(T i, IntNode n, IntNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    public void initializedSentinel() {
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel; sentinel.prev = sentinel;
    }

    public LinkedListDeque() {
        initializedSentinel();
    }

    public LinkedListDeque(T i) {
        initializedSentinel();
        this.addFirst(i);
    }

    public void addFirst(T item) {
        size ++;
        if (sentinel.next.equals(sentinel)) {
            addFirstHelper(item);
            sentinel.prev = sentinel.next;
        } else {
            addFirstHelper(item);
        }
    }

    public void addFirstHelper(T item) {
        IntNode newNode = new IntNode(item, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
    }

    // addLast
    public void addLast(T item) {
        size ++;
        IntNode newNode = new IntNode(item, sentinel.prev, sentinel);
        sentinel.prev = newNode;
    }

    // isEmpty
    public boolean isEmpty() {
        return sentinel.next.equals(sentinel);
    }

    // size
    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> deq = new LinkedListDeque<>(12);
        deq.addFirst(34);
        deq.addFirst(23);
        deq.addFirst(72);
    }
}
