package deque;

import java.util.Comparator;

public class MaxArrayDeque<Bacon> extends ArrayDeque<Bacon> {

    private Comparator<Bacon> comparator;

    public MaxArrayDeque(Comparator<Bacon> c) {
        comparator = c;
    }

    public MaxArrayDeque(Comparator<Bacon> c, Bacon bacon) {
        super(bacon);
        comparator = c;
    }

    public Bacon max(Comparator<Bacon> c) {
        Bacon max = get(0);

        for (int i = 1; i < size; i += 1) {
            Bacon currentBacon = get(i);
            if (c.compare(max, currentBacon) < 0) {
                max = currentBacon;
            }
        }
        return max;
    }

    public Bacon max() {
        return max(comparator);
    }

}