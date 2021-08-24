package ModernJavaInAction;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.Map;

public class ParallelismExamples {
    public static void execute() {
        List<Person> persons = createRoster(10);

        printRoster(persons);

        System.out.println(
            "Average age of male members in parallel: " 
            + getAvgAgeOfMales(persons));

        concurrentReduction(persons);
    }

    public static List<Person> createRoster(int length) {
        List<Person> persons = new ArrayList<>();
        Random randomGen = new Random();

        for (int i=0; i<length; i++) {
            String name = createName(randomGen);
            Person.Sex sex = randomGen.nextInt()%2 == 0
                ? Person.Sex.Male 
                : Person.Sex.Female;
            int age = randomGen.nextInt(50);
            persons.add(new Person(name, sex, age));
        }

        // sort roster by name length
        persons.sort(
            (Person p1, Person p2) ->
                Integer.compare(
                    p1.getName().length(),
                    p2.getName().length())
        );

        return persons;
    }
    private static String createName(Random randomGen) {
        StringBuilder nameBuilder = new StringBuilder();
        int nameLength = 6 + randomGen.nextInt(10);
        for (int i=0; i<nameLength; i++) {
            int randInt = randomGen.nextInt(26);
            //System.out.println(randInt);
            char ch = (char) ('a'+randInt);
            //System.out.println(ch);
            nameBuilder.append(ch);
        }
        return nameBuilder.toString();
    }
    public static void printRoster(List<Person> persons) {
        System.out.println("--------------------------");
        for(Person p : persons) {
            System.out.println(p);
        }
        System.out.println("--------------------------");
    }
    public static double getAvgAgeOfMales(List<Person> persons) {
        return persons
            .parallelStream()
            .filter(p -> p.getSex() == Person.Sex.Male) 
            .mapToInt(Person::getAge)
            .average()
            .getAsDouble();
    }
    public static void concurrentReduction(List<Person> persons) {
        ConcurrentMap<Person.Sex, List<Person>> byGenderParallel =
            persons
            .parallelStream()
            .collect(Collectors.groupingByConcurrent(Person::getSex));

        List<Map.Entry<Person.Sex, List<Person>>> byGenderList =
            new ArrayList<>(byGenderParallel.entrySet());

        System.out.println("--- Group members by sex ---");

        byGenderList
            .stream()
            .forEach(e -> {
                System.out.println("--- Sex: " + e.getKey());
                e.getValue()
                    .stream()
                    .map(Person::getName)
                    .forEach(f -> System.out.println(f));
            });
    }
}