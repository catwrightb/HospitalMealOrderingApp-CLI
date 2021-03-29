import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AppMain {
    private static Scanner scan;
    private static final ArrayList<Nurse> validNurses = new ArrayList<>();
    private static final ArrayList<KitchenStaff> validKitchenStaff= new ArrayList<>();
    //private static ArrayList<Patient> validPatient= new ArrayList<>();


    public static void main(String[] args) throws IOException {
        mainMenu();

    }

    public static void mainMenu() throws IOException, InputMismatchException {
        boolean loop = true;
        do {
            try {
                scan = new Scanner(System.in);
                String welcomeSign = "Welcome to the Salem Oregon Hospital Patient Meal System\n"
                        + "Please select an option from the below";
                System.out.println(welcomeSign);

                String[] options = {"Sign In", "Create Staff Account", "Quit"};


                for (int i = 0; i < options.length; i++) {
                    System.out.println((i + 1) + ". " + options[i]);
                }
                int userScan = scan.nextInt();

                switch (userScan) {
                    case 1:
                        login();
                        loop = false;
                        break;
                    case 2:
                        newUser();
                        loop = false;
                        break;
                    case 3:
                        System.out.println("\nGoodbye!");
                        loop = false;
                        break;
                    default:
                        throw new InputMismatchException();
                }
            }
            catch (InputMismatchException e){
                System.out.println("Sorry I didn't get that, try again.");
                scan.next();
            }

        }while (loop);

    }

    //creates a new staff user or Patient
    public static void newUser() throws InputMismatchException {
        boolean loopCycle = true;
        while (loopCycle){
            try {
                int numb = 1;
                System.out.print("\n");
                    for (Affiliation i : Affiliation.values()) {
                        if (numb <= 2) {
                            System.out.print(numb + ": ");
                            System.out.print(i.toString() + "\n");
                        } else if (numb == 3) {
                            System.out.println(numb + ": Back to Option Menu");
                        }
                        numb++;
                    }

                int affiliationChoice = scan.nextInt();
                if (affiliationChoice == 1 || affiliationChoice == 2) {
                    PrintWriter out = new PrintWriter(new FileWriter("userDetails.txt", true));

                    System.out.println("\nPlease enter users first name?");
                    String firstName = scan.next();

                    System.out.println("Please enter users last name?");
                    String lastName = scan.next();

                    System.out.println("Please enter a password for your new account?");
                    String password = scan.next();

                    switch (affiliationChoice) {
                        case 1:
                            Nurse newNurse = new Nurse(firstName, lastName);
                            newNurse.makeUserName(firstName, lastName);
                            newNurse.setPassword(password);
                            System.out.println("Your details are: username( " + newNurse.getUserName() +
                                    " ) and password( " + newNurse.getPassword() + " ).");
                            validNurses.add(newNurse);
                            out.println(newNurse.toString());
                            loopCycle =false;
                            break;
                        case 2:
                            KitchenStaff newKitchenStaff = new KitchenStaff(firstName, lastName);
                            newKitchenStaff.makeUserName(firstName, lastName);
                            newKitchenStaff.setPassword(password);
                            System.out.println("Your details are: username( " + newKitchenStaff.getUserName() +
                                    " ) and password( " + newKitchenStaff.getPassword() + " ).\n");
                            validKitchenStaff.add(newKitchenStaff);
                            out.println(newKitchenStaff.toString());
                            loopCycle = false;
                            break;
                        default:

                    }
                    out.close();

                    mainMenu();
                }
                else if (affiliationChoice == 3) {
                    loopCycle = false;
                    System.out.print("");
                }
                else {
                    System.out.println("Sorry I didn't get that, try again.");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
                System.out.println("Something went wrong, try again.");
                scan.next();
            }
            catch (InputMismatchException e) {
                System.out.println("Sorry I didn't get that, try again.");
                scan.next();
            }
        }
    }

    //login to main App
    public static void login() throws IOException {
        char usetype = 0;
        boolean loop = true;

        do {

                File file = new File("userDetails.txt");
                Scanner input = new Scanner(file);
                scan = new Scanner(System.in);

                System.out.println("\n Login Portal");
                System.out.println(":------------:");
                System.out.println("Username: ");
                String inpUser = scan.next();
                boolean found = false;
                while(input.hasNext() && !found) {
                    if (input.next().equals(inpUser)){

                        usetype = inpUser.charAt(0);

                        System.out.println("Password: ");
                        String inpPass = scan.next();
                        if (input.next().equals(inpPass)){
                            System.out.println("Login Successful!");
                            found = true;
                            loop = false;
                            break;
                        }
                    }
                }
                if(!found) {
                    validateInput();
                }


        } while (loop);

        loggedInMenu(usetype);

    }

    public static void validateInput() throws IOException {
        boolean userInput = true;
        try{
            do {
                System.out.println("\nLogin failed, try Again.");
                System.out.println("1. Try again?");
                System.out.println("2. Return to Main Menu Options?");
                String option = scan.next();
                switch (option){
                    case "1":
                        userInput = false;
                        break;
                    case "2":
                        System.out.println("");
                        mainMenu();
                        break;
                    default:
                        System.out.println("Sorry I didn't catch that.");
                }
            }while (userInput);
        }
        catch (IOException e){
            System.out.println("ERROR");
        }


    }

    public static void loggedInMenu(char userType) throws IOException {
        try{
            switch (userType){
                case 'N':
                    Nurse nurseUser = new Nurse();
                    nurseUser.userLoggedInMenu();
                    break;
                case 'K':
                    KitchenStaff kitchenUser = new KitchenStaff();
                    kitchenUser.userLoggedInMenu();
                    break;
                default:
                    throw new IOException();
            }

            mainMenu();

        }
        catch (IOException e){
            System.out.println("This is embarrassing something went wrong");
            mainMenu();

        }

    }

}
