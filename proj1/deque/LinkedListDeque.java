package deque;


import java.util.function.ObjIntConsumer;

public class LinkedListDeque<T> {

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

    /**
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
        TNode newNode = new TNode(item, sentinel.prev, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
    }
    */

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

    // isEmpty
    public boolean isEmpty() {
        return sentinel.next.equals(sentinel);
    }

    // size
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("{");
        for (TNode node = sentinel.next; node.item != null; node = node.next) {
            if (node.next.item == null) {
                string.append(node.item.toString() + "}");
            } else {
                string.append(node.item.toString() + ", ");
            }
        }
        return string.toString();
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

    public static void main(String[] args) {
        LinkedListDeque<Integer> deq = new LinkedListDeque<>(12);
        deq.addLast(34);
        deq.addLast(23);
        deq.addLast(72);
        System.out.println(deq);

        LinkedListDeque<Integer> deq2 = LinkedListDeque.of(12, 34, 23, 72);
        System.out.println(deq2);
    }
}
