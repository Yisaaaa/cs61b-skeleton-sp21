package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {


    public TNode sentinel;
    private int size = 0;

    /* TNode class */
    private class TNode {
        public T item;
        public TNode prev;
        public TNode next;

        public TNode(T i, TNode n, TNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private class LinkedListIterator implements Iterator<T> {
        TNode currentNode = sentinel.next;

        @Override
        public boolean hasNext() {
            return !isEmpty() && currentNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }

            T item = currentNode.item;
            currentNode = currentNode.next;
            return item;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private void initializedSentinel() {
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel; sentinel.prev = sentinel;
    }

    public LinkedListDeque() {
        initializedSentinel();
    }

    public LinkedListDeque(T i) {
        initializedSentinel();
        this.addFirst(i);
    }

    public static <TT> LinkedListDeque<TT> of(TT... items) {
        LinkedListDeque<TT> LLD = new LinkedListDeque<>();
        for (TT item : items) {
            LLD.addLast(item);
        }
        return LLD;
    }


    @Override
    public void printDeque() {
        for (TNode node = sentinel.next; node != null; node = node.next) {
            if (node.next == null) {
                System.out.println(node.item);
            } else {
                System.out.print(node.item + " ");
            }
        }
    }

    @Override
    public boolean equals(Object other) {

        if (this == other) {
            return true;
        }
        if (!(other instanceof Deque<?>)) {
            return false;
        }
        Deque<T> o = (Deque<T>) other;
        if (size != o.size()) {
            return false;
        }
        for (int i = 0; i <= size; i++) {
            if (!(get(i).equals(o.get(i)))) {
                return false;
            }
        }
        return true;
    }


    public void addFirst(T item) {
        size ++;
        TNode newNode = new TNode(item, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
    }

    // addLast
    public void addLast(T item) {
        size ++;
        TNode newNode = new TNode(item, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
    }

    public int size() {
        return this.size;
    }

    public T removeFirst() {
        if (sentinel.next != sentinel) {
            size --;
            return removeFirstHelper();
        } else {
            return null;
        }
    }
    private T removeFirstHelper() {
        TNode nextAfterRemoved = sentinel.next.next;
        TNode removedNode = sentinel.next;
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
    private T removeLastHelper() {
        TNode lastAfterRemoved = sentinel.prev.prev;
        TNode removedNode = sentinel.prev;
        sentinel.prev = lastAfterRemoved;
        lastAfterRemoved.next = sentinel;
        return removedNode.item;
    }

    public T get(int index) {
        if (index >= this.size || index < 0) {
            return null;
        } else {
            TNode currNode = sentinel;
            while (index >= 0) {
                currNode = currNode.next;
                index --;
            }
            return currNode.item;
        }
    }

    private T getRecursive(int index) {
        if (index >= this.size() || index < 0) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }
    private T getRecursiveHelper(int index, TNode node) {
        if (index == 0) {
            return node.item;
        } else {
            return getRecursiveHelper(index - 1, node.next);
        }
    }

}
