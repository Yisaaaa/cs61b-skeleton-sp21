package deque;

import com.sun.source.tree.LiteralTree;
import edu.princeton.cs.algs4.StdRandom;

public class ArrayDeque<Bacon> {

    Bacon[] items;
    int size;
    int nextFrontIndex;
    int nextBackIndex;

    public ArrayDeque() {
        items = (Bacon[]) new Object[8];
        size = 0;
        nextFrontIndex = 1;
        nextBackIndex = 2;
    }


    // returns a boolean if deque is empty or not
    public boolean isEmpty() {
        /** We say the deque is empty if
            1. The size is equal to 0
            2. The nextFront and nextBack indices is also
                equal to 0 **/
        return size == 0;
    }



    // Returns this.size
    public int size() {
        return this.size;
    }



    // Prints the items in the deque
    public void printDeque() {
        System.out.println(this);
    }



    // returns a boolean if deque is full
    public boolean isFull() {
        /* We say the deque if full if
        * 1. The size is equal to the items.length */
        return size == items.length;
    }






    // Returns a deque containing bacons
    public static <Bacon> ArrayDeque<Bacon> of(Bacon... bacons) {

        ArrayDeque<Bacon> newArray = new ArrayDeque<>();

        for (Bacon bacon : bacons) {
            int randomInt = StdRandom.uniform(0, 2);
            if (randomInt == 0) { // addFirst
                newArray.addFirst(bacon);
            } else {
                newArray.addLast(bacon);
            }
        }
        return newArray;
    }







    /*******************************
     *      Get Operations
     **/
    // Returns the length of deque.items
    public int getLength() {
        return this.items.length;
    }


    // Returns the item on the front of the deque
    public Bacon getFront() {
        return getByArrayIndex(nextFrontIndex + 1);
    }

    // Returns te item on the back of the deque
    public Bacon getBack() {
        return getByArrayIndex(nextBackIndex - 1);
    }

    // Returns the index of the item on the back of the deque
    public int getBackIndex() {
        return mod(nextBackIndex - 1, 8);
    }

    // Returns the index of the item on the front of the deque
    public int getFrontIndex() {
        return mod(nextFrontIndex + 1, 8);
    }

    /* Returns the nth bacon in the array
    * without regards to the circular aspect
    * of the deque i.e. get on deque.items */
    public Bacon getByArrayIndex(int index) {
        if (index > getMaxIndex() || index < 0) {
            return null;
        }
        return this.items[index];
    }

    /* Returns the index nth bacon in the deque
    * with respect to its circular nature.
    * i.e. the start or 0th index of the deque
    * is nextFrontIndex + 1.
    *  */
    public Bacon get(int index) {
        int circularIndex = mod(getFrontIndex() + index, items.length);
        return getByArrayIndex(circularIndex);
    }

    public int getMaxIndex() {
        // returns the maxIndex given by length of items - 1
        return items.length - 1;
    }




    @Override
    // Returns the string representation of current deque instance
    public String toString() {
        // Change toString so that it respects the array deque
        StringBuilder string = new StringBuilder("{");

        for (int i = 0; i < size; i += 1) {
            if (i == size - 1) {
                string.append(get(i).toString() + "}");
            } else {
                string.append(get(i).toString() + ", ");
        }
    }
        return string.toString();
    }







    // returns the value of num modulo (%) denom expression
    public static int mod(int num, int denom) {

        return ((num % denom) + denom) % denom;
    }


    // decrements the given int index by 1
    public int minusOne(int index) {
        return mod(index - 1, items.length); // e.g -1 % 8 = 7
    }


    // increments the given int index by 1
    public int addOne(int index) {
        return mod(index + 1, items.length); // e.g 8 % 8 = 0
    }


    // returns the next nextFrontIndex
    public int getNextNextFrontIndex() {
        return minusOne(nextFrontIndex);
    }


    // returns the next nextBackIndex
    public int getNextNextBackIndex() {
        return addOne(nextBackIndex);
    }




    //===============================================
    //         Add Operations
    /**
     * nextBackIndex increments
     *
     * nextFrontIndex decrements
     **/
    // Adds Bacon item to the nextFrontIndex
    public void addFirst(Bacon item) {
        if (isFull()) {
            System.out.println("deque is Full");
            return;
        } else {
            items[nextFrontIndex] = item;
            nextFrontIndex = getNextNextFrontIndex();
        }
        size += 1;
    }

    // Adds Bacon item to the nextBackIndex
    public void addLast(Bacon item) {
        if(isFull()) {
            System.out.println("deque is Full");
            return;
        } else {
            items[nextBackIndex] = item;
            nextBackIndex = getNextNextBackIndex();
        }
        size += 1;
    }




    /************************************
     *      Remove Operations
     *
     * nextBackIndex decrements
     *
     * nextFrontIndex increments
     *
     */

    // Removes and returns the item at nth index
    public Bacon remove(int index) {
        if (index < 0 || index >= items.length) {
            return null;
        }
        Bacon removed = getByArrayIndex(index);
        this.items[index] = null;
        size -= 1;
        return removed;
    }

    // Removes and returns the item on the front of the deque
    public Bacon removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Bacon removed = remove(getFrontIndex());
        nextFrontIndex = addOne(nextFrontIndex);
        return removed;
    }

    // Removes and returns the item on the back of the deque
    public Bacon removeLast() {
        if (isEmpty()) {
            return null;
        }
        Bacon removed = remove(getBackIndex());
        nextBackIndex = minusOne(nextBackIndex);
        return removed;
    }



    /*

    * Implement resize function
    * The usage ratio if less than .25 or 25% should be shrunk down
    * i.e. deque size / deque length < .25
    *
    * */


    /*****************************
    *       Resize Operations
    *
    * */

    // Resize the deque
    public void resize() {
        /* shrink down if the usage ratio is less than .25 or 25%
           of the length of the deque.items
         */
        if (((double) size / items.length) < .25) {
            resizeHelper(items.length / 2);
        } else if (isFull()) {
            resizeHelper(items.length * 2);
        }
    }

    public void resizeHelper(int amount) {
        if (amount < size) {
            System.out.println("Deque cannot be resized...");
            return;
        }
        // Check if front bacon went around the deque.items
        if (frontWentAround()) {
            copyHelperFrontWentAround(amount);
        } else if (backWentAround()) {
            copyHelperBackWentAround(amount);
        } else {
            copyHelperNoWentAround(amount);
        }
        nextFrontIndex = 0; // 0 because we copied the bacon to index 1
        nextBackIndex = size + 1;
    }















    // Returns a boolean if front bacon went around the deque.items
    public boolean frontWentAround() {
        return getFrontIndex() > getBackIndex();
    }

    // Returns a boolean if back bacon went around the deque.items
    public boolean backWentAround() {
        return getBackIndex() < getFrontIndex();
    }


    // Copies the bacon from deque.items to a new array with length amount
    public void copyHelperFrontWentAround(int amount) {
        Bacon[] largerItems = (Bacon[]) new Object[amount];
        /* There is two copy here. */
        int dest = 1;

        /* First copy -- is to copy the bacons that went around
         *  the deque.items */
        int copyLength = items.length - getFrontIndex();
        System.arraycopy(items, getFrontIndex(), largerItems, dest, copyLength);

        /* Second copy -- is to copy the bacons starting from 0th index of
        *  deque.items to the new array*/
        dest += copyLength;
        copyLength = nextBackIndex;
        System.arraycopy(items, 0, largerItems, dest, copyLength);

        // Reassign the deque.items
        this.items = largerItems;
    }

    // Copies the bacon from deque.items to a new array with length amount
    public void copyHelperBackWentAround(int amount) {
        Bacon[] largerItems = (Bacon[]) new Object[amount];
        /* There is two copy here. */
        int dest = 1;
        int numOfBaconsWentAround = nextBackIndex + 1;

        /* First copy -- is to copy the bacons starting from
           frontIndex to starting from 0 index
           of the new array */
        int copyLength = size - numOfBaconsWentAround;
        System.arraycopy(items, getFrontIndex(), largerItems, dest, copyLength);

        /* Second copy -- is to copy the bacons that went around */
        dest += copyLength;
        copyLength = numOfBaconsWentAround;
        System.arraycopy(items, 0, largerItems, dest, copyLength);

        // Reassign the deque.items
        this.items = largerItems;
    }

    public void copyHelperNoWentAround(int amount) {
        Bacon[] newArray = (Bacon[]) new Object[amount];
        /* There's only one copy here.
        *  It is to copy from (frontIndex to backIndex)
        *  starting from the 1 index of the array */
        int dest = 1;
        int copyLength = size;
        System.arraycopy(items, getFrontIndex(), newArray, dest, copyLength);

        this.items = newArray;
    }











    public static void main(String[] args) {
        System.out.println(8 / 3);
    }

}