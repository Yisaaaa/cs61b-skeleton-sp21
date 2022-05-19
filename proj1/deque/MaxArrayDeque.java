package deque;

import java.lang.reflect.InaccessibleObjectException;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    Comparator<T> comparator;
    public class Student {

        int studentNumber, gradeLevel;
        String name;

        public Student(String n, int g, int s) {
            studentNumber = s;
            gradeLevel = g;
            name = n;
        }
    }

    public class compareByStudentNumber implements Comparator<Student> {
        @Override
        public int compare(Student student1, Student student2) {
            if (student1.studentNumber == student2.studentNumber) {
                return 0;
            } else if (student1.studentNumber > student2.studentNumber) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    public T max() {

        if (size() == 0) {
            return null;
        }

        T max = get(0);
        for (int i = 1; i < size(); i++) {
            T current = get(i);
            if (comparator.compare(current, max) == 1) {
                max = current;
            }
        }
        return max;
    }

    public T max(Comparator<T> c) {

        if (size() == 0) {
            return null;
        }

        T max = get(0);
        for (int i = 1; i < size(); i++) {
            T current = get(i);
            if (c.compare(current, max) == 1) {
                max = current;
            }
        }
        return max;
    }




}
