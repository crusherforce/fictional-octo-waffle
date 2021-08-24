package com.java.concurrency;

public class BlockingQueue<T> {
    T[] data;
    int capacity;

    int size = 0;
    int head = 0;
    int tail = 0;

    @SuppressWarnings("unchecked")
    public BlockingQueue(int capacity) {
        data = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        // wait for the queue to have space
        while (size == capacity) {
            wait();
        }

        // reset tail to the beginning if the tail is already
        // at the end of the backing array
        if (tail == capacity) {
            tail = 0;
        }

        // place the item in the array
        data[tail] = item;
        size++;
        tail++;

        // notify other threads waiting on a change in value of the size
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        T item = null;

        while (size == 0) {
            wait();
        }

        if (head == capacity) {
            head = 0;
        }

        // store the reference of the object being dequeued
        // and overwrite the null
        item = data[head];
        data[head] = null;
        head++;
        size--;

        notifyAll();

        return item;
    }
}
