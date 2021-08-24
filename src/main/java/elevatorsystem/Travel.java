package elevatorsystem;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public record Travel(ConcurrentMap<Integer, Integer> map,
                     ConcurrentMap<Integer, Boolean> available, int from,
                     int to) implements Runnable {

    public void run() {
        try {
            int thread = -1, distance = Integer.MAX_VALUE;
            for (int i : available.keySet()) {
                if (available.get(i)) {
                    thread = i;
                    available.put(thread, false);
                    break;
                }
            }
            System.out.println("Elevator chosen : " + thread);
            // go to pick the passenger
            int start = map.get(thread);
            System.out.println(thread + " Going to pick passenger : from " + start + " to " + from);
            if (from > start) {
                TimeUnit.SECONDS.sleep(from - start);
            } else {
                TimeUnit.SECONDS.sleep(start - from);
            }

            // drop the passenger
            System.out.println(thread + " Going to drop passenger : from " + from + " to " + to);
            if (from > to) {
                TimeUnit.SECONDS.sleep(from - to);
            } else {
                TimeUnit.SECONDS.sleep(to - from);
            }

            System.out.println(thread + " idle at " + to);
            available.put(thread, true);
            map.put(thread, to);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}