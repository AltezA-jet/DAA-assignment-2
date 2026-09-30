package com.example.daa.structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyLinkedListTest {

    @Test
    void addAndGetWorkCorrectly() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void addAtIndexWorksCorrectly() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);
        list.add(30);

        list.add(1, 20);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void addAtBeginningAndEndWorksCorrectly() {
        MyLinkedList list = new MyLinkedList();

        list.add(20);
        list.add(30);

        list.add(0, 10);
        list.add(3, 40);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
        assertEquals(40, list.get(3));
    }

    @Test
    void removeWorksCorrectly() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);
        list.add(20);
        list.add(30);

        int removed = list.remove(1);

        assertEquals(20, removed);
        assertEquals(10, list.get(0));
        assertEquals(30, list.get(1));
    }

    @Test
    void containsWorksCorrectly() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);
        list.add(20);
        list.add(20);

        assertTrue(list.contains(10));
        assertTrue(list.contains(20));
        assertFalse(list.contains(99));
    }

    @Test
    void duplicateValuesWorkCorrectly() {
        MyLinkedList list = new MyLinkedList();

        list.add(5);
        list.add(5);
        list.add(5);

        assertTrue(list.contains(5));
        assertEquals(5, list.get(0));
        assertEquals(5, list.get(1));
        assertEquals(5, list.get(2));
    }

    @Test
    void invalidGetIndexThrowsException() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    void invalidAddIndexThrowsException() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);

        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 20));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(2, 20));
    }

    @Test
    void invalidRemoveIndexThrowsException() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
    }

    @Test
    void removeFirstAndLastWorksCorrectly() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.remove(0));
        assertEquals(30, list.remove(1));

        assertEquals(20, list.get(0));
    }

    @Test
    void addToEmptyListWorksCorrectly() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);

        assertEquals(10, list.get(0));
        assertTrue(list.contains(10));
    }
}