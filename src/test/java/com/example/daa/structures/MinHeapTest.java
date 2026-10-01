package com.example.daa.structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MinHeapTest {

    @Test
    void insertAndPeekMinWorkCorrectly() {
        MinHeap heap = new MinHeap();

        heap.insert(30);
        heap.insert(10);
        heap.insert(20);

        assertEquals(10, heap.peekMin());
    }

    @Test
    void extractMinReturnsElementsInSortedOrder() {
        MinHeap heap = new MinHeap();

        heap.insert(40);
        heap.insert(10);
        heap.insert(30);
        heap.insert(20);

        assertEquals(10, heap.extractMin());
        assertEquals(20, heap.extractMin());
        assertEquals(30, heap.extractMin());
        assertEquals(40, heap.extractMin());
    }

    @Test
    void extractMinWorksWithOneElement() {
        MinHeap heap = new MinHeap();

        heap.insert(15);

        assertEquals(15, heap.extractMin());
        assertThrows(IllegalStateException.class, heap::peekMin);
    }

    @Test
    void duplicateValuesWorkCorrectly() {
        MinHeap heap = new MinHeap();

        heap.insert(5);
        heap.insert(5);
        heap.insert(2);
        heap.insert(2);

        assertEquals(2, heap.extractMin());
        assertEquals(2, heap.extractMin());
        assertEquals(5, heap.extractMin());
        assertEquals(5, heap.extractMin());
    }

    @Test
    void negativeValuesWorkCorrectly() {
        MinHeap heap = new MinHeap();

        heap.insert(0);
        heap.insert(-10);
        heap.insert(5);
        heap.insert(-3);

        assertEquals(-10, heap.extractMin());
        assertEquals(-3, heap.extractMin());
        assertEquals(0, heap.extractMin());
        assertEquals(5, heap.extractMin());
    }

    @Test
    void emptyHeapThrowsExceptions() {
        MinHeap heap = new MinHeap();

        assertThrows(IllegalStateException.class, heap::peekMin);
        assertThrows(IllegalStateException.class, heap::extractMin);
    }

    @Test
    void resizingWorksCorrectly() {
        MinHeap heap = new MinHeap();

        for (int i = 20; i >= 1; i--) {
            heap.insert(i);
        }

        for (int i = 1; i <= 20; i++) {
            assertEquals(i, heap.extractMin());
        }
    }

    @Test
    void peekMinDoesNotRemoveElement() {
        MinHeap heap = new MinHeap();

        heap.insert(7);
        heap.insert(3);
        heap.insert(9);

        assertEquals(3, heap.peekMin());
        assertEquals(3, heap.peekMin());
        assertEquals(3, heap.extractMin());
    }
}