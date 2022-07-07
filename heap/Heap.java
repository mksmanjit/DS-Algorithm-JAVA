package heap;

import java.util.Arrays;

public class Heap {
    private int[] elements;
    private int maxSize;
    private int size;

    public Heap(int[] elements, int maxSize, int size) {
        this.elements = elements;
        this.maxSize = maxSize;
        this.size = size;
    }

    public int[] getElements() {
        return elements;
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public int getSize() {
        return this.size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public void increaseSize() {
        if(size == maxSize) {
            size = size + 1;
            maxSize = size;
            elements = Arrays.copyOf(elements, size);
        } else {
            size++;
        }
    }
}
