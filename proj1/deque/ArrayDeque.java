package deque;

public class ArrayDeque<Bacon> {

    private Bacon bacons[];
    private int initialLength = 8;
    private int nextFrontIndex;
    private int nextBackIndex;
    private int size;


    public ArrayDeque() {
        bacons = (Bacon[]) new Object[initialLength];
        nextFrontIndex = 1;
        nextBackIndex = 2;
        size = 0;
    }

    public ArrayDeque(Bacon bacon) {
        this();
        this.addFirst(bacon);
    }

    public int increment(int n) {
        return Math.floorMod(n + 1, bacons.length);
    }

    public int decrement(int n) {
        return Math.floorMod(n - 1, bacons.length);
    }

    public int getFrontIndex() {
        return increment(nextFrontIndex);
    }

    public int getBackIndex() {
        return decrement(nextBackIndex);
    }

    public boolean isFull() {
        return size == bacons.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Bacon get(int index) {
        int circularIndex = Math.floorMod(getFrontIndex() + index, bacons.length);
        return bacons[circularIndex];
    }

    public void printDeque() {
        for (int i = 0; i > size; i++) {
            if (i == size - 1) {
                System.out.println(get(i).toString());
                break;
            }
            System.out.print(get(i).toString() + " ");
        }
    }




    /*
    * In add operations:
    *   front decrements
    *   back increments
    * */

    public void addFirst(Bacon bacon) {
        if (isFull()) {
            resize();
        }

        bacons[nextFrontIndex] = bacon;
        nextFrontIndex = decrement(nextFrontIndex);
        size += 1;
    }

    public void addLast(Bacon bacon) {
        if (isFull()) {
            resize();
        }

        bacons[nextBackIndex] = bacon;
        nextBackIndex = increment(nextBackIndex);
        size += 1;
    }

    /* In remove operations:
    *       nextFrontIndex increments
    *       nextBackIndex decrements
    * */

    public Bacon remove(int index) {
        Bacon removed = bacons[index];
        size -= 1;
        bacons[index] = null;
        resize();
        return removed;
    }

    public Bacon removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Bacon removed = remove(getFrontIndex());
        nextFrontIndex = increment(nextFrontIndex);
        return removed;
    }

    public Bacon removeLast() {
        if (isEmpty()) {
            return null;
        }
        Bacon removed = remove(getBackIndex());
        nextBackIndex = decrement(nextBackIndex);
        return removed;
    }

    public float getUsageRatio() {
        return (float) size / bacons.length;
    }

    public void resize() {
        if (isFull()) {
            resizeHelper(bacons.length * 2);
        } else if (getUsageRatio() < 0.25 && bacons.length >= 16) {
            resizeHelper(bacons.length / 2);
        }
    }

    public void resizeHelper(int size) {
        Bacon[] newArray = (Bacon[]) new Object[size];

        if (getFrontIndex() > getBackIndex()) {
            int startingSrcPos = getFrontIndex() - bacons.length;
            System.arraycopy(bacons, getFrontIndex(), newArray, 2, startingSrcPos);
            System.arraycopy(bacons, 0, newArray, 2 + startingSrcPos, getBackIndex() + 1);
        } else {
            System.arraycopy(bacons, getFrontIndex(), newArray, 2, size);
        }

        bacons = newArray;
        nextFrontIndex = 1;
        nextBackIndex = getFrontIndex() + size;
    }






    public static void main(String[] args) {
    }
}