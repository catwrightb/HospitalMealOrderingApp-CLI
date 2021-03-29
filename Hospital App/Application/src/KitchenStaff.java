import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class KitchenStaff extends StaffMembers implements StaffInterface{


    public KitchenStaff(String firstName, String lastName, Affiliation affiliation) {
        super(firstName, lastName, Affiliation.KITCHEN_STAFF);
    }

    public KitchenStaff(String firstName, String lastName) {
        super(firstName, lastName, Affiliation.KITCHEN_STAFF);
    }

    public KitchenStaff() {

    }

    public void makeUserName(String firstName, String lastName){
        String affiliationLetters = "KS";

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
        int min = 1000;
        int max = 2000;

        int randNumber = (int)(Math.random() * (max - min + 1) + min);
        String charLength = String.valueOf(randNumber);
        String createdUserName = affiliationLetters+ fLetter + lastLetters + charLength;

        setUserName(createdUserName);
    }


    public void userLoggedInMenu() throws IOException {
        Scanner scan = new Scanner(System.in);
        String[] options= {"Print Orders Tomorrows Orders", "Log Out"};
        boolean loop = true;

        while (loop){
            try{
                System.out.println("\n Kitchen Staff Portal Menu");
                System.out.println(":-------------------------:");

                for (int i = 0; i < options.length; i++) {
                    System.out.println((i+1)+". "+ options[i]);
                }
                int userScan = scan.nextInt();

                switch (userScan){
                    case 1:
                        printPatientOrder(scan);
                        break;
                    case 2:
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


    public void printPatientOrder(Scanner scan) throws IOException {
       String str;
       String date= "";
       PlacedOrder test = new PlacedOrder();
       date = test.getNextDay();

        System.out.println("\nAll patients orders for "+date+" include:");

       try {
           BufferedReader in = new BufferedReader(new FileReader("foodOrderDetails.txt"));
           ArrayList<PlacedOrder> arrayList= new ArrayList<>();
           while ((str = in.readLine()) != null) {
               if (str.contains(date)) {
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

       }
       catch (IOException e){
           System.out.println("error");

       }


    }


}
