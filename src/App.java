

import java.util.LinkedList;
import java.util.ListIterator;

import static org.junit.Assert.assertEquals;

public class App {
    public static void main(String[] args) {
        final LinkedList<String> testInstance = new LinkedList<>() {
            {
                add("a");
                add("b");
                add("c");
                add("d");
                add("e");
            }
        };
        final ListIterator<String> listIterator = testInstance.listIterator(5);
        assertEquals(5, listIterator.nextIndex());
        assertEquals("e", listIterator.previous());
        assertEquals(4, listIterator.nextIndex());
        assertEquals("e", listIterator.next());
        assertEquals(5, listIterator.nextIndex());
    }
}
