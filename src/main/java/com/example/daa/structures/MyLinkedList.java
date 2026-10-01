package com.example.daa.structures;

import com.example.daa.metrics.Metrics;

public class MyLinkedList {

    private Node head;
    private int size;
    private final Metrics metrics;

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public MyLinkedList() {
        head = null;
        size = 0;
        metrics = new Metrics();
    }

    public void add(int x) {
        Node newNode = new Node(x);

        if (head == null) {
            head = newNode;
            metrics.incrementMoves();
        } else {
            Node current = head;

            while (current.next != null) {
                current = current.next;
                metrics.incrementSteps();
            }

            current.next = newNode;
            metrics.incrementMoves();
        }

        size++;
    }

    public void add(int index, int x) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Node newNode = new Node(x);

        if (index == 0) {
            newNode.next = head;
            head = newNode;
            metrics.incrementMoves();
            metrics.incrementMoves();
            size++;
            return;
        }

        Node current = head;

        for (int i = 0; i < index - 1; i++) {
            current = current.next;
            metrics.incrementSteps();
        }

        newNode.next = current.next;
        metrics.incrementMoves();

        current.next = newNode;
        metrics.incrementMoves();

        size++;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            int removed = head.data;
            head = head.next;
            metrics.incrementMoves();
            size--;
            return removed;
        }

        Node current = head;

        for (int i = 0; i < index - 1; i++) {
            current = current.next;
            metrics.incrementSteps();
        }

        int removed = current.next.data;
        current.next = current.next.next;
        metrics.incrementMoves();

        size--;
        return removed;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
            metrics.incrementSteps();
        }

        return current.data;
    }

    public boolean contains(int x) {
        Node current = head;

        while (current != null) {
            metrics.incrementComparisons();

            if (current.data == x) {
                return true;
            }

            current = current.next;
            if (current != null) {
                metrics.incrementSteps();
            }
        }

        return false;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void resetMetrics() {
        metrics.reset();
    }
}