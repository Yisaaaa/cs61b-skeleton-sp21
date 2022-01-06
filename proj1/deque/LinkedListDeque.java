package deque;

public class LinkedListDeque<T> {
    // sentinel is always 69
    IntNode sentinel;

    /* IntNode class */
    private class IntNode {
        T item;
        IntNode prev;
        IntNode next;

        public IntNode(T i, IntNode n) {
            item = i;
            next = n;
        }
    }

    public LinkedListDeque(T i) {
        sentinel = new IntNode(null, null);
        sentinel.next = new IntNode(i, sentinel);
        sentinel.prev = sentinel.next.next;
        sentinel.next.prev = sentinel;
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> deq = new LinkedListDeque<>(12);
        LinkedListDeque<Integer>.IntNode pev = deq.sentinel;
    }
}
