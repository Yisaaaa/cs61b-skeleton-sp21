package deque;


import java.lang.reflect.Array;

public class ArrayDeque<Bacon> {
    // Todo Do everything in the deque APi

    public int nextFirst;
    public int nextLast;
    private int size;
    private Bacon[] items;
    private int maxIndex;

    public ArrayDeque(Bacon x) {
        initializeArrayDeque();
        this.nextFirst --;
        this.nextLast ++;
        this.items[0] = x;
        size ++;
    }

    public ArrayDeque() {
        initializeArrayDeque();
    }

    public void initializeArrayDeque() {
        items = (Bacon[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 0;
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
        if (nextFirst == -1) {
            nextFirst = maxIndex;
        }
        if (isFull()) {
            System.out.println("array is full");
            return;
        }
        if (nextFirst == 0) {
            nextLast ++;
        }
        items[nextFirst] = x;
        nextFirst --;
        size ++;
    }

    public void addLast(Bacon x) {
        if (nextLast > maxIndex) {
            nextLast = 0;
        }
        if (isFull()) {
            System.out.println("array is full");
            return;
        }
        if (nextLast == 0) {
            nextFirst --;
        }
        items[nextLast] = x;
        nextLast ++;
        size ++;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(12);
        a.addLast(23);
        a.addFirst(17);
        a.addLast(2);
        a.addLast(45);
        a.addFirst(1);
        a.addLast(4);
        a.addLast(7);
        a.addFirst(11);
    }
}
