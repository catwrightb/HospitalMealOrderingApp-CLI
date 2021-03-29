import java.util.ArrayList;

public class Dinner extends Food {
    private ArrayList<Dinner> dinnerOptions;

    public Dinner(String itemName, int calories, double cost) {
        super(itemName, calories, cost);
        setMealTime(MealTime.DINNER);
    }

    public Dinner(){

    }

    public void setDinnerOptions(ArrayList<Dinner> dinnerOptions) {
        this.dinnerOptions = dinnerOptions;
    }


    public ArrayList<Dinner> getDinnerOptions(){
        dinnerOptions= new ArrayList<>();

        dinnerOptions.add(new Lasagna());
        dinnerOptions.add(new Curry());
        dinnerOptions.add(new Chicken());

        return dinnerOptions;
    }

    public void printOptions(){
        Dinner dinnerMenu = new Dinner();
        ArrayList<Dinner> dmenu = dinnerMenu.getDinnerOptions();

        System.out.println("\n"+MealTime.DINNER.toString()+ " Menu");
        System.out.println("--------------------------");

        for (int i = 0; i < 3; i++) {
            System.out.println((i+1)+": "+dmenu.get(i));
        }
    }

}
