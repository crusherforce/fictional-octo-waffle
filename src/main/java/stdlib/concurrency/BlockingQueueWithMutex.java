package com.java.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueWithMutex<T> {
    T[] data;
    int capacity;
    Lock lock = new ReentrantLock();

    int size = 0;
    int head = 0;
    int tail = 0;

    @SuppressWarnings("unchecked")
    public BlockingQueueWithMutex(int capacity) {
        data = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        lock.lock();
        while (size == capacity) {
            lock.unlock();
            lock.lock();
        }

        if (tail == capacity) {
            tail = 0;
        }

        data[tail] = item;
        size++;
        tail++;
        lock.unlock();
    }

    public synchronized T dequeue() throws InterruptedException {
        T item = null;

        lock.lock();
        while (size == 0) {
            lock.unlock();
            lock.lock();
        }

        if (head == capacity) {
            head = 0;
        }

        item = data[head];
        data[head] = null;
        head++;
        size--;

        lock.unlock();
        return item;
    }
}
