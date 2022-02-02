package deque;



public class ArrayDeque<Bacon> {
    // Todo Do everything in the deque APi

    public int nextFront;
    public int nextRear;
    private int size;
    private Bacon[] items;
    private int maxIndex;

    public ArrayDeque(Bacon x) {
        initializeArrayDeque();
        this.nextFront --;
        this.nextRear ++;
        this.items[0] = x;
        size ++;
    }

    public ArrayDeque() {
        initializeArrayDeque();
    }

    public void initializeArrayDeque() {
        items = (Bacon[]) new Object[8];
        size = 0;
        nextFront = 0;
        nextRear = 0;
        maxIndex = items.length - 1;
    }

    public Boolean isEmpty() {
        return size == 0;
    }

    public Bacon get(int index) {
        return items[index];
    }

    public String stringArrayDeque() {
        String string = "";
        int counter = 0;
        while (counter < items.length) {
            if (counter == maxIndex && items[maxIndex] != null) {
                string += items[counter];
            } else if (items[counter] == null) {
                string += "";
            } else {
                string += items[counter] + " ";
            }
            counter++;
        }
        return string;
    }

    public int size() {
        return size;
    }

    public Boolean isFull() {
        return size == items.length;
    }

    public void addFirst(Bacon x) {
        if (nextFront == -1) {
            nextFront = maxIndex;
        }
        if (isFull()) {
            System.out.println("array is full");
            return;
        }
        if (nextFront == 0) {
            nextRear ++;
        }
        items[nextFront] = x;
        nextFront --;
        size ++;
    }

    public void addLast(Bacon x) {
        if (nextRear > maxIndex) {
            nextRear = 0;
        }
        if (isFull()) {
            System.out.println("array is full");
            return;
        }
        if (nextRear == 0) {
            nextFront --;
        }
        items[nextRear] = x;
        nextRear ++;
        size ++;
    }

    private Bacon remove(int index) {
        Bacon removedItem = this.items[index];
        this.items[index] = null;
        return removedItem;
    }

    public Bacon removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Bacon removedItem;
        if (nextFront == maxIndex) {
            removedItem = remove(0);
            nextFront = 0;
        } else {
            removedItem = remove(nextFront + 1);
            nextFront ++;
        }
        size --;
        return removedItem;
    }

    public Bacon removeLast() {
        if (isEmpty()) {
            return null;
        }
        Bacon removedItem ;
        if (nextRear == 0) {
            removedItem = remove(maxIndex);
            nextRear = maxIndex;
        } else {
            removedItem = remove(nextRear - 1);
            nextRear --;
        }
        size --;
        return removedItem;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque<>(2);
        a.addFirst(12);
        a.addLast(23);
        a.removeFirst();
        a.addFirst(17);
        a.addLast(2);
        a.removeFirst();
        a.addLast(45);
        a.addLast(4);
        a.addLast(7);
        a.addLast(11);
    }
}
