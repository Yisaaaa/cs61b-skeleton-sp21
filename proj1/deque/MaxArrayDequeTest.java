
/*
package deque;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Comparator;


class Dog implements Comparable<Dog> {

    private static class NaturalComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.compareTo(b);
        }
    }

    private static class NameComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.name.compareTo(b.name);
        }
    }

    public static Comparator<Dog> getNaturalComparator() {
        return new NaturalComparator();
    }

    public static Comparator<Dog> getNameComparator() {
        return new NameComparator();
    }

    int size;
    String name;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    public void bark() {
        System.out.println(name + " barked!");
    }

    @Override
    public int compareTo(Dog otherDog) {
        return size - otherDog.size;
    }
}


public class MaxArrayDequeTest {

    @Test
    public void maxNoArgumentTest() {
        MaxArrayDeque<Dog> dogs = new MaxArrayDeque<>(Dog.getNaturalComparator());
        dogs.addLast(new Dog("Cadell", 8));
        dogs.addLast(new Dog("Grey", 2));
        dogs.addLast(new Dog("Nico", 5));
        Dog maxDog = dogs.max();

        assertEquals("Cadell", maxDog.name);

    }

    @Test
    public void maxWithArgumentTest() {
        MaxArrayDeque<Dog> dogs = new MaxArrayDeque<>(Dog.getNaturalComparator());
        dogs.addLast(new Dog("Cadell", 8));
        dogs.addLast(new Dog("Grey", 2));
        dogs.addLast(new Dog("Nico", 5));
        Dog maxDog = dogs.max(Dog.getNameComparator());

        assertEquals("Nico", maxDog.name);
    }

}
*/