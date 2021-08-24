package simplerestclient;

public class SimpleRESTClient {
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i=0; i<threads.length; i++) {
            threads[i] = new Thread(new Callable());
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
