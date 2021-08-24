package ModernJavaInAction;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;

public class SupplierExamples {
    public enum Flags {
        intSupplier,
        longSupplier
    }

    public static void execute() {
        Flags flag = Flags.intSupplier;

        switch(flag) {
            case intSupplier:
                intSupplier();
                break;
            case longSupplier:
                longSupplier();
                break;
        }
    }

    public static void intSupplier() {
        IntStream
            .generate(
                new IntSupplier() {
                    private int number = 0;
                    public int getAsInt() {
                        return number++;
                }
            })
            .limit(10)
            .forEach(i -> System.out.print(i + " "));
    }

    public static void longSupplier() {
        LongStream
            .iterate(
                2,
                new LongUnaryOperator() {
                    public long applyAsLong(long operand) {
                        return operand*2;
                    }
                })
            .limit(32)
            .forEach(i -> System.out.print(i + " "));
    }
}