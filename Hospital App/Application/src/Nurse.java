import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Nurse extends StaffMembers implements StaffInterface{
    public Nurse(String firstName, String lastName, Affiliation affiliation) {
        super(firstName, lastName, Affiliation.NURSE);
    }

    public Nurse() {
        super();
    }

    public Nurse(String firstName, String lastName) {
        super(firstName, lastName, Affiliation.NURSE);
    }

    public void makeUserName(String firstName, String lastName){
        char affiliationLetter = 'N';
        String aLetter = Character.toString(affiliationLetter);

        char firstLetter = firstName.charAt(0);
        firstLetter = Character.toLowerCase(firstLetter);
        String fLetter = Character.toString(firstLetter);
        StringBuilder lastLetters = new StringBuilder();

        int lastNameLength = lastName.length();

        if(lastNameLength >= 3){
            for (int i = 0; i < 3; i++) {
                char letter = lastName.charAt(i);
                letter = Character.toLowerCase(letter);
                lastLetters.append(letter);
            }
        }
        //if last name is less then 3 letter, take first letter and random generate last two letters
        else{
            Random rnd = new Random();
            char c2 = (char) ('a' + rnd.nextInt(26));
            char c3 = (char) ('a' + rnd.nextInt(26));
            char letter = lastName.charAt(0);
            letter = Character.toLowerCase(letter);

            char[] letterArray = {letter , c2, c3};

            for (char c : letterArray) {
                lastLetters.append(c);
            }
        }

        Random rand = new Random();
        int randNumber = rand.nextInt(1000);
        String charLength = String.valueOf(randNumber);

        String createdUserName = aLetter+ fLetter + lastLetters + charLength;

        setUserName(createdUserName);

    }

    public void userLoggedInMenu() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);
        String[] options= {"Print Menu", "Order for Patient", "Print Patient Order", "Log Out"};
        boolean loop = true;

        while (loop){
            try{
                System.out.println("\n Nurse Portal Menu");
                System.out.println(":------------------:");

                for (int i = 0; i < options.length; i++) {
                    System.out.println((i+1)+". "+ options[i]);
                }
                int userScan = scan.nextInt();

                switch (userScan){
                    case 1:
                        printMenuOption(scan);
                        break;
                    case 2:
                        try {
                            placeOrderOption(scan);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        printPatientOrder(scan);
                        break;
                    case 4:
                        System.out.println("");
                        loop = false;
                        break;
                    default:
                        System.out.println("Sorry, that is not a option. Try Again.");

                }
            }
            catch (InputMismatchException e){
                System.out.println("Sorry, that is not a option. Try Again.");
                scan.next();
            }
        }
    }

    //prints out patients placed orders along with calorie and cost
    public void printPatientOrder(Scanner scan){
        System.out.println("\n *Print a Patient's Order*");

        Patient patient = new Patient();
        patient = patientSelectionMethod(scan);
        String lastName = patient.getLastName();

        //remove?
        if (patient.getLastName() == null) {

        }

        else {
            System.out.println("\n What time frame would you like to print?");
            System.out.println(":--------------------------------------:");
            System.out.println("1. Patients enter order history");
            System.out.println("2. Patients next day orders");
            int menuChoice = scan.nextInt();
            ArrayList<PlacedOrder> arrayList= new ArrayList<>();

            switch (menuChoice) {
                case 1:
                    try (BufferedReader in = new BufferedReader(new FileReader("foodOrderDetails.txt"))) {
                        String str;
                        System.out.println("\nOrders for patient " + patient.getFirstName() + " " + patient.getLastName() + " in Room " + patient.getRoomNumber() + " are :");

                        //prints string in ascending order (breakfast/lunch/dinner)
                        //change others to do this too
                        while ((str = in.readLine()) != null) {
                            if (str.contains(lastName)) {
                                //make arraylist which collects
                                //stringList.add(str);
                                String[] tokens = str.split(", ");
                                String mealType = tokens[0];
                                MealTime enumMealTime = MealTime.valueOf(mealType.toUpperCase());

                                String patientFirst = tokens[1];
                                String patientLast = tokens[2];


                                int roomNumber = Integer.parseInt(tokens[3]);
                                String mealName = tokens[4];
                                String day = tokens[5];

                                Food foodOrder = new Food(mealName, enumMealTime);
                                Patient patient1 = new Patient(patientFirst,patientLast,roomNumber);

                                arrayList.add(new PlacedOrder(foodOrder,patient1,day));

                            }
                        }

                        Collections.sort(arrayList);

                        for (PlacedOrder placedOrder : arrayList) {
                            System.out.println(placedOrder);
                        }

                        //calculate cost of patients orders but not calories
                        double total = patient.setTotalCost(patient, menuChoice);
                        if (total > 0.00) {
                            System.out.println("Patients total cost is: $" + total);
                        } else {
                            System.out.println("No orders found");
                        }

                    } catch (IOException e) {
                        System.out.println("File Read Error");
                    }
                    break;
                case 2:
                    try {
                        BufferedReader in = new BufferedReader(new FileReader("foodOrderDetails.txt"));
                        String str;
                        PlacedOrder test = new PlacedOrder();
                        String date = test.getNextDay();

                        System.out.println("\nOrders for patient " + patient.getFirstName() + " " + patient.getLastName() +
                                " in Room " + patient.getRoomNumber() + " are :");

                        while ((str = in.readLine()) != null) {
                            if (str.contains(lastName)) {
                                String[] tokens = str.split(", ");
                                String mealType = tokens[0];
                                MealTime enumMealTime = MealTime.valueOf(mealType.toUpperCase());

                                String patientFirst = tokens[1];
                                String patientLast = tokens[2];


                                int roomNumber = Integer.parseInt(tokens[3]);
                                String mealName = tokens[4];
                                String day = tokens[5];

                                Food foodOrder = new Food(mealName, enumMealTime);
                                Patient patient1 = new Patient(patientFirst,patientLast,roomNumber);

                                if (day.equals(date)){
                                    arrayList.add(new PlacedOrder(foodOrder,patient1,day));
                                }

                            }

                        }

                        Collections.sort(arrayList);

                        for (PlacedOrder placedOrder : arrayList) {
                            System.out.println(placedOrder);
                        }

                        //calculate cost and calories
                        double total = patient.setTotalCost(patient, menuChoice);
                        if (total > 0.00) {
                            System.out.println("Patients total cost is: $" + total);
                        } else {
                            System.out.println("No orders found");
                        }

                        int totalCal = patient.setTotalCalories(patient);
                        System.out.println("Patients total calorie count is: " + totalCal);

                    } catch (IOException e) {
                        System.out.println("File Read Error");
                    }
                    break;
            }
        }

    }


    public void placeOrderOption(Scanner scan) throws IOException {
        System.out.println("\n *Place a Meal Order for a Patient*");

        try {
            //Select patient
            Patient selectedPatient = new Patient();
            selectedPatient = patientSelectionMethod(scan);

            if (selectedPatient.getLastName() == null) {
                //exit method
            }

            if (selectedPatient.getLastName() != null) {
                PlacedOrder newOrder = new PlacedOrder();
                String date = "";
                newOrder.setNextDay();

                //instantiates a new PlacedOrder and populates patient selected details
                newOrder.setPatientDetails(selectedPatient);

                //select mealType
                System.out.println("\nFor patient " + selectedPatient.toString());
                System.out.println("Please select meal time");
                System.out.println(":----------------------:");
                int menuChoice = printMealTimeMenus();

                //select meal option

                switchMenu(menuChoice);
                System.out.println("4: Back");

                int mealOrderChoice=0;
                boolean loop = true;
                do {
                   try{
                       System.out.println("Please select which meal option Patient " +selectedPatient.getLastName()+
                               "would like:");

                       //---------
                       mealOrderChoice = scan.nextInt();

                       switch (mealOrderChoice){
                           case 1:
                               //fall through
                           case 2:
                               //fall through
                           case 3:
                               //fall through
                           case 4:
                               loop = false;
                               break;
                           default:
                               System.out.println("Sorry that is not a option, try again\n");
                               break;
                       }
                   }
                   catch (InputMismatchException e){
                       System.out.println("Sorry that is not a option, try again");
                       scan.next();
                   }
                   //---------------
                }while (loop);

                //instantiate PlacedOrder
                newOrder.getFoodChoice(mealOrderChoice, menuChoice, newOrder);
            }
        }
        catch (IOException e) {
                e.printStackTrace();
        }

    }


    //methods which allows user to select patient
    //returns a patient object
    public Patient patientSelectionMethod(Scanner scan) throws InputMismatchException{
        System.out.println(" Please select a patient");
        System.out.println(":----------------------:");
        Patient patient = new Patient();
        ArrayList<Patient> patientsList = patient.instantiatePatients();

        //prints patients by last name ascending
        Collections.sort(patientsList);

        int counter = 1;
        for (Patient i : patientsList) {
            System.out.println(counter + ": "+ i.toString());
            ++counter;
        }
        System.out.println((counter)+": Back");
        boolean loop =true;

        try {
            do {
                int nursePatientChoice = scan.nextInt();
                --nursePatientChoice;

                if (nursePatientChoice == counter) {
                    loop = false;

                }
                else if (nursePatientChoice <= counter) {
                    Patient selectedPatient = new Patient();
                    for (int i = 0; i < patientsList.size(); i++) {
                        if (nursePatientChoice == i) {
                            selectedPatient = patientsList.get(i);
                        }
                    }
                    return selectedPatient;
                }
                else {
                    System.out.println("Sorry, that is not a current Patient Option. Try Again from the above list.");
                }
            }while (loop);
        }
        catch (InputMismatchException e){
                System.out.println("Sorry, that is not a current Patient Option. Try Again from the above list.");
                scan.next();
            }
        return patient;
    }


    //triggered when the user selects the "print menu" option from main nurse menu
    public void printMenuOption(Scanner scan){
        System.out.println("\n Please select which menu you would like to view:");
        System.out.println(":-----------------------------------------------:");

        int menuChoice = printMealTimeMenus();
        switchMenu(menuChoice);

    }

    //switch which prints the selected mealType menu (Breakfast, Lunch, Dinner)
    public void switchMenu(int menuChoice){
        switch (menuChoice){
            case 1:
                Breakfast breakfastMenu = new Breakfast();
                breakfastMenu.printOptions();
                break;
            case  2:
                Lunch lunchMenu = new Lunch();
                lunchMenu.printOptions();
                break;
            case  3:
                Dinner dinnerMenu = new Dinner();
                dinnerMenu.printOptions();
                break;
            case 4:
                break;
            default:
                System.out.println("That is not a valid option, please choose again: ");
                break;
        }
    }

    //for loop that prints the mealTypes that are available to view and verifies user choice is a viable choice
    public int printMealTimeMenus()throws InputMismatchException{
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        int menuChoice = 0;

       while (loop){
            try {
                int counter = 1;

                for (MealTime i : MealTime.values()) {
                    System.out.println(counter + ": "+ i.toString());
                    ++counter;
                }
                System.out.println((counter)+": Back");

                //---------
                menuChoice = scan.nextInt();

                switch (menuChoice){
                    case 1:
                        //fall through
                    case 2:
                        //fall through
                    case 3:
                        //fall through
                    case 4:
                        loop = false;
                        break;
                    default:
                        System.out.println("Sorry that is not a option, try again\n");
                        break;
                }
            }
            catch (InputMismatchException e){
                System.out.println("Sorry that is not a option, try again");
                scan.next();
            }
            //------------
        }
        return menuChoice;
    }
}
