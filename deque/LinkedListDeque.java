package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private Node<T> sentinel;
    private int size;

    public class Node<T> {
        public T item;
        public Node<T> prev;
        public Node<T> next;

        // constructor to help build sentinel node
        public Node() {
        }

        public Node(T i, Node<T> p, Node<T> n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node<T>();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node<T> prevFirst = sentinel.next;
        sentinel.next = new Node<T>(item, sentinel, prevFirst);
        prevFirst.prev = sentinel.next;
        size++;
    }

    public void addLast(T item) {
        Node<T> prevLast = sentinel.prev;
        sentinel.prev = new Node<T>(item, prevLast, sentinel);
        prevLast.next = sentinel.prev;
        size++;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        String output = "";
        Node<T> pointer = sentinel;

        for (int i = 0; i < size; i++) {
            pointer = pointer.next;

            output = output + pointer.item.toString() + " ";
        }

        System.out.println(output);
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        Node<T> oldFirst = sentinel.next;
        Node<T> newFirst = oldFirst.next;
        newFirst.prev = sentinel;
        sentinel.next = newFirst;
        size--;

        return oldFirst.item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        Node<T> oldLast = sentinel.prev;
        Node<T> newLast = oldLast.prev;
        newLast.next = sentinel;
        sentinel.prev = newLast;
        size--;

        return oldLast.item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node<T> pointer = sentinel;

        for (int i = 0; i <= index; i++) {
            pointer = pointer.next;
        }

        return pointer.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node<T> node = getRecursiveHelper(0, index, sentinel.next);
        return node.item;
    }

    public Node<T> getRecursiveHelper(int i, int n, Node<T> currentNode) {
        if (i == n) {
            return currentNode;
        }

        return getRecursiveHelper(i + 1, n, currentNode.next);
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int currentPos;
        private Node<T> currentNode;

        public LinkedListDequeIterator() {
            currentPos = 0;
            currentNode = sentinel.next;
        }

        public boolean hasNext() {
            return currentPos < size;
        }

        public T next() {
            T returnItem = currentNode.item;
            currentPos += 1;
            currentNode = currentNode.next;
            return returnItem;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Deque) {
            Deque otherDeque = (Deque) o;

            if (this.size != otherDeque.size()) {
                return false;
            }

            Node<T> currentNode = sentinel.next;
            for (int i = 0; i < size; i++) {
                if (!get(i).equals(otherDeque.get(i))) {
                    return false;
                }
                currentNode = currentNode.next;
            }
            return true;
        }
        return false;
    }
}
