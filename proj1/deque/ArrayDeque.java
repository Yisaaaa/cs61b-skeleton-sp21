package deque;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T items[];
    private int initialLength = 8;
    private int nextFrontIndex;
    private int nextBackIndex;
    private int size;

    private class ArrayDequeIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T item = get(currentIndex);
            currentIndex += 1;
            return item;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }


    public ArrayDeque() {
        items = (T[]) new Object[initialLength];
        nextFrontIndex = 1;
        nextBackIndex = 2;
        size = 0;
    }

    public ArrayDeque(T T) {
        this();
        this.addFirst(T);
    }

    public int increment(int n) {
        return Math.floorMod(n + 1, items.length);
    }

    public int decrement(int n) {
        return Math.floorMod(n - 1, items.length);
    }

    public int getFrontIndex() {
        return increment(nextFrontIndex);
    }

    public int getBackIndex() {
        return decrement(nextBackIndex);
    }

    public boolean isFull() {
        return size == items.length;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        int circularIndex = Math.floorMod(getFrontIndex() + index, items.length);
        return items[circularIndex];
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (other == this) {
            return true;
        } else if (!(other instanceof Deque<?>)) {
            return false;
        }

        Deque o = (Deque) other;
        if (o.size() != this.size) {
            return false;
        }

        return allItemsIn(o);
    }

    private boolean allItemsIn(Deque other) {
        Set<T> a = new HashSet<>();
        Set b = new HashSet();
        for (int i = 0; i < other.size(); i++) {
            b.add(other.get(i));
        }
        return a.equals(b);
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
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

    public void addFirst(T T) {
        if (isFull()) {
            resize();
        }

        items[nextFrontIndex] = T;
        nextFrontIndex = decrement(nextFrontIndex);
        size += 1;
    }

    public void addLast(T T) {
        if (isFull()) {
            resize();
        }

        items[nextBackIndex] = T;
        nextBackIndex = increment(nextBackIndex);
        size += 1;
    }

    /* In remove operations:
    *       nextFrontIndex increments
    *       nextBackIndex decrements
    * */

    public T remove(int index) {
        T removed = items[index];
        size -= 1;
        items[index] = null;
        return removed;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removed = remove(getFrontIndex());
        nextFrontIndex = increment(nextFrontIndex);
        resize();
        return removed;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T removed = remove(getBackIndex());
        nextBackIndex = decrement(nextBackIndex);
        resize();
        return removed;
    }

    public float getUsageRatio() {
        return (float) size / items.length;
    }

    public void resize() {
        if (isFull()) {
            resizeHelper(items.length * 2);
        } else if (getUsageRatio() < 0.25 && items.length >= 16) {
            resizeHelper(items.length / 2);
        }
    }

    public void resizeHelper(int amount) {
        T[] newArray = (T[]) new Object[amount];

        if (getFrontIndex() > getBackIndex()) {
            int startingSrcPos = items.length - getFrontIndex();
            System.arraycopy(items, getFrontIndex(), newArray, 2, startingSrcPos);
            System.arraycopy(items, 0, newArray, 2 + startingSrcPos, getBackIndex() + 1);
        } else {
            System.arraycopy(items, getFrontIndex(), newArray, 2, size);
        }

        items = newArray;
        nextFrontIndex = 1;
        nextBackIndex = Math.floorMod((getFrontIndex() + size), items.length);
    }

}
