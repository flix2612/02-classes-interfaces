package ohm.softa.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable<Object> {

    private Element head;

    @Override
    public Iterator<Object> iterator() {
        return new ListIterator();
    }

    private static class Element {
        private Object item;
        private Element next;
    }

    private class ListIterator implements Iterator<Object> {
        Element current = head;
        boolean headWasIterated = false;

        @Override
        public boolean hasNext() {
            return current != null && current.next != null;
        }

        @Override
        public Object next() {
            if (headWasIterated) {
                current = current.next;
            } else {
                headWasIterated = true;
            }
            return current.item;
        }
    }

    public void add(Object item) {
        Element newElement = new Element();
        newElement.item = item;
        newElement.next = head;
        head = newElement;
    }

    public int size() {
        int count = 0;
        Element current = head;
        while(current != null) {
            count++;
            current = current.next;
        }
//        for (Object object : this) {
//            count++;
//        }
        return count;
    }

    public SimpleList filter(SimpleFilter filter) {
        SimpleList list = new SimpleListImpl();
        Element current = head;

        while (current != null) {
            if (filter.include(current.item)) {
                list.add(current.item);
            }
            current = current.next;
        }

        return list;
    }
}
