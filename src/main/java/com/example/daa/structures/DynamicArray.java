package com.example.daa.structures;

public class DynamicArray {

    private int[] data;
    private int size;

    public DynamicArray() {
        data = new int[10];
        size = 0;
    }

    public void add(int x) {
        if (size == data.length) {
            resize();
        }

        data[size] = x;
        size++;
    }

    public void add(int index, int x) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (size == data.length) {
            resize();
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = x;
        size++;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        int removed = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;

        return removed;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return data[index];
    }

    public boolean contains(int x) {
        for (int i = 0; i < size; i++) {
            if (data[i] == x) {
                return true;
            }
        }

        return false;
    }

    private void resize() {
        int[] newData = new int[data.length * 2];

        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }
}