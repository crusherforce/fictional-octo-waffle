package com.java.concurrency;

public class BlockingQueueWithSemaphore<T> {
    T[] data;
    int size = 0;
    int capacity;
    int head = 0;
    int tail = 0;
    CountingSemaphore semLock = new CountingSemaphore(1, 1);
    CountingSemaphore semProducer;
    CountingSemaphore semConsumer;

    @SuppressWarnings("unchecked")
    public BlockingQueueWithSemaphore(int capacity) {
        data = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.semProducer = new CountingSemaphore(capacity, capacity);
        this.semConsumer = new CountingSemaphore(capacity);
    }

    public T dequeue() throws InterruptedException {
        T item = null;

        semConsumer.acquire();
        semLock.acquire();

        if (head == capacity)
            head = 0;

        item = data[head];
        data[head] = null;
        head++;
        size--;

        semLock.release();
        semConsumer.release();

        return item;
    }

    public void enqueue(T item) throws InterruptedException {
        semProducer.acquire();
        semLock.acquire();

        if (tail == capacity)
            tail = 0;

        data[tail] = item;
        size++;
        tail++;

        semLock.release();
        semConsumer.release();
    }
}
