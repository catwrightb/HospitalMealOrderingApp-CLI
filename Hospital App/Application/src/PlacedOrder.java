import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class PlacedOrder implements Comparable<PlacedOrder>{
    private Food foodDetails;
    private Patient patientDetails;
    private String nextDay;

    public PlacedOrder(Food foodDetails, Patient patientDetails, String nextDay) {
        this.foodDetails = foodDetails;
        this.patientDetails = patientDetails;
        this.nextDay = nextDay;
    }

    public PlacedOrder(Patient patientDetails) {
        this.patientDetails = patientDetails;
    }

    public PlacedOrder() {
    }

    public Food getFoodDetails() {
        return foodDetails;
    }

    public void setFoodDetails(Food foodDetails) {
        this.foodDetails = foodDetails;
    }

    public Patient getPatientDetails() {
        return patientDetails;
    }

    public void setPatientDetails(Patient patientDetails) {
        this.patientDetails = patientDetails;
    }

    public String getNextDay() {
        setNextDay();

        return nextDay;
    }

    //date is set one day in advance from current day
    public void setNextDay() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy");
        LocalDateTime currentDate =  LocalDateTime.now();
        LocalDateTime tomorrowDay = currentDate.plusDays(1);
        String nextDay = dtf.format(tomorrowDay);

        this.nextDay = nextDay;
    }

    public void getFoodChoice(int mealOrderChoice, int menuChoice, PlacedOrder newOrder) throws IOException {
        switch(menuChoice){
            case 1: //Breakfast Object
                if (mealOrderChoice ==1){
                    Food breakfastOrder = new Pancakes();
                    newOrder.setFoodDetails(breakfastOrder);
                }
                else if(mealOrderChoice==2){
                    Food breakfastOrder = new Benedict();
                    newOrder.setFoodDetails(breakfastOrder);
                }
                else if(mealOrderChoice==3){
                    Food breakfastOrder = new Oatmeal();
                    newOrder.setFoodDetails(breakfastOrder);
                }
                break;

            case 2: //Lunch Object
                if (mealOrderChoice ==1){
                    Food lunchOrder = new Burger();
                    newOrder.setFoodDetails(lunchOrder);

                }
                else if(mealOrderChoice==2){
                    Food lunchOrder = new Soup();
                    newOrder.setFoodDetails(lunchOrder);
                }
                else if(mealOrderChoice==3){
                    Food lunchOrder = new Pizza();
                    newOrder.setFoodDetails(lunchOrder);
                }
                break;

            case 3: //Dinner Object
                if (mealOrderChoice ==1){
                    Food dinnerOrder = new Lasagna();
                    newOrder.setFoodDetails(dinnerOrder);
                }
                else if(mealOrderChoice==2){
                    Food dinnerOrder = new Curry();
                    newOrder.setFoodDetails(dinnerOrder);
                }
                else if(mealOrderChoice==3){
                    Food dinnerOrder = new Chicken();
                    newOrder.setFoodDetails(dinnerOrder);
                }
                break;

        }

        addToTextSheet(newOrder);

    }


    public void addToTextSheet(PlacedOrder order) throws IOException {
        try {
            PrintWriter out = new PrintWriter(new FileWriter("foodOrderDetails.txt", true));
            out.println(order);
            out.close();

            Patient p = order.patientDetails;
            String lastName = order.patientDetails.getLastName();
            int roomNumber = order.patientDetails.getRoomNumber();
            int addCalories = order.getFoodDetails().calories;
            double addCost = order.getFoodDetails().cost;

            PrintWriter out2 = new PrintWriter(new FileWriter("patientCal&Cost.txt", true));
            String outString = lastName +", "+ roomNumber+", "+ addCalories+", "+ addCost+ ", "+ order.getNextDay();
            out2.println(outString);
            out2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        return getFoodDetails().mealTime +", "+ getPatientDetails().getFirstName() +", "+ getPatientDetails().getLastName()+ ", "+ getPatientDetails().getRoomNumber()+
                ", " + getFoodDetails().itemName +", "+ this.nextDay;
    }

    //Orders ArrayList by ascending first name
    @Override
    public int compareTo(PlacedOrder o) {
        return this.getFoodDetails().mealTime.compareTo(((PlacedOrder) o).getFoodDetails().mealTime);
    }


}
