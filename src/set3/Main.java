package set3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

//        ex3();
//        ex4();
//        ex5();
//        ex6();
//        ex7(generateRestaurants(100), "Restaurant10");
//        ex8();
//        ex9();
    }

    //Generate int size number of meals with the name Meal + a random number from 0 to 10
    private static List<String> mealGenerator(int size){
        List<String> meals = Stream.generate(() -> {
                    Random random = new Random();
                    return new String("Meal" + random.nextInt(10));

                }).limit(size)
                .collect(Collectors.toList());
        return meals;
    }

    //Generate int nr of restaurants consisting of : -name Restaurant + random number from 1 to 10
    //-random review from 1 to 5 stars
    //-at least 2 meals
    //-random discount between 0 and 50
    private static List<Restaurant> generateRestaurants(int nr){

        List<Restaurant> restaurants = Stream.generate(() -> {
                    Random random = new Random();
                    String name = "Restaurant" + (random.nextInt(10) + 1);
                    int review = random.nextInt(5) + 1;
                    List<String> meals = mealGenerator((random.nextInt(8) + 2));
                    int discount = random.nextInt(50);

                    return new Restaurant(name, review, meals, discount);
                }).limit(nr)
                .collect(Collectors.toList());
        return restaurants;
    }

    //Group restaurants by review(1 star, 2 stars ...)
    private static void ex9() {
        Map<Integer, List<Restaurant>> integerListMap = generateRestaurants(100).stream()
                .collect(Collectors.groupingBy(restaurant -> restaurant.getReview()));
        for(Map.Entry<Integer, List<Restaurant>> entry : integerListMap.entrySet()){
            System.out.println("Review: " +entry.getKey() + " for "+entry.getValue());
        }
    }

    //Count how many restaurants have at least 3 stars, at least 30% discount and at least 5 meals
    private static void ex8() {
        long count = generateRestaurants(100).stream()
                .filter(restaurant -> restaurant.getReview() >= 3)
                .filter(restaurant -> restaurant.getDeliveryDiscount() >= 30)
                .filter(restaurant -> restaurant.getMeals().size() >= 5)
                .count();
        System.out.println(count);
    }

    //Given a list of restaurants and a restaurant name
    //find the restaurant matching the given name and return an Optional of Restaurants
    private static Optional<Restaurant> ex7(List<Restaurant> restaurants, String restaurantName) {

        Optional<Restaurant> matchingRestaurantName = restaurants.stream()
                .filter(restaurant -> restaurant.getName().equals(restaurantName))
                .findFirst();
        matchingRestaurantName.ifPresent(System.out::println);

        return matchingRestaurantName;
    }

    //Filter restaurants with a discount higher than 30 and containing Meal6
    private static void ex6() {
        generateRestaurants(10).stream()
                .filter(restaurant -> restaurant.getDeliveryDiscount()>=30)
                .filter(restaurant -> restaurant.getMeals().contains("Meal6"))
                .forEach(System.out::println);
    }

    //Filter only restaurants that contain Meal7
    private static void ex5() {
        generateRestaurants(100).stream()
                .filter(el->el.getMeals().contains("Meal7"))
                .forEach(System.out::println);

    }

    //Filter restaurants with a discount higher than 20
    private static void ex4() {
        generateRestaurants(100).stream()
                .filter((restaurant)-> restaurant.getDeliveryDiscount()>=20)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    //Filter restaurants with at least 4 stars
    private static void ex3() {
        generateRestaurants(10).stream()
                .filter((restaurant) ->restaurant.getReview()>=4)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
