package deque;


import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    public TNode sentinel;
    private int size = 0;

    /* TNode class */
    public class TNode {
        public T item;
        public TNode prev;
        public TNode next;

        public TNode(T i, TNode n, TNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {

        private TNode currentNode = sentinel.next;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            TNode node = currentNode;
            currentNode = currentNode.next;
            return node.item;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    public void initializedSentinel() {
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
    public boolean equals(Object o) {
        return (o.getClass() == LinkedListDeque.class) &&
                ((LinkedListDeque<?>) o).size() == this.size() &&
                equalsHelper((LinkedListDeque) o);
    }

    private boolean equalsHelper(LinkedListDeque other) {
        int index = 0;
        while (index< other.size()) {
            if (other.get(index) != this.get(index)) {
                return false;
            }
            index ++;
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
    public T removeFirstHelper() {
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
    public T removeLastHelper() {
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

    public T getRecursive(int index) {
        if (index >= this.size() || index < 0) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }
    public T getRecursiveHelper(int index, TNode node) {
        if (index == 0) {
            return node.item;
        } else {
            return getRecursiveHelper(index - 1, node.next);
        }
    }

}
