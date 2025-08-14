
import java.util.NoSuchElementException;

public class Deque<T> {
    
    private T[] array;
    private int head;
    private int tail;

    public Deque() {
        this.head = 0;
        this.tail = 1;
        this.array = (T[]) new Object[2];
    }

    public boolean isEmpty() {
        return this.tail - this.head == 1;
    }

    public int size() {
        return this.tail - this.head - 1;
    }

    private void resize(int size) {
        T[] newArray = (T[]) new Object[size];
        int currentSize = this.size();
        int emptyCells = (size - currentSize) / 2;
        int startIndex = this.head + 1;

        if (emptyCells < 0) {
            emptyCells *= -1;
        }

        for (int i = emptyCells; i < emptyCells + currentSize; i++) {
            newArray[i] = this.array[startIndex];
            startIndex++; 
        }

        this.head = emptyCells - 1;
        this.tail = emptyCells + currentSize;
        this.array = newArray;
    }

    public void addFirst(T input) {
        this.array[this.head] = input;
        this.head--;

        if (this.head < 0) {
            this.resize((this.size() * 2) + 1);
        }
    }

    public void addLast(T input) {
        this.array[this.tail] = input;
        this.tail++;

        if (this.array.length == this.tail) {
            this.resize((this.size() * 2) + 1);
        }
    }

    public T removeFirst() {

        if (this.isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        T output = this.array[this.head + 1];
        this.array[this.head + 1] = null;
        this.head++;

        if (this.size() > 1 && this.array.length >= this.size() * 4) {
            this.resize((this.size() * 2) + 1);
        }

        return output;
    }

    public T removeLast() {

        if (this.isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        T output = this.array[this.tail - 1];
        this.array[this.tail - 1] = null;
        this.tail--;

        if (this.size() > 1 && this.array.length >= this.size() * 4) {
            this.resize((this.size() * 2) + 1);
        }

        return output;
    }

    public void printDeque() {
        System.out.print("Deque = [");
        for (int i = 0; i < array.length; i++) {
            if (i == head && i == tail) {
                System.out.print("HT");
            } else if (i == head) {
                System.out.print("H");
            } else if (i == tail) {
                System.out.print("T");
            } else if (array[i] == null) {
                System.out.print("null");
            } else {
                System.out.print(array[i]);
            }

            if (i != array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
