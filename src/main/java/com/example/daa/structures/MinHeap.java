package com.example.daa.structures;

import com.example.daa.metrics.Metrics;

public class MinHeap {

    private int[] heap;
    private int size;
    private final Metrics metrics;

    public MinHeap() {
        heap = new int[10];
        size = 0;
        metrics = new Metrics();
    }

    public void insert(int x) {
        if (size == heap.length) {
            resize();
        }

        heap[size] = x;
        metrics.incrementMoves();

        size++;

        int index = size - 1;

        while (index > 0) {
            int parent = (index - 1) / 2;

            metrics.incrementSteps();
            metrics.incrementSteps();
            metrics.incrementComparisons();

            if (heap[index] >= heap[parent]) {
                break;
            }

            swap(index, parent);
            index = parent;
        }
    }

    public int peekMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        metrics.incrementSteps();
        return heap[0];
    }

    public int extractMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int min = heap[0];
        metrics.incrementSteps();

        heap[0] = heap[size - 1];
        metrics.incrementSteps();
        metrics.incrementMoves();

        size--;

        int index = 0;

        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size) {
                metrics.incrementSteps();
                metrics.incrementComparisons();

                if (heap[left] < heap[smallest]) {
                    smallest = left;
                }
            }

            if (right < size) {
                metrics.incrementSteps();
                metrics.incrementComparisons();

                if (heap[right] < heap[smallest]) {
                    smallest = right;
                }
            }

            if (smallest == index) {
                break;
            }

            swap(index, smallest);
            index = smallest;
        }

        return min;
    }

    private void swap(int first, int second) {
        int temp = heap[first];
        heap[first] = heap[second];
        heap[second] = temp;

        metrics.incrementMoves();
        metrics.incrementMoves();
    }

    private void resize() {
        int[] newHeap = new int[heap.length * 2];

        for (int i = 0; i < size; i++) {
            newHeap[i] = heap[i];

            metrics.incrementSteps();
            metrics.incrementMoves();
        }

        heap = newHeap;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void resetMetrics() {
        metrics.reset();
    }
}