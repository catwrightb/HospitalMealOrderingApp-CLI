import java.util.ArrayList;

public class Breakfast extends Food {
    private ArrayList<Breakfast> breakfastsOptions;

    //constructor
    public Breakfast(String itemName, int calories, double cost) {
        super(itemName, calories, cost);
        setMealTime(MealTime.BREAKFAST);
    }

    public Breakfast() {

    }

    public void setBreakfastsOptions(ArrayList<Breakfast> breakfastsOptions) {
        this.breakfastsOptions = breakfastsOptions;
    }

    public ArrayList<Breakfast> getBreakfastsOptions(){
        breakfastsOptions = new ArrayList<>();

        breakfastsOptions.add(new Pancakes());
        breakfastsOptions.add(new Benedict());
        breakfastsOptions.add(new Oatmeal());

        return breakfastsOptions;
    }


    public void printOptions(){
        Breakfast breakfastMenu = new Breakfast();
        ArrayList<Breakfast> bmenu = breakfastMenu.getBreakfastsOptions();

        System.out.println("\n"+MealTime.BREAKFAST.toString()+ " Menu");
        System.out.println("---------------------");

        for (int i = 0; i < 3; i++) {
            System.out.println((i+1)+": "+bmenu.get(i));
        }
    }


}
