package deque;

import javax.naming.NamingEnumeration;

public class ArrayDeque<Bacon>{
    // Todo Do everything in the deque APi

    public int nextFirst;
    public  int nextLast;
    private int size;
    private Bacon[] items;

    public ArrayDeque() {
        items = (Bacon[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public Bacon get(int index) {
        return items[index];
    }

    public int size() {
        return size;
    }

    /* addLast is sorta backwards in circular array. **/
    public void addFirst(Bacon x) {
        items[nextFirst] = x;
        size ++;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
            nextLast ++;
        } else {
            nextFirst --;
        }
    }

    public addLast(Bacon x) {
        items[nextLast] = x;
        size++;
        if 
    }
}
