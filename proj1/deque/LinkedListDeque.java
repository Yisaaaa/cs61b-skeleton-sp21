package deque;

import jh61b.junit.In;

public class LinkedListDeque<T> {
    // sentinel is always 69
    public IntNode sentinel;
    private int size = 0;

    /* IntNode class */
    public class IntNode {
        public T item;
        public IntNode prev;
        public IntNode next;

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
        IntNode newNode = new IntNode(item, sentinel.prev, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
    }

    // addLast
    public void addLast(T item) {
        size ++;
        IntNode newNode = new IntNode(item, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
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

    public void printDeque() {
        System.out.println(printDequeHelper());
    }
    public String printDequeHelper() {
        String stringDeque = "";
        IntNode currNode = sentinel.next;
        while (currNode != sentinel.prev) {
            stringDeque += currNode.item + " ";
            currNode = currNode.next;
        }
        return stringDeque + currNode.item;
    }

    public T removeFirst() {
        if (sentinel.next != sentinel) {
            size --;
            return removeFirstHelper();
        } else {
            return null;
        }
    }
    public T removeFirstHelper() {
        IntNode nextAfterRemoved = sentinel.next.next;
        IntNode removedNode = sentinel.next;
        sentinel.next = nextAfterRemoved;
        nextAfterRemoved.prev = sentinel;
        return removedNode.item;
    }

    public T removeLast() {
        if (sentinel.prev != sentinel) {
            size --;
            return removeLastHelper();
        }else {
            return null;
        }
    }
    public T removeLastHelper() {
        IntNode lastAfterRemoved = sentinel.prev.prev;
        IntNode removedNode = sentinel.prev;
        sentinel.prev = lastAfterRemoved;
        lastAfterRemoved.next = sentinel;
        return removedNode.item;
    }

    public T get(int index) {
        if (index >= this.size || index < 0) {
            return null;
        } else {
            IntNode currNode = sentinel;
            while (index >= 0) {
                currNode = currNode.next;
                index --;
            }
            return currNode.item;
        }
    }

    public T getRecursive(int index) {
        if (index >= this.size() || index < 0) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }
    public T getRecursiveHelper(int index, IntNode node) {
        if (index == 0) {
            return node.item;
        } else {
            return getRecursiveHelper(index - 1, node.next);
        }
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> deq = new LinkedListDeque<>(12);
        deq.addFirst(34);
        deq.addFirst(23);
        deq.addFirst(72);
    }
}
