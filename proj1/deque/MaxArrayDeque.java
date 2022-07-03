
/*
package deque;

import java.util.Comparator;

public class MaxArrayDeque<Item> extends ArrayDeque<Item> {

    private Comparator<Item> comparator;

    public MaxArrayDeque(Comparator<Item> c) {
        comparator = c;
    }

    public Item max(Comparator<Item> c) {
        if (isEmpty()) {
            return null;
        }

        Item maxItem = get(0);
        for (int i = 1; i <= size() - 1; i++) {
            Item currentItem = get(i);
            if (c.compare(currentItem, maxItem) > 0) {
                maxItem = currentItem;
            }
        }
        return maxItem;
    }

    public Item max() {
        return max(this.comparator);
    }



}


 */