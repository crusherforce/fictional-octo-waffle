package com.java.concurrency;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//         testMyRunnable();
//         testBlockingQueue();
//         testBlockingQueueWithMutex();
        testBlockingQueueWithSemaphores();
    }

    private static void testBlockingQueueWithSemaphores() throws InterruptedException {
        final BlockingQueueWithSemaphore<Integer> q = new BlockingQueueWithSemaphore<Integer>(5);

        Thread t1 = new Thread(new Runnable() {

            public void run() {
                try {
                    for (int i = 0; i < 20; i++) {
                        q.enqueue(Integer.valueOf(i));
                        System.out.println("enqueued " + i);
                    }
                } catch (InterruptedException ie) {
                    System.out.println("Exception in thread 1");
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("Thread 2 dequeued: " + q.dequeue());
                    }
                } catch (InterruptedException ie) {
                    System.out.println("Exception in thread 1");
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {

            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("Thread 3 dequeued: " + q.dequeue());
                    }
                } catch (InterruptedException ie) {
                    System.out.println("Exception in thread 1");
                }
            }
        });

        t1.start();
        Thread.sleep(4000);
        t2.start();
        t2.join();

        t3.start();
        t1.join();
        t3.join();
    }

    private static void testBlockingQueueWithMutex() throws InterruptedException {
        final BlockingQueueWithMutex<Integer> q = new BlockingQueueWithMutex<>(5);
        Thread producer1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int i = 1;
                    while (true) {
                        q.enqueue(i);
                        System.out.println("Producer Thread 1 enqueued : " + i);
                        i++;
                    }
                } catch (InterruptedException iex) {
                    System.out.println("Exception inside Producer Thread 1 " + iex.getMessage());
                }
            }
        });

        Thread producer2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int i = 5000;
                    while (true) {
                        q.enqueue(i);
                        System.out.println("Producer Thread 2 enqueued : " + i);
                        i++;
                    }
                } catch (InterruptedException iex) {
                    System.out.println("Exception inside Producer Thread 2 " + iex.getMessage());
                }
            }
        });

        Thread producer3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int i = 100000;
                    while (true) {
                        q.enqueue(i);
                        System.out.println("Producer Thread 3 enqueued : " + i);
                        i++;
                    }
                } catch (InterruptedException iex) {
                    System.out.println("Exception inside Producer Thread 3 " + iex.getMessage());
                }
            }
        });

        Thread consumer1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("Consumer thread 1 dequeued : " + q.dequeue());
                    }
                } catch (InterruptedException iex) {
                    System.out.println("Exception inside Consumer Thread 1 " + iex.getMessage());
                }
            }
        });

        Thread consumer2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("Consumer thread 2 dequeued : " + q.dequeue());
                    }
                } catch (InterruptedException iex) {
                    System.out.println("Exception inside Consumer Thread 2 " + iex.getMessage());
                }
            }
        });

        Thread consumer3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("Consumer thread 3 dequeued : " + q.dequeue());
                    }
                } catch (InterruptedException iex) {
                    System.out.println("Exception inside Consumer Thread 3 " + iex.getMessage());
                }
            }
        });

        producer1.setDaemon(true);
        producer2.setDaemon(true);
        producer3.setDaemon(true);
        consumer1.setDaemon(true);
        consumer2.setDaemon(true);
        consumer3.setDaemon(true);

        producer1.start();
        producer2.start();
        producer3.start();

        consumer1.start();
        consumer2.start();
        consumer3.start();

        Thread.sleep(1000);
    }


    private static void testBlockingQueue() throws InterruptedException {
        final BlockingQueue<Integer> q = new BlockingQueue<>(5);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=0; i<50; i++) {
                        q.enqueue(Integer.valueOf(i));
                        System.out.println("Thread 1 enqueued " + i);
                    }
                } catch (InterruptedException iex) {
                    System.out.println("exception in t1");
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=0; i<25; i++) {
                        System.out.println("Thread 2 dequeued " + q.dequeue());
                    }
                } catch (InterruptedException iex) {
                    System.out.println("exception in t2");
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=0; i<25; i++) {
                        System.out.println("Thread 3 dequeued " + q.dequeue());
                    }
                } catch (InterruptedException iex) {
                    System.out.println("exception in t3");
                }
            }
        });

        t1.start();
        Thread.sleep(4000);

        t2.start();
        t2.join();

        t3.start();
        t1.join();
        t3.join();
    }

    private static void testMyRunnable() {
        MyRunnable[] myRunnable = new MyRunnable[6];
        for (int i=0; i<myRunnable.length; i++) {
            myRunnable[i] = new MyRunnable(i);
            myRunnable[i].t.start();
        }
        try {
            for (int i=0; i<5; i++) {
                System.out.println("Main thread :" + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException iex) {
            System.out.println("Main thread interrupted");
        }
        System.out.println("Exiting Main thread");
    }
}
