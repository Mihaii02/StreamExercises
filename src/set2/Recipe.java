package set2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Recipe {

    private String name;
    private int kCal;
    private List<Ingrediente> ingredients = new ArrayList<>();

    public Recipe(String name, int kCal,List<Ingrediente> ingredients) {
        this.name = name;
        this.kCal = kCal;
        this.ingredients = ingredients;
    }

    public List<Ingrediente> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingrediente> ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getkCal() {
        return kCal;
    }

    public void setkCal(int kCal) {
        this.kCal = kCal;
    }


    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", kCal=" + kCal +
                ", ingredients=" + ingredients +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return kCal == recipe.kCal && Objects.equals(name, recipe.name) && Objects.equals(ingredients, recipe.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, kCal, ingredients);
    }
}
