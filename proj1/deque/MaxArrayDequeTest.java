package deque;
import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.*;


class CompareByStudentNumber implements Comparator<Student> {
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

class CompareByGrade implements Comparator<Student> {

    @Override
    public int compare(Student student1, Student student2) {
        if (student1.gradeLevel == student2.gradeLevel) {
            return 0;
        } else if (student1.studentNumber > student2.studentNumber) {
            return 1;
        } else {
            return -1;
        }
    }

}

public class MaxArrayDequeTest {

    @Test
    public void maxNoArgumentTest() {

        Comparator<Student> compareBySN = new CompareByStudentNumber();
        Comparator<Student> compareByG = new CompareByGrade();
        MaxArrayDeque<Student> a = new MaxArrayDeque<>(compareBySN);

        a.addLast(new Student("Grey", 11, 3));
        a.addLast(new Student("Nico", 12, 1234));
        a.addLast(new Student("Uto", 4, 6));

        int expected = 1234;
        int actual = a.max().studentNumber;

        assertEquals(actual, expected);

        expected = 12;
        actual = a.max(compareByG).gradeLevel;

    }

}
