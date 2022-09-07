package set1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

//        ex1();
//      ex2();
//        ex3();
//       ex4();
//        ex6();
//        ex7();
//        ex8();
//        ex9();
//        ex10();
//        ex11();
//        ex12();

    }

    //Generate a list of Students with a random name Student + random number from 0 to 10 and a random grade from 1 to 10
    private static List<Student> studentsGenerator() {

        List<Student> studentList = Stream.generate(() -> {
                    Random random = new Random();
                    int grade = random.nextInt(10) + 1;
                    String name = "Student" + random.nextInt(10);

                    return new Student(name, grade);
                }).limit(20)
                .collect(Collectors.toList());

        return studentList;
    }

    //Divide students who passed and students who failed (true = passed students, false = failed)
    private static void ex12() {

        Map<Boolean, List<Student>> passedAndFailedStudents = studentsGenerator().stream()
                .collect(Collectors.partitioningBy((s) -> s.getGrade() > 5));
        for(Map.Entry<Boolean, List<Student>> entry : passedAndFailedStudents.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    //Group students by grade
    //Group grade by number of grades(ex for grade 5 = 4 grades)
    private static void ex11() {
        List<Student> students = new ArrayList<>(studentsGenerator());
        Map<Integer, List<Student>> collect = students.stream()
                .collect(Collectors.groupingBy(student -> student.getGrade()));
        for(Map.Entry<Integer, List<Student>> entry : collect.entrySet()){
            System.out.println("Grade " +entry.getKey() + ": " +entry.getValue());
        }
        System.out.println("---------------------");

        Map<Integer, Long> collect1 = students.stream()
                .collect(Collectors.groupingBy((student -> student.getGrade()), Collectors.counting()));
        for(Map.Entry<Integer, Long> entry : collect1.entrySet()){
            System.out.println("Grade " +entry.getKey() + ": " +entry.getValue());
        }

    }

    //Colllect only unique students
    private static void ex10() {
        HashSet<Student> uniqueStudents = studentsGenerator().stream()
                .collect(Collectors.toCollection(() -> new HashSet<>()));
        long numberOfUniqueStudents = uniqueStudents.stream()
                .count();
        System.out.println("The total of students is 20 and the number of unique students is: " +numberOfUniqueStudents);
        uniqueStudents.forEach(System.out::println);

    }

    //Find the student with the highest grade
    private static void ex9() {

        Optional<Student> max = studentsGenerator().stream()
                .max((s1, s2) -> {
                    int rez = s1.getGrade() - s2.getGrade();
                    if (rez == 0) {
                        rez = s2.getName().compareTo(s1.getName());
                    }
                    return rez;
                });
        max.ifPresent(System.out::println);
    }

    //Find the student with the lowest grade
    private static void ex8() {

        Optional<Student> min = studentsGenerator().stream()
                .min((s1, s2) -> {
                    int rez = s1.getGrade() - s2.getGrade();
                    if (rez == 0) {
                        rez = s2.getName().compareTo(s1.getName());
                    }
                    return rez;
                });
        min.ifPresent(System.out::println);
    }

    //Check if:
    //-all students passed
    //-at least one student passed
    //-all students failed
    private static void ex7() {
        boolean anymatch = studentsGenerator().stream()
                .anyMatch(s -> s.getGrade() > 5);
        boolean allmatch = studentsGenerator().stream()
                .allMatch(s->s.getGrade()>5);
        boolean nonematch = studentsGenerator().stream()
                .noneMatch(s->s.getGrade()>5);

        System.out.println("Every student has passed: " +allmatch);
        System.out.println("At least one student has passed: " +anymatch );
        System.out.println("Every student has failed: " +nonematch);
    }

    //Count how many students have passed(grade>5)
    private static void ex6() {
        long notaDeTrecere = studentsGenerator().stream()
                .filter((s) -> s.getGrade() > 5)
                .count();
        System.out.println("The number of students who passed is: " +notaDeTrecere);
    }

    //Given a list of names, check:
    //-if there are only names starting with letter "A"
    //-if there is at least one name starting with the letter "A"
    //-if there are names starting with any letter other than letter "A"
    private static void ex4() {

        List<String> persoane = List.of("Andrei", "Alex", "Mihai", "Vlad", "ristotel");

        boolean allMatch = persoane.stream()
                .allMatch(s -> s.substring(0, 1).equals("A"));
        System.out.println("All match: " +allMatch);

        boolean anyMatch = persoane.stream()
                .anyMatch(s -> s.substring(0, 1).equals("A"));
        System.out.println("Any match: "+anyMatch);

        boolean noneMatch = persoane.stream()
                .noneMatch(s -> s.substring(0, 1).equals("A"));
        System.out.println("None match: "+noneMatch);
    }

    //Count the names which starts with the letter "A"
    private static void ex3() {
        List<String> persoane = List.of("Andrei", "Alex", "Mihai", "Vlad", "ristotel");
        long a = persoane.stream()
                .filter(s -> s.substring(0, 1).equals("A"))
                .count();
        System.out.println(a);
    }

    //Count even numbers
    private static void ex2() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        long count = integers.stream()
                .filter(i -> i % 2 == 0)
                .count();

        System.out.println(count);
    }

    //Create a stream with the first 10 powers of 3
    private static void ex1() {
        Stream.iterate(1, integer -> integer * 3).limit(10)
                .forEach(System.out::println);
    }
}
