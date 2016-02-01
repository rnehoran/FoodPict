package food.eat.food;

/**
 * Created by Roy on 1/31/2016.
 */
public class Meal {
    private String name;
    private int calories;
    private int fat;
    private int carbs;
    private int protein;
    private int cholesterol;
    private double servings;

    public Meal(String name, int calories, int fat, int carbs, int protein, int cholesterol, double servings) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
        this.cholesterol = cholesterol;
        this.servings = servings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(int cholesterol) {
        this.cholesterol = cholesterol;
    }

    public double getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
}
