package flik;

import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {

    @Test
    public void sameNumberTest() {
        int i = 0;
        for (int j = 0; j < 500; j++, i++) {
            assertTrue("i should be equal to j", Flik.isSameNumber(i, j));
        }
    }

}
