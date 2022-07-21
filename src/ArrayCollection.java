
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayCollection<T> implements Collection<T> {

    private T[] array = (T[]) new Object[1];

    private int size;

    @Override
    public final int size() {
        return this.size;
    }

    @Override
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public final boolean contains(final Object o) {
        for (int i = 0; i < size(); i++) {
            if (array[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public final Iterator<T> iterator() {
        return new ElementsIterator();
    }

    @Override
    public final Object[] toArray() {
        final T[] newArray = (T[]) new Object[size];
        System.arraycopy(array, 0, newArray, 0, size());
        return newArray;
    }

    @Override
    public final <T1> T1[] toArray(T1[] a) {
        return (T1[]) toArray();
    }

    @Override
    public final boolean add(final T t) {
        if (array.length == size) {
            final T[] oldArray = array;
            array = (T[]) new Object[size * 2];
            System.arraycopy(oldArray, 0, array, 0, oldArray.length);
        }
        array[size++] = t;
        return true;
    }

    @Override
    public final boolean remove(final Object o) {
        for (int i = 0; i < size(); i++) {
            if (array[i].equals(o)) {
                if (i != this.size() - 1) {
                    System.arraycopy(array, i + 1, array, i, this.size() - 1 - i);
                }
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public final boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public final boolean removeAll(final Collection<?> c) {
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public final boolean retainAll(final Collection<?> c) {
        for (int i = 0; i < size(); i++) {
            if (!c.contains(array[i])) {
                this.remove(array[i--]);
            }
        }
        return true;
    }

    @Override
    public final void clear() {
        array = (T[]) new Object[1];
        size = 0;
    }

    private class ElementsIterator implements Iterator<T> {
        private int size;

        public boolean hasNext() {
            return ArrayCollection.this.size() > size;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return ArrayCollection.this.array[size++];
        }
    }
}
