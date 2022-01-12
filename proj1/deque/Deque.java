package deque;

public class Deque<T> {
    // sentinel is always 69
    IntNode sentinel = new IntNode(69, null);

    /* IntNode class */
    private class IntNode {
        T item;
        IntNode prev;
        IntNode next;

        public IntNode(T i, IntNode n) {
            item = i;
            next = n;
        }

        public IntNode(int i, IntNode n) {

        }
    }

    public Deque(T i) {
        sentinel = new IntNode(null, null);
        sentinel.next = new IntNode(i, sentinel);
        sentinel.prev = sentinel.next.next;
        sentinel.next.prev = sentinel;
    }

    public static void main(String[] args) {
        Deque<Integer> deq = new Deque<>(12);
        Deque<Integer>.IntNode pev = deq.sentinel;
    }
}

