package deque;

public class ArrayDeque<Bacon> {
    // Todo Do everything in the deque APi

    public int nextFront;
    public int nextRear;
    private int size;
    private Bacon[] items;
    private int maxIndex;

    public ArrayDeque() {
        initializeArrayDeque();
    }

    public void initializeArrayDeque() {
        // items = (Bacon[]) new Object[8];
        items = (Bacon[]) new Object[8];
        size = 0;
        nextFront = 0;
        nextRear = 0;
        maxIndex = items.length - 1;
    }

    public ArrayDeque(Bacon x) {
        initializeArrayDeque();
        this.nextFront --;
        this.nextRear ++;
        this.items[0] = x;
        size ++;
    }

    public static <Egg> ArrayDeque<Egg> of(Egg... items) {
        ArrayDeque<Egg> arrayDeque = new ArrayDeque<>();
        for (Egg item : items) {
            arrayDeque.addLast(item);
        }
        return arrayDeque;
    }

    public boolean equals(Object o) {
        return (o.getClass() == ArrayDeque.class) &&
                ((ArrayDeque<?>) o).length() == length() &&
                equalsHelper((ArrayDeque) o);
    }

    public boolean equalsHelper(ArrayDeque other) {
        int index = 0;
        while (index < length()) {
            if (other.get(index) != get(index)) {
                return false;
            }
            index ++;
        }
        return true;
    }

    public Boolean isEmpty() {
        return size == 0;
    }

    public Bacon get(int index) {
        return items[(nextFront + 1 + index) % items.length];
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("{");
        for (int i = 0; i < length(); i ++) {
            if (items[i] != null) {
                if (i == length() - 1) {
                    string.append(items[i].toString() + "}");
                } else {
                    string.append(items[i].toString() + ", ");
                }
            }
        }
    return string.toString();
    }

    public int size() {
        return size;
    }

    public double getUsageRatio() {
        return size() / (double) length();
    }

    public int length() {
        return items.length;
    }

    public Boolean isFull() {
        return size == items.length;
    }

    // cut the array in half if this returns true
    public boolean shouldBeShrinked() {
        return getUsageRatio() < 0.25;
    }

    public void addFirst(Bacon x) {
        if (isFull()) {
            System.out.println("array is full, resizing...");
            resizeGrow(size * 2);
        }

        if (nextFront == -1) {
            nextFront = maxIndex;
        }
        else if (nextFront == 0) {
            nextRear ++;
        }
        items[nextFront] = x;
        nextFront --;
        size ++;
    }

    public void addLast(Bacon x) {
        if (isFull()) {
            System.out.println("array is full, resizing...");
            resizeGrow(size * 2);
        }

        if (nextRear > maxIndex) {
            nextRear = 0;
        }
        else if (nextRear == 0) {
            nextFront --;
        }
        items[nextRear] = x;
        nextRear ++;
        size ++;
    }

    private Bacon remove(int index) {
        Bacon removedItem = this.items[index];
        this.items[index] = null;
        if (shouldBeShrinked()) {
            System.out.println("ArrayDeque is shrinking...");
            resizeShrink();
        }
        return removedItem;
    }

    public Bacon removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size --;
        Bacon removedItem;
        if (nextFront == maxIndex) {
            removedItem = remove(0);
            nextFront = 0;
        } else {
            removedItem = remove(nextFront + 1);
            nextFront ++;
        }
        return removedItem;
    }

    public Bacon removeLast() {
        if (isEmpty()) {
            return null;
        }
        size --;
        Bacon removedItem ;
        if (nextRear == 0) {
            removedItem = remove(maxIndex);
            nextRear = maxIndex;
        } else {
            removedItem = remove(nextRear - 1);
            nextRear --;
        }
        return removedItem;
    }

    private void resizeHelper(Bacon[] newArray) {
        // from 0 to the very rear
        System.arraycopy(items, 0, newArray, 0, nextRear);

        // from the back of the array to the very front
        int newMaxIndex = newArray.length - 1;
        int startIndex = 1 + newMaxIndex - (maxIndex - nextFront);
        System.arraycopy(items, nextFront + 1, newArray, startIndex , maxIndex - nextFront);
        this.items = newArray;
        nextFront = startIndex - 1;
        maxIndex = newMaxIndex;
    }

    // change all array is full condition in add method to resizing
    private void resizeGrow(int amount) {
        Bacon[] newItems = (Bacon[]) new Object[amount];
        resizeHelper(newItems);
    }

    private void resizeShrink() {
        Bacon[] smallerItems = (Bacon[]) new Object[items.length / 2];
        resizeHelper(smallerItems);
    }

    public int getMaxIndex() {
        return maxIndex;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addLast(1);
        a.addFirst(3);

    }
}
