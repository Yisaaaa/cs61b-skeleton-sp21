package deque;

import java.util.Iterator;

public class ArrayDeque<Bacon> implements Deque<Bacon>, Iterable<Bacon> {

    private Bacon bacons[];
    private int initialLength = 8;
    private int nextFrontIndex;
    private int nextBackIndex;
    private int size;

    private class ArrayDequeIterator implements Iterator<Bacon> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public Bacon next() {
            if (!hasNext()) {
                return null;
            }
            Bacon bacon = get(currentIndex);
            currentIndex += 1;
            return bacon;
        }
    }

    public Iterator<Bacon> iterator() {
        return new ArrayDequeIterator();
    }


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
                System.out.println(get(i));
                break;
            }
            System.out.print(get(i) + " ");
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
        return removed;
    }

    public Bacon removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Bacon removed = remove(getFrontIndex());
        nextFrontIndex = increment(nextFrontIndex);
        resize();
        return removed;
    }

    public Bacon removeLast() {
        if (isEmpty()) {
            return null;
        }
        Bacon removed = remove(getBackIndex());
        nextBackIndex = decrement(nextBackIndex);
        resize();
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

    public void resizeHelper(int amount) {
        Bacon[] newArray = (Bacon[]) new Object[amount];

        if (getFrontIndex() > getBackIndex()) {
            int startingSrcPos = bacons.length - getFrontIndex();
            System.arraycopy(bacons, getFrontIndex(), newArray, 2, startingSrcPos);
            System.arraycopy(bacons, 0, newArray, 2 + startingSrcPos, getBackIndex() + 1);
        } else {
            System.arraycopy(bacons, getFrontIndex(), newArray, 2, size);
        }

        bacons = newArray;
        nextFrontIndex = 1;
        nextBackIndex = Math.floorMod((getFrontIndex() + size), bacons.length);
    }

}