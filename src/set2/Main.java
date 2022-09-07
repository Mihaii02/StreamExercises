package set2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

//        ex3();
//        ex4();
//        ex5();
//        ex6();
//        ex7();
//        ex8();
//        ex9();
//        ex10();
//        ex11();

    }
    // Recipe generator with: random name, random kcal between 100 and 1100 and at leas 1 ingredient

    private static List<Recipe> generateRecipe(int nr){

        return   Stream.generate(() -> {
                    Random random = new Random();
                    String recipeName = "Reteta" + random.nextInt(nr);
                    int kcal = random.nextInt(1000) + 100;

                    return new Recipe(recipeName, kcal, generateIngredient(random.nextInt(nr)+1));})
                .limit(nr)
                .collect(Collectors.toList());
    }

    //Ingredients generator with: random name(ingredient+ random number from 0 to size) and random weight 100 to 300
    private static List<Ingrediente> generateIngredient(int size) {
        List<Ingrediente> ingredients = Stream.generate(() -> {
            Random random = new Random();
           return new Ingrediente("ingredient" + random.nextInt(size), random.nextInt(200) + 100);
    })
                .limit(size)
                .collect(Collectors.toList());
        return ingredients;
    }

    //Sort recipes by kCal(over and under 500 kCal)
    private static void ex11() {
        List<Recipe> retete = generateRecipe(10);
        Map<Boolean, List<Recipe>> collect = retete.stream()
                .collect(Collectors.partitioningBy(recipe -> recipe.getkCal() < 500));
        for(Map.Entry<Boolean, List<Recipe>> entry : collect.entrySet()){
            System.out.println(entry.getKey() + ": " +entry.getValue());
        }
    }

    //Sort recipes by number of ingredients used
    private static void ex10() {
        List<Recipe> retete = generateRecipe(10);
        Map<Integer, List<Recipe>> collect = retete.stream()
                .collect(Collectors.groupingBy(recipe -> recipe.getIngredients().size()));
        for(Map.Entry<Integer, List<Recipe>> entry : collect.entrySet()){
            System.out.println("Number of ingredients " + entry.getKey() + " for: " + entry.getValue());
        }
    }

    //Print alphabetically each recipe's name
    private static void ex9() {
        List<Recipe> retete = generateRecipe(10);
        retete.stream()
                .map(recipe -> recipe.getName())
                .sorted(String::compareTo)
                .reduce((r1, r2) -> r1 + ", " + r2)
                .ifPresent(System.out::println);
    }

    //Print every ingredient from every recipe
    private static void ex8() {
        List<Recipe> retete = generateRecipe(10);
//        retete.forEach(s-> System.out.println(s.ingredients));

        retete.stream()
                .flatMap(el->el.getIngredients().stream())
            .forEach(System.out::println);
    }

    //Total kCal for all recipes
    private static void ex7() {
        List<Recipe> retete = generateRecipe(1);
        int sum = retete.stream()
                .mapToInt(s -> s.getkCal())
                .sum();
        retete.forEach(System.out::println);
        System.out.println("Total kCal for all recipes is: "+sum);
    }

    //Sort recipes by name. If there are recipes with the same name, sort after kCal
    //Using comparator vs stream
    private static void ex6() {
      /*class ComparatorIng implements Comparator<Recipe> {

          @Override
          public int compare(Recipe o1, Recipe o2) {
              int rez = o1.getName().compareTo(o2.getName());
              if (rez == 0) {
                  return o1.getkCal() - o2.getkCal();
              } else {
                  return rez;
              }
          }
      }
        List<Recipe> retete = generateRecipe(10);
        TreeSet<Recipe> collect = retete.stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(new ComparatorIng())));
        collect.forEach(System.out::println);*/

        List<Recipe> retete = generateRecipe(10);
        retete.stream()
                .sorted((s1, s2) -> {
                    int rez = s1.getName().compareTo(s2.getName());
                    if (rez == 0) {
                        return rez = s1.getkCal() - s2.getkCal();
                    } else {
                        return rez;
                    }
                }).forEach(System.out::println);

    }

    //Chek if there is at least one recipe that have 1000 kCal
    //Remove every recipe over 500 kCal
    private static void ex5() {
        List<Recipe> retete = generateRecipe(10);
        boolean match = retete.stream()
                .anyMatch(r -> r.getkCal() > 1000);
        System.out.println(match);

       retete.removeIf(s->s.getkCal() >500);

        retete.forEach(System.out::println);
    }

    //Count recipes which have less than 450 kCal
    private static void ex4() {
        List<Recipe> retete = generateRecipe(10);
        long count = retete.stream()
                .filter(r -> r.getkCal() < 450)
                .count();
        System.out.println("Avem "+count+" retete sub 450kCal");
    }

    //Filter every recipe that have more than 200 kCal
    private static void ex3() {
        List<Recipe> retete = generateRecipe(10);
        List<Recipe> recipesMin200 = retete.stream()
                .filter(r -> r.getkCal() >= 200)
                .collect(Collectors.toList());
        recipesMin200.forEach(System.out::println);
    }
}
