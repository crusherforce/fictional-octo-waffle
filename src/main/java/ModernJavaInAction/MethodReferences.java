package ModernJavaInAction;

import java.util.function.BiFunction;

public class MethodReferences {
    public static void execute() {
        BiFunction<Integer, Apple.Color, Apple> biFunc = Apple::new;
        Apple apple = biFunc.apply(160, Apple.Color.RED);
        System.out.println(apple);
    }
}