package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;
    private int initialSize = 8;

    public ArrayDeque() {
        items = (T[]) new Object[initialSize];
        nextFirst = initialSize - 1;
        nextLast = 0;
        size = 0;
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst = fixIndex(nextFirst - 1);
        size++;
    }

    public void addLast(T item) {
        items[nextLast] = item;
        nextLast = fixIndex(nextLast + 1);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        String output = "";

        int j = fixIndex(nextFirst + 1);
        for (int i = 0; i < size; i++) {
            if (j == items.length) {
                j = 0;
            }

            output = output + items[j].toString() + " ";
            j++;
        }

        System.out.println(output);
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        int oldFirstIndex = fixIndex(nextFirst + 1);
        T oldFirst = items[oldFirstIndex];
        items[oldFirstIndex] = null;
        nextFirst = oldFirstIndex;
        size--;

        return oldFirst;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        int oldLastIndex = fixIndex(nextLast - 1);
        T oldLast = items[oldLastIndex];
        items[oldLastIndex] = null;
        nextLast = oldLastIndex;
        size--;

        return oldLast;
    }

    public T get(int index){
        if (index < 0 || index >= size) {
            return null;
        }

        int adjustedIndex = fixIndex(nextFirst + 1 + index);
        return items[adjustedIndex];
    }

    public int fixIndex(int index) {
        if (index < 0) {
            return index + items.length;
        } else if (index >= items.length) {
            return index - items.length;
        } else {
            return index;
        }
    }
}
