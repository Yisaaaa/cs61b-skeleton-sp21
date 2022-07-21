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

    private int increment(int n) {
        return Math.floorMod(n + 1, items.length);
    }

    private int decrement(int n) {
        return Math.floorMod(n - 1, items.length);
    }

    private int getFrontIndex() {
        return increment(nextFrontIndex);
    }

    private int getBackIndex() {
        return decrement(nextBackIndex);
    }

    private boolean isFull() {
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

    private T remove(int index) {
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

    private float getUsageRatio() {
        return (float) size / items.length;
    }

    public void resize() {
        if (isFull()) {
            resizeHelper(items.length * 2);
        } else if (getUsageRatio() < 0.25 && items.length >= 16) {
            resizeHelper(items.length / 2);
        }
    }

    private void resizeHelper(int amount) {
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
