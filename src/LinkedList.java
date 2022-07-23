import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {

    private Item<T> firstInList = null;

    private Item<T> lastInList = null;

    private int size;

    @Override
    public final int size() {
        return this.size;
    }

    @Override
    public final boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public final boolean contains(final Object o) {
        // BEGIN (write your solution here)
        return indexOf(o) != -1;
        // END
    }

    @Override
    public final Iterator<T> iterator() {
        return new ElementsIterator(0);
    }

    @Override
    public final Object[] toArray() {
        // BEGIN (write your solution here)
        T[] newArray = (T[]) new Object[size];
        int index = 0;
        for (Item<T> n = firstInList; n != null; n = n.nextItem) {
            newArray[index] = n.element;
            index++;
        }
        return newArray;
        // END
    }

    @Override
    public final <T1> T1[] toArray(T1[] a) {
        T1[] newArray = (T1[]) new Object[size];
        int index = 0;
        // BEGIN (write your solution here)
        if (a.length < this.size()) {
            for (Item<T> n = firstInList; n != null; n = n.nextItem) {
                newArray[index] = (T1) n.element;
                index++;
            }
            a = (T1[]) Arrays.copyOf(newArray, newArray.length, a.getClass());
        }
        if (a.length > this.size()) {
            for (Item<T> n = firstInList; n != null; n = n.nextItem) {
                a[index] = (T1) n.element;
                index++;
            }
            a[size] = null;
        }
        return a;
        // END
    }

    @Override
    public final boolean add(final T newElement) {
        // BEGIN (write your solution here)
        final Item<T> lastNode = lastInList;
        final Item<T> newNode = new Item<>(newElement, lastNode, null);
        lastInList = newNode;
        if (lastNode == null) {
            firstInList = newNode;
        } else {
            lastNode.nextItem = newNode;
        }
        size++;
        return true;
        // END
    }

    @Override
    public final void add(final int index, final T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final boolean remove(final Object o) {
        // BEGIN (write your solution here)
        if (o == null) {
            for (Item<T> n = firstInList; n != null; n = n.nextItem) {
                if (n.element == null) {
                    unlink(n);
                    return true;
                }
            }
        } else {
            for (Item<T> n = firstInList; n != null; n = n.nextItem) {
                if (o.equals(n.element)) {
                    unlink(n);
                    return true;
                }
            }
        }
        return false;
        // END
    }

    T unlink(Item<T> item) {
        final T element = item.element;
        final Item<T> nextLink = item.nextItem;
        final Item<T> prevLink = item.prevItem;

        if (prevLink == null) {
            firstInList = nextLink;
        } else {
            prevLink.nextItem = nextLink;
            item.prevItem = null;
        }


        if (nextLink == null) {
            lastInList = prevLink;
        } else {
            nextLink.prevItem = prevLink;
            item.nextItem = null;
        }

        item.element = null;
        size--;
        return element;
    }

    @Override
    public final T remove(final int index) throws IndexOutOfBoundsException {
        // BEGIN (write your solution here)
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException();
        }
        Item<T> n = firstInList;
        for (int i = 0; i < index; i++) {
            n = n.nextItem;
        }
        return unlink(n);
        // END
    }

    private void remove(final Item<T> current) {
        // BEGIN (write your solution here)
        unlink(current);
        // END
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
    public final boolean addAll(final int index, final Collection elements) {
        throw new UnsupportedOperationException();
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
        this.removeIf(item -> !c.contains(item));
        return true;
    }

    @Override
    public final void clear() {
        // BEGIN (write your solution here)
        firstInList = null;
        lastInList = null;
        size = 0;
        // END
    }

    @Override
    public final List<T> subList(final int start, final int end) {
        return null;
    }

    @Override
    public final ListIterator<T> listIterator() {
        return new ElementsIterator(0);
    }

    @Override
    public final ListIterator<T> listIterator(final int index) {
        return new ElementsIterator(index);
    }

    @Override
    public final int lastIndexOf(final Object target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final int indexOf(final Object o) {
        // BEGIN (write your solution here)
        int index = 0;
        if (o == null) {
            for (Item<T> n = firstInList; n != null; n = n.nextItem) {
                if (n.element == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Item<T> n = firstInList; n != null; n = n.nextItem) {
                if (o.equals(n.element)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
        // END
    }

    @Override
    public final T set(final int index, final T element) {
        // BEGIN (write your solution here)
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Item<T> n = firstInList;
        for (int i = 0; i < index; i++) {
            n = n.nextItem;
        }
        return n.element;
        // END
    }

    @Override
    public final T get(final int index) {
        // BEGIN (write your solution here)
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) toArray()[index];
        // END
    }

    private Item<T> getItemByIndex(final int index) {
        // BEGIN (write your solution here) An auxiliary method for searching for node by index.
        Item<T> n = firstInList;
        for (int i = 0; i < index; i++) {
            n = n.nextItem;
        }
        return n;
        // END
    }

    private class ElementsIterator implements ListIterator<T> {

        private Item<T> currentItemInIterator;

        private Item<T> lastReturnedItemFromIterator;

        private int index;

        ElementsIterator(final int index) {
            // BEGIN (write your solution here)
            currentItemInIterator = (index == size) ? null : getItemByIndex(index);
            this.index = index;
            // END
        }

        @Override
        public boolean hasNext() {
            // BEGIN (write your solution here)
            return index < size;
            // END
        }

        @Override
        public T next() {
            // BEGIN (write your solution here)
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturnedItemFromIterator = currentItemInIterator;
            currentItemInIterator = currentItemInIterator.nextItem;
            index++;
            return lastReturnedItemFromIterator.element;

            // END
        }

        @Override
        public boolean hasPrevious() {
            // BEGIN (write your solution here)
            return index > 0;
            // END
        }

        //CHECKSTYLE: stop InnerAssignment check
        @Override
        public T previous() {
            // BEGIN (write your solution here)
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (currentItemInIterator == null) {
                currentItemInIterator = lastInList;
                lastReturnedItemFromIterator = currentItemInIterator;
            } else {
                currentItemInIterator = currentItemInIterator.getPrevItem();
                lastReturnedItemFromIterator = currentItemInIterator;
            }
            index--;
            return lastReturnedItemFromIterator.element;
            // END
        }
        //CHECKSTYLE: resume InnerAssignment check

        @Override
        public void add(final T element) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(final T element) {
            // BEGIN (write your solution here)
            if (lastReturnedItemFromIterator == null) {
                throw new IllegalStateException();
            }
            lastReturnedItemFromIterator.element = element;
            // END
        }

        @Override
        public int previousIndex() {
            // BEGIN (write your solution here)
            return index - 1;
            // END
        }

        @Override
        public int nextIndex() {
            // BEGIN (write your solution here)
            return index;
            // END
        }

        @Override
        public void remove() {
            // BEGIN (write your solution here)
            if (lastReturnedItemFromIterator == null) {
                throw new IllegalStateException();
            }
            Item<T> lastNode = lastReturnedItemFromIterator.nextItem;
            unlink(lastReturnedItemFromIterator);
            if (currentItemInIterator == lastReturnedItemFromIterator) {
                currentItemInIterator = lastNode;
            } else {
                index--;
            }
            lastReturnedItemFromIterator = null;
            // END
        }
    }

    private static class Item<T> {

        private T element;

        private Item<T> nextItem;

        private Item<T> prevItem;

        Item(final T element, final Item<T> prevItem, final Item<T> nextItem) {
            this.element = element;
            this.nextItem = nextItem;
            this.prevItem = prevItem;
        }

        public Item<T> getNextItem() {
            return nextItem;
        }

        public Item<T> getPrevItem() {
            return prevItem;
        }
    }
}

