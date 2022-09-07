package set2;

import java.util.Objects;

public class Ingrediente {

    private String name;
    private int weight;

    public Ingrediente(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }


    @Override
    public String toString() {
        return "Ingrediente{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingrediente that = (Ingrediente) o;
        return weight == that.weight && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
