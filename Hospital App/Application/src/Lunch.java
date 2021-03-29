import java.util.ArrayList;

public class Lunch extends Food {
    private ArrayList<Lunch> lunchOptions;

    public Lunch(String itemName, int calories, double cost) {
        super(itemName, calories, cost);
        setMealTime(MealTime.LUNCH);
    }

    public Lunch(){

    }

    public void setLunchOptions(ArrayList<Lunch> lunchOptions) {
        this.lunchOptions = lunchOptions;
    }

    public ArrayList<Lunch> getLunchOptions(){
        lunchOptions= new ArrayList<>();

        lunchOptions.add(new Burger());
        lunchOptions.add(new Soup());
        lunchOptions.add(new Pizza());

        return lunchOptions;
    }

    public void printOptions(){
        Lunch lunchMenu = new Lunch();
        ArrayList<Lunch> lmenu = lunchMenu.getLunchOptions();

        System.out.println("\n"+MealTime.LUNCH.toString()+ " Menu");
        System.out.println("--------------------------");

        for (int i = 0; i < 3; i++) {
            System.out.println((i+1)+": "+lmenu.get(i));
         }

    }

}
