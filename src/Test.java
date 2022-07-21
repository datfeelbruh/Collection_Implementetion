import java.util.Collection;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class AppTest {
    final Collection<Integer> testInstance = new ArrayCollection<>();
    @Test
    public void testRetainAll3() {
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(6);
        testInstance.add(7);
        testInstance.add(8);
        testInstance.add(9);

        testInstance2.add(3);
        testInstance2.add(4);
        testInstance2.add(5);
        testInstance2.add(6);

        testInstance.retainAll(testInstance2);

        assertEquals(2, testInstance.size());
        assertTrue(testInstance.contains(3));
        assertFalse(testInstance.contains(4));
        assertFalse(testInstance.contains(5));
        assertTrue(testInstance.contains(6));
    }

    @Test
    public void testRetainAll2() {
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);
        testInstance.add(6);
        testInstance.add(7);
        testInstance.add(8);
        testInstance.add(9);

        testInstance2.add(3);
        testInstance2.add(4);
        testInstance2.add(5);
        testInstance2.add(6);

        testInstance.retainAll(testInstance2);

        assertEquals(4, testInstance.size());
        assertTrue(testInstance.contains(3));
        assertTrue(testInstance.contains(4));
        assertTrue(testInstance.contains(5));
        assertTrue(testInstance.contains(6));
    }

    @Test
    public void testRetainAll1() {
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(3);
        testInstance.add(3);
        testInstance.add(6);
        testInstance.add(7);
        testInstance.add(8);
        testInstance.add(9);

        testInstance2.add(3);
        testInstance2.add(4);
        testInstance2.add(5);
        testInstance2.add(6);

        testInstance.retainAll(testInstance2);

        assertEquals(4, testInstance.size());
        assertTrue(testInstance.contains(3));
        assertTrue(testInstance.contains(6));
    }
}
