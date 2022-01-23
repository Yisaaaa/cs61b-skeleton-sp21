package deque;


public class ArrayDeque<Bacon> {
    // Todo Do everything in the deque APi

    public int nextFirst;
    public int nextLast;
    private int size;
    private Bacon[] items;
    private int maxIndex;

    public ArrayDeque() {
        items = (Bacon[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
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

    /* addLast is sorta backwards in circular array. **/
    public void addFirst(Bacon x) {
        if (isFull()) {
            System.out.println("Array is full");
        } else {
            items[nextFirst] = x;
            size++;
            if (nextFirst == 0) {
                nextFirst = items.length - 1;
            } else {
                nextFirst--;
            }
        }
    }

    public void addLast(Bacon x) {
        if (isFull()) {
            System.out.println("Array is full");
        } else {
            items[nextLast] = x;
            size++;
            if (nextLast == items.length - 1) {
                nextLast = 0;
            } else {
                nextLast++;
            }
        }
    }

    public Bacon remove(int index) {
        Bacon removed = items[index];
        items[index] = null;
        return removed;
    }

    public Bacon removeFirst() {
        if (items[0] == null) {
            return null;
        }
        return removeFirstHelper();
    }

    public Bacon removeFirstHelper() {
        Bacon removed;

        if (nextFirst == maxIndex) {
            removed = remove(0);
            nextFirst = 0;
        } else {
            removed =  remove(nextFirst + 1);
            nextFirst++;
        }
        return removed;
    }

    public Bacon removeLast() {
        if (items[1] == null) {
            return null;
        }
        return removeLastHelper();
    }

    public Bacon removeLastHelper() {
        Bacon removed;

        if (nextLast == 0) {
            removed = remove(maxIndex);
            nextLast = maxIndex;
        } else {
            removed = remove(nextLast - 1);
            nextLast --;
        }
        return removed;
    }

}
