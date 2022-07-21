package deque;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {


    private TNode sentinel;
    private int size = 0;

    /* TNode class */
    public class TNode {
        private T item;
        private TNode prev;
        private TNode next;

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
            return !isEmpty() && currentNode != sentinel;
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
        for (TNode node = sentinel.next; node != sentinel; node = node.next) {
            if (node.next == sentinel) {
                System.out.println(node.item);
            } else {
                System.out.print(node.item + " ");
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (other == this) {
            return true;
        } else if (other instanceof Deque<?>) {
            Deque castedOther = (Deque) other;
            if (this.size != castedOther.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!(get(i).equals(castedOther.get(i)))) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }



    public void addFirst(T item) {
        size++;
        TNode newNode = new TNode(item, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
    }

    // addLast
    public void addLast(T item) {
        size++;
        TNode newNode = new TNode(item, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
    }

    public int size() {
        return this.size;
    }

    public T removeFirst() {
        if (sentinel.next != sentinel) {
            size--;
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
            size--;
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
                index--;
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
    private T getRecursiveHelper(int index, TNode node) {
        if (index == 0) {
            return node.item;
        } else {
            return getRecursiveHelper(index - 1, node.next);
        }
    }

}
