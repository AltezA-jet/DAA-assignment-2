package com.example.daa.structures;

import com.example.daa.metrics.Metrics;

public class DynamicArray {

    private int[] data;
    private int size;
    private final Metrics metrics;

    public DynamicArray() {
        data = new int[10];
        size = 0;
        metrics = new Metrics();
    }

    public void add(int x) {
        if (size == data.length) {
            resize();
        }

        data[size] = x;
        metrics.incrementMoves();
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
            metrics.incrementSteps();
            metrics.incrementMoves();
        }

        data[index] = x;
        metrics.incrementMoves();
        size++;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        int removed = data[index];
        metrics.incrementSteps();

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
            metrics.incrementSteps();
            metrics.incrementMoves();
        }

        size--;
        return removed;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        metrics.incrementSteps();
        return data[index];
    }

    public boolean contains(int x) {
        for (int i = 0; i < size; i++) {
            metrics.incrementSteps();
            metrics.incrementComparisons();

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
            metrics.incrementSteps();
            metrics.incrementMoves();
        }

        data = newData;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void resetMetrics() {
        metrics.reset();
    }
}