package ModernJavaInAction;

import java.util.List;
import java.util.Arrays;

public class ParallelStreams {
    public enum Flags {
        printThreads,
        getRuntimeInfo
    }

    public static void execute() {
        Flags flag = Flags.getRuntimeInfo;

        switch(flag) {
            case printThreads:
                printThreads(false);
                printThreads(true);
                break;
            case getRuntimeInfo:
                getRuntimeInfo();
        }
    }

    public static void getRuntimeInfo() {
        System.out.println("Available Processors = " 
            + Runtime.getRuntime().availableProcessors());
        System.out.println("Free Memory = "
            + Runtime.getRuntime().freeMemory());
        System.out.println("Max Memory = "
            + Runtime.getRuntime().maxMemory());
        System.out.println("Total Memory = "
            + Runtime.getRuntime().totalMemory());
    }

    public static void printThreads(boolean parallel) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,1,2,3,4,5,6,7);
        if (parallel) {
            list
                .parallelStream()
                .map(ParallelStreams::apply)
                .forEach(i->{});
        } else {
            list
                .stream()
                .map(ParallelStreams::apply)
                .forEach(i->{});
        }
    }

    public static int apply(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException iEx) {            
        }
        System.out.println(Thread.currentThread());
        return i;
    }
}