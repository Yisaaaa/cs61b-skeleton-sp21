
package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public MaxArrayDeque(Comparator<T> c, T bacon) {
        this(c);
        this.addFirst(bacon);
    }

    public T max(Comparator<T> c) {
        T max = get(0);

        for (int i = 1; i < size(); i += 1) {
            T currentBacon = get(i);
            if (c.compare(max, currentBacon) < 0) {
                max = currentBacon;
            }
        }
        return max;
    }

    public T max() {
        return max(comparator);
    }

}