package com.example.daa.structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DynamicArrayTest {

    @Test
    void addAndGetWorkCorrectly() {
        DynamicArray array = new DynamicArray();

        array.add(10);
        array.add(20);
        array.add(30);

        assertEquals(10, array.get(0));
        assertEquals(20, array.get(1));
        assertEquals(30, array.get(2));
    }

    @Test
    void addAtIndexWorksCorrectly() {
        DynamicArray array = new DynamicArray();

        array.add(10);
        array.add(30);

        array.add(1, 20);

        assertEquals(10, array.get(0));
        assertEquals(20, array.get(1));
        assertEquals(30, array.get(2));
    }

    @Test
    void addAtBeginningAndEndWorksCorrectly() {
        DynamicArray array = new DynamicArray();

        array.add(20);
        array.add(30);

        array.add(0, 10);
        array.add(3, 40);

        assertEquals(10, array.get(0));
        assertEquals(20, array.get(1));
        assertEquals(30, array.get(2));
        assertEquals(40, array.get(3));
    }

    @Test
    void removeWorksCorrectly() {
        DynamicArray array = new DynamicArray();

        array.add(10);
        array.add(20);
        array.add(30);

        int removed = array.remove(1);

        assertEquals(20, removed);
        assertEquals(10, array.get(0));
        assertEquals(30, array.get(1));
    }

    @Test
    void containsWorksCorrectly() {
        DynamicArray array = new DynamicArray();

        array.add(10);
        array.add(20);
        array.add(20);

        assertTrue(array.contains(10));
        assertTrue(array.contains(20));
        assertFalse(array.contains(99));
    }

    @Test
    void duplicateValuesWorkCorrectly() {
        DynamicArray array = new DynamicArray();

        array.add(5);
        array.add(5);
        array.add(5);

        assertTrue(array.contains(5));
        assertEquals(5, array.get(0));
        assertEquals(5, array.get(1));
        assertEquals(5, array.get(2));
    }

    @Test
    void invalidGetIndexThrowsException() {
        DynamicArray array = new DynamicArray();

        array.add(10);

        assertThrows(IndexOutOfBoundsException.class, () -> array.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(1));
    }

    @Test
    void invalidAddIndexThrowsException() {
        DynamicArray array = new DynamicArray();

        array.add(10);

        assertThrows(IndexOutOfBoundsException.class, () -> array.add(-1, 20));
        assertThrows(IndexOutOfBoundsException.class, () -> array.add(2, 20));
    }

    @Test
    void invalidRemoveIndexThrowsException() {
        DynamicArray array = new DynamicArray();

        array.add(10);

        assertThrows(IndexOutOfBoundsException.class, () -> array.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> array.remove(1));
    }

    @Test
    void removeFirstAndLastWorksCorrectly() {
        DynamicArray array = new DynamicArray();

        array.add(10);
        array.add(20);
        array.add(30);

        assertEquals(10, array.remove(0));
        assertEquals(30, array.remove(1));

        assertEquals(20, array.get(0));
    }

    @Test
    void dynamicResizingWorksCorrectly() {
        DynamicArray array = new DynamicArray();

        for (int i = 0; i < 25; i++) {
            array.add(i);
        }

        for (int i = 0; i < 25; i++) {
            assertEquals(i, array.get(i));
        }
    }
}