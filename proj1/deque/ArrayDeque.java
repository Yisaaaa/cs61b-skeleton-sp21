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
        if (isFull()) {
            System.out.println("array is full");
            return;
        }
        else if (nextFirst == -1) {
            nextFirst = maxIndex;
        }
        items[nextFirst] = x;
        nextFirst --;
        size ++;
    }
}
