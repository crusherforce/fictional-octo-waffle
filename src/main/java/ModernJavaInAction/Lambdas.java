package ModernJavaInAction;

import java.util.List;
import java.util.function.Function;
import java.util.Arrays;
import java.util.ArrayList;

public class Lambdas {
    public <A,B> List<B> map(List<A> list, Function<A,B> f) {
        List<B> result = new ArrayList<>();
        for (A t : list) {
            result.add(f.apply(t));
        }
        return result;
    } 
    public void execute() {
        List<Integer> result = 
            map(
                Arrays.asList("Helen","Katrina","Malaika"), 
                (String s) -> s.length()
            );
        result
            .stream()
            .forEach(a -> System.out.println(a));
    }
}