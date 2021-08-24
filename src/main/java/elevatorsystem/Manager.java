package elevatorsystem;

import java.util.concurrent.*;

public class Manager {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentMap<Integer, Integer> map = new ConcurrentHashMap<>();
        ConcurrentMap<Integer, Boolean> available = new ConcurrentHashMap<>();
        int nThreads = 4;

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(nThreads);
        for (int i=0; i<nThreads; i++) {
            map.put(i, 0);
            available.put(i, true);
        }

        int[] from = new int[]{4,4,4,2,0};
        int[] to =   new int[]{0,0,0,0,1};

        for (int i = 0; i < from.length; i++)
        {
            Travel task = new Travel(map, available, from[i], to[i]);
            executor.execute(task);
        }

        System.out.println("Waiting for shutdown");
        int count = 1;
        while (executor.getActiveCount()>0) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(count++ + " seconds later, (active, completed) = " + executor.getActiveCount() + " , " + executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}
