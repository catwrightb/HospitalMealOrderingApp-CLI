public class Food {
    String itemName;
    int calories;
    double cost;
    MealTime mealTime;

    public Food() {
    }


    public Food(String itemName, int calories, double cost) {
        this.itemName = itemName;
        this.calories = calories;
        this.cost = cost;
    }

    public Food(String itemName, MealTime mealTime) {
        this.itemName = itemName;
        this.mealTime = mealTime;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public MealTime getMealTime() {
        return mealTime;
    }

    public void setMealTime(MealTime mealTime) {
        this.mealTime = mealTime;
    }

    @Override
    public String toString() {
        return  getItemName() +", $"+ getCost()+", "+ getCalories() +" calories";
    }
}



