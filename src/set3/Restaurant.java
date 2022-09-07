package set3;

import java.util.List;
import java.util.Objects;

public class Restaurant {

    private String name;
    private int review;
    private List<String> meals;
    private int deliveryDiscount;

    public Restaurant(String name, int review, List<String> meals, int deliveryDiscount) {
        this.name = name;
        this.review = review;
        this.meals = meals;
        this.deliveryDiscount = deliveryDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return review == that.review && deliveryDiscount == that.deliveryDiscount && Objects.equals(name, that.name) && Objects.equals(meals, that.meals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, review, meals, deliveryDiscount);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", review=" + review +
                ", meals=" + meals +
                ", deliveryDiscount=" + deliveryDiscount +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public List<String> getMeals() {
        return meals;
    }

    public void setMeals(List<String> meals) {
        this.meals = meals;
    }

    public int getDeliveryDiscount() {
        return deliveryDiscount;
    }

    public void setDeliveryDiscount(int deliveryDiscount) {
        this.deliveryDiscount = deliveryDiscount;
    }
}
